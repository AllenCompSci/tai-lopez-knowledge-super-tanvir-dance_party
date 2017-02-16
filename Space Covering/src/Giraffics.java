import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.SampleModel;

import static java.awt.Color.black;
import static java.awt.Color.getColor;
import static java.awt.Color.white;

public class Giraffics implements Runnable, KeyListener, WindowListener {
    private final int TOP_BAR_HEIGHT = 30;
    private boolean isRunning, isDone, gameComplete, gameBegun;
    private Coolframe frame;
    private Image imgbuffer;
    private square [][] game;
    private int side = 50;
    public int HudsagonR = 13;
    public int HudsagonC = 21;
    private int MaxI=0;
    private int MaxJ=0;
    private int MaxScore=1;
    private int VanceScore=1;
    private int VanceI = HudsagonR-1;
    private int VanceJ = HudsagonC-1;
    Image im = Toolkit.getDefaultToolkit().getImage("Max.png");
    Image im1 = Toolkit.getDefaultToolkit().getImage("vance head.png");

    public Giraffics() {
        Dimension SIZE = new Dimension(2000, 1600);
        frame = new Coolframe("fu", SIZE);
        frame.addKeyListener(this);
        frame.addWindowListener(this);
        isRunning = true;
        isDone = false;
        frame.setVisible(true);
        imgbuffer = frame.createImage(frame.getWidth(), frame.getHeight());
        setGame(new square [HudsagonR][HudsagonC]);
        int x = 0;
        int y = 50;
        gameComplete = false;
        gameBegun = false;
        Main.gameTimer.resume();
        double h = CalculateH(side);
        double r = CalculateR(side);
        for(int i = 0; i < HudsagonR; i++){
            System.out.print((i%2 == 0)? "" : " ");
            x = (i%2 == 0)? 100: 100 + side-5;
            for(int j = 0; j < HudsagonC; j++){
                game[i][j] = new square((i== 0) || (i == 10) || (j==0) || (j==4) , new Point(x,y),side, h, r, i, j);
                x+= 2*r;
                //System.out.println("i : " + i + ", j : " + j + "is Edge : " + game[i][j].isEdge());
                //System.out.print((game[i][j].isEdge())? "x" : "o");
            }
            y+= h+side;

            //System.out.println();
        }
        setConnection();
        game[MaxI][MaxJ].setMax(true);
        game[VanceI][VanceJ].setVance(true);
    }
    private void setConnection(){

        for(int j = 0; j < 13; j++){
            for(int i = 0; i < 21; i++){
                // Connection 0 - 1 o'clock
                if(j > 0){
                    if(j%2 == 0)
                        game[j][i].getSurrounding()[0] = game[j-1][i];
                    else if(i < 20)
                        game[j][i].getSurrounding()[0] = game[j-1][i+1];
                }
                // Connection 1 - 3 o'clock
                if(i < 20){
                    game[j][i].getSurrounding()[1] = game[j][i+1];
                }
                // Connection 2 - 5 o'clock
                if(j < 12){
                    if(j%2 == 0){
                        game[j][i].getSurrounding()[2] = game[j+1][i];

                    }
                    else if(i < 20){
                        game[j][i].getSurrounding()[2] = game[j+1][i+1];
                    }
                    // Connection 3 - 7 o'clock
                    if(j%2 == 0){
                        if(i > 0){
                            game[j][i].getSurrounding()[3] = game[j+1][i-1];
                        }
                    }
                    else {
                        game[j][i].getSurrounding()[3] = game[j+1][i];
                    }
                }


                // Connection 4 - 9 o'clock
                if(i > 0){
                    game[j][i].getSurrounding()[4] = game[j][i-1];
                }
                // Connection 5 - 11 o'clock
                if(j > 0){
                    if(j % 2 == 0){
                        if(i > 0){
                            game[j][i].getSurrounding()[5] = game[j-1][i-1];
                        }
                    }
                    else{
                        game[j][i].getSurrounding()[5] = game[j-1][i];
                    }
                }
            }
        }




    }



    public void run(){
    int count = 0;
        while(isRunning) {

            if(!Main.gameTimer.isRunning() && !gameComplete) {
                if (!gameBegun) {
                    Main.gameTimer.restart(30000);
                    gameBegun = true;
                } else {
                    gameComplete = true;
                }
            }
            if(gameComplete){
                count++;
                if(count > 100){
                    isRunning = false;
                }
            }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
                draw();
            }



        try {
            Thread.sleep(4500);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
        isDone = true;
        System.exit(0);

    }

    public void draw() {
        Font f = new Font("arial", Font.PLAIN, 32);
        Graphics2D art = (Graphics2D) imgbuffer.getGraphics();
        art.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        art.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        art.setFont(f);
        art.setColor(Color.black);
        art.fillRect(0, 0, frame.getWidth(), frame.getHeight());
     if(!gameComplete){
        String time = Main.gameTimer.toString(false);
        //art.setFont(art.getFont().deriveFont(30f));
        art.setColor(white);
        if (isRunning)
            drawCenteredString(time, frame.getWidth() / 2, 50, art);


        for (int i = 0; i < HudsagonR; i++) {
            for (int j = 0; j < HudsagonC; j++) {

                art.setColor(game[i][j].getCurrColor());
                art.fill(game[i][j].getSquareset());
                art.setColor(Color.cyan);
                art.draw(game[i][j].getSquareset());


                art.setColor(Color.ORANGE);

                if (game[i][j] == game[MaxI][MaxJ]) {
                    art.fill(game[i][j].getSquareset());
                }


                art.setColor(Color.cyan);

                if (game[i][j] == game[VanceI][VanceJ]) {

                    art.fill(game[i][j].getSquareset());
                }

            }
        }
        art.drawImage(im, game[MaxI][MaxJ].getSquareset().xpoints[5], game[MaxI][MaxJ].getSquareset().ypoints[5], game[MaxI][MaxJ].getSquareset().xpoints[2], game[MaxI][MaxJ].getSquareset().ypoints[2], 22, 0, 426, 513, null);
        art.drawImage(im1, game[VanceI][VanceJ].getSquareset().xpoints[5], game[VanceI][VanceJ].getSquareset().ypoints[5], game[VanceI][VanceJ].getSquareset().xpoints[2], game[VanceI][VanceJ].getSquareset().ypoints[2], 0, 0, 500, 513, null);
    }

        else {
         MaxScore = 0;
         VanceScore = 0;
            for (int i = 0; i < HudsagonR; i++) {
                for (int j = 0; j < HudsagonC; j++) {
                    if (game[i][j].getCurrColor().equals(Color.ORANGE))
                        MaxScore++;
                    else if (game[i][j].getCurrColor().equals(Color.cyan))
                        VanceScore++;

                }

            }
         //art.setFont(art.getFont().deriveFont(42f));
            art.setColor(Color.white);
            drawCenteredString("MAX'S SCORE", frame.getWidth() / 2-  400, frame.getHeight() / 2-100, art);
            drawCenteredString(String.valueOf(VanceScore), frame.getWidth() / 2 + 400, frame.getHeight() / 2, art);
            drawCenteredString("VANCE'S SCORE", frame.getWidth() / 2 + 400, frame.getHeight() / 2-100, art);
            drawCenteredString(String.valueOf(MaxScore), frame.getWidth() / 2 - 400, frame.getHeight() / 2, art);
         if(MaxScore > VanceScore){
             drawCenteredString("MAX WINS", frame.getWidth() / 2 , frame.getHeight() / 2-100, art);
         }
         else
             drawCenteredString("VANCE WINS", frame.getWidth() / 2, frame.getHeight() / 2-100, art);

        }



        if(isRunning)
            art = (Graphics2D) frame.getGraphics();

        art.drawImage(imgbuffer, 0,  0, frame.getWidth(), frame.getHeight(), 0, 0, frame.getWidth(), frame.getHeight(), null);






        }



    private void setMaxI(square node){
        MaxI = node.getX();
    }
    private void setMaxJ(square node){
        MaxJ = node.getY();
    }
    private void getMax(square node){
        setMaxI(node);
        setMaxJ(node);
    }
    private void setVanceI(square node){ VanceI = node.getX();}
    private void setVanceJ (square node){ VanceJ = node.getY();}
    private void getVance(square node){
        setVanceI(node);
        setVanceJ(node);
    }

    public void setGame(square [][] game) {
        this.game = game;
    }



    @Override
    public void keyTyped(KeyEvent e) {

    }
    private void drawCenteredString(String s, int midX, int y, Graphics2D art) {
        Font f = art.getFont();
        FontMetrics fm = art.getFontMetrics(f);
        int width = fm.stringWidth(s);
        art.drawString(s, midX - (width / 2), y);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int Key;
        Key = e.getKeyCode();



        if (gameBegun && Key == KeyEvent.VK_A) {

            //System.out.println("Travel left");
            if (game[MaxI][MaxJ].getSurrounding()[4] != null && !gameComplete)
                if (!game[MaxI][MaxJ].getSurrounding()[4].isOccupied()) {

                    game[MaxI][MaxJ].setOccupied(false);
                    getMax(game[MaxI][MaxJ].getSurrounding()[4]);
                    game[MaxI][MaxJ].setMax(true);
                    game[MaxI][MaxJ].setVance(false);
                    game[MaxI][MaxJ].setOccupied(true);

                }

        }
       else if (gameBegun && Key == KeyEvent.VK_E && !gameComplete) {

            //System.out.println("Travel up right");
            if (game[MaxI][MaxJ].getSurrounding()[0] != null)
                if (!game[MaxI][MaxJ].getSurrounding()[0].isOccupied()) {
//                    game[MaxI][MaxJ].setMax(false);
                    game[MaxI][MaxJ].setOccupied(false);
                    getMax(game[MaxI][MaxJ].getSurrounding()[0]);
                    game[MaxI][MaxJ].setMax(true);
                    game[MaxI][MaxJ].setVance(false);
                    game[MaxI][MaxJ].setOccupied(true);


                }

        }
        else if (gameBegun && Key == KeyEvent.VK_D && !gameComplete) {

            //System.out.println("Travel right");
            if (game[MaxI][MaxJ].getSurrounding()[1] != null)
                if (!game[MaxI][MaxJ].getSurrounding()[1].isOccupied()) {
//                    game[MaxI][MaxJ].setMax(false);
                    game[MaxI][MaxJ].setOccupied(false);
                    getMax(game[MaxI][MaxJ].getSurrounding()[1]);
                    game[MaxI][MaxJ].setMax(true);
                    game[MaxI][MaxJ].setVance(false);
                    game[MaxI][MaxJ].setOccupied(true);


                }

        }
        else if (gameBegun && Key == KeyEvent.VK_C && !gameComplete) {

            //System.out.println("Travel down right);
            if (game[MaxI][MaxJ].getSurrounding()[2] != null)
                if (!game[MaxI][MaxJ].getSurrounding()[2].isOccupied()) {
//                    game[MaxI][MaxJ].setMax(false);
                    game[MaxI][MaxJ].setOccupied(false);
                    getMax(game[MaxI][MaxJ].getSurrounding()[2]);
                    game[MaxI][MaxJ].setMax(true);
                    game[MaxI][MaxJ].setVance(false);
                    game[MaxI][MaxJ].setOccupied(true);


                }

        }
        else if (gameBegun && Key == KeyEvent.VK_Z && !gameComplete) {

            //System.out.println("Travel downleft");
            if (game[MaxI][MaxJ].getSurrounding()[3] != null)
                if (!game[MaxI][MaxJ].getSurrounding()[3].isOccupied()) {
//                    game[MaxI][MaxJ].setMax(false);
                    game[MaxI][MaxJ].setOccupied(false);
                    getMax(game[MaxI][MaxJ].getSurrounding()[3]);
                    game[MaxI][MaxJ].setMax(true);
                    game[MaxI][MaxJ].setVance(false);
                    game[MaxI][MaxJ].setOccupied(true);


                }

        }
        else if (gameBegun && Key == KeyEvent.VK_Q && !gameComplete) {

            //System.out.println("Travel downleft");
            if (game[MaxI][MaxJ].getSurrounding()[5] != null)
                if (!game[MaxI][MaxJ].getSurrounding()[5].isOccupied()) {
//                    game[MaxI][MaxJ].setMax(false);
                    game[MaxI][MaxJ].setOccupied(false);
                    getMax(game[MaxI][MaxJ].getSurrounding()[5]);
                    game[MaxI][MaxJ].setMax(true);
                    game[MaxI][MaxJ].setVance(false);
                    game[MaxI][MaxJ].setOccupied(true);


                }

        }
        if(gameBegun && Key == 105 && !gameComplete){

            //System.out.println("Travel up right");
            if(game[VanceI][VanceJ].getSurrounding()[0] != null   )
                if(  !game[VanceI][VanceJ].getSurrounding()[0].isOccupied()){
                    game[VanceI][VanceJ].setOccupied(false);
                    getVance(game[VanceI][VanceJ].getSurrounding()[0]);
                    game[VanceI][VanceJ].setVance(true);
                    game[VanceI][VanceJ].setMax(false);
                    game[VanceI][VanceJ].setOccupied(true);


                }
        }
        else if(gameBegun && Key == 102 && !gameComplete){

            //System.out.println("Travel right");

            if(game[VanceI][VanceJ].getSurrounding()[1] != null  ) if(  !game[VanceI][VanceJ].getSurrounding()[1].isOccupied()){
                game[VanceI][VanceJ].setMax(false);
                game[VanceI][VanceJ].setOccupied(false);
                getVance(game[VanceI][VanceJ].getSurrounding()[1]);
                game[VanceI][VanceJ].setVance(true);
                game[VanceI][VanceJ].setMax(false);
                game[VanceI][VanceJ].setOccupied(true);


            }
        }
        else if(gameBegun && Key == 99 && !gameComplete){

            //	System.out.println("Travel down right");
            if(game[VanceI][VanceJ].getSurrounding()[2] != null  ) if(  !game[VanceI][VanceJ].getSurrounding()[2].isOccupied()){
                game[VanceI][VanceJ].setMax(false);
                game[VanceI][VanceJ].setOccupied(false);
                getVance(game[VanceI][VanceJ].getSurrounding()[2]);
                game[VanceI][VanceJ].setVance(true);
                game[VanceI][VanceJ].setMax(false);
                game[VanceI][VanceJ].setOccupied(true);


            }
        }
        else if(gameBegun && Key == 97 && !gameComplete){

            //System.out.println("Travel down left");
            if(game[VanceI][VanceJ].getSurrounding()[3] != null  ) if(  !game[VanceI][VanceJ].getSurrounding()[3].isOccupied()){
                game[VanceI][VanceJ].setMax(false);
                game[VanceI][VanceJ].setOccupied(false);
                getVance(game[VanceI][VanceJ].getSurrounding()[3]);
                game[VanceI][VanceJ].setVance(true);
                game[VanceI][VanceJ].setMax(false);
                game[VanceI][VanceJ].setOccupied(true);


            }
        }else if(gameBegun && Key == 100 && !gameComplete){

            //System.out.println("Travel left");
            if(game[VanceI][VanceJ].getSurrounding()[4] != null ) if(  !game[VanceI][VanceJ].getSurrounding()[4].isOccupied()){
                game[VanceI][VanceJ].setMax(false);
                game[VanceI][VanceJ].setOccupied(false);
                getVance(game[VanceI][VanceJ].getSurrounding()[4]);
                game[VanceI][VanceJ].setVance(true);
                game[VanceI][VanceJ].setMax(false);
                game[VanceI][VanceJ].setOccupied(true);

            }
        }else if(gameBegun && Key == 103 && !gameComplete){

            //System.out.println("Travel up left");
            if(game[VanceI][VanceJ].getSurrounding()[5] != null ) if( !game[VanceI][VanceJ].getSurrounding()[5].isOccupied()){
                game[VanceI][VanceJ].setMax(false);
                game[VanceI][VanceJ].setOccupied(false);
                getVance(game[VanceI][VanceJ].getSurrounding()[5]);
                game[VanceI][VanceJ].setVance(true);
                game[VanceI][VanceJ].setMax(false);
                game[VanceI][VanceJ].setOccupied(true);

            }
        }


    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        frame.setVisible(false);
        frame.dispose();
        isRunning = false;
    }

    @Override
    public void windowClosed(WindowEvent e) {
        while(true){

            if(isDone){
                //System.out.println("EXIT"); // never gets here
                System.exit(0);
            }
            try{
                Thread.sleep(100);
            }
            catch(InterruptedException ie){
                ie.printStackTrace();
            }
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

    public static double CalculateH(float side)
    {
        return Math.sin(DegreesToRadians(30)) * side;
    }

    public static double CalculateR(float side)
    {
        return (Math.cos(DegreesToRadians(30)) * side);
    }
    public static double DegreesToRadians(double degrees)
    {
        return degrees * Math.PI / 180;
    }
}
