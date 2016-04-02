package com.fdmy.dao;

public class DAOFactory {
	public static IItemDao getItemDao() {
		return new ItemDao();
	}

	public static IAccountDao getAccountDao() {
		return new AccountDao();
	}

	public static IUserDao getUserDao() {
		return new UserDao();
	}

}
