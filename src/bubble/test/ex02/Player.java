package bubble.test.ex02;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Player extends JLabel implements Moveable {

	private int x;
	private int y;
	private ImageIcon playerR, playerL;

	// 움직임의 상태
	private boolean left;
	private boolean right;
	private boolean up;
	private boolean down;
	private int n1 = 0;

	// 플레이어의 속도 상태
	private final int SPEED = 4;
	private final int JUMPSPEED = 2;

	public Player() {
		initData();
		setInitLayout();
	}

	private void initData() {
		playerR = new ImageIcon("img/playerR.png");
		playerL = new ImageIcon("img/playerL.png");
		// 처음 실행 시 초기값 셋팅
		x = 55;
		y = 535;

		// 가만히 멈춰있는 상태
		left = false;
		right = false;
		up = false;
		down = false;

		setIcon(playerR);
		setSize(50, 50);
		setLocation(x, y);
	}

	private void setInitLayout() {
	}

	@Override
	public void left() {
		if (n1 >= 2) {
			n1 = 0;
			return;
		}
		System.out.println("left 실행");
		left = true;
		// <-<-<-
		n1++;
		setIcon(playerL);
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (left) {
					x -= SPEED;
					setLocation(x, y);
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();

		// 왼쪽으로 가고 있는 상태
		// 멈춰 있는 상태란 개념이 필요하다.
	}

	@Override
	public void right() {
		right = true;
		setIcon(playerR);
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (right) {
					x += SPEED;
					setLocation(x, y);
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();

	}

	@Override
	public void up() {
		System.out.println("점프");
		up = true;
		new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 130; i++) {
					if (i < 65) {
						y -= JUMPSPEED;
					} else {
						y += JUMPSPEED;
					}
					try {
						Thread.sleep(8);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					setLocation(x, y);
				}
				up = false;
			}
		}).start();
		// 상태값 처리를 확실히 하자.
	}

	@Override
	public void down() {
		System.out.println("다운");
	}

	// setter

	public void isRight(boolean right) {
		this.right = right;
	}

	public void isLeft(boolean left) {
		this.left = left;
	}

}
