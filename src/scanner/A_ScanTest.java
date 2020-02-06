package scanner;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import jssc.SerialPortList;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class A_ScanTest implements SerialPortEventListener{

	SerialPort serialPortr;

	String scanningData;
	byte readBuffer[];

	
	Date today = new Date();	//오늘 날짜

	protected Image printImage;

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

	public A_ScanTest() { // 생성자
		UIsettings();
	}

	public static void main(String[] args) {
		new A_ScanTest();
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

				// 유효기한 (17)
				JPanel expPanel = new JPanel();
				expPanel.setLayout(new BoxLayout(expPanel, BoxLayout.X_AXIS));

				exp = new JTextField("123");
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

				JPanel previewBtnPanel = new JPanel();
				previewBtnPanel.setSize(500, 100);
				imgPrintBtn = new JButton("이미지인쇄");
				lblPrintBtn = new JButton("라벨프린터인쇄");
				previewBtnPanel.add(imgPrintBtn, BorderLayout.PAGE_END);
				previewBtnPanel.add(lblPrintBtn, BorderLayout.PAGE_END);

				previewPanel.add(imgPanel);
				previewPanel.add(previewBtnPanel);

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

				searchBtn = new JButton("조회");
				
				searchBtn.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
					}
				});
				
				searchPanel.add(fullcode);
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
		
		
		jf.add(topPanel, BorderLayout.NORTH);
		jf.add(bottomPanel, BorderLayout.CENTER);
		jf.pack();
		jf.setResizable(false); // 창의 크기 변경 불가
		jf.setVisible(true); // 보이게
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 창을 닫을 시 프로그램(JFrame)이 정상 종료 되도록 함
		jf.setLocationRelativeTo(null); // 창을 가운데에 띄우기
	}
	
	
	@Override
	public void serialEvent(SerialPortEvent event) {
		//포트이름 확인 
		String[] portNames = SerialPortList.getPortNames();
		
		try {
			//연결된 포트 있냐?????
			if(portNames.length != 0) { 
				for (int i = 0; i < portNames.length; i++) {
					System.out.println("연결된 포트이름 : " + portNames[i]);
				}
			}else if(portNames.length == 0){
				System.out.println("연결된 포트가 없습니당^_^~");
			}
		} catch (Exception e) {
			System.out.println("에러----> " + e.getMessage());
		}
		
		for(String portNm : portNames) {
			if(portNm.equals("COM3")) {
				JOptionPane.showMessageDialog(null, "스캐너가 연결되었습니다.");
			};
		}
		//Reading data from serial port
	    serialPortr = new SerialPort("COM3");
	    
	    try {
	        serialPortr.openPort();//Open serial port
			serialPortr.addEventListener(this);
		} catch (SerialPortException e) {
			e.printStackTrace();
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
}
