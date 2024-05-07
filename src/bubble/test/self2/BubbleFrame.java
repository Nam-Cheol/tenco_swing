package bubble.test.self2;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class BubbleFrame extends JFrame {

	private JLabel backgroundMap;
	private Player player; // <-- composition 관계
	private Bubble bubble;
	private Enemy enemy1;
	private Enemy enemy2;
	private Enemy enemy3;

	public BubbleFrame() {
		initData();
		setInitLayout();
		addEventListener();

		// Player 백그라운드 서비스 시작
		new Thread(new BackgroundPlayerService(player)).start();
		

	}

	private void initData() {
		setTitle("Bubble! Bubble!");
		setSize(1000, 640);
		backgroundMap = new JLabel(new ImageIcon("img/backgroundMapService.png"));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setContentPane(backgroundMap);

		player = new Player();
		
//		enemy1 = new Enemy();
//		enemy2 = new Enemy();
//		enemy3 = new Enemy();
//		enemy1.setX(200);
//		enemy3.setX(800);
//		
//		enemy1.down();
		
	}

	private void setInitLayout() {
		setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null); // JFrame을 모니터 정중앙에 배치
		setVisible(true);

		add(player);
//		add(enemy1);
//		add(enemy2);
//		add(enemy3);
	}

	private void addEventListener() {

		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				switch (e.getKeyCode()) {

				case KeyEvent.VK_LEFT:
					// 구현
					// 방어적 코드 --> 왼쪽으로 방향키를 누르고 있다면
					// KeyEvent가 계속 <-<-<-
					// 왼쪽 상태가 아니라면
					// 왼쪽 벽에 충돌한게 아니라면
					if (!player.isLeft() && !player.isLeftWallCrash()) {
						player.left();
					}
					break;
				case KeyEvent.VK_RIGHT:
					// 구현
					if (!player.isRight() && !player.isRightWallCrash()) {
						player.right();
					}
					break;
				case KeyEvent.VK_UP:
					// 구현
					if (!player.isUp()) {
						player.up();
					}
					break;
				case KeyEvent.VK_SPACE:
					System.out.println("버블 생성");
					backgroundMap.add(player.attack());
					break;
				default:
					break;

				}

			}// end of keyPressed

			@Override
			public void keyReleased(KeyEvent e) {

				switch (e.getKeyCode()) {

				case KeyEvent.VK_LEFT:
					// 구현
					player.isLeft(false);
					break;
				case KeyEvent.VK_RIGHT:
					// 구현
					player.isRight(false);
					break;
				default:
					break;
				}

			}// end of keyReleased

		});

	}

	// 코드 테스트
	public static void main(String[] args) {
		new BubbleFrame();
		
	}// end of main

}