package com.fdmy.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fdmy.controller.vo.StockParamVO;
import com.fdmy.dao.IItemPlanDao;
import com.fdmy.dao.IStockDao;
import com.fdmy.model.ItemPlan;
import com.fdmy.model.Stock;

/*
 * 库存相关操作控制器
 */
@Controller("stockController")
@RequestMapping("/stock")
public class StockController {
	private IStockDao stockDao;
	private IItemPlanDao itemPlanDao;

	public IStockDao getStockDao() {
		return stockDao;
	}

	@Resource
	public void setStockDao(IStockDao stockDao) {
		this.stockDao = stockDao;
	}

	public IItemPlanDao getItemPlanDao() {
		return itemPlanDao;
	}

	@Resource
	public void setItemPlanDao(IItemPlanDao itemPlanDao) {
		this.itemPlanDao = itemPlanDao;
	}

	public StockController() {
		System.out.println("a new StockController");
	}

	@RequestMapping("/index")
	public String index() {
		return "/stock/stockindex";
	}

	@RequestMapping(value = "/checkstock", method = RequestMethod.GET)
	public String checkStock(StockParamVO vo, Model model) {
		List<Stock> resultList = new ArrayList<Stock>(); // 定义最后的查询结果对象
		boolean checkAllItem = vo.getItemCode() == null || vo.getItemCode().equals("");
		// 如果itemCode没有传值，则查询所有itemcode的库存信息
		if (checkAllItem) {
			// =========================多次查询数据库的算法====start===============
			/*
			 * // 先查询所有itemcode的入库信息 vo.setType(1); List<Account> inList =
			 * dao.getStockInfo(vo); // 查询结果可能是多条 for (Account account : inList)
			 * { //每个itemcode 的入库总量 double inCount = account.getNumber();
			 * //依次取每个itemcode数据的出库统计数据，每个itemcode只有一条出库统计数据
			 * vo.setItemCode(account.getItem().getCode()); vo.setType(0);
			 * List<Account> outList = dao.getStockInfo(vo); //每个itemcode的出库总量
			 * double outCount = outList.get(0).getNumber();
			 * account.setNumber(inCount - outCount); //库存信息加入结果集
			 * resultList.add(account); }
			 */
			// =========================多次查询数据库的算法====end===============
			// =========================一次查询数据库 循环取数====start===============
			// 先查询所有itemcode的入库信息
			vo.setType(1);
			List<Stock> inList = stockDao.getStockInfo(vo);
			// 再查询所有itemcode的出库信息
			vo.setType(0);
			List<Stock> outList = stockDao.getStockInfo(vo);
			for (Stock inAcc : inList) {
				// 每个itemcode 的入库总量
				double inCount = inAcc.getNumber();
				// 依次取每个itemcode数据的出库统计数据，每个itemcode只有一条出库统计数据
				for (Stock outAcc : outList) {
					if (inAcc.getItemcode().equals(outAcc.getItemcode()) && inAcc.getDepartment().equals(outAcc.getDepartment())) {
						// 如果找到部门相同并且itemcode相同的出库数据，则入库-出库
						double outCount = outAcc.getNumber();
						inAcc.setNumber(inCount - outCount);
						break;
					}
				}
				// 库存信息加入结果集
				resultList.add(inAcc);
			}
			// =========================一次查询数据库 循环取数====end===============
		}
		// 如果itemCode不为空，则查询该itemcode的库存信息
		else {
			// type为1的数据为入库信息
			vo.setType(1);
			// 查询入库数量，查询结果小于等于一条
			List<Stock> list = stockDao.getStockInfo(vo);
			if (list.size() > 0) {
				Stock acc = list.get(0);
				// 获取入库总和
				double inCount = acc.getNumber();

				// type为0的数据为出库信息
				vo.setType(0);
				// 查询出库数量，查询结果小于等于一条
				list = stockDao.getStockInfo(vo);
				if (list.size() > 0) {
					acc = list.get(0);
					double outCount = acc.getNumber();
					acc.setNumber(inCount - outCount); // 将计算后的库存结果保存在acc对象的number中
					resultList.add(acc); // 放入结果集
				}
			}
		}

		// 计算完库存以后，计算预警级别，库存量小于等于计划量的50%为黄色预警，库存量小于等于计划量的20%为红色预警
		// TODO: 该方法目前默认每月每种材料只有一条记录，sql中没有做count计算
		List<ItemPlan> itemPlanList = itemPlanDao.queryCurrPlan(new ItemPlan()); // 查询当月所有的计划
		int redAlert = 0;
		int yellowAlert = 0;
		for (Stock stock : resultList) {
			String itemcode = stock.getItemcode();
			String department = stock.getDepartment();
			double number = stock.getNumber();
			stock.setColor(null); // 设置color默认值为null
			for (ItemPlan plan : itemPlanList) {
				// 遍历本月计划，如果存在本月计划，则
				if (itemcode.equals(plan.getItemCode()) && department.equals(plan.getDepartment())) { // 本部门该项材料的计划匹配
					if (plan.getPlanNumber() > 0) { // 如果本月有相应计划，且数量不为0
						if (number / plan.getPlanNumber() < 0.2) { // 如果库存量不足计划量的20%，则红色提醒
							stock.setColor("red");
							redAlert++;
						} else if (number / plan.getPlanNumber() < 0.5) { // 如果库存量不足计划量的50%，则黄色提醒
							stock.setColor("yellow");
							yellowAlert++;
						} else { // 如果库存量大于计划量的50%，则底色为空（如果不设置颜色，color为null，后面会把该数据删除）
							stock.setColor("");
						}
					}
					break; // 每项材料的计划数据只有一条，所以不用继续循环
				}
			}
		}
		System.out.println("库存清单共计 [" + resultList.size() + "]条，其中红色预警 [" + redAlert + "]条，黄色预警 [" + yellowAlert + "]条。");

		// 删除库存为0，且当月没有计划的数据
		Iterator<Stock> iterator = resultList.iterator();
		int deleteNumber = 0;
		while (iterator.hasNext()) {
			Stock stock = iterator.next();
			if (stock.getNumber() == 0 && stock.getColor() == null) { // 如果当月有计划，则该数据的color会为red、yellow或""，null表示没有计划
				System.out.println("删除库存清单：" + stock.getItemcode() + "[" + stock.getItemname() + "]");
				iterator.remove();
				deleteNumber++;
			}
		}
		System.out.println("共删除本月没有计划且库存为0的数据 [" + deleteNumber + "]条。");

		// 按照库存量排序
		Collections.sort(resultList, new Comparator<Stock>() {
			public int compare(Stock arg0, Stock arg1) {
				if (arg0.getNumber() > arg1.getNumber()) {
					return -1;
				} else if (arg0.getNumber() == arg1.getNumber()) {
					return 0;
				} else {
					return 1;
				}
			}
		});

		model.addAttribute("stockList", resultList);
		return "/stock/stockindex";
	}

}
