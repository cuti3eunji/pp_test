package test;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class B_JFrameTest {

	static class setGUI extends JFrame{
		// �����ڸ� ���� GUI �ʱ� ������ ���ش�.
		setGUI(){
		    
	    // ������ ����(Title)�� ����
		setTitle("GUI TITLE");
		
		// �� �κк��� ���ϴ� ��ư, ���̺�, �޺��ڽ� ��� ����
        
		
		// ���� ��ư ����
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// ������ â ũ�� ����(����, ����)
		setSize(500, 500);
		
		// �� �޼ҵ带 �̿��ؾ� ������ â�� ��Ÿ����.
		setVisible(true);
		
		setLocationRelativeTo(null);
        }

	}

	
	
	public static void main(String[] args) {
		new setGUI();
	}

}


