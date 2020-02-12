package com.bacode.hi.controller;

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
public class ScannerControl implements SerialPortEventListener{
	SwingClientContoller parent;
	public SerialPort serialPort;
	
	public ScannerControl(SwingClientContoller parent) {
		this.parent = parent;
	}
	
	public void connect() {
		//포트이름 확인 
		String[] portNames = SerialPortList.getPortNames();
		for (int i = 0; i < portNames.length; i++) {
			if(portNames[i].equals("COM3")) {
				System.out.println("===== [COM3]포트 연결 =====");
				serialPort = new SerialPort("COM3");
				
				try {
					serialPort.openPort(); // Open serial port
					serialPort.addEventListener(this);
				} catch (SerialPortException e) {
					e.printStackTrace();
				}
			}
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
