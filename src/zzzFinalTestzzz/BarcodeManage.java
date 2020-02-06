package zzzFinalTestzzz;

public class BarcodeManage {
	
	private UIsettings ui;
	
	public BarcodeManage() {
		ui = new UIsettings();
		connectScanner();
	}
	
	private void connectScanner() {
		ScannerTest st = new ScannerTest(this);
		st.connect();
	}

	public static void main(String[] args) {
		BarcodeManage bm = new BarcodeManage();
	}
	
	public void dataa(String data){
		ui.setBarcode(data);
	}
}