package physics;

import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;

/**
 * Created by mario on 1/25/2017.
 */
public class Graphics implements Runnable, WindowListener, WindowFocusListener {
	final int WINDOW_BAR_HEIGHT = 30;
	boolean running, done, visible;
	int width = 800, height = 600;
	BetterFrame frame;
	Image imgBuffer;
	Font f;
	Graphics2D art;

	public Graphics() {
		frame = new BetterFrame("Frame", new Dimension(width, height));
		initializeCommonVariables();
	}

	public Graphics(String title) {
		frame = new BetterFrame(title, new Dimension(width, height));
		initializeCommonVariables();
	}
	private void initializeCommonVariables() {
		running = true;
		done = false;
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int mon_width = gd.getDisplayMode().getWidth();
		int mon_height = gd.getDisplayMode().getHeight();
		frame.setLocation(mon_width / 2 - width / 2, mon_height / 2 - height / 2);
		frame.addWindowListener(this);
		frame.addWindowFocusListener(this);
		visible = false;
	}

	@Override
	public void run() {

	}

	public void updateSize() {
		if (width != frame.getWidth())
			width = frame.getWidth();
		if (height != frame.getHeight())
			height = frame.getHeight();
	}

	void startDraw() {
		art = (Graphics2D) imgBuffer.getGraphics();
		art.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		art.setFont(new Font("Arial", Font.PLAIN, 50));
		f = art.getFont();
	}

	void endDraw() {
		art = (Graphics2D) frame.getGraphics();
		if (art != null) {
			imgBuffer = Resizer.PROGRESSIVE_BILINEAR.resize((BufferedImage) imgBuffer, width, height);
			art.drawImage(imgBuffer, 0, 0, width, height, 0, 0, width, height, null);
			art.dispose();
		}
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
		if (done && this instanceof MainWindow)
			System.exit(0);
		try {
			Thread.sleep(100);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}

	public BetterFrame getFrame() {
		return frame;
	}

	public void setVisible(boolean visible) {
		frame.setVisible(visible);
		if(visible)
			imgBuffer = frame.createImage(width, height);
		this.visible = visible;
	}

	//region unused overrides
	@Override
	public void windowOpened(WindowEvent e) {

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
	//endregion
}
