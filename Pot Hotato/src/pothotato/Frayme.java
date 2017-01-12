package pothotato;

import java.awt.*;
import java.awt.event.*;

/**
 * Created by 223671 on 1/12/2017.
 */
public class Frayme extends Frame implements MouseMotionListener, MouseWheelListener, KeyListener, WindowListener {

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

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {

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
