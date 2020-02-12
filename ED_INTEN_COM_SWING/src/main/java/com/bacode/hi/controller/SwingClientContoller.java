package com.bacode.hi.controller;

public class SwingClientContoller {
	
	private UIControl ui;

	public SwingClientContoller() {
		
		ui = new UIControl();
		connectScanner();
	}
	
	private void connectScanner() {
		ScannerControl st = new ScannerControl(this);
		st.connect();
	}

	public static void main(String[] args) {
		SwingClientContoller sc = new SwingClientContoller();
	}
	
	public void dataa(String data){
		ui.setBarcode(data);
	}
}