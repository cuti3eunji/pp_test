package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class TestLayout{
	
	
	
	public TestLayout() {
		layout();
	}
	
	public static void main(String[] args) {
		new TestLayout();
	}
	
	public void layout() {
		Dimension mainDim = new Dimension(800,500);
		JFrame jf = new JFrame();
		
		JTabbedPane tabpane = new JTabbedPane();
		jf.setPreferredSize(mainDim);
		
		JPanel createPanel = new JPanel();
		createPanel.setLayout(new BorderLayout());
//		createPanel.setBackground(Color.WHITE);
		
		JScrollPane creInputScroll = new JScrollPane();
//		creInputScroll.setBorder(new LineBorder(Color.red));
		
		JPanel creInputPanel = new JPanel();
		creInputPanel.setBorder(new TitledBorder(new LineBorder(Color.black), "제품 정보 입력"));
		creInputPanel.setLayout(new BorderLayout());

		creInputScroll.setViewportView(creInputPanel);
//		creInputScroll.setPreferredSize(new Dimension((int)mainDim.getWidth(), (int)mainDim.getHeight()/2));
		creInputScroll.setPreferredSize(new Dimension((int)mainDim.getWidth(), (int)mainDim.getHeight()/3));
		
		JPanel inputpanel = new JPanel();
		inputpanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
//		inputpanel.setBorder(new LineBorder(Color.yellow));
		
		String[] prod = new String[] { "이지엔6이브연질캡슐", "타이레놀어쩌구", "두통약어쩌구" };
		JComboBox<String> gtinCombo = new JComboBox<String>(prod);
		
		inputpanel.add(new JLabel("표준코드(GTIN)"));   
		inputpanel.add(gtinCombo);
		
		JButton addBtn = new JButton("+");
		inputpanel.add(addBtn);
		
		creInputPanel.add(inputpanel, BorderLayout.NORTH);
		
		JPanel creBtnPanel = new JPanel();
		creBtnPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
		JButton createBtn = new JButton("바코드생성");
		JButton resetBtn = new JButton("초기화");
		
		creBtnPanel.add(createBtn);
		creBtnPanel.add(resetBtn);
		
		
		createPanel.add(creInputScroll, BorderLayout.NORTH);
		createPanel.add(creBtnPanel);
		
		
		
		
		
		
		
		
		tabpane.add("바코드 생성", createPanel);
//		tabpane.add("두번째 탭", secondPanel);
		
		tabpane.setUI(new TabUI());
		
		jf.add(tabpane);
//		jf.add(bottomPanel, BorderLayout.CENTER);
		jf.pack();
		jf.setVisible(true); // 보이게
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 창을 닫을 시 프로그램(JFrame)이 정상 종료 되도록 함
		jf.setLocationRelativeTo(null); // 창을 가운데에 띄우기
	}

}
