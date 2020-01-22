package test2;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class GridLayoutTest {

	static class setGUI extends JFrame{
		// 생성자를 통해 GUI 초기 세팅을 해준다.
		setGUI(){
		    
	    // 윈도우 제목(Title)을 생성
		setTitle("GUI TITLE");
		
		// 이 부분부터 원하는 버튼, 레이블, 콤보박스 등등 설정
		   // GridLayout을 이용하고 수평, 수직 간격을 3px로 한다.
        this.setLayout(new GridLayout(3,3));
        
        JButton btn1 = new JButton("버튼1");    
        this.add(btn1);        
        
        JButton btn2 = new JButton("버튼2");  
        this.add(btn2);          
        
        JButton btn3 = new JButton("버튼3");  
        this.add(btn3);          
        
        JButton btn4 = new JButton("버튼4");
        this.add(btn4);        
        
        JButton btn5 = new JButton("버튼5");  
        this.add(btn5);      

        JButton btn6 = new JButton("버튼6");
        this.add(btn6);

        JButton btn7 = new JButton("버튼7");
        this.add(btn7);

        
		
		// 종료 버튼 생성
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// 윈도우 창 크기 설정(가로, 세로)
		setSize(500, 500);
		
		// 이 메소드를 이용해야 윈도우 창이 나타난다.
		setVisible(true);
		
		setLocationRelativeTo(null);
        }

	}

	
	
	public static void main(String[] args) {
		new setGUI();
	}

}


