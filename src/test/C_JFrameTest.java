package test;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class C_JFrameTest {
	public static void main(String[] args) {
		Dimension dim = new Dimension(400,150);//크기 지정
		
		JFrame jf = new JFrame("타이틀");
//		jf.setLocation(200,400);
		jf.setLocationRelativeTo(null);
		jf.setPreferredSize(dim);	//크기 지정
		
		
		//
		JPanel panel1 = new JPanel();
		panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));
		panel1.add(new JLabel("이름 : "));
		panel1.add(new JTextField());
		
		JPanel panel2 = new JPanel();
		panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));
		panel2.add(new JLabel("나이 : "));
		panel2.add(new JTextField());
		
		JPanel panel3 = new JPanel();
		panel3.setLayout(new BoxLayout(panel3, BoxLayout.X_AXIS));
		panel3.add(new JLabel("주소 : "));
		panel3.add(new JTextField());
		
		
		//
		JPanel mp = new JPanel();
		mp.setLayout(new BoxLayout(mp, BoxLayout.Y_AXIS));
		mp.add(panel1);
		mp.add(panel2);
		mp.add(panel3);
		
		jf.add(mp, BorderLayout.CENTER);
		jf.add(new JButton("입력하기"), BorderLayout.SOUTH);
		jf.pack();	//프레임내에 서브컴포넌트들의 레이아웃과 Preferred Size에 맞도록 윈도우의 사이즈를 맞추는 작업이다.
		jf.setVisible(true);
	}

}