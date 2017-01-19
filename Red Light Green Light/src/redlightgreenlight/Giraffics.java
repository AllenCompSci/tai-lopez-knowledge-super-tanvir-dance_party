package redlightgreenlight;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.Image;


import static java.awt.Color.*;
import static redlightgreenlight.Main.*;

public class Giraffics implements Runnable, KeyListener, WindowListener {
    private final int TOP_BAR_HEIGHT = 30;
    private boolean isRunning, isDone, gameComplete, gameBegun;
    private Color currColor = GREEN;
    private int numPlayers, currPlayer = 0;
    private Light frame;
    private Image imgbuffer;
    private ArrayList<String> playerNames;
    private int PlayerHeight;
    public Image im;
    private Player Vance, Max, Kyle;
    private int currRace;
    private int finish;


    public Giraffics() {
        PlayerHeight = 400;
        Dimension SIZE = new Dimension(2000, 1600);
        frame = new Light("RG", SIZE);
        frame.addKeyListener(this);
        frame.addWindowListener(this);
        isRunning = true;
        isDone = false;
        frame.setVisible(true);
        Vance = new Player(0,(frame.getHeight()/2)-PlayerHeight - 10);
        Max = new Player(0, Vance.getY()+400+10);
        Kyle = new Player(0, Max.getY() + 400);
        imgbuffer = frame.createImage(frame.getWidth(), frame.getHeight());
        playerNames = new ArrayList<>();
        gameComplete = false;
        gameBegun = false;
        gameTimer.resume();
        currRace = 2;

    }

    @Override
    public void run() {
        while (isRunning) {
            if(!gameTimer.isRunning() && !gameComplete) {
                if(!gameBegun) {
                    gameTimer.restart(30000);
                    gameBegun = true;
                } else
                    gameComplete = true;
            }
            if(!greenTimer.isRunning()) {
                if(currColor.equals(green)){
                    currColor = red;
                } else{
                    currColor = green;
                }
                int randInterval = (int) (Math.random() * 4000  + 100);
                greenTimer.restart(randInterval);
            }



            try {
                Thread.sleep(10);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
            draw();
        }
        draw();
        System.out.println(Max.getX());
        System.out.println(Vance.getX());
        try {
            Thread.sleep(4500);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
        isDone = true;

    }
    public void draw () {
        Graphics2D art = (Graphics2D) imgbuffer.getGraphics();

        art.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        art.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        Font f = art.getFont();


        art.setColor(currColor);
        art.fillRect(0,0, frame.getWidth(), frame.getHeight());

        art.setColor(black);
        String time = gameTimer.toString(false);
        art.setFont(art.getFont().deriveFont(30f));
        if(gameBegun)
        {
            currRace = gameTimer.getMS();

            if (currRace==0) {

                if (Max.getX() > Vance.getX()) {
                    drawCenteredString("MAX WINS", frame.getWidth() / 2, (frame.getHeight() / 2) - 300, art);
                }
                else if (Max.getX() < Vance.getX()) {
                    drawCenteredString("VANCE WINS", frame.getWidth() / 2, (frame.getHeight() /2) - 300 , art);
                }
                else if(Max.getX() == Vance.getX()){
                    drawCenteredString("ITS A TIE", frame.getWidth() / 2, (frame.getHeight() / 2) - 300, art);
                }
                isRunning = false;
            }
            if(isRunning) {
                currRace = (30 - (currRace / 1000)) + 2;
                Vance.Update(currRace);
                Max.Update(currRace);
            }
        }

        im = Toolkit.getDefaultToolkit().getImage("Max.png");
        Image im1 = Toolkit.getDefaultToolkit().getImage("Vance.png");
        art.drawImage(im1, Vance.getX(), Vance.getY(), 250, 400, null); // Vance
        art.drawImage(im, Max.getX(), Max.getY(), 250, 250, null); // MAX

        finish = (int) (Math.random() * 1600 );
        if(Max.getX() >= 1800){

            drawCenteredString("MAX WINS", frame.getWidth() / 2, (frame.getHeight() / 2) + 300, art);
            isRunning = false;
        }
        else if (Vance.getX() >= 1800 ){
            drawCenteredString("VANCE WINS", frame.getWidth() / 2, (frame.getHeight() /2) + 300 , art);
            isRunning = false;
        }

        else if(isRunning)
            drawCenteredString(time, frame.getWidth() / 2, frame.getHeight() / 2, art);





        art = (Graphics2D) frame.getGraphics();
        if (art != null) {
            imgbuffer = Resizer.PROGRESSIVE_BILINEAR.resize((BufferedImage) imgbuffer, frame.getWidth(), frame.getHeight());
            art.drawImage(imgbuffer, 0, 0, frame.getWidth(), frame.getHeight(), 0, 0, frame.getWidth(), frame.getHeight(), null);
            art.dispose();
        }







    }
    // TODO Auto-generated method stub

    private void drawCenteredString(String s, int midX, int y, Graphics2D art) {
        Font f = art.getFont();
        FontMetrics fm = art.getFontMetrics(f);
        int width = fm.stringWidth(s);
        art.drawString(s, midX - (width / 2), y);
    }


    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        int key = e.getKeyCode();
        if(gameBegun && key == KeyEvent.VK_SPACE){
            Max.getJackedTitanic();
        }
        else if(gameBegun && key == KeyEvent.VK_ENTER){
                Vance.getJackedTitanic();
        }
        else if(gameBegun && key == KeyEvent.VK_UP){
            Vance.getJackedTitanic();
        }
        else if(gameBegun && key == KeyEvent.VK_A){
            Max.getJackedTitanic();
        }
        else if(gameBegun && key == KeyEvent.VK_DOWN){
            Vance.getJackedTitanic();
        }
        else if(gameBegun && key == KeyEvent.VK_Z){
            Max.getJackedTitanic();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {


    }

    @Override
    public void keyPressed(KeyEvent arg0) {
        int key = arg0.getKeyCode();

        if(gameBegun && key == KeyEvent.VK_SPACE){
            if(currColor == red){
                Max.Penalty();
            }
            else if (!Max.neverLetGo())
                Max.MoveX();
        }
        else if(gameBegun && key == KeyEvent.VK_ENTER){
            if(currColor == red)
                Vance.Penalty();
            else if(!Vance.neverLetGo())
                Vance.MoveX();

        }
        else if(gameBegun && key == KeyEvent.VK_UP){
            if(currColor == red)
                Vance.Penalty();
            else if(!Vance.neverLetGo())
                Vance.MoveUp();

        }
        else if(gameBegun && key == KeyEvent.VK_A){
            if(currColor == red)
               Max.Penalty();
            else if(!Max.neverLetGo())
                Max.MoveUp();

        }
        else if(gameBegun && key == KeyEvent.VK_DOWN){
            if(currColor == red)
                Vance.Penalty();
            else if(!Vance.neverLetGo())
                Vance.MoveDown();

        }
        else if(gameBegun && key == KeyEvent.VK_Z){
            if(currColor == red)
                Max.Penalty();
            else if(!Max.neverLetGo())
                Max.MoveDown();

        }

    }

    @Override
    public void windowActivated(WindowEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void windowClosed(WindowEvent e) {
        while (true) {
            if (isDone)
                System.exit(0);
            try {
                Thread.sleep(50);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }


    }

    public Light getFrame() {
        return frame;
    }

    @Override
    public void windowClosing(WindowEvent e) {
        frame.setVisible(false);
        isRunning = false;
        frame.dispose();
        isDone = true;


    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void windowIconified(WindowEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void windowOpened(WindowEvent e) {
        // TODO Auto-generated method stub

    }



    public void initNumPlayers() {
        for (int i = 0; i < numPlayers; i++) {
            playerNames.add("Player " + (i + 1));
        }
    }
}



