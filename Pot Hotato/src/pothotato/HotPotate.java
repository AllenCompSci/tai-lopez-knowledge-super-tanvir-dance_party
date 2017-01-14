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
		int circleRadius = 200;
		int centerX = width / 2, centerY = height / 2;
		int numPoints = 3;
		int period_MS = 3000;
		double theta_deg = (Mayne.animTimer.getMS() * 360 / period_MS) % 360;//angle in degrees, 60 rpm
		double theta = theta_deg * Constants.pi / 180d;
		double[] angles = new double[numPoints];
		for (int i = 0; i < numPoints; i++) {
			angles[i] = theta + (double) i * (2d * Constants.pi) / (double) numPoints;
		}
		int[] pointsX = new int[numPoints];
		int[] pointsY = new int[numPoints];
		for (int i = 0; i < numPoints; i++) {
			pointsX[i] = centerX + (int) Math.round(circleRadius * Math.cos(angles[i]));
			pointsY[i] = centerY + (int) Math.round(circleRadius * Math.sin(angles[i]));
		}
		art.setColor(Color.green);
		art.fillPolygon(pointsX, pointsY, numPoints);
		art.setColor(Color.black);
		art.drawPolygon(pointsX, pointsY, numPoints);

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
