package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

import print.ImagePrint;

public class ResetUI implements ActionListener {
	protected Image printImage;

	JPanel imgPanel;

	JTextArea infoArea;

	JButton createBtn, resetBtn, imgPrintBtn;
	JComboBox<String> gtinCombo;
	JTextField gtin, useDate, makingNum, serialNum; // 필수 필드
	JLabel img, saveName;

	String barcodeNum;

	// 현재 프로젝트 경로가져오기
	String rootPath = System.getProperty("user.dir");

	public ResetUI() { // 생성자
		UIsettings();
	}

	public static void main(String[] args) {
		new ResetUI();
	}

	// 기본 UI 세팅
	void UIsettings() {
		// 창 크기 지정
		Dimension dim = new Dimension(1024, 800);
		JFrame jf = new JFrame();

		jf.setPreferredSize(dim);

		// ** 화면 구성
		// inputPanel
		JPanel inputPanel = new JPanel(); // 정보 입력 panel
//		inputPanel.setBorder(BorderFactory.createEmptyBorder(10 , 10 , 10 , 10));
		inputPanel.setBorder(
				BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10), "▶ 바코드 생성"));

		inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));

		// 버튼
		JPanel inputBtnPanel = new JPanel();
		inputBtnPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

		createBtn = new JButton("바코드 생성");
		resetBtn = new JButton("리셋");

		inputBtnPanel.add(createBtn);
		inputBtnPanel.add(resetBtn);

		// GTIN 표준코드 (01)
		JPanel gtinPanel = new JPanel();
		gtinPanel.setLayout(new BoxLayout(gtinPanel, BoxLayout.X_AXIS));

		String[] prod = new String[] { "-- 상품명을 선택하세요. --", "이지엔6이브연질캡슐", "표준코드 직접입력" };
		gtinCombo = new JComboBox<String>(prod);

		gtin = new JTextField();
		gtin.setDocument(new JTextFieldLimit(14));
		gtin.setEditable(false); // 수정불가능

		gtinPanel.add(new JLabel("    상   품   명     "));
		gtinPanel.add(gtinCombo);

		JPanel gtinP = new JPanel();
		gtinP.setLayout(new BoxLayout(gtinP, BoxLayout.X_AXIS));

		gtinP.add(new JLabel("표준코드 (01)  "));
		gtinP.add(gtin);

		gtinCombo.addActionListener(this);

		// 유효기한 (17)
		JPanel useDatePanel = new JPanel();
		useDatePanel.setLayout(new BoxLayout(useDatePanel, BoxLayout.X_AXIS));

		useDate = new JTextField();
		useDate.setDocument(new JTextFieldLimit(6));

		useDatePanel.add(new JLabel("유효기한 (17)  "));
		useDatePanel.add(useDate);

		// 제조번호 (10)
		JPanel makingNumPanel = new JPanel();
		makingNumPanel.setLayout(new BoxLayout(makingNumPanel, BoxLayout.X_AXIS));

		makingNum = new JTextField();
		makingNum.setDocument(new JTextFieldLimit(20));

		makingNumPanel.add(new JLabel("제조번호 (10)  "));
		makingNumPanel.add(makingNum);

		// 일련번호 (21)
		JPanel serialNumPanel = new JPanel();
		serialNumPanel.setLayout(new BoxLayout(serialNumPanel, BoxLayout.X_AXIS));

		serialNum = new JTextField();
		serialNum.setDocument(new JTextFieldLimit(20));

		serialNumPanel.add(new JLabel("일련번호 (21)  "));
		serialNumPanel.add(serialNum);

		inputPanel.add(inputBtnPanel);
		inputPanel.add(gtinPanel);

		inputPanel.add(gtinP);

		inputPanel.add(useDatePanel);
		inputPanel.add(makingNumPanel);
		inputPanel.add(serialNumPanel);

		// 미리보기 패널
		JPanel previewPanel = new JPanel(); // 미리보기
		previewPanel.setBorder(new TitledBorder(new LineBorder(Color.black), "미리보기"));
		previewPanel.setPreferredSize(new Dimension(500, 360));
		previewPanel.setBackground(Color.white);
		previewPanel.setLayout(new BoxLayout(previewPanel, BoxLayout.Y_AXIS));

		imgPanel = new JPanel();
		imgPanel.setPreferredSize(new Dimension(500, 300));

		img = new JLabel(); // 이미지 띄울 곳

		imgPanel.add(img, BorderLayout.CENTER);

		createBtn.addActionListener(this);

		JPanel previewBtnPanel = new JPanel();
		previewBtnPanel.setSize(500, 100);
		imgPrintBtn = new JButton("이미지 인쇄");
		previewBtnPanel.add(imgPrintBtn, BorderLayout.PAGE_END);

		previewPanel.add(imgPanel);
		previewPanel.add(previewBtnPanel);

		imgPrintBtn.addActionListener(this);

		// inputPanel과 previewPanel을 담을 leftPanel
		JPanel topPanel = new JPanel();
//		topPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		topPanel.setBorder(new LineBorder(Color.yellow));
		topPanel.setPreferredSize(new Dimension(500, 280));
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));

		topPanel.add(inputPanel);
		topPanel.add(previewPanel);

		// 풀코드 입력할 패널
		JPanel searchPanel = new JPanel();
		searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.X_AXIS));
		searchPanel.setBorder(
				BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10), "▶ 바코드 조회"));

		JTextField fullcode = new JTextField();
		JButton searchBtn = new JButton("조회");
		JButton searchPrintBtn = new JButton("인쇄");

		searchPanel.add(fullcode);
		searchPanel.add(searchBtn);
		searchPanel.add(searchPrintBtn);

		// 바코드 정보를 띄워 줄 패널
		JPanel infoPanel = new JPanel();

		infoArea = new JTextArea();
		infoArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		infoArea.setPreferredSize(new Dimension(1000, 350));
		infoPanel.add(infoArea);

		JPanel bottomPanel = new JPanel();
//		bottomPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		bottomPanel.setBorder(new LineBorder(Color.red));
//		bottomPanel.setPreferredSize(new Dimension(500,500));
		bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));

		bottomPanel.add(searchPanel);
		bottomPanel.add(infoPanel);

		// 화면 구성 **

		// 이벤트 설정
		resetBtn.addActionListener(this);

		jf.add(topPanel, BorderLayout.NORTH);
		jf.add(bottomPanel, BorderLayout.SOUTH);
		jf.pack();
		jf.setResizable(false); // 창의 크기 변경 불가
		jf.setVisible(true); // 보이게
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 창을 닫을 시 프로그램(JFrame)이 정상 종료 되도록 함
		jf.setLocationRelativeTo(null); // 창을 가운데에 띄우기
	}

	// 이벤트 핸들러
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == gtinCombo) { // 표준코드 선택시 이벤트
			if (gtinCombo.getSelectedItem().equals("표준코드 직접입력")) {
				gtin.setEditable(true); // 수정가능
				gtin.setText("");
			} else if (gtinCombo.getSelectedIndex() != 0) {
				gtin.setEditable(false);
				gtin.setText("8806416055519");
			} else {
				gtin.setEditable(false);
				gtin.setText("");
			}

		} else if (e.getSource() == resetBtn) { // 리셋 버튼
			gtinCombo.setSelectedIndex(0);
			gtin.setText("");
			useDate.setText("");
			makingNum.setText("");
			serialNum.setText("");
			img.setIcon(new ImageIcon());
			saveName.setText("");

		} else if (e.getSource() == createBtn) { // 바코드 생성
			saveName = new JLabel();
			saveName.setText("");
			barcodeNum = gtin.getText().trim();

			// 공백검사
			if (inputNullCheck(gtin.getText(), useDate.getText(), makingNum.getText(), serialNum.getText())) { // 자릿수 +
																												// 널체크

				if (barcodeNum.length() < 14) { // 자릿수 확인해서 14자리 미만이면 앞에 0을 붙여줌
					barcodeNum = String.format("%014d", Long.parseLong(barcodeNum));
				}

				// 정규식검사
				if (regCheck(barcodeNum, useDate.getText(), makingNum.getText(), serialNum.getText())) {
					int chkSum = checkDigit(barcodeNum);

					if (String.valueOf(chkSum).equals(barcodeNum.charAt(barcodeNum.length() - 1))) {
						JOptionPane.showMessageDialog(null, "잘못된 바코드 입니다. 다시 입력해주세요.");
						return;
					}

					///////////////////////////////////////////////////////////////////////////////////////////////////////
//				String gtinAI = "01" + barcodeNum;
//				String useDateAI = "17" + useDate.getText();
//				String makingNumAI = "10" + makingNum.getText();
//				String serialNumAI = "21" + serialNum.getText();
//				
//				System.out.println("length -> " + gtinAI.length());
//				System.out.println("length -> " + useDateAI.length());
//				System.out.println("length -> " + makingNumAI.length());
//				System.out.println("length -> " + serialNumAI.length());
//				
				CharSequence st = Character.toString((char) 232);
				CharSequence gs = Character.toString((char) 29);         
				
				String barcode = st + "0108809999999997";

//				byte fncst = (byte) 0xE8;	// 시작문자 (ASCII 232)
//				byte separator = 0x1D; 		// 필드분리자(Separator) (ASCII 29:<GS>)
//				
//				byte[] buffers = barcodeNum.getBytes();
//				
//				System.out.println("buffers 길이 => " + buffers.length);
//				
//				byte[] tempbytes = new byte[buffers.length+1];
//				tempbytes[0] = fncst;
//				for(int i=0; i<buffers.length; i++) {
//					tempbytes[i+1] = buffers[i];
//				}
//				
//				String bt = new String(tempbytes);
//				
//				
//				int newbyteLength = gtinAI.length() + useDateAI.length() + makingNumAI.length() + serialNumAI.length(); 
//				byte[] newbyte = new byte[newbyteLength+2];
//				
//				byte[] gtinbf = gtinAI.getBytes();
//				byte[] useDatebf = useDateAI.getBytes();
//				byte[] makingNumbf = makingNumAI.getBytes();
//				
//				System.out.println(Arrays.toString(makingNumbf));
//				
//				byte[] serialNumbf = serialNumAI.getBytes();
//				
//				newbyte[0] = fncst;
//				System.out.println("0newbyte -> " + Arrays.toString(newbyte));
//				
//				System.arraycopy(gtinbf, 0, newbyte, 1, gtinbf.length);
//				System.out.println("1 g newbyte -> " + Arrays.toString(newbyte));
//				
//				System.arraycopy(useDatebf, 0, newbyte, gtinbf.length+1, useDatebf.length);
//				System.out.println("2 u newbyte -> " + Arrays.toString(newbyte));
//				  
//				
//				System.arraycopy(makingNumbf, 0, newbyte, useDatebf.length+1, makingNumbf.length);
////				System.arraycopy(makingNumbf, 0, newbyte, useDatebf.length+1, makingNumbf.length);
//				System.out.println("3 m newbyte -> " + Arrays.toString(newbyte));
//				
//				
//				System.out.println("sep index " + (gtinbf.length + useDatebf.length + makingNumbf.length +1));
//				newbyte[gtinbf.length + useDatebf.length + makingNumbf.length+1] = separator;
//				System.out.println("4 sep newbyte -> " + Arrays.toString(newbyte));
//				
//				System.arraycopy(serialNumbf, 0, newbyte, gtinbf.length + useDatebf.length + makingNumbf.length+2, serialNumbf.length);
//				System.out.println("5 s newbyte -> " + Arrays.toString(newbyte));
//				

//				for(int i=0; i<gtinbf.length; i++) {
//					newbyte[i+1] = gtinbf[i];
//				}
//				

//				정규식 : /^[!%&)(*+,-./_:;/>/</=/?a-zA-Z0-9]{1,20}$/;
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
					/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

					// 파일명 생성
					SimpleDateFormat format = new SimpleDateFormat("yyMMmmss");
					Date time = new Date();
					String time1 = format.format(time);

					String uuid = UUID.randomUUID().toString();
					String fileName = uuid.substring(0, 10) + time1;

					try {
						createBarcodeImg(barcode, fileName); // 이미지로 저장하고
					} catch (Exception e1) {
						e1.printStackTrace();
					}

					// 라벨에 이미지 띄우기
					ImageIcon icon = new ImageIcon(rootPath + "\\src\\barcodeimg\\" + fileName + ".gif"); // 이미지 아이콘 객체
																											// 생성

					// 이미지를 실제로 갖고 있는 Image객체 뽑아오기
					Image im = icon.getImage(); // 뽑아온 이미지 객체 사이즈를 새롭게 만들기!
					Image im2 = im.getScaledInstance(250, 250, Image.SCALE_DEFAULT);

					// 새로 조절된 사이즈의 이미지(im2)를 가지는 ImageIcon 객체를 다시 생성
					ImageIcon icon2 = new ImageIcon(im2);
					img.setIcon(icon2);

					saveName.setText("저장 경로 : " + rootPath + "\\src\\barcodeimg\\" + fileName + ".gif");
					imgPanel.add(saveName, BorderLayout.CENTER);

				}
			} else {
				return;
			}

		} else if (e.getSource() == imgPrintBtn) { // 이미지 인쇄 버튼
//			System.out.println("img.getGraphics() : " + img.getGraphics());
//			img.printAll(img.getGraphics());
			ImagePrint ip = new ImagePrint(System.getProperty("user.dir") + "\\src\\barcodeimg\\08682f64-620020955.gif");
		}
	}
	public void ImagePrint(String fileName) {
		printImage = new javax.swing.ImageIcon(fileName).getImage(); // 이미지파일생성
		Paper p = new Paper();

		// 출력될 영역 설정
		p.setImageableArea(1 * 72, // Left margin 1 inch //for mm 0.0395
						1.5 * 72, // Top margin 1.5 inches
						6.5 * 72, // Width 6.5 inches
						8 * 72); // Height 8 inches
		PageFormat format = new PageFormat();
		format.setPaper(p); // 페이지포맷 페이지영역설정을 인자로..
		PrinterJob pj = PrinterJob.getPrinterJob();
		pj.setPrintable(new MyPrintable(), format);
		try {
			pj.print();
		} catch (PrinterException pe) {
			System.out.println("Printingfailed : " + pe.getMessage());
		}
	}
	class MyPrintable implements Printable {
		public int print(Graphics g, PageFormat pf, int pageIndex) {
			g.translate((int) (pf.getImageableX()), (int) (pf.getImageableY()));

			if (pageIndex == 0) {
				g.drawImage(printImage, 0, 0, null);
				return Printable.PAGE_EXISTS;
			}
			return Printable.NO_SUCH_PAGE;
		}
	}

	void createBarcodeImg(String gtinCode, String fileName) throws Exception {
		DataMatrix barcode = new DataMatrix();
		
		/*
		 * Data Matrix Valid data char set: ASCII values 0 - 127 in accordance with the
		 * US national version of ISO/IEC 646 ASCII values 128 - 255 in accordance with
		 * ISO 8859-1. These are referred to as extended ASCII.
		 * 
		 */
		barcode.setData(gtinCode);

		barcode.setDataMode(DataMatrix.M_AUTO);

		// if your selected format mode doesnot have enough space to encode your data,
		// the library will choose the right format mode for you automatically.
		barcode.setFormatMode(DataMatrix.F_10X10);

		// Set the processTilde property to true, if you want use the tilde character
		// "~" to specify special characters in the input data. Default is false.
		// 1-byte character: ~ddd (character value from 0 ~ 255)
		// ASCII (with EXT): from ~000 to ~255
		// 2-byte character: ~6ddddd (character value from 0 ~ 65535)
		// Unicode: from ~600000 to ~665535
		// ECI: from ~7000000 to ~7999999
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

		barcode.drawBarcode(rootPath + "\\src\\barcodeimg\\" + fileName + ".gif");
	}

	/**
	 * 빈 칸 확인
	 */
	public boolean inputNullCheck(String gtin, String useDate, String makingNum, String serialNum) {

		if (gtin.equals("") || useDate.equals("") || makingNum.equals("") || serialNum.equals("")) {
			JOptionPane.showMessageDialog(null, "필수 입력 사항을 모두 입력해주세요.");
			return false;
		} else if (useDate.length() != 6) { // 고정 값 확인 (날짜)
			JOptionPane.showMessageDialog(null, "유효기한은 YYMMDD 형식의 6자리 입니다. \n 다시 확인해주세요.");
			return false;
		} else if (gtin.length() != 13 && gtin.length() != 14) {
			System.out.println(gtin.length());
			JOptionPane.showMessageDialog(null, "표준 코드의 자릿수를 다시 확인해주세요.");
			return false;
		} else
			return true;
	}

	/**
	 * Check Digit 검증
	 * 
	 * @param barcodeNum
	 * @return
	 */
	public int checkDigit(String barcodeNum) {

		if (barcodeNum.length() > 13 && String.valueOf(barcodeNum.charAt(0)).equals("0")) {
			barcodeNum = barcodeNum.substring(1);
		}

		if (barcodeNum.length() == 13) {
			ArrayList<Integer> oddList = new ArrayList<Integer>();
			ArrayList<Integer> evenList = new ArrayList<Integer>();

			for (int i = 1; i <= 12; i++) {
				if (i % 2 == 0) { // 인덱스가 짝수면
					evenList.add(Integer.parseInt(String.valueOf(barcodeNum.charAt(i - 1))));
				} else {
					oddList.add(Integer.parseInt(String.valueOf(barcodeNum.charAt(i - 1))));
				}
			}

			int sum = 0;
			for (Integer odd : oddList) {
				sum += odd;
			}
			for (Integer even : evenList) {
				sum += even * 3;
			}
			int chkSum = 0;
			chkSum = 10 - (sum % 10);
			return chkSum;
		} else {
			return 0;
		}

	};

	/**
	 * 정규식 체크
	 */
	public boolean regCheck(String gtin, String useDate, String makingNum, String serialNum) {
		/*
		 * (01) 숫자만 14자리 고정 (17) 숫자만 6자리 YYMMDD 고정 (10) 영문+숫자 20자 까지 +가용특수문자 가변길이 (21)
		 * 영문+숫자 20자 까지 +가용특수문자 가변길이
		 * 
		 * 특수문자)) ! " % & ' ( ) * + , - . / _ : ; < = > ?
		 * 
		 */

		Pattern reg01 = Pattern.compile("^[0-9]{14}$");
		Pattern reg17 = Pattern.compile("^[0-9]{6}$");
		Pattern reg1021 = Pattern.compile("^[!\"%&'()*+,-./_:;<=>?a-zA-Z0-9]{1,20}$");

		Matcher ma01 = reg01.matcher(gtin);
		Matcher ma17 = reg17.matcher(useDate);
		Matcher ma10 = reg1021.matcher(makingNum);
		Matcher ma21 = reg1021.matcher(serialNum);

		if (!ma01.matches()) {
			JOptionPane.showMessageDialog(null, "표준코드는 숫자만 입력가능합니다.");
			return false;
		} else if (!ma17.matches()) {
			JOptionPane.showMessageDialog(null, "유효기한은 YYMMDD형태로 숫자만 입력가능합니다.");
			return false;
		} else if (!ma10.matches()) {
			JOptionPane.showMessageDialog(null, "제조번호 형식을 다시 확인해주세요.");
			return false;
		} else if (!ma21.matches()) {
			JOptionPane.showMessageDialog(null, "일련번호 형식을 다시 확인해주세요.");
			return false;
		} else {
			return true;
		}
	}
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

		if ((getLength() + str.length()) <= limit) {
			if (toUppercase) {
				str = str.toUpperCase();
			}
			super.insertString(offset, str, attr);
		}
	}
}
