package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import com.onbarcode.barcode.DataMatrix;
import com.onbarcode.barcode.IBarcode;


public class ResetUI implements ActionListener{
	
	JPanel imgPanel;
	
	JTextArea infoArea;

	JButton createBtn, resetBtn, imgPrintBtn;
	JComboBox<String> gtinCombo;
	JTextField gtin, useDate, makingNum, serialNum;	//필수 필드
	JLabel img;
	
	String barcodeNum;
	
	// 현재 프로젝트 경로가져오기
    String rootPath = System.getProperty("user.dir");
	
	public ResetUI() {	//생성자
		UIsettings();
	}
	
	public static void main(String[] args) {
		new ResetUI();
	}
	
	//기본 UI 세팅 
	void UIsettings(){
		//창 크기 지정
		Dimension dim = new Dimension(1024,600);
		JFrame jf = new JFrame();
		
		jf.setPreferredSize(dim);
		
		//** 화면 구성 
		// inputPanel
		JPanel inputPanel = new JPanel();	//정보 입력 panel
//		inputPanel.setBorder(BorderFactory.createEmptyBorder(10 , 10 , 10 , 10));
		inputPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(10 , 10 , 10 , 10), "▶ 바코드 생성"));
	
		inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
		
		//버튼
		JPanel inputBtnPanel = new JPanel();
		inputBtnPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		createBtn = new JButton("바코드 생성");
		resetBtn = new JButton("리셋");
		
		inputBtnPanel.add(createBtn);
		inputBtnPanel.add(resetBtn);
		
		//GTIN 표준코드 (01)
		JPanel gtinPanel = new JPanel();
		gtinPanel.setLayout(new BoxLayout(gtinPanel, BoxLayout.X_AXIS));
		
		String[] prod = new String[] {"-- 상품명을 선택하세요. --", "이지엔6이브연질캡슐", "표준코드 직접입력"};
		gtinCombo = new JComboBox<String>(prod);

		gtin = new JTextField();
		gtin.setDocument(new JTextFieldLimit(14));
		gtin.setEditable(false);	//수정불가능
		
		gtinPanel.add(new JLabel("    상   품   명     "));
		gtinPanel.add(gtinCombo);
		
		JPanel gtinP = new JPanel();
		gtinP.setLayout(new BoxLayout(gtinP, BoxLayout.X_AXIS));
	
		gtinP.add(new JLabel("표준코드 (01)  "));
		gtinP.add(gtin);
		
		gtinCombo.addActionListener(this);
		
		
		//유효기한 (17)
		JPanel useDatePanel = new JPanel();
		useDatePanel.setLayout(new BoxLayout(useDatePanel, BoxLayout.X_AXIS));
		
		useDate = new JTextField();
		useDate.setDocument(new JTextFieldLimit(6));
		
		useDatePanel.add(new JLabel("유효기한 (17)  "));
		useDatePanel.add(useDate);
		
		//제조번호 (10)
		JPanel makingNumPanel = new JPanel();
		makingNumPanel.setLayout(new BoxLayout(makingNumPanel, BoxLayout.X_AXIS));

		makingNum = new JTextField();
		
		makingNumPanel.add(new JLabel("제조번호 (10)  "));
		makingNumPanel.add(makingNum);

		//일련번호 (21)
		JPanel serialNumPanel = new JPanel();
		serialNumPanel.setLayout(new BoxLayout(serialNumPanel, BoxLayout.X_AXIS));
		
		serialNum = new JTextField();

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
		previewPanel.setPreferredSize(new Dimension(500,360));
		previewPanel.setBackground(Color.white);
		previewPanel.setLayout(new BoxLayout(previewPanel, BoxLayout.Y_AXIS));
		
		imgPanel = new JPanel(); 
		imgPanel.setPreferredSize(new Dimension(500,300));
		
		img = new JLabel(); // 이미지 띄울 곳
		
		imgPanel.add(img, BorderLayout.CENTER);
		
		createBtn.addActionListener(this);
		
		JPanel previewBtnPanel = new JPanel();
		previewBtnPanel.setSize(500,100);
		imgPrintBtn = new JButton("이미지 인쇄");
		previewBtnPanel.add(imgPrintBtn, BorderLayout.PAGE_END);
		
		previewPanel.add(imgPanel);
		previewPanel.add(previewBtnPanel);
		
		imgPrintBtn.addActionListener(this);
		
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
		searchPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(0 , 0 , 0 , 0), "▶ 바코드 조회"));

		JTextField fullcode = new JTextField();
		JButton searchBtn = new JButton("조회");
		JButton searchPrintBtn = new JButton("인쇄");
		
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
		resetBtn.addActionListener(this);
		
		jf.add(leftPanel, BorderLayout.WEST);
		jf.add(rightPanel, BorderLayout.EAST);
		jf.pack();
		jf.setResizable(false);	//창의 크기 변경 불가
		jf.setVisible(true);	//보이게 
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//창을 닫을 시 프로그램(JFrame)이 정상 종료 되도록 함
		jf.setLocationRelativeTo(null);		//창을 가운데에 띄우기
	}
	
	void createBarcodeImg(String gtinCode, String fileName) throws Exception {
		DataMatrix barcode = new DataMatrix();
		
		/*
		   Data Matrix Valid data char set:
		        ASCII values 0 - 127 in accordance with the US national version of ISO/IEC 646
		            ASCII values 128 - 255 in accordance with ISO 8859-1. These are referred to as extended ASCII.
		
		*/
		barcode.setData(gtinCode);
		
		barcode.setDataMode(DataMatrix.M_AUTO);
		
		// if your selected format mode doesnot have enough space to encode your data,
		// the library will choose the right format mode for you automatically.
		barcode.setFormatMode(DataMatrix.F_10X10);
		
		//  Set the processTilde property to true, if you want use the tilde character "~" to specify special characters in the input data. Default is false.
		//  1-byte character: ~ddd (character value from 0 ~ 255)
		//  ASCII (with EXT): from ~000 to ~255
		//  2-byte character: ~6ddddd (character value from 0 ~ 65535)
		//  Unicode: from ~600000 to ~665535
		//  ECI: from ~7000000 to ~7999999
		barcode.setProcessTilde(true);
		
		// Data Matrix Unit of Measure, pixel, cm, or inch
		barcode.setUom(IBarcode.UOM_PIXEL);
		// Data Matrix barcode bar module width (X) in pixel
		barcode.setX(3f);
		
		barcode.setLeftMargin(10f);
		barcode.setRightMargin(10f);
		barcode.setTopMargin(10f);
		barcode.setBottomMargin(10f);
		// barcode image resolution in dpi
		barcode.setResolution(72);
		
//    	String outputFile = dir + fileName+"."+fileFormat;
//    	System.out.println(outputFile);
//    	
//		barcode.drawBarcode(outputFile);
		
//		barcode.drawBarcode("C:\\Users\\Metabiz0350\\Desktop\\barcodeImage\\datamatrix"+ num + ".gif");
		
		barcode.drawBarcode(rootPath+"\\src\\barcodeimg\\"+ fileName + ".gif");
	}
	
	
	
	/**
	 * 
	 */
	public boolean inputCheck(String gtin, String useDate, String makingNum, String serialNum) {
		
//		//빈 칸 확인
//		if(gtin.equals("") || useDate.equals("") || makingNum.equals("") || serialNum.equals("")) {
//			JOptionPane.showMessageDialog(null, "필수 입력 사항을 모두 입력해주세요.");
//			return false;
//		}else if(useDate.length() != 6) {	// 고정 값 확인 (날짜)
//			JOptionPane.showMessageDialog(null, "유효기한은 6자리 입니다. 다시 확인해주세요.");
//			return false;
//		}else if(gtin.length() != 13 && gtin.length() != 14 ) {
//			System.out.println(gtin.length());
//			
//			JOptionPane.showMessageDialog(null, "잘못된 바코드 입니다. 다시 확인해주세요.");
//			return false;
//		}
//		else 
			return true;
		
	}
	
	//이벤트 핸들러
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == gtinCombo ) { //표준코드 선택시 이벤트
			if(gtinCombo.getSelectedItem().equals("표준코드 직접입력")) {
				gtin.setEditable(true); //수정가능
				gtin.setText("");
			}else if(gtinCombo.getSelectedIndex() != 0){
				gtin.setEditable(false);
				gtin.setText("8806416055519");
			}else {
				gtin.setEditable(false);
				gtin.setText("");
			}
			
		}else if(e.getSource() == resetBtn ) {	//리셋 버튼
			gtinCombo.setSelectedIndex(0);
			gtin.setText("");
			useDate.setText("");
			makingNum.setText("");
			serialNum.setText("");
			img.setIcon(new ImageIcon());
			
		}else if(e.getSource() == createBtn) {	//바코드 생성
			barcodeNum = gtin.getText().trim();
			
			if(barcodeNum.length() < 14 ) {	// 자릿수 확인해서 14자리 미만이면 앞에 0을 붙여줌
				barcodeNum = "01" + String.format("%014d", Long.parseLong(barcodeNum));
			}
			
			if(inputCheck(gtin.getText(), useDate.getText(), makingNum.getText(), serialNum.getText())) {	//자릿수 + 널체크
				System.out.println(barcodeNum.length());
				
				int chkSum = checkDigit(barcodeNum);
//				if(chkSum != Integer.parseInt(String.valueOf(barcodeNum.charAt(barcodeNum.length()-1)))) {
//					JOptionPane.showMessageDialog(null, "잘못된 바코드 입니다. 다시 입력해주세요.");
//					return;
//				}
				
//				String barcodeData = gtin.getText();
				
				System.out.println(barcodeNum);
				
				byte separator = 0x1D; 
				byte fncst = (byte) 0xE8;
				
				byte[] buffers = barcodeNum.getBytes();
				
				byte[] tempbytes = new byte[buffers.length+1];
				tempbytes[0] = separator;
				for(int i=0; i<buffers.length; i++) {
					tempbytes[i+1] = buffers[i];
				}
				
				String bt = new String(tempbytes);
				
				
//				String str1 = “Hello World!”;
//				
//				// 변수 str1의 바이트 값
//				// 72101108108111328711111410810033
//				byte[] buffers = str1.getBytes(); 
//
//				// 바이트 배열 자체의 문자열 값
//				// [B@ca0b6
//				String buffersArrayString = buffers.toString();
//				
//				// 바이트 배열을 문자열로 변환한 값
//				// Hello World!
//				String str2 = new String(buffers);



				//파일명 생성
				SimpleDateFormat format = new SimpleDateFormat ("yyMMmmss");
				Date time = new Date();
				String time1 = format.format(time);
				
				String uuid = UUID.randomUUID().toString();
				String fileName = uuid.substring(0,10) + time1;
				
		    	try {
					createBarcodeImg(bt, fileName);	// 이미지로 저장하고
				} catch (Exception e1) {
					e1.printStackTrace();
				}
		    	
		    	System.out.println(fileName);
		    	
		    	//라벨에 이미지 띄우기
				ImageIcon icon = new ImageIcon(rootPath + "\\src\\barcodeimg\\"+ fileName + ".gif"); //이미지 아이콘 객체 생성

				//이미지를 실제로 갖고 있는 Image객체 뽑아오기
				Image im = icon.getImage(); //뽑아온 이미지 객체 사이즈를 새롭게 만들기!
				Image im2 = im.getScaledInstance(250, 250, Image.SCALE_DEFAULT);

				//새로 조절된 사이즈의 이미지(im2)를 가지는 ImageIcon 객체를 다시 생성
				ImageIcon icon2 = new ImageIcon(im2);
				img.setIcon(icon2);
				imgPanel.add(new JLabel("저장 경로 : " + rootPath + "\\src\\barcodeimg\\"+ fileName + ".gif"), BorderLayout.CENTER);

				
				
			}else {
				return;
			}
			
		}else if(e.getSource() == imgPrintBtn) { //이미지 인쇄 버튼
			System.out.println("img.getGraphics() : " + img.getGraphics());
			img.printAll(img.getGraphics());
		}
	}
	
	
	/**
	 * Check Digit 검증
	 * @param barcodeNum
	 * @return
	 */
	public int checkDigit(String barcodeNum) {
		
		if(barcodeNum.length() > 13 && String.valueOf(barcodeNum.charAt(0)).equals("0")) {
			barcodeNum = barcodeNum.substring(1);
		}else if(barcodeNum.length() == 13) {
			ArrayList<Integer> oddList = new ArrayList<Integer>();
			ArrayList<Integer> evenList = new ArrayList<Integer>();
			
			for(int i=1; i<=12; i++) {
				if(i%2 == 0) { //인덱스가 짝수면
					evenList.add(Integer.parseInt(String.valueOf(barcodeNum.charAt(i-1))));
				}else {
					oddList.add(Integer.parseInt(String.valueOf(barcodeNum.charAt(i-1))));
				}
			}
			
			int sum = 0;
			for(Integer odd : oddList) {
				sum += odd;
			}
			for(Integer even : evenList) {
				sum += even*3;
			}
			int chkSum = 0;
			chkSum = 10-(sum%10);
			return chkSum;
		}
		return 0;
		
	};
	
}

//글자 수 제한
class JTextFieldLimit extends PlainDocument {
	private int limit;
	private boolean toUppercase = false;

	JTextFieldLimit(int limit) {
		super();
		this.limit = limit;
	}

	JTextFieldLimit(int limit, boolean upper) {
		super();
		this.limit = limit;
		this.toUppercase = upper;
	}

	public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
	      if (str == null) {
	         return;
	      }

	      if ( (getLength() + str.length()) <= limit) {
	         if (toUppercase) {
	            str = str.toUpperCase();
	         }
	         super.insertString(offset, str, attr);
	      }
	   }
}
