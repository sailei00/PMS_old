package com.fdmy.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.fdmy.model.Account;
import com.fdmy.util.DBUtil;

public class AccountDao implements IAccountDao {

	@Override
	public List<Account> query(Account acc) {
		SqlSession session = null;
		List<Account> list = null;
		try {
			session = DBUtil.getSession();
			list = session.selectList(Account.class.getName() + ".query",acc);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeSession(session);
		}
		return list;
	}

	@Override
	public void add(Account acc) {
		SqlSession session = null;
		try {
			session = DBUtil.getSession();
			session.insert(Account.class.getName() + ".add", acc);
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
	public void delete(String id) {
		SqlSession session = null;
		try {
			session = DBUtil.getSession();
			session.delete(Account.class.getName() + ".delete", id);
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
	public void update(Account acc) {
		SqlSession session = null;
		try {
			session = DBUtil.getSession();
			session.update(Account.class.getName() + ".update", acc);
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
	public Account load(String id) {
		SqlSession session = null;
		Account acc = null;
		try {
			session = DBUtil.getSession();
			acc = session.selectOne(Account.class.getName() + ".load", id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeSession(session);
		}
		return acc;
	}

}
