package com.fdmy.dao;

import java.util.List;

import com.fdmy.controller.vo.StockParamVO;
import com.fdmy.model.Account;

public interface IStockDao {

	public List<Account> getStockInfo(StockParamVO vo);
}