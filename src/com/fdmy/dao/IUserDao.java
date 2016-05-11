package com.fdmy.dao;

import java.util.List;

import com.fdmy.model.User;

public interface IUserDao {
	public void add(User user);

	public void delete(String usercode);

	public void update(User user);

	public User load(String usercode);

	public List<User> query(User user);
	
	public User login(String usercode,String password);
	
	public void changePassword(User user);
}