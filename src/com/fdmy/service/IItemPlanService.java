package com.fdmy.service;

import java.util.List;

import com.fdmy.model.ItemPlan;

public interface IItemPlanService {
	
	public void add(ItemPlan itemPlan);

	public void delete(String code);

	public void update(ItemPlan itemPlan);

	public ItemPlan load(String id);

	public ItemPlan load(ItemPlan plan);

	public List<ItemPlan> query(ItemPlan itemPlan);

	public List<ItemPlan> queryCurrPlan(ItemPlan itemPlan);
}
