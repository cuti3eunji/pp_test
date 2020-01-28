package _Final;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class BarcodeTest implements ActionListener{
	JButton printBtn, searchBtn;
	JTextField printInputField, searchInputField;
	JTextArea infoArea;
	
	public BarcodeTest() {
		UIsettings();
	}
	
	public static void main(String[] args) {
		new BarcodeTest();
	}
	
	//기본 UI 세팅 
	void UIsettings(){
		//창 크기 지정
		Dimension dim = new Dimension(500,300);
		JFrame jf = new JFrame();
		
		jf.setPreferredSize(dim);
		
		//** 화면 구성 
		// 프린트
		JPanel printPanel = new JPanel();	//프린트용 panel
		printPanel.setBorder(BorderFactory.createEmptyBorder(10 , 10 , 10 , 10));
		printPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(10 , 10 , 10 , 10), "바코드 출력하기"));
	
		printBtn = new JButton("프린트");	//프린트 버튼
		printBtn.setPreferredSize(new Dimension(100,20));
		printInputField = new JTextField(14);
//		printInputField.setColumns(14);
		 
		
		printPanel.setLayout(new BoxLayout(printPanel, BoxLayout.X_AXIS));
		printPanel.add(printInputField);
		printPanel.add(printBtn);
		
		
		// 바코드 정보 검색
		JPanel searchPanel = new JPanel();
		searchPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(10 , 10 , 10 , 10), "바코드 정보 확인"));
	
		searchBtn = new JButton("확인"); //정보 검색 버튼
		searchBtn.setPreferredSize(new Dimension(100,20));
		searchInputField = new JTextField(14);
	
		searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.X_AXIS));
		searchPanel.add(searchInputField);
		searchPanel.add(searchBtn);
		
		//바코드 정보 출력
		JPanel infoPanel = new JPanel();
		infoArea = new JTextArea();
		infoArea.setBorder(BorderFactory.createEmptyBorder(10 , 10 , 10 , 10));
		infoArea.setPreferredSize(new Dimension(100,100));
	
		infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
		infoPanel.add(infoArea);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.add(printPanel);
		mainPanel.add(searchPanel);
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
		if(e.getActionCommand().equals("프린트")) {	// 바코드 프린트 하기 
			System.out.println(e.getActionCommand());
			System.out.println(printInputField.getColumns());
			
			// 바코드 셋팅 파일(.prn)읽어오고
			
			// 코드 자릿수 확인해서 
			String pif = printInputField.getText().trim();
			long inputCode = Long.parseLong(pif);
			
			if(pif.length() < 14 ) {	// 자릿수 확인해서 14자리 미만이면 앞에 0을 붙여줌
				pif = String.format("%014d", inputCode);
			}
			System.out.println(pif);
			// 읽어온 셋팅 파일에 코드부분 inputCode로 세팅해서 저장
			
			// 자바 소켓 통신으로 라벨 프린터와 연결해서
			
		}else if(e.getActionCommand().equals("확인"))	{// 바코드 정보 찾아서 TextArea에 띄우기 
			
			String barcodeNum = searchInputField.getText();	//인식된 바코드를 넣을 변수
			
			////// 바코드 분석
			
			String info = "바코드 정보정보정보";	//받아온 바코드 정보를 저장할 변수
			infoArea.setText(info + "\n" + barcodeNum);
		}
	}
	
	
	
}
