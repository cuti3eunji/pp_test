package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.krysalis.barcode4j.BarcodeClassResolver;
import org.krysalis.barcode4j.DefaultBarcodeClassResolver;
import org.krysalis.barcode4j.impl.AbstractBarcodeBean;
import org.krysalis.barcode4j.output.java2d.Java2DCanvasProvider;

public class UITest {

	public static void main(String[] args) {
		Dimension dim = new Dimension(500,300);	//크기 지정
		
		JFrame jf = new JFrame("UI Test");	//타이틀 설정
		
		jf.setPreferredSize(dim);	
		
		//화면 구성
		// 프린트
		JPanel printPanel = new JPanel();	//프린트용 panel
//		printPanel.setPreferredSize(new Dimension(200,40));
//		printPanel.setBorder(BorderFactory.createTitledBorder("바코드 출력하기"));
		printPanel.setBorder(BorderFactory.createEmptyBorder(10 , 10 , 10 , 10));
		printPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(10 , 10 , 10 , 10), "바코드 출력하기"));


		
		JButton printBtn = new JButton("프린트");	//프린트 버튼
		printBtn.setPreferredSize(new Dimension(100,20));
		final JTextField inputField = new JTextField();
//		inputField.setPreferredSize(new Dimension(10,40));

		//프린트 버튼 클릭시 이벤트 처리
		printBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton btn = (JButton) e.getSource();
				if(inputField.getText() != null && inputField.getText().length() != 0) { //input값이 빈칸이 아닐때
					btn.setText(inputField.getText());
				}else { //빈칸일 경우 경고창?
					
				}
				
			}
		});
		
		
		printPanel.setLayout(new BoxLayout(printPanel, BoxLayout.X_AXIS));
		printPanel.add(new JLabel());
		printPanel.add(inputField);
		printPanel.add(printBtn);
		
		// 바코드 정보 검색
		JPanel searchPanel = new JPanel();
		searchPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(10 , 10 , 10 , 10), "바코드 정보 확인"));

		JButton searchBtn = new JButton("확인");
		searchBtn.setPreferredSize(new Dimension(100,20));
		
		searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.X_AXIS));
		searchPanel.add(new JLabel());
		searchPanel.add(new JTextField());
		searchPanel.add(searchBtn);
		
		//바코드 정보 출력
		JPanel infoPanel = new JPanel();
		JTextArea infoArea = new JTextArea();
		infoArea.setBorder(BorderFactory.createEmptyBorder(10 , 10 , 10 , 10));
		infoArea.setPreferredSize(new Dimension(100,100));

		infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
		infoPanel.add(infoArea);
		
		
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.add(printPanel);
		mainPanel.add(searchPanel);
		mainPanel.add(infoPanel);
		
		
		
		
		
		
		
		
		
		
		jf.add(mainPanel, BorderLayout.CENTER);
		jf.pack();
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//창을 닫을 시 프로그램(JFrame)이 정상 종료 되도록 함
		jf.setLocationRelativeTo(null);

	}

	/**
	 * 바코드 생성
	 * 
	 * @param barcodeType
	 * @param barcodeData
	 * @param scaleY 
	 * @param scaleX 
	 * @param dpi
	 */
	private void createBarcode(Graphics g, String barcodeType, String barcodeData, int x, int y,  int scaleX, int scaleY) throws Exception {
		AbstractBarcodeBean bean = null;
		
		BarcodeClassResolver resolver = new DefaultBarcodeClassResolver();
		Class clazz = resolver.resolveBean(barcodeType);
		bean = (AbstractBarcodeBean) clazz.newInstance();
		bean.doQuietZone(true);

		Graphics2D g2d = (Graphics2D) g;
		g2d.translate(x, y); 			/* 좌표 지정 */
		g2d.scale(scaleX, scaleY); 				/* 크기 지정 */

		Java2DCanvasProvider j2dp = new Java2DCanvasProvider(g2d, 0);
		bean.generateBarcode(j2dp, barcodeData);
	}
	
}
