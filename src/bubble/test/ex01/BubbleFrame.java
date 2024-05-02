package bubble.test.ex01;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class BubbleFrame extends JFrame {

	private JLabel backgroundMap;
	private Player player; // <-- composition 관계

	public BubbleFrame() {
		initData();
		setInitLayout();
		addEventListener();
	}

	private void initData() {
		setTitle("Bubble! Bubble!");
		setSize(1000, 640);
		backgroundMap = new JLabel(new ImageIcon("img/backgroundMap.png"));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setContentPane(backgroundMap);

		player = new Player();

	}

	private void setInitLayout() {
		setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null); // JFrame을 모니터 정중앙에 배치
		setVisible(true);

		add(player);
	}

	private void addEventListener() {

		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				System.out.println("key code : " + e.getKeyCode());

				switch (e.getKeyCode()) {

				case KeyEvent.VK_LEFT:
					// 구현
					player.left();
					break;
				case KeyEvent.VK_RIGHT:
					// 구현
					player.right();
					break;
				case KeyEvent.VK_UP:
					// 구현
					player.up();
					break;

				}

			}// end of keyPressed
		});

	}

	//코드 테스트
	public static void main(String[] args) {
		new BubbleFrame();
	}// end of main
	
}