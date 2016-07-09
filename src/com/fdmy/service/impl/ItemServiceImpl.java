package com.fdmy.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fdmy.dao.IItemDao;
import com.fdmy.model.Item;
import com.fdmy.service.IItemService;


@Service("itemService")
public class ItemServiceImpl implements IItemService {
	
	private IItemDao itemDao;
	
	public IItemDao getItemDao() {
		return itemDao;
	}

	@Resource
	public void setItemDao(IItemDao itemDao) {
		this.itemDao = itemDao;
	}

	@Override
	public void add(Item item) {
		itemDao.add(item);
	}

	@Override
	public void delete(String code) {
		itemDao.delete(code);
	}

	@Override
	public void update(Item item) {
		itemDao.update(item);
	}

	@Override
	public Item load(String code) {
		return itemDao.load(code);
	}

	@Override
	public List<Item> query(Item item) {
		return itemDao.query(item);
	}

}
