package ch02;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyFramePanel extends JFrame {

	private JButton button1;
	private JButton button2;
	private JButton button3;

	// 패널 추가하기
	private JPanel panel1;
	private JPanel panel2;
	private JPanel panel3;
	
	private JPanel outerPanal;

	public MyFramePanel() {
		initData();
		setInitLayout();
	}

	private void initData() {
		setTitle("패널추가 연습");
		setSize(400, 100);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		outerPanal = new JPanel();
		
		outerPanal.setBackground(Color.black);
		
		panel1 = new JPanel();
		panel1.setBackground(Color.blue);
		panel2 = new JPanel();
		panel2.setBackground(Color.white);
		panel3 = new JPanel();
		panel3.setBackground(Color.red);
		
		
//		button1 = new JButton("프랑스");
	}

	private void setInitLayout() {
		// 루트 패널 --> BorderLayout
//		add(outPanal, BorderLayout.CENTER);
//		add(panel1, BorderLayout.WEST);
//		add(panel2, BorderLayout.CENTER);
//		add(panel3, BorderLayout.EAST);
		
		outerPanal.setLayout(new BorderLayout());
		
		outerPanal.add(panel1, BorderLayout.WEST);
		outerPanal.add(panel2, BorderLayout.CENTER);
		outerPanal.add(panel3, BorderLayout.EAST);
		
//		panel1.add(button1, BorderLayout.CENTER);
		panel1.setLayout(new FlowLayout());
//		panel2.add(button2);
		panel2.setLayout(new FlowLayout());
//		panel3.add(button3);
		panel3.setLayout(new FlowLayout());
		
		
		add(outerPanal);
		pack();
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new MyFramePanel();
	}

}
