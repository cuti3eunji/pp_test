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
		Dimension dim = new Dimension(400,150);//ũ�� ����
		
		JFrame jf = new JFrame("Ÿ��Ʋ");
//		jf.setLocation(200,400);
		jf.setLocationRelativeTo(null);
		jf.setPreferredSize(dim);	//ũ�� ����
		
		
		//
		JPanel panel1 = new JPanel();
		panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));
		panel1.add(new JLabel("�̸� : "));
		panel1.add(new JTextField());
		
		JPanel panel2 = new JPanel();
		panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));
		panel2.add(new JLabel("���� : "));
		panel2.add(new JTextField());
		
		JPanel panel3 = new JPanel();
		panel3.setLayout(new BoxLayout(panel3, BoxLayout.X_AXIS));
		panel3.add(new JLabel("�ּ� : "));
		panel3.add(new JTextField());
		
		
		//
		JPanel mp = new JPanel();
		mp.setLayout(new BoxLayout(mp, BoxLayout.Y_AXIS));
		mp.add(panel1);
		mp.add(panel2);
		mp.add(panel3);
		
		jf.add(mp, BorderLayout.CENTER);
		jf.add(new JButton("�Է��ϱ�"), BorderLayout.SOUTH);
		jf.pack();	//�����ӳ��� ����������Ʈ���� ���̾ƿ��� Preferred Size�� �µ��� �������� ����� ���ߴ� �۾��̴�.
		jf.setVisible(true);
	}

}
