package com.fdmy.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.fdmy.model.User;

@Repository("userDao")
public class UserDao implements IUserDao {
	
	private SqlSessionTemplate sessionTemplate;

	@Resource
	public void setSessionTemplate(SqlSessionTemplate sessionTemplate) {
		this.sessionTemplate = sessionTemplate;
	}

	@Override
	public void add(User user) {
		sessionTemplate.insert(User.class.getName() + ".add", user);
	}

	@Override
	public void delete(String usercode) {
		sessionTemplate.delete(User.class.getName() + ".delete", usercode);
	}

	@Override
	public void update(User user) {
		sessionTemplate.update(User.class.getName() + ".update", user);
	}

	@Override
	public User load(String usercode) {
		return sessionTemplate.selectOne(User.class.getName() + ".load", usercode);
	}

	@Override
	public List<User> query(User user) {
		return sessionTemplate.selectList(User.class.getName() + ".query", user);
	}

	@Override
	public User login(String usercode,String password) {
		return sessionTemplate.selectOne(User.class.getName() + ".login", usercode);
	}

	@Override
	public void changePassword(User user) {
		sessionTemplate.update(User.class.getName() + ".changepassword", user);
	}
	
	

}
