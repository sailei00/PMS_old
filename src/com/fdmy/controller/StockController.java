package com.fdmy.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fdmy.controller.vo.StockParamVO;
import com.fdmy.dao.IItemPlanDao;
import com.fdmy.dao.IStockDao;
import com.fdmy.model.Account;
import com.fdmy.model.ItemPlan;

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
		List<Account> resultList = new ArrayList<Account>(); // 定义最后的查询结果对象
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
			List<Account> inList = stockDao.getStockInfo(vo);
			// 再查询所有itemcode的出库信息
			vo.setType(0);
			List<Account> outList = stockDao.getStockInfo(vo);
			for (Account inAcc : inList) {
				// 每个itemcode 的入库总量
				double inCount = inAcc.getNumber();
				// 依次取每个itemcode数据的出库统计数据，每个itemcode只有一条出库统计数据
				for (Account outAcc : outList) {
					if (inAcc.getItem().getCode().equals(outAcc.getItem().getCode())
							&& inAcc.getDepartment().equals(outAcc.getDepartment())) {
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
			// 查询入库数量，查询结果只有一条
			List<Account> list = stockDao.getStockInfo(vo);
			Account acc = list.get(0);
			// 获取入库总和
			double inCount = acc.getNumber();

			// type为0的数据为出库信息
			vo.setType(0);
			// 查询出库数量，查询结果只有一条
			list = stockDao.getStockInfo(vo);
			acc = list.get(0);
			double outCount = acc.getNumber();
			acc.setNumber(inCount - outCount); // 将计算后的库存结果保存在acc对象的number中
			resultList.add(acc); // 放入结果集
		}

		// 计算完库存以后，计算预警级别，库存量小于等于计划量的50%为黄色预警，库存量小于等于计划量的20%为红色预警
		List<ItemPlan> itemPlanList = itemPlanDao.queryCurrPlan(new ItemPlan()); // 查询当月所有的计划
		for (Account acc : resultList) {
			String itemcode = acc.getItem().getCode();
			String department = acc.getDepartment();
			double number = acc.getNumber();
			for (ItemPlan plan : itemPlanList) {
				if (itemcode.equals(plan.getItemCode()) && department.equals(plan.getDepartment())) {
					if (plan.getPlanNumber() > 0) {
						if (number / plan.getPlanNumber() < 0.2) {
							acc.setReason("red");
						} else if (number / plan.getPlanNumber() < 0.5) {
							acc.setReason("yellow");
						}
					}
					break;
				}

			}
		}

		model.addAttribute("stockList", resultList);
		return "/stock/stockindex";
	}

}
