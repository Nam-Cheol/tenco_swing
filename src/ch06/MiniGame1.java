package ch06;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class MiniGame1 extends JFrame {

	private JLabel jPlayer;
	private int x = 300;
	private int y = 200;
	private final int move = 30;
	private int jump = 100;
	private final int FRAME_WIDTH = 500;
	private final int FRAME_HEIGHT = 500;
	private final String PLAYER_NAME = "박태현";
	private final int PLAYER_WIDTH = 100;
	private final int PLAYER_HEIGHT = 100;

	public MiniGame1() {
		initData();
		setInitLayout();
		addEventListener();
	}

	private void initData() {
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		jPlayer = new JLabel(PLAYER_NAME);
		jPlayer.setSize(PLAYER_WIDTH, PLAYER_HEIGHT);
	}

	private void setInitLayout() {
		// 좌표 기반으로 배치관리자 변경
		setLayout(null);
		add(jPlayer);
		jPlayer.setLocation(x, y);

		setVisible(true);
	}

	private void addEventListener() {
		// jPlayer 객체에게서만 keyListener 동작을 시키고자 한다면
		// 익명 구현클래스로 KeyListener 인터페이스를 재정의할 수 있다.

		addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_UP) {
					// jPlayer.setLocation(100,50);
					y -= move;
				} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					x -= move;
				} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					x += move;
				} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					y += move;
				} else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					y -= jump;
				}
				jPlayer.setLocation(x, y);
			}
		});
	}

	// 코드 테스트
	public static void main(String[] args) {

		new MiniGame1();

	}// end of main

}// end of class