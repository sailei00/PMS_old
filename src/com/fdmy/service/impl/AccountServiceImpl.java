package com.fdmy.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.fdmy.dao.IAccountDao;
import com.fdmy.dao.IItemDao;
import com.fdmy.model.Account;
import com.fdmy.model.Item;
import com.fdmy.service.IAccountService;

@Service("accountService")
public class AccountServiceImpl implements IAccountService {

	private IAccountDao accountDao;
	private IItemDao itemDao;

	public IAccountDao getAccountDao() {
		return accountDao;
	}

	@Resource
	public void setAccountDao(IAccountDao accountDao) {
		this.accountDao = accountDao;
	}

	public IItemDao getItemDao() {
		return itemDao;
	}

	@Resource
	public void setItemDao(IItemDao itemDao) {
		this.itemDao = itemDao;
	}

	@Override
	public List<Account> query(Account acc) {
		List<Account> list = accountDao.query(acc);
		return list;
	}

	@Override
	public void add(Account acc, Item item) {
		int type = acc.getType();
		if (type == 0) { // 0出库
			item.setAmount(item.getAmount() - acc.getNumber());
		} else if (type == 1) { // 1入库
			double num = acc.getNumber();
			double amount = item.getAmount();
			item.setAmount(num + amount);
		}
		// 如果原物料编码信息中没有单位信息，则更新该物料编码信息的单位
		if (item.getUnit() == null || "".equals(item.getUnit())) {
			item.setUnit(acc.getItem().getUnit());
		}
		// 如果原物料编码信息中没有单价则更新单价信息
		if(item.getPrice() == 0) {
			item.setPrice(acc.getPrice());
		}
		accountDao.add(acc);
		itemDao.update(item);
	}

	@Override
	public void update(Account acc) {
		// 修改入出库单也需要更新item的库存
		// 获取对应的 物料编码信息
		Item item = itemDao.load(acc.getItem().getCode());
		// 先获取单据原数据的number
		Account oldAcc = accountDao.load(acc.getId());
		double oldNumber = oldAcc.getNumber();
		boolean changedNumber = false;
		if (oldNumber != acc.getNumber()) {
			changedNumber = true;
		}
		int type = acc.getType();
		if (type == 0) { // 0 出库单
			if (changedNumber) {
				Logger.getLogger(this.getClass()).debug("修改了出库单的出库数量，同步调整库存");
				item.setAmount(item.getAmount() + oldNumber - acc.getNumber());
			}
		} else if (type == 1) { // 1 入库单
			if (changedNumber) {
				Logger.getLogger(this.getClass()).debug("修改了入库单的出库数量，同步调整库存");
				item.setAmount(item.getAmount() - oldNumber + acc.getNumber());
			}
		}
		// 如果原物料编码信息中没有单位信息，则更新该物料编码信息的单位
		if (item.getUnit() == null || "".equals(item.getUnit())) {
			item.setUnit(acc.getItem().getUnit());
		}
		// 如果原物料编码信息中没有单价则更新单价信息
		if(item.getPrice() == 0) {
			item.setPrice(acc.getPrice());
		}
		accountDao.update(acc);
		itemDao.update(item);
	}

	@Override
	public void delete(String id) {
		// 删除入出库单也需要更新item的库存
		// 先获取单据原数据的number
		Account acc = accountDao.load(id);
		double number = acc.getNumber();
		Item item = itemDao.load(acc.getItem().getCode());
		int type = acc.getType();
		if (type == 0) { // 0 出库单
			Logger.getLogger(this.getClass()).debug("删除了出库单，同步调整库存");
			item.setAmount(item.getAmount() + number);
		} else if (type == 1) { // 1 入库单
			Logger.getLogger(this.getClass()).debug("删除了入库单，同步调整库存");
			item.setAmount(item.getAmount() - number);
		}
		accountDao.delete(id);
		itemDao.update(item);
	}

	@Override
	public Account load(String id) {
		return accountDao.load(id);
	}

}
