package ch08;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameFrame extends JFrame {

	BufferedImage backgroundImage;
	BufferedImage player1;
	BufferedImage enemy1;
	ImagePanel imagePanel;
	private int player1X = 850;
	private int player1Y = 535;
	private int movePlayer1 = 10;
	private int enemy1X = 650;
	private int enemy1Y = 535;
	private int charSize = 50;
	private int charSize1 = 50;
	private boolean stop = true;
	private int enemyMove = 0;

	public GameFrame() {
		initData();
		setInitLayout();
		addEventListener();

	}

	// 클래스 안에 클래스 -> 중첩 클래스 -> 외부클래스, 내부클래스
	private class ImagePanel extends JPanel implements Runnable {
		// paintComponents -- 아님
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(backgroundImage, 0, 0, 1000, 600, null);
			g.drawImage(player1, player1X, player1Y, charSize1, charSize1, null);
			g.drawImage(enemy1, enemy1X, enemy1Y, charSize, charSize, null);

			// TODO 플레이어, 적군 그림 그려야 함
			// Thread 를 활용할 예정

		}

		@Override
		public void run() {

			// true : 왼쪽으로 가는 상황
			// false : 오른쪽으로 가는 상황
			boolean direction = true;

			while (stop) {
				if (direction) {
					enemy1X -= 10;
				} else {
					enemy1X += 10;
				}

				// 방향 바꾸는 개념은 적군 x 좌표값이
				if (enemy1X <= 50) {
					direction = false;
				}

				if (enemy1X >= 900) {
					direction = true;
				}

				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				if (player1X <= enemy1X && player1X <= enemy1X + 30) {
					if (player1Y <= enemy1Y && player1Y <= enemy1Y + 30) {

						charSize1 = 0;

					}
				}
				
				repaint();
			}

		}
	}

	private void initData() {
		setTitle("Thread 를 활용한 미니 예제");
		setSize(1000, 640);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		try {
			// 예외가 발생할 수 있는 코드를 작성하는 영역
			backgroundImage = ImageIO.read(new File("img/backgroundMap.png"));

			player1 = ImageIO.read(new File("img/PlayerL.png"));

			enemy1 = ImageIO.read(new File("img/enemyL.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		imagePanel = new ImagePanel();
		// 다른 작업자에게 일을 위임시킨다.
		Thread thread = new Thread(imagePanel);
		thread.start();

	}

	private void setInitLayout() {

		// setLayout(null);

		// setResizable(false);
		// 프레임 크기 조절 불가 설정
		setVisible(true);
		add(imagePanel);
	}

	private void addEventListener() {

		// 이벤트 리스너 등록 방법 중 2가지
		this.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				System.out.println("ㅋㅋ");
				int code = e.getKeyCode();
				System.out.println(e.getKeyCode());
				if (code == KeyEvent.VK_LEFT) {
					player1X -= movePlayer1;
				} else if (code == KeyEvent.VK_RIGHT) {
					player1X += movePlayer1;
				} else if (code == KeyEvent.VK_UP) {
					player1Y -= movePlayer1;
				} else if (code == KeyEvent.VK_DOWN) {
					player1Y += movePlayer1;
				} else if (code == KeyEvent.VK_SPACE) {
					// 1. 스페이스를 눌렀을 때 적군을 멈출 수 있도록 코드 수정
					stop = false;
				}

				// 2. player 가 적군을 만났다면 player 그림을 없애주세요
				
				
				if (player1X <= enemy1X || player1X <= enemy1X + 30) {
					if (player1Y <= enemy1Y || player1Y <= enemy1Y + 30) {

						charSize1 = 0;

					}
				}
				repaint();

			}
		});
	}

}
