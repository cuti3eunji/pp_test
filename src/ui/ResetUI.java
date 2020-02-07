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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
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
import javax.swing.JSpinner;
import javax.swing.JSpinner.DateEditor;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import com.onbarcode.barcode.DataMatrix;
import com.onbarcode.barcode.IBarcode;
import com.zebra.sdk.comm.Connection;
import com.zebra.sdk.comm.ConnectionException;
import com.zebra.sdk.comm.TcpConnection;

import fileio.FileModify;
import javafx.scene.layout.Border;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
import print.ImagePrintTest;

public class ResetUI implements ActionListener {
	Date today = new Date();	//오늘 날짜

	Image printImage;

	JPanel imgPanel;

	JTextArea infoArea;
	JButton createBtn, resetBtn, imgPrintBtn, lblPrintBtn, scanningBtn, searchBtn;
	JComboBox<String> gtinCombo;
	JTextField gtin, exp, lot, serial; // 필수 필드
	JTextField fullcode;
	JLabel img, saveName;

	String barcodeNum,fileName;
	
	JDatePanelImpl datePanel;
	JDatePickerImpl datePicker;

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
		Dimension dim = new Dimension(1024, 700);
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
		inputBtnPanel.setLayout(new BoxLayout(inputBtnPanel, BoxLayout.Y_AXIS));
		inputBtnPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

		createBtn = new JButton("생성하기");
		resetBtn = new JButton("다시입력");

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
		JPanel expPanel = new JPanel();
		expPanel.setLayout(new BoxLayout(expPanel, BoxLayout.X_AXIS));

		exp = new JTextField();
		exp.setDocument(new JTextFieldLimit(6));
		exp.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				datePicker.getModel().setValue(null);
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		//JDatePicker : http://www.codejava.net/java-se/swing/how-to-use-jdatepicker-to-display-calendar-component 
		UtilDateModel model = new UtilDateModel();
		datePanel = new JDatePanelImpl(model);
		datePicker = new JDatePickerImpl(datePanel);
		
		datePicker.addActionListener(this);
		expPanel.add(new JLabel("유효기한 (17)  "));
		expPanel.add(datePicker);
		expPanel.add(exp);

		// 제조번호 (10)
		JPanel lotPanel = new JPanel();
		lotPanel.setLayout(new BoxLayout(lotPanel, BoxLayout.X_AXIS));

		lot = new JTextField();
		lot.setDocument(new JTextFieldLimit(20));

		lotPanel.add(new JLabel("제조번호 (10)  "));
		lotPanel.add(lot);

		// 일련번호 (21)
		JPanel serialPanel = new JPanel();
		serialPanel.setLayout(new BoxLayout(serialPanel, BoxLayout.X_AXIS));

		serial = new JTextField();
		serial.setDocument(new JTextFieldLimit(20));

		serialPanel.add(new JLabel("일련번호 (21)  "));
		serialPanel.add(serial);

		inputPanel.add(gtinPanel);

		inputPanel.add(gtinP);

		inputPanel.add(expPanel);
		inputPanel.add(lotPanel);
		inputPanel.add(serialPanel);

		// 미리보기 패널
		JPanel previewPanel = new JPanel(); // 미리보기
		previewPanel.setBorder(new TitledBorder(new LineBorder(Color.black), "미리보기"));
		previewPanel.setPreferredSize(new Dimension(400, 300));
		previewPanel.setBackground(Color.white);
		previewPanel.setLayout(new BoxLayout(previewPanel, BoxLayout.Y_AXIS));

		imgPanel = new JPanel();
		imgPanel.setPreferredSize(new Dimension(200, 150));

		img = new JLabel(); // 이미지 띄울 곳

		imgPanel.add(img, BorderLayout.PAGE_START);

		createBtn.addActionListener(this);

		JPanel previewBtnPanel = new JPanel();
		previewBtnPanel.setSize(500, 100);
		imgPrintBtn = new JButton("이미지인쇄");
		lblPrintBtn = new JButton("라벨프린터인쇄");
		previewBtnPanel.add(imgPrintBtn, BorderLayout.PAGE_END);
		previewBtnPanel.add(lblPrintBtn, BorderLayout.PAGE_END);

		previewPanel.add(imgPanel);
		previewPanel.add(previewBtnPanel);

		imgPrintBtn.addActionListener(this);
		lblPrintBtn.addActionListener(this);

		// inputPanel과 previewPanel을 담을 leftPanel
		JPanel topPanel = new JPanel();
		topPanel.setPreferredSize(new Dimension(500, 250));
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));

		topPanel.add(inputPanel);
		topPanel.add(inputBtnPanel);
		topPanel.add(previewPanel);

		// 풀코드 입력할 패널
		JPanel searchPanel = new JPanel();
		searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.X_AXIS));
		searchPanel.setBorder(
				BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10), "▶ 바코드 조회 (스캐너를 사용해주세요)"));

		fullcode = new JTextField();
		fullcode.setEditable(false);

		scanningBtn = new JButton("스캐닝");
		searchBtn = new JButton("조회");
		
		scanningBtn.addActionListener(this);
		searchBtn.addActionListener(this);
		
		searchPanel.add(fullcode);
		searchPanel.add(scanningBtn);
		searchPanel.add(searchBtn); 

		// 바코드 정보를 띄워 줄 패널
		JPanel infoPanel = new JPanel();

		infoArea = new JTextArea();
		infoArea.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		infoArea.setPreferredSize(new Dimension(1000, 350));
		infoPanel.add(infoArea);

		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));

		bottomPanel.add(searchPanel);
		bottomPanel.add(infoPanel);

		// 화면 구성 **

		// 이벤트 설정
		resetBtn.addActionListener(this);

		jf.add(topPanel, BorderLayout.NORTH);
		jf.add(bottomPanel, BorderLayout.CENTER);
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
			} else if (gtinCombo.getSelectedItem().equals("이지엔6이브연질캡슐")) {
				gtin.setEditable(false);
				gtin.setText("8806416055519");
			} else {
				gtin.setEditable(false);
				gtin.setText("");
			}

		} else if(e.getSource() == datePanel) { //날짜 선택시 
			exp.setText(""); //쓸수있는거 지우고
			
			Date expDate = (Date) datePicker.getModel().getValue();	//JDatePicker로 선택한 날짜
			
			int compare = dateCheck(today, expDate);
			
			if(compare > 0 || compare == 0) {
				JOptionPane.showMessageDialog(null, "현재 이후 날짜만 입력 할 수 있습니다. 다시 입력해주세요.");
				datePicker.getModel().setValue(null);
				exp.setText("");
				return;
			}
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
			exp.setText(sdf.format(expDate)); //날짜세팅
		
		} else if (e.getSource() == resetBtn) { // 리셋 버튼
				saveName = new JLabel();

				gtinCombo.setSelectedIndex(0);
				gtin.setText("");
				exp.setText("");
				lot.setText("");
				serial.setText("");
				img.setIcon(new ImageIcon());
				saveName.setText("");
				datePicker.getModel().setValue(null);

		} else if (e.getSource() == createBtn) { // 바코드 생성
			saveName = new JLabel();
			saveName.setText("");
			
			//유효기간 필드가 비어있으면
			if(exp.getText() == null || exp.getText().equals("") || datePicker.getModel().getValue() == null) {
				JOptionPane.showMessageDialog(null, "유효기한은 YYMMDD형식의 6자리입니다.");
				return;
			}
			barcodeNum = gtin.getText().trim();
			

			// 공백검사
			if (inputNullCheck(gtin.getText(), exp.getText(), lot.getText(), serial.getText())) { // 자릿수 + 널체크

				if (barcodeNum.length() < 14) { // 자릿수 확인해서 14자리 미만이면 앞에 0을 붙여줌
					barcodeNum = String.format("%014d", Long.parseLong(barcodeNum));
				}

				// 정규식검사
				if (regCheck(barcodeNum, exp.getText(), lot.getText(), serial.getText())) {
					int chkSum = checkDigit(barcodeNum);

					if (String.valueOf(chkSum).equals(barcodeNum.charAt(barcodeNum.length() - 1))) {
						JOptionPane.showMessageDialog(null, "잘못된 바코드 입니다. 다시 입력해주세요.");
						return;
					}

					///////////////////////////////////////////////////////////////////////////////////////////////////////
//				String gtinAI = "01" + barcodeNum;
//				String expAI = "17" + exp.getText();
//				String lotAI = "10" + lot.getText();
//				String serialAI = "21" + serial.getText();
//				
//				System.out.println("length -> " + gtinAI.length());
//				System.out.println("length -> " + expAI.length());
//				System.out.println("length -> " + lotAI.length());
//				System.out.println("length -> " + serialAI.length());
//				
//				CharSequence st = Character.toString((char) 232);
//				CharSequence gs = Character.toString((char) 29);         
//				
//				String barcode = st + "0108809999999997";

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
//				int newbyteLength = gtinAI.length() + expAI.length() + lotAI.length() + serialAI.length(); 
//				byte[] newbyte = new byte[newbyteLength+2];
//				
//				byte[] gtinbf = gtinAI.getBytes();
//				byte[] expbf = expAI.getBytes();
//				byte[] lotbf = lotAI.getBytes();
//				
//				System.out.println(Arrays.toString(lotbf));
//				
//				byte[] serialbf = serialAI.getBytes();
//				
//				newbyte[0] = fncst;
//				System.out.println("0newbyte -> " + Arrays.toString(newbyte));
//				
//				System.arraycopy(gtinbf, 0, newbyte, 1, gtinbf.length);
//				System.out.println("1 g newbyte -> " + Arrays.toString(newbyte));
//				
//				System.arraycopy(expbf, 0, newbyte, gtinbf.length+1, expbf.length);
//				System.out.println("2 u newbyte -> " + Arrays.toString(newbyte));
//				  
//				
//				System.arraycopy(lotbf, 0, newbyte, expbf.length+1, lotbf.length);
////				System.arraycopy(lotbf, 0, newbyte, expbf.length+1, lotbf.length);
//				System.out.println("3 m newbyte -> " + Arrays.toString(newbyte));
//				
//				
//				System.out.println("sep index " + (gtinbf.length + expbf.length + lotbf.length +1));
//				newbyte[gtinbf.length + expbf.length + lotbf.length+1] = separator;
//				System.out.println("4 sep newbyte -> " + Arrays.toString(newbyte));
//				
//				System.arraycopy(serialbf, 0, newbyte, gtinbf.length + expbf.length + lotbf.length+2, serialbf.length);
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
					fileName = uuid.substring(0, 10) + time1;
					
					String gtinAI = "01" + barcodeNum;
					String expAI = "17" + exp.getText();
					String lotAI = "10" + lot.getText();
					String serialAI = "21" + serial.getText();
					
					String gg = "(01)"+barcodeNum+"(17)"+exp.getText()+"(10)"+lot.getText()+"(21)"+serial.getText();
					
					barcodeNum = gtinAI + expAI + lotAI + serialAI;
					
					try {
						createBarcodeImg(barcodeNum, fileName); // 이미지로 저장하고
					} catch (Exception e1) {
						e1.printStackTrace();
					}

					// 라벨에 이미지 띄우기
					ImageIcon icon = new ImageIcon(rootPath + "\\src\\barcodeimg\\" + fileName + ".gif"); // 이미지 아이콘 객체 생성

					// 이미지를 실제로 갖고 있는 Image객체 뽑아오기
					Image im = icon.getImage(); // 뽑아온 이미지 객체 사이즈를 새롭게 만들기!
					Image im2 = im.getScaledInstance(100, 100, Image.SCALE_DEFAULT);

					// 새로 조절된 사이즈의 이미지(im2)를 가지는 ImageIcon 객체를 다시 생성
					ImageIcon icon2 = new ImageIcon(im2);
					img.setIcon(icon2);

					saveName.setText(gg);
					JTextArea jj = new JTextArea(gg);
					jj.setPreferredSize(new Dimension(150,80));
					imgPanel.add(jj, BoxLayout.Y_AXIS);
					
					JOptionPane.showMessageDialog(null, "바코드가 생성되었습니다.");
				}
			} else {
				return;
			}

		} else if (e.getSource() == imgPrintBtn) { // 이미지 인쇄 버튼
			
			if(img.getIcon() == null) {
				JOptionPane.showMessageDialog(null, "바코드를 먼저 생성해 주세요.");
				return;
			}
			
			ImagePrintTest ip = new ImagePrintTest(rootPath + "\\src\\barcodeimg\\" + fileName + ".gif");
			JOptionPane.showMessageDialog(null, "인쇄가 완료되었습니다.");
		} else if (e.getSource() == lblPrintBtn) {
			
			if(img.getIcon() == null) {
				JOptionPane.showMessageDialog(null, "바코드를 먼저 생성해 주세요.");
				return;
			}
			// 받아온 정보로 바코드 출력하기
			printBarcode(barcodeChange(String.format("%014d", Long.parseLong(gtin.getText())), exp.getText(), lot.getText(), serial.getText()));
			JOptionPane.showMessageDialog(null, "인쇄가 완료되었습니다.");
			
		}else if(e.getSource() == scanningBtn) { //스캐닝 버튼 입력시 인풋창 활성화
			fullcode.setEditable(true);
			fullcode.requestFocus();
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
	
	//zebra 라벨프린터 인쇄
	//바코드 수정
	public byte[] barcodeChange(String gtin, String exp, String lot, String ser) {
		String path = ResetUI.class.getResource("").getPath(); // 현재 클래스의 절대 경로를 가져온다.
		File basicfile = new File(path + "zbarcodeTest.prn");	// 기본이 될 파일 경로
		
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
			strToBytes = str.replace("#GTIN#", gtin).replace("#EXP#", exp).replace("#LOT#", lot).replace("#SERIAL#", ser).getBytes();
			
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
	void createBarcodeImg(String barcodef, String fileName) throws Exception {
		DataMatrix barcode = new DataMatrix();
		
		/*
		 * Data Matrix Valid data char set: ASCII values 0 - 127 in accordance with the
		 * US national version of ISO/IEC 646 ASCII values 128 - 255 in accordance with
		 * ISO 8859-1. These are referred to as extended ASCII.
		 * 
		 */
		barcode.setData(barcodef);

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

		barcode.drawBarcode(rootPath + "\\src\\barcodeimg\\" + fileName + ".gif");
	}

	/**
	 * 빈 칸 확인
	 */
	public boolean inputNullCheck(String gtin, String exp, String lot, String serial) {

		if (gtin.equals("") || exp.equals("") || lot.equals("") || serial.equals("")) {
			JOptionPane.showMessageDialog(null, "필수 입력 사항을 모두 입력해주세요.");
			return false;
		} else if (exp.length() != 6 || exp.length()==0 && datePicker.getModel().getValue() == null) { // 고정 값 확인 (날짜)
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
	public boolean regCheck(String gtin, String exp, String lot, String serial) {
		/*
		 * (01) 숫자만 14자리 고정 (17) 숫자만 6자리 YYMMDD 고정 (10) 영문+숫자 20자 까지 +가용특수문자 가변길이 (21)
		 * 영문+숫자 20자 까지 +가용특수문자 가변길이
		 * 
		 * 특수문자)) ! " % & ' ( ) * + , - . / _ : ; < = > ?
		 * 
		 */

		Pattern reg01 = Pattern.compile("^[0-9]{14}$");
//		Pattern reg17 = Pattern.compile("^[0-9]{6}$");
		Pattern reg17 = Pattern.compile("^\\d{2}(0[1-9]|1[012])(0[1-9]|[12][0-9]|3[01])$");
//		Pattern reg17 = Pattern.compile("^(19|20)\\d{2}(0[1-9]|1[012])(0[1-9]|[12][0-9]|3[01]){6}$");
		Pattern reg1021 = Pattern.compile("^[!\"%&'()*+,-./_:;<=>?a-zA-Z0-9]{1,20}$");
		
		Matcher ma01 = reg01.matcher(gtin);
		Matcher ma17 = reg17.matcher(exp);
		Matcher ma10 = reg1021.matcher(lot);
		Matcher ma21 = reg1021.matcher(serial);
		
		if (!ma01.matches()) {
			JOptionPane.showMessageDialog(null, "표준코드는 숫자만 입력가능합니다.");
			return false;
		} else if (!ma17.matches()) {
			JOptionPane.showMessageDialog(null, "유효기한 형식이 잘못되었습니다.");
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
	
	/**
	 * 선택된 날짜가 현재  날짜 이후인지 확인
	 * @param today 오늘 날짜
	 * @param expDate 비교 날짜 (선택한 날짜)
	 */
	int dateCheck(Date today, Date expDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd"); //Date형식 지정

		//지정 포맷으로 변환
		String expDateStr = sdf.format(expDate);
		String todayStr = sdf.format(today);
		
		//다시 Date객체로 변환 -> 시간 지우기
		try {
			expDate = sdf.parse(expDateStr);
			today = sdf.parse(todayStr);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		// * 날짜 비교
		// 기준날짜.compareTo(비교날짜)  
		// 0 : 기준날짜와 비교날짜가 같을때 
		//-1 : 기준날짜 이전
		// 1 : 기준날짜 이후
		int compare = today.compareTo(expDate);
		
//		if(compare > 0 ) { //현재날짜가 비교날짜 후인 경우
//			System.out.println("compare -> " + compare);
//			System.out.println("현재날짜가 비교날짜 후인 경우");
//		}else if(compare < 0) { //현재날짜가 비교날짜 전인 경우
//			System.out.println("compare -> " + compare);
//			System.out.println("현재날짜가 비교날짜 전인 경우");
//		}else { //날짜 일치
//			System.out.println("compare -> " + compare);
//			System.out.println("당일");
//		}
		return compare;
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
