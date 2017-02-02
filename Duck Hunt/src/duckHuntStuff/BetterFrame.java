package duckHuntStuff;

import java.awt.*;
import java.awt.event.*;

/**
 * @author onContentStop
 */
public class BetterFrame extends Frame implements MouseListener, MouseMotionListener, MouseWheelListener {
    public int mouseX, mouseY;
    private boolean clicking;
    private int mouseButton = - 1;
    private String name;

    /**
     * Creates a new BetterFrame.
     *
     * @param title Title to display on the menu bar of the new BetterFrame, this is also used to reference a particular BetterFrame
     * @param size  The dimensions of the new BetterFrame
     */
    public BetterFrame(String title, Dimension size) {
        super(title);
        name = title;
        setSize(size);
        addMouseListener(this);
        addMouseMotionListener(this);
        addMouseWheelListener(this);
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

    /**
     * Gets the current state of the mouse buttons.
     *
     * @return whether or not a button is currently being pressed on the mouse
     */
    public boolean clicking() {
        return clicking;
    }

    /**
     * Gets the current mouse button down, if any.
     *
     * @return the mouse button being pressed or -1 if no button is down
     */
    public int getMouseButton() {
        return mouseButton;
    }
}