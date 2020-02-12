package com.bacode.hi.vo;

import java.io.Serializable;

public class ProdMasterVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String ma_code;
	private String ma_name;
	private String ma_gtin;
	
	public String getMa_code() {
		return ma_code;
	}
	public void setMa_code(String ma_code) {
		this.ma_code = ma_code;
	}
	public String getMa_name() {
		return ma_name;
	}
	public void setMa_name(String ma_name) {
		this.ma_name = ma_name;
	}
	public String getMa_gtin() {
		return ma_gtin;
	}
	public void setMa_gtin(String ma_gtin) {
		this.ma_gtin = ma_gtin;
	}

}
