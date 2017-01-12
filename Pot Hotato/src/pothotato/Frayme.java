package pothotato;

import java.awt.*;
import java.awt.event.*;

/**
 * Created by 223671 on 1/12/2017.
 */
public class Frayme extends Frame implements MouseMotionListener, MouseWheelListener, KeyListener {

    private int mouseX, mouseY, mouseWheelDiff;

    Frayme() {
        super();
    }

    Frayme(String title, Dimension size) {
        super(title);
        setSize(size);
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {
        KeyHandler.pressKey(e.getKeyCode());
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
