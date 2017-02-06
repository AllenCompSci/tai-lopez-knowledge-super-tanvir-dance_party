import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by 254397 on 2/6/2017.
 */
public class Girrafics extends Graphics {
	public BufferedImage bird;
	public Girrafics() {
		super("Giraffe!!!");
		load();
	}
	public void load(){
		try{
			bird = ImageIO.read(new File("bird.png"));
		}
		catch(IOException e){

		}
	}
	public void draw(){
		startDraw();
		art.setColor(Color.BLACK);
		art.drawImage(bird, 100, 100, null);
		endDraw();
	}
	public void run() {
		while(running){
			if(visible){
				updateSize();
				draw();
			}
			try{
				Thread.sleep(10);

			}
			catch(InterruptedException ie){
				ie.printStackTrace();
			}
		}
	}
}
