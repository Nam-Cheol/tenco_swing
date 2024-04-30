package ch07;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;

public class LottoFrame extends JFrame implements ActionListener {

	private JButton button;
	private LottoRandomNumber lottoRandomNumber;
	boolean isStart = true;
	private int NUMBER_DISTANCE = 80;

	public LottoFrame() {
		initData();
		setInitLayout();
		addEventListener();
	}

	private void initData() {
		setTitle("Lotto Game");
		setSize(600, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		button = new JButton("Game Start");
		lottoRandomNumber = new LottoRandomNumber();
	}

	private void setInitLayout() {
		add(button, BorderLayout.NORTH);

		setVisible(true);
	}

	private void addEventListener() {
		button.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("기림");
		// 이벤트가 일어나면 그림을 다시 그려라
		isStart = false;
		repaint(); // 다시 그림을 그려라 요청하는 명령어 // F5와 동일
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);

		Font f = new Font("궁서체", Font.BOLD, 20);
		g.setFont(f);

		int[] getNumbers = lottoRandomNumber.createNumber();

		if (isStart) {
			g.drawString("로또 번호를 클릭하세요", 190, 220);
		} else {

			for (int i = 0; i < getNumbers.length; i++) {
				g.drawString(getNumbers[i] + " ", (i+1) * NUMBER_DISTANCE, 230);

			}
		}

	}

	public static void main(String[] args) {
		new LottoFrame();
	}// end of main

}// end of class