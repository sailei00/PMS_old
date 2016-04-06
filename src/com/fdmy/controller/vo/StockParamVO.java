package com.fdmy.controller.vo;

public class StockParamVO {
	private String itemCode;
	private String itemName;
	private String model;
	private String department;
	private int type;

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = "".equals(itemCode) ? null : itemCode;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		itemName = "".equals(itemName) ? null : itemName;
		this.itemName = itemName;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		model = "".equals(model) ? null : model;
		this.model = model;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		department = "".equals(department) ? null : department;
		this.department = department;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
