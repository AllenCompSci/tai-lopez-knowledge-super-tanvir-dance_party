package physics;

import java.awt.*;
import java.awt.event.*;

/**
 * Created by 254397 on 1/25/2017.
 */
public class BetterFrame extends Frame implements MouseListener, MouseMotionListener, MouseWheelListener, KeyListener {
	public int mouseX, mouseY;
	private boolean clicking;
	private int mouseButton = - 1;

	public BetterFrame(String title, Dimension size) {
		super(title);
		setSize(size);
		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
		addMouseWheelListener(this);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		KeyHandler.pressKey(e);
	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		clicking = true;
		mouseButton = e.getButton();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		clicking = false;
		mouseButton = - 1;
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {

	}

	public boolean isClicking() {
		return clicking;
	}

	public int getMouseButton() {
		return mouseButton;
	}
}
