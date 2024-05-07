package bubble.test.ex10;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Enemy extends JLabel implements Moveable {

	BubbleFrame mContext;

	// 살아있는 상태 0, 물방울에 갇힌 상태 1
	private int state;
	
	private int x;
	private int y;

	private ImageIcon enemyR, enemyL;

	// 움직임의 상태
	private boolean left;
	private boolean right;
	private boolean up;
	private boolean down;

	// Get in blue wall
	private boolean centerWallCrash;

	// 적군 속도 상태
	private final int SPEED = 3;
	private final int JUMPSPEED = 1;

	// 적군 방향 상태
	private EnemyWay enemyWay;

	
	public Enemy(BubbleFrame mContext) {
		initData();
		setInitLayout();
		this.mContext = mContext;
	}
	// setter
	
	public BubbleFrame getmContext() {
		return mContext;
	}
	
	
	public void setmContext(BubbleFrame mContext) {
		this.mContext = mContext;
	}
	
	
	public int getState() {
		return state;
	}
	
	
	public void setState(int state) {
		this.state = state;
	}
	
	
	public int getX() {
		return x;
	}
	
	
	public void setX(int x) {
		this.x = x;
	}
	
	
	public int getY() {
		return y;
	}
	
	
	public void setY(int y) {
		this.y = y;
	}
	
	
	public ImageIcon getEnemyR() {
		return enemyR;
	}
	
	
	public void setEnemyR(ImageIcon enemyR) {
		this.enemyR = enemyR;
	}
	
	
	public ImageIcon getEnemyL() {
		return enemyL;
	}
	
	
	public void setEnemyL(ImageIcon enemyL) {
		this.enemyL = enemyL;
	}
	
	
	public boolean isLeft() {
		return left;
	}
	
	
	public void setLeft(boolean left) {
		this.left = left;
	}
	
	
	public boolean isRight() {
		return right;
	}
	
	
	public void setRight(boolean right) {
		this.right = right;
	}
	
	
	public boolean isUp() {
		return up;
	}
	
	
	public void setUp(boolean up) {
		this.up = up;
	}
	
	
	public boolean isDown() {
		return down;
	}
	
	
	public void setDown(boolean down) {
		this.down = down;
	}
	
	
	public boolean isCenterWallCrash() {
		return centerWallCrash;
	}
	
	
	public void setCenterWallCrash(boolean centerWallCrash) {
		this.centerWallCrash = centerWallCrash;
	}
	
	
	public EnemyWay getEnemyWay() {
		return enemyWay;
	}
	
	
	public void setEnemyWay(EnemyWay enemyWay) {
		this.enemyWay = enemyWay;
	}
	
	
	public int getSPEED() {
		return SPEED;
	}
	
	
	public int getJUMPSPEED() {
		return JUMPSPEED;
	}

	private void initData() {
		enemyL = new ImageIcon("img/enemyL.png");
		enemyR = new ImageIcon("img/enemyR.png");
		
		state = 0;
		
		// 처음 실행 시 적군 위치
		x = 720;
		y = 175;

		// 적군 방향 상태
		left = false;
		right = false;
		up = false;
		down = false;

		enemyWay = enemyWay.LEFT;
		left();
	}



	private void setInitLayout() {
		this.setIcon(enemyL); // JLabel 의 기능
		this.setLocation(x, y);
		this.setSize(50, 50);

	}

	@Override
	public void left() {
		enemyWay = enemyWay.LEFT;
		left = true;
		setIcon(enemyL);

		// <- <- 반복

		new Thread(new Runnable() {

			@Override
			public void run() {
				while (left) {
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					x -= SPEED;
					setLocation(x, y);
				}

			}
		}).start();

		// 왼쪽 방향키 이벤트 발생 시
		// 이미지를 왼쪽으로 보는 이미지로 세팅
		// 왼쪽으로 가고 있는 상태

		// 멈춰있는 상태란 개념 필요

	} // end of left

	@Override
	public void right() {
		enemyWay = enemyWay.RIGHT;
		right = true;
		setIcon(enemyR);

		new Thread(new Runnable() {

			@Override
			public void run() {
				while (right) {
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					x += SPEED;
					setLocation(x, y);
				}
			}
		}).start();

	} // end of right

	@Override
	public void up() {
		up = true;
		new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 130; i++) {
					y -= JUMPSPEED;
					setLocation(x, y);
					try {
						Thread.sleep(5);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				up = false;
				up = false;
				down();

			}

		}).start();
		// 객체의 상태값을 잘 조절해야 한다.

	}

	@Override
	public void down() {
		down = true;

		new Thread(new Runnable() {

			@Override
			public void run() {
				while (down) {
					y += SPEED;
					setLocation(x, y);

					try {
						Thread.sleep(3);
					} catch (InterruptedException e) {

						e.printStackTrace();
					}
				}
				down = false;

			}
		}).start();
	}
	
	public void beAttack() {
	}
}