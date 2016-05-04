package com.fdmy.model;

import org.hibernate.validator.constraints.NotEmpty;

public class Item {
	private String code;
	private String name;
	private String model;
	private Double price;
	private String unit;
	private Double amount;

	@NotEmpty(message="编码不能为空")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = "".equals(code) ? null : code;
	}

	@NotEmpty(message="名称不能为空")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		name = "".equals(name) ? null : name;
		this.name = name;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		model = "".equals(model) ? null : model;
		this.model = model;
	}

	public Double getPrice() {
		return price == null ? 0 : price;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		unit = "".equals(unit) ? null : unit;
		this.unit = unit;
	}

	public Double getAmount() {
		return amount == null ? 0 : amount;
	}

	public void setAmount(Double amount) {
		amount = amount == null ? 0 : amount;
		this.amount = amount;
	}

	public void setPrice(Double price) {
		price = price == null ? 0 : price;
		this.price = price;
	}

	@Override
	public String toString() {
		return "name:" + name + ",code:" + code + ",model:" + model + ",price:" + price + "amount:" + amount;
	}

}
