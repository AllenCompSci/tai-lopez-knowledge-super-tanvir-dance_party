package pothotato;

import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

/**
 * Created by 223671 on 1/12/2017.
 */
public class HotPotate implements Runnable, WindowListener {
	private boolean running, done = false;
	private int maxFPS = 60;
	Frayme frame;
	private Image imgBuffer;
	private BufferedImage stringBuffer;
	private Font f;
	private SpinningPolygon countdownPoly;

	HotPotate() {
		countdownPoly = new SpinningPolygon(3, 10000, 0, 0, Color.green, 200, Mayne.animTimer);
		running = true;
		frame = new Frayme("Hot Potato", new Dimension(800, 600));
		frame.setExtendedState(Frame.MAXIMIZED_BOTH);//auto maximize window, may not be desired depending on if this is a fullscreen game
		frame.addWindowListener(this);
		frame.setVisible(true);
		imgBuffer = frame.createImage(frame.getWidth(), frame.getHeight());
		stringBuffer = new BufferedImage(100,100,BufferedImage.TYPE_3BYTE_BGR);
		Graphics2D tres = (Graphics2D) stringBuffer.getGraphics();
		tres.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		tres.setFont(new Font("Arial", Font.PLAIN, 50));
		f = tres.getFont();
		DrawingTools.drawTextAround(f, "3", 50, 50, tres);


	}

	@Override
	public void run() {
		while (running) {
			int width = frame.getWidth(), height = frame.getHeight();
			Background.updateColor();
			countdownPoly.setCenter(width / 2, height / 2);
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
		art.translate(countdownX - 50, countdownY - 50);
		trans.rotate(-1 * countdownPoly.getAngle(), 50, 50);
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
