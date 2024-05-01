package ch06;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MiniGame3 extends JFrame {

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
	private Icon playerR = new ImageIcon("img/playerR.png");
	private Icon playerL = new ImageIcon("img/playerL.png");
	private Icon player;

	public MiniGame3() {
		initData();
		setInitLayout();
		addEventListener();
	}

	private void initData() {
		setSize(FRAME_WIDTH, 640);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		Icon icon1 = new ImageIcon("img/backgroundMap.png");
		Icon obstruction1 = new ImageIcon("img/3.png");

		jBackground = new JLabel(icon1);
		jBackground.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		jBackground.setLocation(0, -200);
		jPlayer = new JLabel(player);
		jPlayer.setIcon(playerR);
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
					if (y < 200) {
						return;
					}
					y -= move;
				} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					
					jPlayer.setIcon(playerL);
					if (x < 50) {
						return;
					}
					x -= move;
				} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					
					jPlayer.setIcon(playerR);
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
					
					
					if (y < 200) {
						return;
					}
					y -= jump;
				}
				jPlayer.setLocation(x, y);
			}
		});
	}
	
//	public void max() {
//		if(x < 50) {
//			return;
//		}
//		if(x > 860) {
//			return;
//		}
//		if(y < 200) {
//			return;
//		}
//		if(y > 700) {
//			return;
//		}
//	}

	// 코드 테스트
	public static void main(String[] args) {

		new MiniGame3();

	}// end of main

}// end of class