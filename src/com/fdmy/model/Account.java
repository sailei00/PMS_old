package com.fdmy.model;

import java.util.Calendar;
import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

/**
 * 流水账
 */
public class Account {
	/** 属性账目编号 */
	private String id;
	/** 属性物料编码相关信息 */
	private Item item;
	/**
	 * 属性单据类型 0 出库，1入库 ,9：默认（出现9则是异常）
	 */
	private int type = 9;
	/** 属性数量 */
	private Double number;
	/** 属性归属单位 */
	private String department;
	/** 属性操作员 */
	private String operator;
	/** 属性经办人 */
	private String handler;
	/** 属性出入库原因 */
	private String reason;
	/** 属性出入库时间 */
	private Date optTime;
	/** 属性录入时间 */
	private Date createTime = Calendar.getInstance().getTime();
	/** 属性最后修改时间 */
	private Date updateTime;
	/** 属性最后修改人 */
	private String updater;

	public Date getOptTime() {
		return optTime;
	}

	public void setOptTime(Date optTime) {
		this.optTime = optTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = "".equals(id.trim()) ? null : id.trim();
	}

	@NotNull(message = "物料信息不能为空")
	@Valid
	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	@Range(min = 0, max = 1, message = "请选择正确的单据类型")
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@NotNull(message = "请填写数量")
	public Double getNumber() {
		return number;
	}

	public void setNumber(Double number) {
		this.number = number;
	}

	@NotBlank(message = "部门信息不能为空")
	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = "".equals(department.trim()) ? null : department.trim();
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getHandler() {
		return handler;
	}

	public void setHandler(String handler) {
		this.handler = handler;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getUpdater() {
		return updater;
	}

	public void setUpdater(String updater) {
		this.updater = "".equals(updater.trim()) ? null : updater.trim();
	}

}
