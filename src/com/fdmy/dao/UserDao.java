package com.fdmy.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.fdmy.model.User;
import com.fdmy.util.DBUtil;

public class UserDao implements IUserDao {

	@Override
	public void add(User user) {
		SqlSession session = null;
		try {
			session = DBUtil.getSession();
			session.insert(User.class.getName() + ".add", user);
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
	public void delete(String usercode) {
		SqlSession session = null;
		try {
			session = DBUtil.getSession();
			session.delete(User.class.getName() + ".delete", usercode);
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
	public void update(User user) {
		SqlSession session = null;
		try {
			session = DBUtil.getSession();
			session.update(User.class.getName() + ".update", user);
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
	public User load(String usercode) {
		SqlSession session = null;
		User user = null;
		try {
			session = DBUtil.getSession();
			user = session.selectOne(User.class.getName() + ".load", usercode);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeSession(session);
		}
		return user;
	}

	@Override
	public List<User> query(User user) {
		SqlSession session = null;
		List<User> list = null;
		try {
			session = DBUtil.getSession();
			list = session.selectList(User.class.getName() + ".query", user);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeSession(session);
		}
		return list;
	}

	@Override
	public User login(String usercode,String password) {
		SqlSession session = null;
		User user = null;
		try {
			session = DBUtil.getSession();
			user = session.selectOne(User.class.getName() + ".login", usercode);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeSession(session);
		}
		return user;
	}

}
