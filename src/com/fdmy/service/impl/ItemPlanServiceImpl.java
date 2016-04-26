package com.fdmy.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fdmy.dao.IItemPlanDao;
import com.fdmy.model.ItemPlan;
import com.fdmy.service.IItemPlanService;


@Service("itemPlanService")
public class ItemPlanServiceImpl implements IItemPlanService {
	
	private IItemPlanDao itemPlanDao;
	
	public IItemPlanDao getItemPlanDao() {
		return itemPlanDao;
	}

	@Resource
	public void setItemPlanDao(IItemPlanDao itemPlanDao) {
		this.itemPlanDao = itemPlanDao;
	}

	@Override
	public void add(ItemPlan itemPlan) {
		itemPlanDao.add(itemPlan);
	}

	@Override
	public void delete(String id) {
		itemPlanDao.delete(id);
	}

	@Override
	public void update(ItemPlan itemPlan) {
		itemPlanDao.update(itemPlan);
	}

	@Override
	public ItemPlan load(String id) {
		return itemPlanDao.load(id);
	}

	@Override
	public List<ItemPlan> query(ItemPlan itemPlan) {
		return itemPlanDao.query(itemPlan);
	}

	@Override
	public ItemPlan load(ItemPlan plan) {
		return itemPlanDao.load(plan);
	}

	@Override
	public List<ItemPlan> queryCurrPlan(ItemPlan itemPlan) {
		return itemPlanDao.queryCurrPlan(itemPlan);
	}

}
