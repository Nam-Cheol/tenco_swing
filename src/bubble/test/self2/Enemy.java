package bubble.test.self2;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Enemy extends JLabel{
	
	private int x;
	private int y;
	
	private ImageIcon enemyL;
	private ImageIcon enemyR;
	
//	private BufferedImage image;
	
	private boolean left;
	private boolean right;
	private boolean down;
	
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
	
	public Enemy() {
		initData();
		setInitLayout();
		//initThread();
	}
	
	private void initData() {
		x = 500;
		y = 0;
		
		enemyL = new ImageIcon("img/enemyL.png");
		enemyR = new ImageIcon("img/enemyR.png");
		
		left = false;
		right = false;
	}
	
	private void setInitLayout() {
		
		setIcon(enemyL);
		setSize(50,50);
		setLocation(x, y);
		setVisible(true);
	}
	
	private void initThread() {
		
		new Thread(() -> {
//			down();
		}).start();
		
	}
	
//	private void mapSetting(Enemy enemy, int y) {
//		for (int i = 0; i < y; i++) {
//			enemy.y++;
//			try {
//				Thread.sleep(100);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
//	}
	
	public void down() {
		for (int i = 0; i < 200; i++) {
			this.y++;
			setLocation(x, y);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
