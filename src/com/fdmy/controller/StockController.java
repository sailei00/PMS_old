package com.fdmy.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fdmy.controller.vo.StockParamVO;
import com.fdmy.dao.DaoFactory;
import com.fdmy.dao.IStockDao;
import com.fdmy.model.Account;

/*
 * 库存相关操作控制器
 */
@Controller
@RequestMapping("/stock")
public class StockController {
	private IStockDao dao = DaoFactory.getStockDao();

	public StockController() {
		System.out.println("a new StockController");
	}

	@RequestMapping("/index")
	public String index() {
		return "/stock/stockindex";
	}

	@RequestMapping(value = "/checkstock", method = RequestMethod.GET)
	public String checkStock(StockParamVO vo, Model model) {
		System.out.println(1);
		System.out.println(vo.getDepartment());
		List<Account> resultList = new ArrayList<Account>(); // 定义最后的查询结果对象
		boolean checkAllItem = vo.getItemCode() == null || vo.getItemCode().equals("");
		// 如果itemCode没有传值，则查询所有itemcode的库存信息
		if (checkAllItem) {
			System.out.println(2);
			// 先查询所有itemcode的入库信息
			vo.setType(1);
			List<Account> inList = dao.getStockInfo(vo);
			// 查询结果可能是多条
			for (Account account : inList) {
				//每个itemcode 的入库总量
				double inCount = account.getNumber();
				//依次取每个itemcode数据的出库统计数据，每个itemcode只有一条出库统计数据
				vo.setItemCode(account.getItem().getCode());
				vo.setType(0);
				List<Account> outList = dao.getStockInfo(vo);
				//每个itemcode的出库总量
				double outCount =  outList.get(0).getNumber();
				account.setNumber(inCount - outCount);
				//库存信息加入结果集
				resultList.add(account);
			}
		}
		// 如果itemCode不为空，则查询该itemcode的库存信息
		else {
			System.out.println(3);
			// type为1的数据为入库信息
			vo.setType(1);
			// 查询入库数量，查询结果只有一条
			List<Account> list = dao.getStockInfo(vo);
			Account acc = list.get(0);
			// 获取入库总和
			double inCount = acc.getNumber();

			// type为0的数据为出库信息
			vo.setType(0);
			// 查询出库数量，查询结果只有一条
			list = dao.getStockInfo(vo);
			acc = list.get(0);
			double outCount = acc.getNumber();
			acc.setNumber(inCount - outCount); // 将计算后的库存结果保存在acc对象的number中
			resultList.add(acc); // 放入结果集
		}
		System.out.println(4);
		model.addAttribute("stockList", resultList);
		return "/stock/stockindex";
	}

}
