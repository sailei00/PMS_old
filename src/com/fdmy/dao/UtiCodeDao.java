package com.fdmy.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.fdmy.exception.DBException;
import com.fdmy.model.UtiCode;
import com.fdmy.util.DBUtil;

public class UtiCodeDao implements IUtiCodeDao {

	@Override
	public UtiCode load(UtiCode code) {
		SqlSession session = null;
		UtiCode uticode = null;
		try {
			session = DBUtil.getSession();
			uticode = session.selectOne(UtiCode.class.getName() + ".load",code);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DBException(e.getMessage());
		} finally {
			DBUtil.closeSession(session);
		}
		return uticode;
	}

	@Override
	public List<UtiCode> getCodesByType(String codetype) {
		SqlSession session = null;
		List<UtiCode> codeList = null;
		try {
			session = DBUtil.getSession();
			codeList = session.selectList(UtiCode.class.getName() + ".getCodesByType",codetype);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DBException(e.getMessage());
		} finally {
			DBUtil.closeSession(session);
		}
		return codeList;
	}

}
