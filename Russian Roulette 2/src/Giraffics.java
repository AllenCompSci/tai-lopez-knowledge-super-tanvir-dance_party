import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Giraffics implements Runnable, KeyListener, WindowListener {
    private final int TOP_BAR_HEIGHT = 30;
    private boolean isRunning, isDone;
    private int numPlayers, currPlayer = 0;
    private coolframe frame;
    private Image imgbuffer;
    private Kbutton knowledgebutton;
    private boolean knowledgePeopleKnown;
    private String shotMessage;
    private ArrayList<String> playerNames;
    public Giraffics() {
        knowledgePeopleKnown = false;
        knowledgebutton = new Kbutton(50, 50, 100, 100, "TRIGGER", Color.GRAY, 20);
        Dimension SIZE = new Dimension(800, 600);
        frame = new coolframe("big fat fucking gamba", SIZE);
        frame.addKeyListener(this);
        frame.addWindowListener(this);
        isRunning = true;
        isDone = false;
        frame.setVisible(true);
        imgbuffer = frame.createImage(frame.getWidth(), frame.getHeight());
        playerNames = new ArrayList<>();
    }
    public int getFuelUnits(){
        return numPlayers;
    }
    public boolean getFuel(){
        return knowledgePeopleKnown;
    }
    @Override
    public void run() {
        while (isRunning) {

            if(numPlayers == 1) {
                knowledgebutton.setVisible(false);
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
            draw();
        }

    }
    public void draw () {
        Graphics2D art = (Graphics2D) imgbuffer.getGraphics();
        art.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        art.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        Font f = art.getFont();


        art.setColor(new Color(200, 200, 200));
        art.fillRect(0, 0, frame.getWidth(), frame.getHeight());
        knowledgebutton.draw(frame.mousex, frame.mousey,art);
        art.setColor(Color.black);
        if(!knowledgePeopleKnown) {
            f = art.getFont().deriveFont(30f);
            art.setFont(f);
            FontMetrics informacion = art.getFontMetrics();
            drawCenteredString("Input number of players desired (1-9) : " + numPlayers, frame.getWidth() / 2, frame.getHeight() / 2 + informacion.getHeight() / 2, art);

        } else {
            FontMetrics informacion = art.getFontMetrics();
            String words = "Number of players: " + numPlayers;
            drawCenteredString(words, frame.getWidth() - informacion.stringWidth(words) + 40, 50, art);
        }
        if(shotMessage != null)
            drawCenteredString(shotMessage, frame.getWidth() / 2, frame.getHeight() / 2, art);

        if(Main.russian != null && Main.russian.numPlayers() != null) {
            int k = (currPlayer + 1) >= Main.russian.numPlayers() ? 0 : currPlayer + 1;
            drawCenteredString("Pick up the gun " + playerNames.get(k) + " and pull the trigger", frame.getWidth() / 2, frame.getHeight() / 2 + 50, art);
        }

        if(numPlayers == 1) {
            drawCenteredString(playerNames.get(0) + " is bloody but wins!", frame.getWidth() / 2, frame.getHeight() / 2 + 100, art);
        }

        //begin drawing status box {

        // } finish drawing status box



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
    public void keyPressed(KeyEvent arg0) {
        char x = arg0.getKeyChar();
        int code = arg0.getKeyCode();
        if( !knowledgePeopleKnown && x != 0 && Integer.parseInt(""+x)!=-1){
            numPlayers = Integer.parseInt(""+x);
            knowledgePeopleKnown = true;
            knowledgebutton.setVisible(true);
        }
        // TODO Auto-generated method stub
        switch(code) {
            case KeyEvent.VK_SPACE:
                //pass the gun
                if(!knowledgebutton.isVisible() && numPlayers > 1) {
                    currPlayer++;
                    knowledgebutton.setVisible(true);
                }
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

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

    public coolframe getFrame() {
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

    public void doClickAction(Kbutton knowl) {
        if(knowl == knowledgebutton) {
            if(currPlayer >= numPlayers) {
                currPlayer = 0;
            }
            boolean getShotBoyyy = Main.russian.pullTheTrigger();
            if(getShotBoyyy) {
                shotMessage = "BANG " + playerNames.get(currPlayer) + " eliminated";



                playerNames.remove(currPlayer);
                Main.russian.reset();
                currPlayer--;
                numPlayers--;
            } else {
                shotMessage = "CLICK";
            }

            knowledgebutton.setVisible(false);
        }
    }

    public void initNumPlayers() {
        for (int i = 0; i < numPlayers; i++) {
            playerNames.add("Player " + (i + 1));
        }
    }
}



