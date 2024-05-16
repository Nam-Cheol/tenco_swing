package bubble;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import bubble.components.Enemy;
import bubble.components.Player;
import bubble.service.BackgroundPlayerService;

public class BubbleFrame extends JFrame {

	// 컨텍스트를 생성하는 방법 (셀프참조)
	BubbleFrame mContext = this;

	private JLabel backgroundMap;
	// 포함관계 - 콤포지션
	private Player player;
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
		// TODO 이미지 변경
		backgroundMap = new JLabel(new ImageIcon("img/backgroundMap11.png"));
		// backgroundMap = new JLabel(new ImageIcon("img/test.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Frame --> root Panel
		setContentPane(backgroundMap); // add 처리
		setSize(1280, 980);

		// mContext --> 참조 타입() --> 32bit 기준 주소값의 크기는 기본 4byte 이다. (우리는 64bit)
		player = new Player(mContext);
		enemy1 = new Enemy(mContext);
		enemy2 = new Enemy(mContext);
		enemy2.setY(300);
		enemy3 = new Enemy(mContext);
		enemy3.setY(420);
	}

	private void setInitLayout() {
		// 좌표 값으로 배치
		setLayout(null);
		setResizable(false); // 프레임 조절 불가
		setLocationRelativeTo(null); // JFrame 여러분 모니터 가운데 자동 배치
		setVisible(true);

		add(player);
		add(enemy1);
		add(enemy2);
		add(enemy3);
	}

	private void addEventListener() {

		this.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				System.out.println("key code : " + e.getKeyCode());

				switch (e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					// 왼쪽으로 방향키 누르고 있다면
					// key 이벤트가 계속 <- <- <- <- <- <-
					// 왼쪽 상태가 아니라면
					// 왼쪽 벽에 충돌 한게 아니라면
					if (!player.isLeft() && !player.isLeftWallCrash()) {
						player.left();
					}
					break;
				case KeyEvent.VK_RIGHT:
					if (!player.isRight() && !player.isRightWallCrash()) {
						player.right();
					}

					break;
				case KeyEvent.VK_UP:
					if (!player.isUp()) {
						player.up();
					}
					break;
				case KeyEvent.VK_SPACE:
//						player.attack(bubble, backgroundMap);
					player.attack();
					// 프레임에 컴포넌트를 add 동작을 누가 하느냐?

					break;
				default:
					break;
				}
			} // end of KeyPressed

			@Override
			public void keyReleased(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					// 왼쪽으로 가능 상태 멈춤
					player.setLeft(false);
					break;
				case KeyEvent.VK_RIGHT:
					// 오른쪽으로 가능 상태 멈춤
					player.setRight(false);
					break;
				default:
					break;
				}
			} // end of KeyReleased

		});
		
		this.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println(e.getY());
			}
		});
	}

	// getter
	public Player getPlayer() {
		return this.player;
	}
	
	public Enemy getEnemy1() {
		return enemy1;
	}
	public Enemy getEnemy2() {
		return enemy1;
	}
	public Enemy getEnemy3() {
		return enemy1;
	}
	// 코드 테스트
	public static void main(String[] args) {
		// main 함수를 가지고 있는 클래스는 하위에 생성된 모든 객체들의
		// ★★★주소값을 알고 있다.★★★
		new BubbleFrame();
	} // end of main

}
