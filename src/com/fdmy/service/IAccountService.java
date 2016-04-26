package com.fdmy.service;

import java.util.List;

import com.fdmy.model.Account;
import com.fdmy.model.Item;

public interface IAccountService {
	public List<Account> query(Account acc);

	public void add(Account acc,Item item);

	public void update(Account acc);

	public void delete(String id);

	public Account load(String id);

}
