package com.fdmy.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.fdmy.controller.vo.StockParamVO;
import com.fdmy.model.Stock;

@Repository("stockDao")
public class StockDao implements IStockDao {
	private SqlSessionTemplate sessionTemplate;

	@Resource
	public void setSessionTemplate(SqlSessionTemplate sessionTemplate) {
		this.sessionTemplate = sessionTemplate;
	}

	@Override
	public List<Stock> getStockInfo(StockParamVO vo) {
		return sessionTemplate.selectList(Stock.class.getName() + ".checkstock", vo);
	}

}
