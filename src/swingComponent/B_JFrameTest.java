package swingComponent;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class B_JFrameTest {

	static class setGUI extends JFrame{
		// 생성자를 통해 GUI 초기 세팅을 해준다.
		setGUI(){
		    
	    // 윈도우 제목(Title)을 생성
		setTitle("GUI TITLE");
		
		// 이 부분부터 원하는 버튼, 레이블, 콤보박스 등등 설정
        
		
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