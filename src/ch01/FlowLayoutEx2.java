package ch01;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class FlowLayoutEx2 extends JFrame {

	// 배열 활용
	
	private JButton[] button;

	public FlowLayoutEx2() {
		super.setTitle("FlowLayout 연습");
		super.setSize(500, 500);
		super.setVisible(true);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		initData();
		setInitLayout();
	}

	public void initData() {
		//반복문
		button = new JButton[12];
		
		for (int i = 0; i < button.length; i++) {
			button[i] = new JButton("button" + (i+1));
		}
		
	}

	public void setInitLayout() {
		super.setLayout(new FlowLayout(FlowLayout.LEADING, 50, 50));

		//반복문
		for (int i = 0; i < button.length; i++) {
			super.add(button[i]);
		}
		
	}

	public static void main(String[] args) {

		new FlowLayoutEx2();

	}// end of main

}// end of class
