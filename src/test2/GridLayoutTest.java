package test2;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class GridLayoutTest {

	static class setGUI extends JFrame{
		// �����ڸ� ���� GUI �ʱ� ������ ���ش�.
		setGUI(){
		    
	    // ������ ����(Title)�� ����
		setTitle("GUI TITLE");
		
		// �� �κк��� ���ϴ� ��ư, ���̺�, �޺��ڽ� ��� ����
		   // GridLayout�� �̿��ϰ� ����, ���� ������ 3px�� �Ѵ�.
        this.setLayout(new GridLayout(3,3));
        
        JButton btn1 = new JButton("��ư1");    
        this.add(btn1);        
        
        JButton btn2 = new JButton("��ư2");  
        this.add(btn2);          
        
        JButton btn3 = new JButton("��ư3");  
        this.add(btn3);          
        
        JButton btn4 = new JButton("��ư4");
        this.add(btn4);        
        
        JButton btn5 = new JButton("��ư5");  
        this.add(btn5);      

        JButton btn6 = new JButton("��ư6");
        this.add(btn6);

        JButton btn7 = new JButton("��ư7");
        this.add(btn7);

        
		
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


