package com.fdmy.dao;

public class DaoFactory {
	public static IItemDao getItemDao() {
		return new ItemDao();
	}

	public static IAccountDao getAccountDao() {
		return new AccountDao();
	}

	public static IUserDao getUserDao() {
		return new UserDao();
	}

	public static IUtiCodeDao getUtiCodeDao() {
		return new UtiCodeDao();
	}
	
	public static IStockDao getStockDao(){
		return new StockDao();
	}

	public static IItemPlanDao getItemPlanDao() {
		return new ItemPlanDao();
	}
}
