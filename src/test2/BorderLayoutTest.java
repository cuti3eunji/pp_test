package test2;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class BorderLayoutTest {

	static class setGUI extends JFrame{
		// �����ڸ� ���� GUI �ʱ� ������ ���ش�.
		setGUI(){
		    
	    // ������ ����(Title)�� ����
		setTitle("GUI TITLE");
		
		// �� �κк��� ���ϴ� ��ư, ���̺�, �޺��ڽ� ��� ����
		//BorderLayout(���򰣰�,��������)
        this.setLayout(new BorderLayout(20,20));
        
//        JButton btn1 = new JButton("��ư1");    
//        this.add(btn1, BorderLayout.NORTH);        
//        
//        JButton btn2 = new JButton("��ư2");  
//        this.add(btn2, BorderLayout.WEST);          
//        
        JButton btn3 = new JButton("��ư3");  
        this.add(btn3, BorderLayout.CENTER);
        
//        JButton btn4 = new JButton("��ư4");
//        this.add(btn4, BorderLayout.EAST);
//        
//        JButton btn5 = new JButton("��ư5");  
//        this.add(btn5, BorderLayout.SOUTH);  


		
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


