package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class ResetUI {
	JButton printBtn, searchBtn;
	JTextField barcodeInputField;
	JTextArea infoArea;
	
	String barcodeNum;	// 바코드 번호 
	
	public ResetUI() {	//생성자
		UIsettings();
	}
	
	public static void main(String[] args) {
		try {
//		    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
//		    UIManager.setLookAndFeel("com.sun.java.swing.plaf.moti.MotiLookAndFeel");
//		    UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		} catch (Exception e) {
		    /*
		        ClassNotFoundException
		        InstantiationException
		        IllegalAccessException
		        UnsupportedLookAndFeelException e
		     */
		}
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
		inputPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(10 , 10 , 10 , 10), "▶ 바코드 정보를 입력하세용^~^"));
	
		barcodeInputField = new JTextField(14);
		
		inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
		
		//버튼
		JPanel inputBtnPanel = new JPanel();
		inputBtnPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		JButton previewBtn = new JButton("미리보기");
		JButton resetBtn = new JButton("리셋");
		
		inputBtnPanel.add(previewBtn);
		inputBtnPanel.add(resetBtn);
		
		//GTIN 표준코드 (01)
		JPanel gtinPanel = new JPanel();
		gtinPanel.setLayout(new BoxLayout(gtinPanel, BoxLayout.X_AXIS));
		
		String[] prod = new String[] {"-- 상품명을 선택하세요. --", "이지엔6이브연질캡슐", "표준코드 직접입력"};
		JComboBox<String> gtinCombo = new JComboBox<String>(prod);

		final JTextField gtin = new JTextField();
		gtin.setEditable(false);	//수정불가능
		
		gtinPanel.add(new JLabel("    상   품   명     "));
		gtinPanel.add(gtinCombo);
		
		JPanel gtinP = new JPanel();
		gtinP.setLayout(new BoxLayout(gtinP, BoxLayout.X_AXIS));
	
		gtinP.add(new JLabel("표준코드 (01)  "));
		gtinP.add(gtin);
		
		//표준코드 선택시 이벤트
		gtinCombo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				if(gtinCombo.getSelectedItem().equals("표준코드 직접입력")) {
					gtin.setEditable(true); //수정가능
				}else {
					gtin.setEditable(false);
					gtin.setText("");
				}
			}
		});
		
		
		//유효기한 (17)
		JPanel useDatePanel = new JPanel();
		useDatePanel.setLayout(new BoxLayout(useDatePanel, BoxLayout.X_AXIS));
		
		JTextField useDate = new JTextField();
		
		useDatePanel.add(new JLabel("유효기한 (17)  "));
		useDatePanel.add(useDate);
		
		//제조번호 (10)
		JPanel makingNumPanel = new JPanel();
		makingNumPanel.setLayout(new BoxLayout(makingNumPanel, BoxLayout.X_AXIS));

		JTextField makingNum = new JTextField();
		
		makingNumPanel.add(new JLabel("제조번호 (10)  "));
		makingNumPanel.add(makingNum);

		//일련번호 (21)
		JPanel serialNumPanel = new JPanel();
		serialNumPanel.setLayout(new BoxLayout(serialNumPanel, BoxLayout.X_AXIS));
		
		JTextField serialNum = new JTextField();

		serialNumPanel.add(new JLabel("일련번호 (21)  "));
		serialNumPanel.add(serialNum);
		
		inputPanel.add(inputBtnPanel);
		inputPanel.add(gtinPanel);

		inputPanel.add(gtinP);

		inputPanel.add(useDatePanel);
		inputPanel.add(makingNumPanel);
		inputPanel.add(serialNumPanel);
		
		
		//미리보기 패널 
		JPanel previewPanel = new JPanel();	//미리보기
		previewPanel.setBorder(new TitledBorder(new LineBorder(Color.black), "미리보기"));
		previewPanel.setPreferredSize(new Dimension(500,500));
		previewPanel.setBackground(Color.white);
		previewPanel.setLayout(new BoxLayout(previewPanel, BoxLayout.Y_AXIS));
		
		JPanel imgOutput = new JPanel();// 이미지 띄울 곳
		
		
		
		JPanel preBtnPanel = new JPanel();//미리보기안에 버튼들
		preBtnPanel.setLayout(new BoxLayout(preBtnPanel, BoxLayout.X_AXIS));
//		preBtnPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

		JButton imgSaveBtn = new JButton("이미지로 저장");
		JButton imgPrintBtn = new JButton("이미지 인쇄");

		preBtnPanel.add(imgSaveBtn);
		preBtnPanel.add(imgPrintBtn);
		
		previewPanel.add(imgOutput);
		previewPanel.add(preBtnPanel);
		
		
		
		//inputPanel과 previewPanel을 담을 leftPanel
		JPanel leftPanel = new JPanel();
		leftPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		leftPanel.setPreferredSize(new Dimension(500,500));
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		
		leftPanel.add(inputPanel);
		leftPanel.add(previewPanel);
		
		
		// 풀코드 입력할 패널
		JPanel searchPanel = new JPanel();
		searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.X_AXIS));
		
		JTextField fullcode = new JTextField();
		JButton searchBtn = new JButton("조회");
		JButton searchPrintBtn = new JButton("인쇄");
		
//		searchPanel.add(new JLabel("조회"));
		searchPanel.add(fullcode);
		searchPanel.add(searchBtn);
		searchPanel.add(searchPrintBtn);
		
		
		// 바코드 정보를 띄워 줄 패널
		JPanel infoPanel = new JPanel();
		
		infoArea = new JTextArea();
		infoArea.setBorder(BorderFactory.createEmptyBorder(10 , 10 , 10 , 10));
		infoArea.setPreferredSize(new Dimension(500,500));
		infoPanel.add(infoArea);
		
		JPanel rightPanel = new JPanel();
		rightPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		rightPanel.setPreferredSize(new Dimension(500,500));
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
		
		rightPanel.add(searchPanel);
		rightPanel.add(infoPanel);
		
		
		// 화면 구성 **
		
		//이벤트 설정
//		imgPrintBtn.addActionListener(this);
//		searchBtn.addActionListener(this);
		
		//리셋버튼 클릭시 이벤트
		resetBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				gtinCombo.setSelectedIndex(0);
				gtin.setText("");
				useDate.setText("");
				makingNum.setText("");
				serialNum.setText("");
				
//				if(gtinCombo.getSelectedIndex() == 0 && gtin.isEditable()) {
//					System.out.println("콤보박스 0번째가 아니고 밑에코드가 열려있을때");
//				}
			}
		});
		
		jf.add(leftPanel, BorderLayout.WEST);
		jf.add(rightPanel, BorderLayout.EAST);
		jf.pack();
		jf.setResizable(false);	//창의 크기 변경 불가
		jf.setVisible(true);	//보이게 
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//창을 닫을 시 프로그램(JFrame)이 정상 종료 되도록 함
		jf.setLocationRelativeTo(null);		//창을 가운데에 띄우기
	}

	
}
