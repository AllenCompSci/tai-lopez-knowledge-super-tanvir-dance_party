import java.awt.*;
import java.awt.event.*;

/**
 * Created by 219305 on 2/6/2017.
 */
public class Coolframe extends Frame implements MouseMotionListener, MouseWheelListener, MouseListener {
    public int mousex, mousey;
    private int mouseButton;
    public int scrollAmt;
    boolean hasClicked, clicking;

    public Coolframe(String title, Dimension SIZE) {
        setTitle(title);
        setSize(SIZE);

        addMouseListener(this);
        addMouseMotionListener(this);
        addMouseWheelListener(this);

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

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
}

