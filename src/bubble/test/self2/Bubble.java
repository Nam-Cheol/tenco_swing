package bubble.test.self2;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Bubble extends JLabel implements Moveable {

	private Player player;
	private BufferedImage image;

	private int x;
	private int y;

	// 움직임 상태
	private boolean left;
	private boolean right;
	private boolean up;
	private boolean redWallCrash;

	// 적군을 맞춘 상태
	private int state; // 0.(기본 물방울) 1. (적을 가둔 상태의 물방울)

	private ImageIcon bubble; // 기본 물방울
	private ImageIcon bubbled; // 적을 가둔 물방울
	private ImageIcon bomb; // 터진 물방울

	// 연관관계, 의존성 composition, 생성자 의존 주입(DI)
	public Bubble(Player player) {
		this.player = player;
		try {
			image = ImageIO.read(new File("img/backgroundMapService.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		initData();
		setInitLayout();
		initThread();
	}

	// get, set

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
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

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public ImageIcon getBubble() {
		return bubble;
	}

	public void setBubble(ImageIcon bubble) {
		this.bubble = bubble;
	}

	public ImageIcon getBubbled() {
		return bubbled;
	}

	public void setBubbled(ImageIcon bubbled) {
		this.bubbled = bubbled;
	}

	public ImageIcon getBomb() {
		return bomb;
	}

	public void setBomb(ImageIcon bomb) {
		this.bomb = bomb;
	}

	public boolean isRedWallCrash() {
		return redWallCrash;
	}

	public void setRedWallCrash(boolean redWallCrash) {
		this.redWallCrash = redWallCrash;
	}

	private void initData() {
		bubble = new ImageIcon("img/bubble.png");
		bubbled = new ImageIcon("img/bubbled.png");
		bomb = new ImageIcon("img/bomb.png");

		left = false;
		right = false;
		up = false;
		redWallCrash = false;
		state = 0;
	}

	private void setInitLayout() {
		this.x = player.getX() - 30;
		this.y = player.getY();

		setIcon(bubble);
		setSize(50, 50);
		setLocation(x, y);
		setVisible(true);
	}

	// 공통으로 사용하는 부분을 메서드로 만들어보자.
	// 내부에서만 사용할 예정.

	private void initThread() {
		// 버블은 스레드가 하나면 된다.

		new Thread(() -> {
			if (player.playerWay == PlayerWay.LEFT) {
				left();
				up();
				bubbleBomb();
			} else {
				right();
				up();
				bubbleBomb();
			}
		}).start();

	}

	@Override
	public void left() {
		left = true;

		while (left) {
			x--;
			setLocation(x, y);
			Color color = new Color(image.getRGB(x + 10, y + 25));
			if (color.getRed() == 255 && color.getGreen() == 0 && color.getBlue() == 0) {
				setLeft(false);
			}

			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return;

	}

	@Override
	public void right() {
		right = true;
		while (right) {
			x++;
			setLocation(x, y);
			Color color = new Color(image.getRGB(x + 50, y + 25));
			if (color.getRed() == 255 && color.getGreen() == 0 && color.getBlue() == 0) {
				setRight(false);
			}
			if (color.getRed() == 0 && color.getGreen() == 0 && color.getBlue() == 0) {
				System.out.println("흰색!");
				return;
			}
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void up() {
		up = true;

		while (up) {
			y--;
			setLocation(x, y);
			Color color = new Color(image.getRGB(x + 20, y));
			if (color.getRed() == 255 && color.getGreen() == 0 && color.getBlue() == 0) {
				setUp(false);
			}
			if (player.playerWay == PlayerWay.LEFT && color.getRed() == 0 && color.getGreen() == 0
					&& color.getBlue() == 255) {
				if(color.getRed() == 255 && color.getGreen() == 255 && color.getBlue() == 255) {
					System.out.println("11");
					setUp(true);
				}
				right();
			} else if (player.playerWay == PlayerWay.RIGHT && color.getRed() == 0 && color.getGreen() == 0
					&& color.getBlue() == 255) {
				if(color.getRed() == 255 && color.getGreen() == 255 && color.getBlue() == 255) {
					System.out.println("11");
					setUp(true);
				}
				left();
			} 
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void bubbleBomb() {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			setIcon(bomb);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			setIcon(null);

	}
}
