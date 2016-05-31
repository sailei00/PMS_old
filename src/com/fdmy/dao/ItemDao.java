package com.fdmy.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.fdmy.model.Item;
import com.fdmy.util.SystemContext;
import com.github.pagehelper.PageHelper;

@Repository("itemDao")
public class ItemDao implements IItemDao {

	private SqlSessionTemplate sessionTemplate;

	@Resource
	public void setSessionTemplate(SqlSessionTemplate sessionTemplate) {
		this.sessionTemplate = sessionTemplate;
	}

	@Override
	public void add(Item item) {
		sessionTemplate.insert(Item.class.getName() + ".add", item);
	}

	@Override
	public void delete(String code) {
		sessionTemplate.delete(Item.class.getName() + ".delete", code);
	}

	@Override
	public void update(Item item) {
		sessionTemplate.update(Item.class.getName() + ".update", item);
	}

	@Override
	public Item load(String code) {
		return sessionTemplate.selectOne(Item.class.getName() + ".load", code);
	}

	@Override
	public List<Item> query(Item item) {
		int pageSize = SystemContext.getPageSize();
		int pageNo = SystemContext.getPageNo();
		PageHelper.startPage(pageNo, pageSize);
		List<Item> list = sessionTemplate.selectList(Item.class.getName() + ".query", item);
		
		return list;
	}

}
