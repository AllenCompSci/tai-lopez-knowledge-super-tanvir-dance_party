import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Your Local Weeb on 2/6/2017.
 */
public class Girrafics extends Graphics{
	public BufferedImage bird, blood, dog, hadouken;
	public Image background;
	Duck duck1 = new Duck();
	private boolean isDead = false;
	long start = System.currentTimeMillis();
	private boolean runOnce;
	int dogi, hadoukeni;
	boolean clicking;
	Graphics2D g2d;

	public Girrafics() {
		super("Giraffe!!!");
		frame.setSize(1280, 720);
		runOnce = true;
		dogi = 0;
		hadoukeni = 0;
		load();

	}
	public void load(){
		try{
			background = ImageIO.read(new File("Background.jpeg"));

			bird = ImageIO.read(new File("bird.png"));

			blood = ImageIO.read(new File("boom.gif"));

			dog = ImageIO.read(new File("DOggO.png"));

			hadouken = ImageIO.read(new File("hadouken.png"));
		}
		catch(IOException e){

		}
	}
	public void draw(){

		startDraw();
		art.drawImage(background, 0, 0, width, height, 0, 0, width, height, null);
		art.setColor(Color.BLACK);

		if(duck1.isDead()) {
			art.drawImage(blood, duck1.getxPos(), duck1.getyPos(), null);
			if(dogi == 151)
				dogi = 60;
			dOggoO(dogi++);
			//hadouken(hadoukeni++);

		}
		else{
			art.drawImage(bird, duck1.getxPos(), duck1.getyPos(), null);
		}
		endDraw();

	}

	public void dOggoO(int i) {
		if(i > 59){
			if(i < 75){
				art.drawImage(dog.getSubimage(168*(6), 0, 168, 168), duck1.getxPos(), 432, null);
			}
			else if(i < 90){
				art.drawImage(dog.getSubimage(168*(6), 0, 168, 168), duck1.getxPos(), 434, null);
			}
			else if(i < 105) {
				art.drawImage(dog.getSubimage(168 * (6), 0, 168, 168), duck1.getxPos(), 435, null);
			}
			else if (i < 120){
				art.drawImage(dog.getSubimage(168*(6), 0, 168, 168), duck1.getxPos(), 437, null);
			}
			else if(i < 135){
				art.drawImage(dog.getSubimage(168 * (6), 0, 168, 168), duck1.getxPos(), 435, null);
			}
			else{
				art.drawImage(dog.getSubimage(168*(6), 0, 168, 168), duck1.getxPos(), 434, null);
			}
		}
		else
		art.drawImage(dog.getSubimage(168*(int)(i/10), 0, 168, 168), duck1.getxPos(), 432, null);

	}



	public void hadouken(int i){
		if(i > 59){
			if(i%5 == 0){
				art.drawImage(hadouken.getSubimage((625*i), 0, 625, 449), duck1.getxPos(), duck1.getyPos(), null);
			}
			/*if(i%5 == 1 || i % 5 == 4){
				art.drawImage(hadouken.getSubimage((625*i), 0, 625, 449), duck1.getxPos(), duck1.getyPos(), null);
			}*/
			else{
				art.drawImage(hadouken.getSubimage((625* (int)(i/10)), 0, 625, 449), duck1.getxPos(), duck1.getyPos(), null);
			}


		}
	}


	public void run() {
		while(running){
			if(visible){
				updateSize();
				if (duck1.isOnScreen()) {
					duck1.updatePosition();
					if (System.currentTimeMillis() - start > 2000 && Math.random() > 0.33) {
						duck1.changeDirection();
						start = System.currentTimeMillis();
					}
				} else {
					duck1.updatePosition();
					duck1.fixDirection();
				}
				draw();
				if(frame.clicking()){
					try{
						frame.setClick(false);
						Color ColorPoint = getPointerColor();
						if(duck1.isHit(frame.mouseX, frame.mouseY)){

						}
						else{
							System.out.println(ColorPoint);
						}
						//System.out.println(ColorPoint);
					}
					catch(AWTException awte){

					}
				}
			}
			try{
				Thread.sleep(10);

			}
			catch(InterruptedException ie){
				ie.printStackTrace();
			}
		}
	}




	public Color getPointerColor() throws AWTException {
		Point coordinates = new Point(frame.mouseX, frame.mouseY);
		Robot robot = new Robot();
		return robot.getPixelColor((int) coordinates.getX(), (int) coordinates.getX());
	}
}
