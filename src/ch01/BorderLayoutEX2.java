package ch01;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

public class BorderLayoutEX2 extends JFrame {

	JButton[] buttons;
	String[] directions = {BorderLayout.EAST,BorderLayout.WEST,BorderLayout.SOUTH,
						   BorderLayout.NORTH,BorderLayout.CENTER};
	String[] five = {"동", "서", "남", "북", "센터"};
	
	public BorderLayoutEX2() {

		initData();
		setInitLayout();
	}

	public void initData() {
		setTitle("BorderLayout 연습");
		setSize(600, 600);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		buttons = new JButton[directions.length];
	}

	public void setInitLayout() {
		// 배치관리자 선정 (컨테이너)
		// BorderLayout -- 컴포넌트들을 동서남북 가운데로 배치시켜주는 레이아웃이다.
		setLayout(new BorderLayout());

		// 반복문을 활용해서 코드를 완성하세요
		for (int i = 0; i < buttons.length; i++) {
			
			buttons[i] = new JButton(five[i].toString());
			
			add(buttons[i],directions[i]);
		}
	}

	public static void main(String[] args) {
		new BorderLayoutEX2();
	}
}
