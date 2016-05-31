package com.fdmy.model;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

public class User {
	private String usercode;
	private String username;
	private String oldpassword;
	private String password;
	private String department;
	/**
	 * 用户类型 0 : 科室， 1 : 队组，  2：领导，    9:默认值(未设置)
	 */
	private int usertype = 9;
	/**
	 * 用户状态： 0:停用 1:启用(正常) 9默认值(未设置)
	 */
	private int status = 9;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@NotNull(message = "用户编码不能为空")
	public String getUsercode() {
		return usercode;
	}

	public void setUsercode(String usercode) {
		this.usercode = "".equals(usercode) ? null : usercode;
	}

	@NotNull(message = "用户名不能为空")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = "".equals(username) ? null : username;
	}

	public String getOldpassword() {
		return oldpassword;
	}

	public void setOldpassword(String oldpassword) {
		this.oldpassword = oldpassword;
	}

	@NotNull(message = "密码不能为空")
	@Length(min = 6, message = "密码长度最小为6位")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@NotNull(message = "归属部门不能为空")
	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = "".equals(department) ? null : department;
	}

	@Range(min = 0, max = 1, message = "请选择正确的用户类型")
	public int getUsertype() {
		return usertype;
	}

	public void setUsertype(int usertype) {
		this.usertype = usertype;
	}

}
