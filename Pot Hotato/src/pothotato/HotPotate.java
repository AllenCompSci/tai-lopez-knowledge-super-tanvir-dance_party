package pothotato;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by 223671 on 1/12/2017.
 */
public class HotPotate implements Runnable, WindowListener {
	private boolean running, done = false;
	private int maxFPS = 60, currCountdown = 3;
	Frayme frame;
	private Image imgBuffer;
	private BufferedImage stringBuffer;
	private int stringBufferW = 200, stringBufferH = 200;
	private Graphics2D tres;
	private Font f;
	private SpinningPolygon countdownPoly;


	HotPotate() {


        countdownPoly = new SpinningPolygon(5, 1000, 0, 0, Color.green, 200, Mayne.animTimer);
		running = true;
		frame = new Frayme("Hot Potato", new Dimension(800, 600));
		frame.setExtendedState(Frame.MAXIMIZED_BOTH);//auto maximize window, may not be desired depending on if this is a fullscreen game
		frame.addWindowListener(this);
		frame.setVisible(true);
		imgBuffer = frame.createImage(frame.getWidth(), frame.getHeight());
		stringBuffer = new BufferedImage(stringBufferW,stringBufferH,BufferedImage.TYPE_3BYTE_BGR);
		tres = (Graphics2D) stringBuffer.getGraphics();
		tres.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		tres.setFont(new Font("Arial", Font.PLAIN, 50));
		f = tres.getFont();
        tres.setColor(countdownPoly.getInnerColor());
		tres.fillRect(0, 0, stringBufferW, stringBufferH);//
       	tres.setColor(Color.BLACK);
		DrawingTools.drawTextAround(f, "3", stringBufferW/2, stringBufferH/2, tres);
		Mayne.countdownTimer.start();

	}

	@Override
	public void run() {
		while (running) {
			int width = frame.getWidth(), height = frame.getHeight();
			Background.updateColor();
			countdownPoly.setCenter(width / 2, height / 2);
			int countdownStatus = Mayne.countdownTimer.getMS() / 1000 + 1;
			if(countdownStatus < currCountdown) {
				tres.dispose();
				stringBuffer = new BufferedImage(stringBufferW,stringBufferH,BufferedImage.TYPE_3BYTE_BGR);
				tres = (Graphics2D) stringBuffer.getGraphics();
				tres.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				tres.setFont(new Font("Arial", Font.PLAIN, 50));
				f = tres.getFont();
				tres.setColor(countdownPoly.getInnerColor());
				tres.fillRect(0, 0, stringBufferW, stringBufferH);
				tres.setColor(Color.black);
				DrawingTools.drawTextAround(f, Integer.toString(countdownStatus), stringBufferW/2, stringBufferH/2, tres);
				currCountdown = countdownStatus;
			}
			if(Mayne.countdownTimer.getMS() == 0) {
				tres.dispose();
				stringBuffer = new BufferedImage(stringBufferW,stringBufferH,BufferedImage.TYPE_3BYTE_BGR);
				tres = (Graphics2D) stringBuffer.getGraphics();
				tres.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				tres.setFont(new Font("Arial", Font.PLAIN, 50));
				f = tres.getFont();
				tres.setColor(countdownPoly.getInnerColor());
				tres.fillRect(0, 0, stringBufferW, stringBufferH);
				tres.setColor(Color.black);
				DrawingTools.drawTextAround(f, "GO!", stringBufferW/2, stringBufferH/2-(stringBufferH/10), tres); // Modification of 10% reduction
			}
			draw();
			try {
				Thread.sleep(Math.round(1000d / (double) maxFPS));//this math might be wrong, if so oopsy doops
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void draw() {
		Graphics2D art = (Graphics2D) imgBuffer.getGraphics();
		art.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		art.setFont(new Font("Arial", Font.PLAIN, 50));
		f = art.getFont();

		int width = frame.getWidth(), height = frame.getHeight();
		art.setColor(Background.getCurrentColor());
		art.fillRect(0, 0, width, height);
		art.setColor(Color.black);
		DrawingTools.drawCenteredText(f, "HOT POTATO", 100, art);
		int countdownX = width / 2, countdownY = height / 2;
		countdownPoly.draw(art);
		AffineTransform trans = new AffineTransform();

		trans.setTransform(new AffineTransform());
		art.translate(countdownX - stringBufferW/2, countdownY - stringBufferH/2);
		trans.rotate(countdownPoly.getAngle(), stringBufferW/2, stringBufferH/2);
		art.drawImage(stringBuffer, trans, null);
		art.translate(0, 0);

		art = (Graphics2D) frame.getGraphics();
		if (art != null) {
			imgBuffer = Resizer.PROGRESSIVE_BILINEAR.resize((BufferedImage) imgBuffer, frame.getWidth(), frame.getHeight());
			art.drawImage(imgBuffer, 0, 0, frame.getWidth(), frame.getHeight(), 0, 0, frame.getWidth(), frame.getHeight(), null);
			art.dispose();
		}
	}

	@Override
	public void windowOpened(WindowEvent e) {

	}

	@Override
	public void windowClosing(WindowEvent e) {
		frame.setVisible(false);
		running = false;
		frame.dispose();
		done = true;
	}

	@Override
	public void windowClosed(WindowEvent e) {
		if (done) {
			System.exit(0);
		}
		try {
			Thread.sleep(100);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void windowIconified(WindowEvent e) {

	}

	@Override
	public void windowDeiconified(WindowEvent e) {

	}

	@Override
	public void windowActivated(WindowEvent e) {

	}

	@Override
	public void windowDeactivated(WindowEvent e) {

	}
}
