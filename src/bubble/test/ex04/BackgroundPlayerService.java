package bubble.test.ex04;

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
			Color leftColor = new Color(image.getRGB(player.getX() + 10, player.getY() + 25));
			Color rightColor = new Color(image.getRGB(player.getX() + 50 + 10, player.getY() + 25));
			Color downColor = new Color(image.getRGB(player.getX(), player.getY() + 50));

			
			// 왼쪽 벽에 충돌함.
			if (leftColor.getRed() == 255 && leftColor.getGreen() == 0 && leftColor.getBlue() == 0) {
				System.out.println("왼쪽 벽에 충돌함.");
				player.setLeftWallCrash(true);
				player.setLeft(false);
			} else if (rightColor.getRed() == 255 && rightColor.getGreen() == 0 && rightColor.getBlue() == 0) {
				System.out.println("오른쪽 벽에 충돌함.");
				player.setRightWallCrash(true);
				player.setRight(false);
			} else if (downColor.getRed() == 0 && downColor.getGreen() == 0 && downColor.getBlue() == 255) {
				System.out.println("블루에 충돌");
				player.setDownWallCrash(true);
				player.setDown(false);
//				if (downColor.getRed() == 255 && downColor.getGreen() == 255 && downColor.getBlue() == 255) {
//					player.setDownWallCrash(false);
//					player.setDown(true);
//				}
//			} else if (downColor.getRed() == 255 && downColor.getGreen() == 255 && downColor.getBlue() == 255) {
//				player.down();
//				if (downColor.getRed() == 255 && downColor.getGreen() == 0 && downColor.getBlue() == 0) {
//					player.setDown(false);
//				}
			} else {
				player.setLeftWallCrash(false);
				player.setRightWallCrash(false);
				player.setDownWallCrash(false);
			}

//			if (downColor.getRed() == 255 && downColor.getGreen() == 255 && downColor.getBlue() == 255) {
//				if (!(downColor.getRed() == 255 && downColor.getGreen() == 0 && downColor.getBlue() == 0)) {
//					player.down();
//				}
//			}else {
//				player.setDown(false);
//			}

			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			// 위 두 조건이 아니면 player는 마음대로 움직일 수 있다.

		}
	}

}
