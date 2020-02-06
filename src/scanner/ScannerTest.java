package scanner;

import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import jssc.SerialPortList;

public class ScannerTest implements SerialPortEventListener{
	SerialPort serialPortr;
	
	String scanningData;
	byte readBuffer[];

	public static void main(String[] args) {
		//포트이름 확인 
		String[] portNames = SerialPortList.getPortNames();
		
		
		try {
			//연결된 포트 있냐?????
			if(portNames.length != 0) { 
				for (int i = 0; i < portNames.length; i++) {
					System.out.println("연결된 포트이름 : " + portNames[i]);
				}
			}else if(portNames.length == 0){
				System.out.println("연결된 포트가 없습니당^_^~"); 
			}
			new ScannerTest();
		} catch (Exception e) {
			System.out.println("에러----> " + e.getMessage());
		}
	}
	
	public ScannerTest() { //생성자
		//Reading data from serial port
	    serialPortr = new SerialPort("COM3");
	    
	    try {
	        serialPortr.openPort();//Open serial port
			serialPortr.addEventListener(this);
		} catch (SerialPortException e) {
			e.printStackTrace();
		}
	    
	}
	
	@Override
	public void serialEvent(SerialPortEvent event) {
		try {
	        
	        if(event.isRXCHAR()){//If data is available
	        	int count =event.getEventValue();
	            if(count > 0){//Check bytes count in the input buffer
	                //Read data, if 10 bytes available 
	                    readBuffer = serialPortr.readBytes(count);
	                    
	                    scanningData = new String(readBuffer);
	                    System.out.println(scanningData);

	                    serialPortr.closePort();//Close serial port
	                    
	                    dataCheck();
	            }
	        }
	    }
	    catch (SerialPortException ex) { 
	        System.out.println(ex);
	    }
	}
	
	public void dataCheck() {
		CharSequence st = Character.toString((char) 232);
		CharSequence gs = Character.toString((char) 29);
		
		System.out.println("contains st ? " + scanningData.startsWith(st.toString()));
		System.out.println("contains gs ? " + scanningData.contains(gs));

		
		System.out.println("01088064160555191720022010aaaaaa21bbbbbbbbbbbb".length());
		System.out.println("scanningData = " + scanningData + ", length = " + scanningData.length());
		
		String[] barcodeArr = scanningData.split(gs.toString());
		System.out.println("barcode without ser = " + barcodeArr[0]);
		System.out.println("barcode ser = " + barcodeArr[1]);
	}

}
