package com.fdmy.dao;

import java.util.List;

import com.fdmy.model.Account;

public interface IAccountDao {
	public List<Account> query(Account acc);
	
	public void add(Account acc);
	
	public void delete(String id);
	
	public void update(Account acc);
	
	public Account load(String id);

}
