import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

@SuppressWarnings("serial")
public class graphics extends Component implements Runnable, KeyListener, WindowListener {
	public final String TITLE = "Game";
	public final Dimension SIZE = new Dimension (1200,900);
	public Frame frame;
	private boolean isRunning, isDone;
	public Image imgBuffer;
	public BufferedImage desu, collision[];
		private long start, randomtime, speed;
		private boolean begin, hit, listen, premature, end;
	//boolean up, down, left, right, space, hit = false;
	
	public int collisionframe = 0;
	public int xpos, ypos = 500;
	
	
	public void load(){
		try{
			desu = ImageIO.read(new File ("the legend.png"));
		}
		catch(IOException e){
		}
	}
	
	public graphics(){
		load();
		frame = new Frame();
		frame.addKeyListener(this);
		frame.addWindowListener(this);
		frame.setSize(SIZE);
		frame.setTitle(TITLE);
		isRunning = true;
		isDone = false;
		frame.setVisible(true);
		//the screen was going epileptic black, ask kyle how he did it
		imgBuffer =frame.createImage(frame.getWidth(), frame.getHeight());// frame.createImage(SIZE.width, SIZE.height); // new BufferedImage(WIDTH, HEIGHT,BufferedImage.TYPE_INT_RGB);
		start = System.currentTimeMillis();
		randomtime = start + (long) Math.random()*20000 + 10000;
		begin = false;
		listen = false;
		hit = false;
		premature = false;
		end = false;
		//run();
		}
	
	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		while(true){
			if(isDone){
				System.exit(0);
			}
			
			try{
				Thread.sleep(30);
			}
			catch(InterruptedException ie){
				ie.printStackTrace();
			}
		}
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		frame.setVisible(false);
		isRunning = false;
		frame.dispose();
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(listen == true && hit == false){
			if(key == KeyEvent.VK_SPACE){
				speed = System.currentTimeMillis() - randomtime;
				hit = true;
				System.out.println("Your reaction time is: " + speed + "ms");
				listen = false;
				
			}
			
		}
		else if(listen == true){
			premature = true;
			hit = true;
			System.out.println("You were premature heheXD");
			listen = false;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run() {
		
		while(isRunning){
			if(true){
				//System.out.println(System.currentTimeMillis() + " : " + randomtime);
				if(!listen && !hit && System.currentTimeMillis() >= randomtime && !end){
					begin = true;
				}
				frame.repaint();
				draw();
			}
			try{
				Thread.sleep(32);
			}
			catch(InterruptedException ie){
				ie.printStackTrace();
			}
		}
		
		isDone = true;
		
	}
	
	
	private void draw(){
		
		
		Graphics2D kek = (Graphics2D)imgBuffer.getGraphics();
	
		kek.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		
		
		kek.setFont(new Font("Lucida Sans Typewriter", Font.PLAIN, 50));
	
		if(begin == true){
			kek.setColor(Color.white);
			kek.fillRect(0, 0, SIZE.width, SIZE.height);
			premature = false;
			System.out.println("SPACE");
			kek.setColor(Color.black);
			kek.drawString("PRESS SPACE BAR", 200, 400);
			listen = true;
			//randomtime = System.currentTimeMillis() + (long) Math.random()*10000 + 1000;
			kek.setColor(Color.white);
			
		}
		if(listen && hit == true){
			kek.setColor(Color.white);
			kek.fillRect(0, 0, SIZE.width, SIZE.height);
			begin = false;
			kek.setColor(Color.black);
			kek.drawString("Your reaction time is: " + speed + "ms", 200, 400);
			//randomtime = start + (long) Math.random()*10000 + 1000;
			System.out.println("WIN?");
			//hit = false; 
			listen = false;
			//end = true;
		}
		if(listen && premature == true){
			kek.setColor(Color.white);
			kek.fillRect(0, 0, SIZE.width, SIZE.height);
			begin = false;
			kek.setColor(Color.black);
			kek.drawString("You were premature, idiot.", 200, 400);
			//randomtime = start + (long)Math.random()*10000 + 1000;
			//premature = false;
			listen = false;
			end = true;
		}

		kek = (Graphics2D) frame.getGraphics();
		
		kek.drawImage(imgBuffer, 0, 0, SIZE.width, SIZE.height, 0, 0, SIZE.width, SIZE.height, null);
 
		kek.dispose();
		
	}
}
