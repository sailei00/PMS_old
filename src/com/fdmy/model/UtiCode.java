package com.fdmy.model;

public class UtiCode {
	private String codeType;
	private String codeTypeDesc;
	private String codeCode;
	private String codeName;
	private String codeCode1;
	private String codeCode2;
	private String codeCode3;

	public String getCodeType() {
		return codeType;
	}

	public void setCodeType(String codeType) {
		this.codeType = "".equals(codeType) ? null : codeType;
	}

	public String getCodeTypeDesc() {
		return codeTypeDesc;
	}

	public void setCodeTypeDesc(String codeTypeDesc) {
		this.codeTypeDesc = "".equals(codeTypeDesc) ? null : codeTypeDesc;
	}

	public String getCodeCode() {
		return codeCode;
	}

	public void setCodeCode(String codeCode) {
		this.codeCode = "".equals(codeCode) ? null : codeCode;
	}

	public String getCodeName() {
		return codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = "".equals(codeName) ? null : codeName;
	}

	public String getCodeCode1() {
		return codeCode1;
	}

	public void setCodeCode1(String codeCode1) {
		this.codeCode1 = "".equals(codeCode1) ? null : codeCode1;
	}

	public String getCodeCode2() {
		return codeCode2;
	}

	public void setCodeCode2(String codeCode2) {
		this.codeCode2 = "".equals(codeCode2) ? null : codeCode2;
	}

	public String getCodeCode3() {
		return codeCode3;
	}

	public void setCodeCode3(String codeCode3) {
		this.codeCode3 = "".equals(codeCode3) ? null : codeCode3;
	}
}
