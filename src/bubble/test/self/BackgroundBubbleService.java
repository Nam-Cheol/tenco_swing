package bubble.test.self;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BackgroundBubbleService implements Runnable{

	private BufferedImage image;
	private Player player;
	private Bubble bubble;
	
	public BufferedImage getImage() {
		return image;
	}

	public BackgroundBubbleService(Player player, Bubble bubble) {
		this.player = player;
		this.bubble = bubble;
		try {
			image = ImageIO.read(new File("img/backgroundMapService.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {

		while(true) {
			
			Color BLeftColor = new Color(image.getRGB(player.attack().getX()-50,player.attack().getY()));
			
			System.out.println(BLeftColor);
			System.out.println(player.attack().getX()-50);
			System.out.println(player.attack().getY());
			System.out.println(bubble.isRedWallCrash());
			if (BLeftColor.getRed() == 255 && BLeftColor.getGreen() == 0 && BLeftColor.getBlue() == 0) {
				bubble.setRedWallCrash(true);
			}
			if(bubble.isRedWallCrash()) {
				bubble.setLeft(false);
			}
			System.out.println(bubble.isRedWallCrash());
			System.out.println(BLeftColor);

			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}
