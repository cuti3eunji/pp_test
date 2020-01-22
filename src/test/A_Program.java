package test;

import javax.swing.JFrame;

public class A_Program extends JFrame{	//JFrame 상속
	public A_Program() {	//생성자 
		setTitle("TEST-TITLE");	//타이틀 설정
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//창을 닫을 시 프로그램(JFrame)이 정상 종료 되도록 함
		
		//JFrame 크기 설정
		setSize(1280,720);		//JFrame의 크기 setSize(x,y)
		setResizable(false);	//창의 크기 변경 불가 - true는 변경 가능 ----> 기본 true
		setLocationRelativeTo(null);	//가운데에 창이 뜨도록 함
		setVisible(true);		//창이 보이게 함
	}
	
}
