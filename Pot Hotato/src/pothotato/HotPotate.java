package pothotato;

import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * Created by 223671 on 1/12/2017.
 */
public class HotPotate implements Runnable, WindowListener {
    private boolean running, done = false;
    private Frayme frame;

    HotPotate () {
        running = true;
        frame = new Frayme("Hot Potato", new Dimension(800, 600));
        frame.addWindowListener(this);
        frame.setVisible(true);
    }

    @Override
    public void run() {
        while(running) {
            draw();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void draw() {

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
        if(done) {
            System.exit(0);
        }
        try {
            Thread.sleep(100);
        } catch(InterruptedException e1){
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
