package com.fdmy.dao;

import java.util.List;

import com.fdmy.controller.vo.StockParamVO;
import com.fdmy.model.Stock;

public interface IStockDao {

	public List<Stock> getStockInfo(StockParamVO vo);
}