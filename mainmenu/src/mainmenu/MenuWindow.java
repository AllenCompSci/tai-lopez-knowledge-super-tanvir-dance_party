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
	private BetterFrame frame;
	private Image imgBuffer;
	private Font f;
	private AllButtons allButtons;
	private ButtonList buttons;
	private Button bStartGame;

	public MenuWindow() {
		createButtons();
		allButtons.setWindow("main");
		running = true;
		done = false;
		frame = new BetterFrame("Main Menu", new Dimension(800, 600));
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
			repositionButtons();
			Background.updateColor();
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
		int width = frame.getWidth(), height = frame.getHeight(), effectiveHeight = height - 30;

		art.setColor(Background.getCurrentColor());
		art.fillRect(0, 30, width, effectiveHeight);
		buttons.drawAll(art);

		art = (Graphics2D) frame.getGraphics();
		if (art != null) {
			imgBuffer = Resizer.PROGRESSIVE_BILINEAR.resize((BufferedImage) imgBuffer, width, height);
			art.drawImage(imgBuffer, 0, 0, width, height, 0, 0, width, height, null);
			art.dispose();
		}
	}

	private void createButtons() {
		allButtons = new AllButtons();

		buttons = new ButtonList("main");
		bStartGame = new Button(0, 0, 100, 100, "Start Game");
		buttons.add(bStartGame);
		allButtons.add(buttons);
	}

	private void repositionButtons() {
		bStartGame.setPos(frame.getWidth() / 2 - bStartGame.getSize().width / 2, frame.getHeight() / 2 - bStartGame.getSize().height / 2);
	}

	//region Unused
	@Override
	public void windowOpened(WindowEvent e) {

	}
	//endregion

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

	//region Unused Overrides
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

	public BetterFrame getFrame() {
		return frame;
	}
}
