package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UITest2 extends JFrame{
	
	void makeUI() {
		JPanel pa = new JPanel();
		add(pa, BorderLayout.NORTH);
		pa.setBorder(BorderFactory.createTitledBorder("개인정보")); // Panel테두리
		pa.add(new JLabel("이 름"));
		pa.add(new JTextField(10));
		pa.add(new JLabel("비밀번호"));
		pa.add(new JTextField(10));
		
		JPanel pa2 = new JPanel();
		pa2.setLayout(new GridLayout(0,1,20,5));
		add(pa2, BorderLayout.SOUTH);
		pa2.setBorder(BorderFactory.createTitledBorder("색 상"));
		String[] s1 = {"Red", "Yellow","Green"};
		pa2.add(new JComboBox(s1));
		
		
	}
	

	public static void main(String[] args) {
	}	

}
