package com.fdmy.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.fdmy.controller.vo.StockParamVO;
import com.fdmy.exception.DBException;
import com.fdmy.model.Account;
import com.fdmy.util.DBUtil;

public class StockDao implements IStockDao {

	@Override
	public List<Account> getStockInfo(StockParamVO vo) {
		SqlSession session = null;
		List<Account> list = null;
		try {
			session = DBUtil.getSession();
			list = session.selectList(Account.class.getName() + ".checkstock", vo);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DBException(e.getMessage());
		} finally {
			DBUtil.closeSession(session);
		}
		return list;
	}

}
