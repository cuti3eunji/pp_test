package scanner;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.zebra.sdk.comm.Connection;
//import com.zebra.sdk.comm.ConnectionException;
//import com.zebra.sdk.comm.TcpConnection;
//import com.zebra.sdk.printer.PrinterLanguage;
//import com.zebra.sdk.printer.ZebraPrinter;
//import com.zebra.sdk.printer.ZebraPrinterFactory;
//import com.zebra.sdk.printer.ZebraPrinterLanguageUnknownException;
import com.zebra.sdk.comm.ConnectionException;
import com.zebra.sdk.comm.TcpConnection;

import fileio.FileModify;
import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import jssc.SerialPortList;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class BarcodeTestScan implements ActionListener, SerialPortEventListener{
	JButton printBtn, searchBtn;
	JTextField barcodeInputField;
	JTextArea infoArea;
	
	
	SerialPort serialPortr;
	String scanningData;
	byte readBuffer[];
	
	String barcodeNum;	// 바코드 번호 
	
	public BarcodeTestScan() {	//생성자
		UIsettings();
	}
	
	public static void main(String[] args) {
		//포트이름 확인 
		String[] portNames = SerialPortList.getPortNames();
		
//		try {
			//연결된 포트 있냐?????
			if(portNames.length != 0) { 
				for (int i = 0; i < portNames.length; i++) {
					System.out.println("연결된 포트이름 : " + portNames[i]);
				}
			}else if(portNames.length == 0){
				System.out.println("연결된 포트가 없습니당^_^~");
			}
			new BarcodeTestScan();
			
//		} catch (Exception ex) {
//			System.out.println("에러----> " + ex.());
//		}
	}
	
	//기본 UI 세팅 
	void UIsettings(){
		//창 크기 지정
		Dimension dim = new Dimension(500,400);
		JFrame jf = new JFrame();
		
		jf.setPreferredSize(dim);
		
		//** 화면 구성 
		// inputPanel
		JPanel inputPanel = new JPanel();	//프린트용 panel
		inputPanel.setBorder(BorderFactory.createEmptyBorder(10 , 10 , 10 , 10));
		inputPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(10 , 10 , 10 , 10), "바코드"));
	
		barcodeInputField = new JTextField(14);
		
		inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.X_AXIS));
		inputPanel.add(barcodeInputField);
		
		// 버튼 Panel
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(10 , 10 , 10 , 10)));
	
		printBtn = new JButton("프린트");	//프린트 버튼
		printBtn.setPreferredSize(new Dimension(100,20));
		
		searchBtn = new JButton("확인"); //정보 검색 버튼
		searchBtn.setPreferredSize(new Dimension(100,20));
		
		//JDatePicker : http://www.codejava.net/java-se/swing/how-to-use-jdatepicker-to-display-calendar-component 
		UtilDateModel model = new UtilDateModel();
		JDatePanelImpl datePanel = new JDatePanelImpl(model);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);
		
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		buttonPanel.add(printBtn);
		buttonPanel.add(searchBtn);
		buttonPanel.add(datePicker);
		
		datePicker.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(datePanel.getModel().getValue());
			}
		});
		
		//바코드 정보 출력
		JPanel infoPanel = new JPanel();
		infoArea = new JTextArea();
		infoArea.setBorder(BorderFactory.createEmptyBorder(10 , 10 , 10 , 10));
		infoArea.setPreferredSize(new Dimension(100,250));
	
		infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
		infoPanel.add(infoArea);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.add(inputPanel);
		mainPanel.add(buttonPanel);
		mainPanel.add(infoPanel);
		// 화면 구성 **
		
		//이벤트 설정
		printBtn.addActionListener(this);
		searchBtn.addActionListener(this);
		
		jf.add(mainPanel, BorderLayout.CENTER);
		jf.pack();
		jf.setVisible(true);	//보이게 
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//창을 닫을 시 프로그램(JFrame)이 정상 종료 되도록 함
		jf.setLocationRelativeTo(null);		//창을 가운데에 띄우기
	}
	
	//이벤트 핸들러
	@Override
	public void actionPerformed(ActionEvent e) {
		
		barcodeNum = barcodeInputField.getText().trim();
		if(barcodeNum.length() < 14 ) {	// 자릿수 확인해서 14자리 미만이면 앞에 0을 붙여줌
			barcodeNum = String.format("%014d", Long.parseLong(barcodeNum));
		}else if(barcodeNum.length() > 14) {
			barcodeNum = barcodeNum.substring(0, 14);	// 입력된 바코드 값이 14자리가 넘으면 14자리까지 자름 (GS1 DataMatrix는 14자리)
		}
		System.out.println(barcodeNum);
		
		if(e.getActionCommand().equals("프린트")) {	// 바코드 프린트 하기 
			System.out.println("[ " + e.getActionCommand() + " ]");
			
			// 받아온 정보로 바코드 출력하기
			printBarcode(barcodeChange(barcodeNum));
			
		}else if(e.getActionCommand().equals("확인"))	{// 바코드 정보 찾아서 TextArea에 띄우기 
		}
	}
	
	//바코드 수정
	public byte[] barcodeChange(String barcode) {
		String path = FileModify.class.getResource("").getPath(); // 현재 클래스의 절대 경로를 가져온다.
		File basicfile = new File(path + "gs1DataMatrixTest.prn");	// 기본이 될 파일 경로
		
		String str = "";	//읽어온 파일을 저장
		byte[] strToBytes = null;
		
		//파일의 내용을 읽어서 저장하고 지정 경로에 새파일로 저장(복사)
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					new FileInputStream(basicfile), "UTF-8"));
			
			//파일 읽기
			int c;	//읽어온 데이터를 저장할 변수
			while((c=reader.read()) != -1) {
				str += (char)c;
			}
			reader.close();
			
			// 읽어온 파일에서 표시해놓은 부분을 바코드로 바꿔준다
			strToBytes = str.replaceAll("@@@@@", barcode).getBytes();
			
		}
		catch (IOException e) {
			e.printStackTrace();
			System.out.println("입출력오류");
		}
		return strToBytes;
	}
	
	public void printBarcode(byte[] byteBarcode) {
		//프린터연결
		Connection connection = new TcpConnection("192.168.1.154", TcpConnection.DEFAULT_ZPL_TCP_PORT);
		try {
			connection.open(); // 연결

			connection.write(byteBarcode); // 파일인쇄
			
			connection.close(); // 연결 해제
			
		} catch (ConnectionException e) {
			e.printStackTrace();
		} 
	}

	@Override
	public void serialEvent(SerialPortEvent event) {
		try {
	        
	        if(event.isRXCHAR()){//If data is available
	        	int count = event.getEventValue();
	            if(count > 0){//Check bytes count in the input buffer
	                //Read data, if 10 bytes available 
	                    readBuffer = serialPortr.readBytes(count);
	                    
	                    scanningData = new String(readBuffer);
	                    System.out.println(scanningData);
	                    
	                    
	                    barcodeInputField.setText(scanningData);

	                    dataCheckGS1();
	            }
	        }
	    }
	    catch (SerialPortException ex) { 
	        System.out.println(ex);
	    }
		
	}

	private void dataCheckGS1() throws SerialPortException {
		CharSequence st = Character.toString((char) 232);
		CharSequence gs = Character.toString((char) 29);
		
		System.out.println("contains st ? " + scanningData.startsWith(st.toString()));
		System.out.println("contains gs ? " + scanningData.contains(gs));

		
		System.out.println("01088064160555191720022010aaaaaa21bbbbbbbbbbbb".length());
		System.out.println("scanningData = " + scanningData + ", length = " + scanningData.length());
		
		String[] barcodeArr = scanningData.split(gs.toString());
		System.out.println("barcode without ser = " + barcodeArr[0]);
		System.out.println("barcode ser = " + barcodeArr[1]);
		
		
		if(scanningData.startsWith("01")) {
			
			String ai01 = scanningData.substring(scanningData.indexOf("01"), scanningData.indexOf("01")+14);
//			String ai17 = scanningData.substring()
			System.out.println("01 data : " + ai01);
			
			
		}else {
			JOptionPane.showMessageDialog(null, "바코드가 이상해용");
		}
//		scanningData.indexOf("01");
        serialPortr.closePort();//Close serial port

	}
	
}
