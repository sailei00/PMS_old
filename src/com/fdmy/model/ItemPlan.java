package com.fdmy.model;

import java.text.DecimalFormat;

import javax.validation.constraints.DecimalMin;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

public class ItemPlan {

	DecimalFormat df = new DecimalFormat("0.00");

	private String id;
	private String itemCode;
	private String itemName;
	private String itemModel;
	private String itemUnit;

	private String planMonth;
	private String planMonthEnd;
	private String department;
	private double planNumber;
	/** 单价 */
	private double price;
	/** 金额 */
	private double amount;
	private String purpose;
	private String costType; // 费用类别（承包费、掘进费、生产费、安全费）

	@NotEmpty(message = "物料名称不能为空")
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

	@NotEmpty(message = "请填写计划月份")
	public String getPlanMonth() {
		return planMonth;
	}

	public void setPlanMonth(String planMonth) {
		this.planMonth = "".equals(planMonth) ? null : planMonth;
	}

	@NotEmpty(message = "归属部门不能为空")
	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = "".equals(department) ? null : department;
	}

	@DecimalMin(value = "0.01", message = "请输入计划数量")
	public double getPlanNumber() {
		return planNumber;
	}

	public void setPlanNumber(double planNumber) {
		this.planNumber = planNumber;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = "".equals(purpose) ? null : purpose;
	}

	@NotBlank(message = "请选择费用类别")
	public String getCostType() {
		return costType;
	}

	public void setCostType(String costType) {
		this.costType = costType;
	}

	@DecimalMin(value = "0.01", message = "请输入单价")
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = Double.parseDouble(df.format(price));
	}

	@DecimalMin(value = "0.01", message = "请输入金额")
	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = Double.parseDouble(df.format(amount));
	}

	@Override
	public String toString() {
		return "ItemPlan [id=" + id + ", itemCode=" + itemCode + ", itemName=" + itemName + ", itemModel=" + itemModel
				+ ", itemUnit=" + itemUnit + ", planMonth=" + planMonth + ", department=" + department + ", planNumber="
				+ planNumber + ", purpose=" + purpose + ", costType=" + costType + "]";
	}

	public String getPlanMonthEnd() {
		return planMonthEnd;
	}

	public void setPlanMonthEnd(String planMonthEnd) {
		this.planMonthEnd = "".equals(planMonthEnd) ? null : planMonthEnd;
	}

}
