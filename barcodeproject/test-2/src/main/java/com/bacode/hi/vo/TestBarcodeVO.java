package com.bacode.hi.vo;

public class TestBarcodeVO {

	private String gs1code; // 상품식별코드
	private String expire;  // 유효기간
	private String lNum; 	// 제조번호
	private String sNum;	// 일련번호
	private String name;

	public String getGs1code() {
		return gs1code;
	}

	public void setGs1code(String gs1code) {
		this.gs1code = gs1code;
	}

	public String getExpire() {
		return expire;
	}

	public void setExpire(String expire) {
		this.expire = expire;
	}

	public String getlNum() {
		return lNum;
	}

	public void setlNum(String lNum) {
		this.lNum = lNum;
	}

	public String getsNum() {
		return sNum;
	}

	public void setsNum(String sNum) {
		this.sNum = sNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
