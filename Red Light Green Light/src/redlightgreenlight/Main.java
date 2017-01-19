package redlightgreenlight;


import java.awt.*;

public class Main {
    public static Timer gameTimer;
    public static Timer greenTimer;
    public static Giraffics BD;
    public static void main(String[] memes){

        greenTimer = new Timer(false, 0);
        new Thread (greenTimer).start();

        gameTimer = new Timer(false, 3000);
        new Thread(gameTimer).start();

        BD = new Giraffics();
        new Thread(BD).start();

    }
}
