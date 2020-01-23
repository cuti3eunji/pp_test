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
import javax.swing.JTextArea;
import javax.swing.JTextField;

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
		printBtn.setPreferredSize(new Dimension(100,40));
		JTextField inputField = new JTextField();
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
		searchBtn.setPreferredSize(new Dimension(100,80));
		
		searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.X_AXIS));
		searchPanel.add(new JLabel());
		searchPanel.add(new JTextField());
		searchPanel.add(searchBtn);
		
		//바코드 정보 출력
		JPanel infoPanel = new JPanel();
		
		infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
		infoPanel.add(new JTextField());
		
		
		
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

}
