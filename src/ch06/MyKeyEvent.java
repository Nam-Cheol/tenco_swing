package ch06;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JTextArea;

/**
 * 키 이벤트 리스너 사용해보기
 */
public class MyKeyEvent extends JFrame implements KeyListener{

	private JTextArea textArea;
	static int temp = 0;
	
	public MyKeyEvent() {
		initData();
		setInitLayout();
		addEventListener();
	}
	
	private void initData() {
		setSize(500,500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false); // 프레임 사이즈 조절 불가
		
		textArea = new JTextArea();
	}
	
	private void setInitLayout() {
		// 좌표 기준으로 세팅하고 싶다면 null
		setLayout(new BorderLayout());
		add(textArea);
		setVisible(true);
	}
	
	private void addEventListener() {
		// textArea 위에서 사용자가 키보드를 누르면 감지해서
		// 나에게 (코드기준) 알려줘
		textArea.addKeyListener(this);
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// 응답 받고 싶다면 1
		//System.out.println(e.toString());
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// 응답 받고 싶다면 2
		//System.out.println("keyPressed : " + e.getKeyCode());
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// 응답 받고 싶다면 3
		// 문제 1
		// 제어문을 활용해서 방향키 위 아래 왼쪽 오른쪽이 눌었다가 떼면
		// 콘솔창에 한글로 위 아래 왼쪽 오른쪽 글자를 표기하시오
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			System.out.println("왼쪽");
		} else if (e.getKeyCode() == KeyEvent.VK_UP) {
			System.out.println("위");
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			System.out.println("오른쪽");
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			System.out.println("아래");
		}
	}
}
