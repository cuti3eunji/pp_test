package zzzFinalTestzzz;

import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import jssc.SerialPortList;

/**
 * 스캐너 연결, 리스너 연결
 * 
 * @author Metabiz0350
 *
 */
public class ScannerTest implements SerialPortEventListener{
	BarcodeManage parent;
	public SerialPort serialPort;
	
	public ScannerTest(BarcodeManage parent) {
		this.parent = parent;
	}
	
	public void connect() {
		
		// 포트이름 확인
		String[] portNames = SerialPortList.getPortNames();

		for (String portNm : portNames) {
			System.out.println("port : " + portNm);

			if (portNm.equals("COM3")) {
				System.out.println("스캐너 연결 완료");
			}
		}
		serialPort = new SerialPort("COM3");

		try {
			serialPort.openPort(); // Open serial port
			serialPort.addEventListener(this);
		} catch (SerialPortException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void serialEvent(SerialPortEvent event) {
		String scanningData = null;
		
		try {
	        if(event.isRXCHAR()){//If data is available
	        	int count =event.getEventValue();
	            if(count > 0){//Check bytes count in the input buffer
	                    
	                    scanningData = new String(serialPort.readBytes(count));
//	                    serialPort.closePort();//Close serial port
	            }
	        }
	    }
	    catch (SerialPortException ex) { 
	        System.out.println(ex);
	    }
		parent.dataa(scanningData);
	}

}
