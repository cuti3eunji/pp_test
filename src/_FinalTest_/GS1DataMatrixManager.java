package _FinalTest_;

public class GS1DataMatrixManager {
	
	private UIControl ui;
	
	public GS1DataMatrixManager() {
		ui = new UIControl();
		connectScanner();
	}
	
	private void connectScanner() {
		ScannerControl st = new ScannerControl(this);
		System.out.println(st.serialPort);
		if(st.serialPort != null) {
			st.connect();
		}
	}

	public static void main(String[] args) {
		GS1DataMatrixManager bm = new GS1DataMatrixManager();
	}
	
	public void dataa(String data){
		ui.setBarcode(data);
	}
}