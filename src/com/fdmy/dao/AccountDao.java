package com.fdmy.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.fdmy.model.Account;
import com.fdmy.util.SystemContext;
import com.github.pagehelper.PageHelper;

@Repository("accountDao")
public class AccountDao implements IAccountDao {

	private SqlSessionTemplate sessionTemplate;

	@Resource
	public void setSessionTemplate(SqlSessionTemplate sessionTemplate) {
		this.sessionTemplate = sessionTemplate;
	}

	@Override
	public List<Account> query(Account acc) {
		int pageSize = SystemContext.getPageSize();
		int pageNo = SystemContext.getPageNo();
		PageHelper.startPage(pageNo, pageSize);
		return sessionTemplate.selectList(Account.class.getName() + ".query", acc);
	}

	@Override
	public void add(Account acc) {
		sessionTemplate.insert(Account.class.getName() + ".add", acc);
	}

	@Override
	public void delete(String id) {
		sessionTemplate.delete(Account.class.getName() + ".delete", id);
	}

	@Override
	public void update(Account acc) {
		sessionTemplate.update(Account.class.getName() + ".update", acc);
	}

	@Override
	public Account load(String id) {
		return sessionTemplate.selectOne(Account.class.getName() + ".load", id);
	}

}
