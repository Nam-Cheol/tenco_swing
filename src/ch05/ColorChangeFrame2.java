package ch05;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ColorChangeFrame2 extends JFrame implements ActionListener {

	private JPanel panel;
	private JButton button1;
	private JButton button2;

	public ColorChangeFrame2() {
		initData();
		setInitLayout();
		addEventListener();
	}

	private void initData() {
		setSize(500,500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		panel = new JPanel();
		panel.setBackground(Color.yellow);
		button1 = new JButton("click1");
		button2 = new JButton("click2");
	}

	private void setInitLayout() {
		add(button1, BorderLayout.NORTH);
		add(button2, BorderLayout.SOUTH);
		add(panel, BorderLayout.CENTER);
		setVisible(true);
	}

	// 이 메소드의 책임은 이벤트 리스너만 등록
	private void addEventListener() {
		// 이벤트 리스너를 구현하는 두번째 방법
		button1.addActionListener(this);
		button2.addActionListener(this);
	}

	public static void main(String[] args) {
		new ColorChangeFrame2();
	}

	// 오버라이드 : 이벤트가 일어나면 호출되어지는 메소드
	@Override
	public void actionPerformed(ActionEvent e) {
		//System.out.println(((JButton)e.getSource()).toString());
		
		JButton b = (JButton)e.getSource();
		
		if(b.equals(button1)) {
			System.out.println("1");
			panel.setBackground(Color.blue);
		} else if(b.equals(button2)) {
			System.out.println("2");
			panel.setBackground(Color.red);
		}
		
	}
	
}
