package bubble.test.ex06;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * 현재 메인 쓰레드는 바쁨 백그라운드에서 계속 Player 의 움직임을 관찰할 예정
 */
public class BackgroundPlayerService implements Runnable {

	private BufferedImage image;
	private Player player;

	// 싱글톤 패턴 짜보기
	// DI --> 생성자 의존 주입
	public BackgroundPlayerService(Player player) {
		this.player = player;

		try {
			image = ImageIO.read(new File("img/backgroundMapService.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {

		while (true) {

			// 색상 확인 TODO 보정 값 필요
			Color leftColor = new Color(image.getRGB(player.getX() + 20, player.getY() + 25));
			Color rightColor = new Color(image.getRGB(player.getX() + 50 + 10, player.getY() + 25));
//			Color bottomColor = new Color(image.getRGB(player.getX(), player.getY() + 50));
			int bottomColorLeft = image.getRGB(player.getX(), player.getY() + 55);
			int bottomColorRight = image.getRGB(player.getX()+50 - 10, player.getY() + 55);
			System.out.println("bottomColorLeft : " + bottomColorLeft);
			System.out.println("bottomColorRight : " + bottomColorLeft);
			
			if(bottomColorLeft + bottomColorRight != -2) {
				// 여기는 멈춰야됨
				player.setDown(false);
				// 플레이어가 올라가는 상태가 아니라면
				// 플레이어가 내려가는 상태가 아니라면
			} else if(!player.isUp() && !player.isDown()) {
					player.down();
			}
//			else if(player.isUp()==false) {
//				player.down();
//			}
			
			// 바닥인 경우 --> 255 0 0 --> 바닥이라고 판단 가능
			// 바닥이 --> 0 0 255 블루


			// 왼쪽 벽에 충돌함.
			if (leftColor.getRed() == 255 && leftColor.getGreen() == 0 && leftColor.getBlue() == 0) {
				System.out.println("왼쪽 벽에 충돌함.");
				player.setLeftWallCrash(true);
				player.setLeft(false);
			} else if (rightColor.getRed() == 255 && rightColor.getGreen() == 0 && rightColor.getBlue() == 0) {
				System.out.println("오른쪽 벽에 충돌함.");
				player.setRightWallCrash(true);
				player.setRight(false);
//			} else if (bottomColor.getRed() == 0 && bottomColor.getGreen() == 0 && bottomColor.getBlue() == 255) {
//				System.out.println("블루에 충돌");
//				player.setDownWallCrash(true);
			} else {
				player.setLeftWallCrash(false);
				player.setRightWallCrash(false);
				player.setDownWallCrash(false);
			}


			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
 
			// 위 두 조건이 아니면 player 는 마음대로 움직일 수 있다.

		}
	}

}
