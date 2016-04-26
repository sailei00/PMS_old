package com.fdmy.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.fdmy.model.ItemPlan;

@Repository("itemPlanDao")
public class ItemPlanDao extends BaseDao<ItemPlan> implements IItemPlanDao {

	@Override
	public void add(ItemPlan itemPlan) {
		super.add(itemPlan);
	}

	@Override
	public void delete(String code) {
		super.delete(ItemPlan.class, code);
	}

	@Override
	public void update(ItemPlan itemPlan) {
		super.update(itemPlan);
	}

	@Override
	public ItemPlan load(String id) {
		ItemPlan itemplan = super.load(ItemPlan.class, id);
		return itemplan;
	}
	
	/**
	 * 根据itemcode、planMonth、department进行唯一性校验
	 */
	@Override
	public ItemPlan load(ItemPlan plan) {
		ItemPlan itemplan = super.load(ItemPlan.class.getName() + ".loadonebyparam", plan);
		return itemplan;
	}
	

	@Override
	public List<ItemPlan> query(ItemPlan itemPlan) {
		List<ItemPlan> list = super.query(ItemPlan.class, itemPlan);
		return list;
	}
	
	@Override
	public List<ItemPlan> queryCurrPlan(ItemPlan itemPlan) {
		List<ItemPlan> list = super.query(ItemPlan.class.getName() + ".querycurrplan", itemPlan);
		return list;
	}

}
