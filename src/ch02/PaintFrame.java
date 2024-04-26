package ch02;

import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

// 내부 클래스를 활용해서 코드를 완성해주세요.
public class PaintFrame extends JFrame {

	PaintPanel paintPanel;
	
	public PaintFrame() {
		initData();
		setInitLayout();
	}
	
	private void initData() {
		setTitle("저 푸른 초원 위에");
		setSize(1000, 1000);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		paintPanel = new PaintPanel();
	}
	
	private void setInitLayout() {
		add(paintPanel);
		setVisible(true);
	}
	
	
	class PaintPanel extends JPanel {
		@Override
		public void paint(Graphics g) {
			super.paint(g);
			g.drawLine(500, 100, 200, 400);
			g.drawLine(500, 100, 800, 400);
			g.drawLine(625, 430, 625, 580);
			g.drawLine(550, 505, 700, 505);
			g.drawRect(200, 400, 600, 500);
			g.drawRect(550, 430, 150, 150);
			g.drawRoundRect(400, 600, 200, 300, 200, 50);
			g.drawOval(420, 750, 30, 30);
			
			
		}
	}
	
}
