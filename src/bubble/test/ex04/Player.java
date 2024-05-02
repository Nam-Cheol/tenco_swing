package bubble.test.ex04;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import lombok.Getter;

public class Player extends JLabel implements Moveable {

	private int x;
	private int y;
	private ImageIcon playerR, playerL;

	// 움직임의 상태
	private boolean left;
	private boolean right;
	private boolean up;
	private boolean down;

	// 벽에 충돌한 상태
	private boolean leftWallCrash;
	private boolean rightWallCrash;
	private boolean upWallCrash;
	private boolean downWallCrash;

	// 플레이어의 속도 상태
	private final int SPEED = 4;
	private final int JUMPSPEED = 2;

	// get set

	public Player() {
		initData();
		setInitLayout();
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

	public ImageIcon getPlayerR() {
		return playerR;
	}

	public void setPlayerR(ImageIcon playerR) {
		this.playerR = playerR;
	}

	public ImageIcon getPlayerL() {
		return playerL;
	}

	public void setPlayerL(ImageIcon playerL) {
		this.playerL = playerL;
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

	public boolean isLeftWallCrash() {
		return leftWallCrash;
	}

	public void setLeftWallCrash(boolean leftWallCrash) {
		this.leftWallCrash = leftWallCrash;
	}

	public boolean isRightWallCrash() {
		return rightWallCrash;
	}

	public void setRightWallCrash(boolean rightWallCrash) {
		this.rightWallCrash = rightWallCrash;
	}

	public int getSPEED() {
		return SPEED;
	}

	public int getJUMPSPEED() {
		return JUMPSPEED;
	}

	public boolean isUpWallCrash() {
		return upWallCrash;
	}
	
	public void setUpWallCrash(boolean upWallCrash) {
		this.upWallCrash = upWallCrash;
	}
	
	public boolean isDownWallCrash() {
		return downWallCrash;
	}
	
	public void setDownWallCrash(boolean downWallCrash) {
		this.downWallCrash = downWallCrash;
	}
	
	// ----------------------------------------------


	private void initData() {
		playerR = new ImageIcon("img/playerR.png");
		playerL = new ImageIcon("img/playerL.png");
		// 처음 실행 시 초기값 셋팅
		x = 450;
		y = 535;

		// 가만히 멈춰있는 상태
		left = false;
		right = false;
		up = false;
		down = false;

		leftWallCrash = false;
		rightWallCrash = false;

	}

	private void setInitLayout() {
		setIcon(playerR);
		setSize(50, 50);
		setLocation(x, y);
	}

	@Override
	public void left() {
		left = true;
		// <-<-<-
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
				for(int i = 0; i < 180 / JUMPSPEED; i++) {
					y -= JUMPSPEED;
					setLocation(x, y);
					try {
						Thread.sleep(5);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
				// 객체의 상태값을 잘 조절해야 한다.
				up = false; 
				down();
//				for (int i = 0; i < 130; i++) {
//					if (i < 65) {
//						y -= JUMPSPEED;
//					} else {
//						y += JUMPSPEED;
//					}
//					try {
//						Thread.sleep(8);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//					setLocation(x, y);
//				}
				//up = false;
			}
		}).start();
		// 상태값 처리를 확실히 하자.
	}

	@Override
	public void down() {
		System.out.println("다운");
		down = true; 
		new Thread(new Runnable() {
			@Override
			public void run() {
				for(int i = 0; i < 180 / JUMPSPEED; i++) {
					y += JUMPSPEED;
					if(downWallCrash) {
						return;
					}else {
						downWallCrash = false;
						down = true;
					}
					setLocation(x, y);
					try {
						Thread.sleep(5);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
			}
		}).start();
		// 상태값 처리를 확실히 하자.
		down = false;
	}

	// setter

	public void isRight(boolean right) {
		this.right = right;
	}

	public void isLeft(boolean left) {
		this.left = left;
	}

}
