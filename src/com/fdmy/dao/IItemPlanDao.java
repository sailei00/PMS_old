package com.fdmy.dao;

import java.util.List;

import com.fdmy.model.ItemPlan;

public interface IItemPlanDao {

	/**
	 * 保存前根据itemcode、planMonth、department进行唯一性校验
	 * @param itemPlan
	 */
	public void add(ItemPlan itemPlan);

	public void delete(String code);

	public void update(ItemPlan itemPlan);

	public ItemPlan load(String id);
	
	/**
	 * 
	 * @param plan 要求根据 itemcode、planMonth、department查询唯一记录
	 * @return
	 */
	public ItemPlan load(ItemPlan plan);

	public List<ItemPlan> query(ItemPlan itemPlan);

	public List<ItemPlan> queryCurrPlan(ItemPlan itemPlan);

}
