package test;

import javax.swing.JFrame;

public class A_Program extends JFrame{	//JFrame ���
	public A_Program() {	//������ 
		setTitle("TEST-TITLE");	//Ÿ��Ʋ ����
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//â�� ���� �� ���α׷�(JFrame)�� ���� ���� �ǵ��� ��
		
		//JFrame ũ�� ����
		setSize(1280,720);		//JFrame�� ũ�� setSize(x,y)
		setResizable(false);	//â�� ũ�� ���� �Ұ� - true�� ���� ���� ----> �⺻ true
		setLocationRelativeTo(null);	//����� â�� �ߵ��� ��
		setVisible(true);		//â�� ���̰� ��
	}
	
}
