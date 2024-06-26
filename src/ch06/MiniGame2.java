package ch06;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MiniGame2 extends JFrame {

	private JLabel jBackground;
	private JLabel jPlayer;
	private JLabel obstruction;
	private int x = 50;
	private int y = 710;
	private final int move = 10;
	private int jump = 100;
	private final int FRAME_WIDTH = 1000;
	private final int FRAME_HEIGHT = 1000;
	private final int PLAYER_WIDTH = 100;
	private final int PLAYER_HEIGHT = 100;
	private Icon playerR = new ImageIcon("images/playerR.png");
	private Icon playerL = new ImageIcon("images/playerL.png");
	private Icon player;

	public MiniGame2() {
		initData();
		setInitLayout();
		addEventListener();
	}

	private void initData() {
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		Icon icon1 = new ImageIcon("images/backgroundMap.png");
		Icon obstruction1 = new ImageIcon("images/3.png");

		jBackground = new JLabel(icon1);
		jBackground.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		jBackground.setLocation(0, 0);
		player = playerR;
		jPlayer = new JLabel(player);
		jPlayer.setSize(PLAYER_WIDTH, PLAYER_HEIGHT);

		obstruction = new JLabel(obstruction1);
		obstruction.setSize(200, 200);
	}

	private void setInitLayout() {
		// 좌표 기반으로 배치관리자 변경
		setLayout(null);
		add(jBackground);
		jBackground.add(jPlayer);
		jBackground.add(obstruction);
		jPlayer.setLocation(x, y);
		obstruction.setLocation(300, 650);

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
					if (x < 50) {
						return;
					}
					player = playerL;
					x -= move;
				} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					if (x > 860) {
						return;
					}
					player = playerR;
					x += move;
				} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					if (y > 700) {
						return;
					}
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

		new MiniGame2();

	}// end of main

}// end of class