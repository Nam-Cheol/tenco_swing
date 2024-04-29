package ch05;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ColorChangeFrame3 extends JFrame implements ActionListener {

	private JPanel panel;
	private JPanel panel1;

	private JButton[] buttons = new JButton[7];

	public ColorChangeFrame3() {
		initData();
		setInitLayout();
		addEventListener();
	}

	private void initData() {
		setSize(700, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		panel = new JPanel();
		panel1 = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel1.setBackground(Color.cyan);

		for (int i = 0; i < buttons.length; i++) {
			buttons[i] = new JButton("나는 " + (i + 1));
		}

	}

	private void setInitLayout() {
		add(panel, BorderLayout.CENTER);
		add(panel1, BorderLayout.SOUTH);

		for (int i = 0; i < buttons.length; i++) {
			panel1.add(buttons[i]);
		}
		setVisible(true);
	}

	private void addEventListener() {
		for (int i = 0; i < buttons.length; i++) {
			buttons[i].addActionListener(this);
		}
	}

	public static void main(String[] args) {
		new ColorChangeFrame3();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		JButton bu = (JButton) e.getSource();

		for (int i = 0; i < buttons.length; i++) {
			Random random = new Random();
			int r = random.nextInt(256);
			int g = random.nextInt(256);
			int b = random.nextInt(256);
			Color randomColor = new Color(r, g, b);
			panel.setBackground(randomColor);
			if (bu.equals(buttons[i])) {
				panel.setBackground(randomColor);
			}
		}
		
		

	}

}
