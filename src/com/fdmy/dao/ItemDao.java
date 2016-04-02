package com.fdmy.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.fdmy.exception.DBException;
import com.fdmy.model.Item;
import com.fdmy.util.DBUtil;

public class ItemDao implements IItemDao {

	@Override
	public void add(Item item) {
		SqlSession session = null;
		try {
			session = DBUtil.getSession();
			session.insert(Item.class.getName() + ".add", item);
			session.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
			throw new DBException(e.getMessage());
		} finally {
			DBUtil.closeSession(session);
		}
	}

	@Override
	public void delete(String code) {
		SqlSession session = null;
		try {
			session = DBUtil.getSession();
			session.delete(Item.class.getName() + ".delete", code);
			session.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		} finally {
			DBUtil.closeSession(session);
		}
	}

	@Override
	public void update(Item item) {
		SqlSession session = null;
		try {
			session = DBUtil.getSession();
			session.update(Item.class.getName() + ".update", item);
			session.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		} finally {
			DBUtil.closeSession(session);
		}
	}

	@Override
	public Item load(String code) {
		SqlSession session = null;
		Item item = null;
		try {
			session = DBUtil.getSession();
			item = session.selectOne(Item.class.getName() + ".load", code);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeSession(session);
		}
		return item;
	}

	@Override
	public List<Item> query(Item item) {
		SqlSession session = null;
		List<Item> list = null;
		try {
			session = DBUtil.getSession();
			list = session.selectList(Item.class.getName() + ".query",item);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeSession(session);
		}
		return list;
	}

}
