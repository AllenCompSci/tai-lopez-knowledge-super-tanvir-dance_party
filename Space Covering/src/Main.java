/**
 * Created by 219305 on 1/31/2017.
 */
public class Main {
    public static Giraffics CK;
    public static Timer gameTimer;


    public static void main(String[] memes){

        gameTimer = new Timer (false, 3000);
        new Thread(gameTimer).start();
        gameTimer.begin();


        CK = new Giraffics();
        new Thread(CK).start();
    }




}
