package com.fdmy.dao;

import java.util.List;

import com.fdmy.model.UtiCode;

public interface IUtiCodeDao {

//	public void add(UtiCode code);

//	public void delete(String codeType, String codeCode);

//	public void update(UtiCode code);

	public UtiCode load(UtiCode code);

	public List<UtiCode> getCodesByType(String codetype);
}