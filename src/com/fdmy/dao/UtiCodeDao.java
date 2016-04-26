package com.fdmy.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;

import com.fdmy.model.UtiCode;

public class UtiCodeDao implements IUtiCodeDao {
	private SqlSessionTemplate sessionTemplate;

	@Resource
	public void setSessionTemplate(SqlSessionTemplate sessionTemplate) {
		this.sessionTemplate = sessionTemplate;
	}

	@Override
	public UtiCode load(UtiCode code) {
		return sessionTemplate.selectOne(UtiCode.class.getName() + ".load", code);
	}

	@Override
	public List<UtiCode> getCodesByType(String codetype) {
		return sessionTemplate.selectList(UtiCode.class.getName() + ".getCodesByType", codetype);
	}

}
