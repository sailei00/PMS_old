package com.fdmy.dao;

import java.util.List;

import com.fdmy.model.Item;

public interface IItemDao {
	public void add(Item item);

	public void delete(String code);

	public void update(Item item);

	public Item load(String code);

	public List<Item> query(Item item);
}