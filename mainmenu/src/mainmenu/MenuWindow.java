package mainmenu;

import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;

/**
 * Created by 254397 on 1/25/2017.
 */
public class MenuWindow implements Runnable, WindowListener, WindowFocusListener {
	private boolean running, done;
	private Fraime frame;
	private Image imgBuffer;
	private Font f;

	public MenuWindow() {
		running = true;
		done = false;
		frame = new Fraime("Main Menu", new Dimension(8, 8));
		frame.setExtendedState(Frame.MAXIMIZED_BOTH);
		frame.addWindowListener(this);
		frame.addWindowFocusListener(this);
		frame.setVisible(true);
		imgBuffer = frame.createImage(frame.getWidth(), frame.getHeight());
	}

	@Override
	public void run() {
		while (running) {
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


		art = (Graphics2D) frame.getGraphics();
		if (art != null) {
			imgBuffer = Resizer.PROGRESSIVE_BILINEAR.resize((BufferedImage) imgBuffer, width, height);
			art.drawImage(imgBuffer, 0, 0, width, height, 0, 0, width, height, null);
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


	@Override
	public void windowGainedFocus(WindowEvent e) {

	}

	@Override
	public void windowLostFocus(WindowEvent e) {

	}
}
