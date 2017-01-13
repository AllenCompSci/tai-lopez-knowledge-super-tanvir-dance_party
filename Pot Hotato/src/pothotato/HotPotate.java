package pothotato;

import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;

/**
 * Created by 223671 on 1/12/2017.
 */
public class HotPotate implements Runnable, WindowListener {
	private boolean running, done = false;
	Frayme frame;
	private Image imgBuffer;
	private Font f;

	HotPotate() {
		running = true;
		frame = new Frayme("Hot Potato", new Dimension(800, 600));
		frame.setExtendedState(Frame.MAXIMIZED_BOTH);//auto maximize window, may not be desired depending on if this is a fullscreen game
		frame.addWindowListener(this);
		frame.setVisible(true);
		imgBuffer = frame.createImage(frame.getWidth(), frame.getHeight());
	}

	@Override
	public void run() {
		while (running) {
			Background.updateColor();
			draw();
			try {
				Thread.sleep(10);
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
		art.drawString("Interval: " + Background.getColorIntervalMS(), 50, 150);
		art.setColor(Background.getInitialColor());
		art.fillRect(15, 165, 30, 30);
		art.setColor(Color.black);
		art.drawString("Beginning Color: " + Background.getInitialColor().getRed() + ", " + Background.getInitialColor().getGreen() + ", " + Background.getInitialColor().getBlue(), 50, 200);
		art.drawString("Current Color: " + Background.getCurrentColor().getRed() + ", " + Background.getCurrentColor().getGreen() + ", " + Background.getCurrentColor().getBlue(), 50, 250);
		art.setColor(Background.getDestinationColor());
		art.fillRect(15, 265, 30, 30);
		art.setColor(Color.black);
		art.drawString("Destination Color: " + Background.getDestinationColor().getRed() + ", " + Background.getDestinationColor().getGreen() + ", " + Background.getDestinationColor().getBlue(), 50, 300);



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
