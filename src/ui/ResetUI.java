package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ResetUI {
	JButton printBtn, searchBtn;
	JTextField barcodeInputField;
	JTextArea infoArea;
	
	String barcodeNum;	// 바코드 번호 
	
	public ResetUI() {	//생성자
		UIsettings();
	}
	
	public static void main(String[] args) {
		new ResetUI();
	}
	
	//기본 UI 세팅 
	void UIsettings(){
		//창 크기 지정
		Dimension dim = new Dimension(1024,768);
		JFrame jf = new JFrame();
		
		jf.setPreferredSize(dim);
		
		//** 화면 구성 
		// inputPanel
		JPanel inputPanel = new JPanel();	//정보 입력 panel
//		inputPanel.setBorder(BorderFactory.createEmptyBorder(10 , 10 , 10 , 10));
		inputPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(10 , 10 , 10 , 10), "바코드 정보를 입력하세용^~^"));
	
		barcodeInputField = new JTextField(14);
		
		inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.X_AXIS));
		
		JLabel gtin = new JLabel("표준코드");
		JLabel useDate = new JLabel("유효기한");
		JLabel gtin = new JLabel("제조번호");
		JLabel gtin = new JLabel("일련번호");
		
		
		
		
		
		
		
		
		
		
		
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
		
		JTabbedPane tabbedPanel = new JTabbedPane();
		tabbedPanel.setPreferredSize(new Dimension(100,300));
		tabbedPanel.add("바코드 생성", inputPanel);
		tabbedPanel.add("바코드 검색", buttonPanel);
		tabbedPanel.add(infoPanel);

//		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		// 화면 구성 **
		
		//이벤트 설정
		printBtn.addActionListener(e -> System.out.println("프린트버튼클릭"));
		searchBtn.addActionListener(e -> System.out.println("정보확인버튼클릭"));
		
		jf.add(tabbedPanel, BorderLayout.CENTER);
		jf.pack();
		jf.setVisible(true);	//보이게 
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//창을 닫을 시 프로그램(JFrame)이 정상 종료 되도록 함
		jf.setLocationRelativeTo(null);		//창을 가운데에 띄우기
	}

	
}
