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
	public BufferedImage bird;
	public Image background;
	Duck duck1 = new Duck();
	long start = System.currentTimeMillis();
	private boolean runOnce;
	boolean clicking;
	Graphics2D g2d;

	public Girrafics() {
		super("Giraffe!!!");
		frame.setSize(1280, 720);
		runOnce = true;

		load();

	}
	public void load(){
		try{
			background = ImageIO.read(new File("Background.jpeg"));

			bird = ImageIO.read(new File("bird.png"));
		}
		catch(IOException e){

		}
	}
	public void draw(){

		startDraw();


		art.drawImage(background, 0, 0, width, height, 0, 0, width, height, null);
		art.setColor(Color.BLACK);
		art.drawImage(bird, duck1.getxPos(), duck1.getyPos(), null);

		endDraw();

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
							System.out.println("YOU HIT IT BOIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII");
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
