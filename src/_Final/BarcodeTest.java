package _Final;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.zebra.sdk.comm.Connection;
import com.zebra.sdk.comm.ConnectionException;
import com.zebra.sdk.comm.TcpConnection;
import com.zebra.sdk.printer.PrinterLanguage;
import com.zebra.sdk.printer.ZebraPrinter;
import com.zebra.sdk.printer.ZebraPrinterFactory;
import com.zebra.sdk.printer.ZebraPrinterLanguageUnknownException;

import fileio.FileModify;

public class BarcodeTest implements ActionListener{
	JButton printBtn, searchBtn;
	JTextField barcodeInputField;
	JTextArea infoArea;
	
	String barcodeNum;	// 바코드 번호 
	
	public BarcodeTest() {	//생성자
		UIsettings();
	}
	
	public static void main(String[] args) {
		new BarcodeTest();
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
		inputPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(10 , 10 , 10 , 10), "BARCODE 바코드"));
	
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
		
	
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		buttonPanel.add(printBtn);
		buttonPanel.add(searchBtn);
		
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
			System.out.println("[ " + e.getActionCommand() + " ]");

			////// 바코드 분석
			if(barcodeNum.indexOf("0") == 0) { //barcodeNum 첫 자리가 0일 때 (indexOf("찾는문자") : 찾는 문자가 있으면 0 반환)
				barcodeNum = barcodeNum.substring(1);
			}
			String country = barcodeNum.substring(0,3);	//국가
			String company = barcodeNum.substring(3,7); //회사
			String prod = barcodeNum.substring(7,12);	//제품코드
			String pCode = prod.substring(0,4);	//함량포함한품목코드
			String packingUnit = String.valueOf(prod.charAt(prod.length()-1));	//포장단위
			
//			if(barcodeNum.equals("8806416055519") || barcodeNum.equals("8806416055502")) {
//				country = "한국";
//				company = "(주)대웅제약";
//			}

			if(country.equals("880")) {
				country = "한국";
			}
			
			if(company.equals("6416")) {
				company = "(주)대웅제약";
			}

			//테스트 바코드 : 8806416055519 / 8806416055502
			infoArea.setText("● 바코드 정보 ●\n");
			infoArea.append("♡ 바코드 : " + barcodeNum + "\n");
			infoArea.append("♡ 국가 : " + country + "\n");
			infoArea.append("♡ 회사 : " + company + "\n");
			infoArea.append("♡ 제품정보 \n");
			if(prod.equals("05551") || prod.equals("05550")) {
				infoArea.append(" - 제품명 : 이지엔6이브연질캡슐\n");
				infoArea.append(" - 전문/일반 : 일반의약품\n");
			}
			infoArea.append(" - 품목코드 : " + prod + "\n");
			infoArea.append("  -> 함량포함한 품목코드 : " + pCode + "\n");
			infoArea.append("  -> 포장단위 : " + packingUnit + "\n");
			
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
	
}
