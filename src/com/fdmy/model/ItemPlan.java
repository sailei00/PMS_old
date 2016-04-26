package com.fdmy.model;

import org.hibernate.validator.constraints.NotEmpty;

public class ItemPlan {
	private String id;
	private String itemCode;
	private String itemName;
	private String itemModel;
	private String itemUnit;
	
	private String planMonth;
	private String department;
	private int planNumber;
	private String purpose;
	
	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = "".equals(itemName) ? null : itemName;
	}

	public String getItemModel() {
		return itemModel;
	}

	public void setItemModel(String itemModel) {
		this.itemModel = "".equals(itemModel) ? null : itemModel;
	}

	public String getItemUnit() {
		return itemUnit;
	}

	public void setItemUnit(String itemUnit) {
		this.itemUnit = "".equals(itemUnit) ? null : itemUnit;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = "".equals(id) ? null : id;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = "".equals(itemCode) ? null : itemCode;
	}

	public String getPlanMonth() {
		return planMonth;
	}

	@NotEmpty(message="请填写计划月份")
	public void setPlanMonth(String planMonth) {
		this.planMonth = "".equals(planMonth) ? null : planMonth;
	}

	public String getDepartment() {
		return department;
	}

	@NotEmpty(message="归属部门不能为空")
	public void setDepartment(String department) {
		this.department = "".equals(department) ? null : department;
	}

	public int getPlanNumber() {
		return planNumber;
	}

	public void setPlanNumber(int planNumber) {
		this.planNumber = planNumber;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = "".equals(purpose) ? null : purpose;
	}

}
