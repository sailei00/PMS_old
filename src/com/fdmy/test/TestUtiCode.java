package com.fdmy.test;

import java.util.List;

import org.junit.Test;

import com.fdmy.dao.DaoFactory;
import com.fdmy.dao.IUtiCodeDao;
import com.fdmy.model.UtiCode;

public class TestUtiCode {
	IUtiCodeDao dao = DaoFactory.getUtiCodeDao();

	@Test
	public void testGetCodesByType() {
		List<UtiCode> codelist = null;
		codelist = dao.getCodesByType("department");
		for (UtiCode code : codelist) {
			System.out.println("codecode:" + code.getCodeCode());
			System.out.println("codename:" + code.getCodeName());
		}
	}

	@Test
	public void testLoad() {
		UtiCode code = new UtiCode();
		code.setCodeType("department");
		code.setCodeCode("机电机运队");
		UtiCode c = dao.load(code);
		System.out.println(c.getCodeType());
		System.out.println(c.getCodeCode());
		System.out.println(c.getCodeName());
	}

}
