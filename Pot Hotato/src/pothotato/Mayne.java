package pothotato;


/**
 * Created by 223671 on 1/12/2017.
 */
public class Mayne {
    public static Timer colorSwitcher;
    public static Timer animTimer;
    public static Timer countdownTimer;
    public static HotPotate potate;
    public static void main(String[] args) {
        System.out.println("FUEL-UNITS LOW NEED MOAR KNOWLEDGE");
        colorSwitcher = new Timer();
        new Thread(colorSwitcher).start();
        animTimer = new Timer();
        new Thread(animTimer).start();
        animTimer.start();
        countdownTimer = new Timer(false, 3000);
        new Thread(countdownTimer).start();
        potate = new HotPotate();
        new Thread(potate).start();
    }

}
