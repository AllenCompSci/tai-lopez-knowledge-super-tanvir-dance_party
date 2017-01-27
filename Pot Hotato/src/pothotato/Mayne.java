package pothotato;


import knowledge.*;

/**
 * Created by 223671 on 1/12/2017.
 */
public class Mayne {
    public static Timer colorSwitcher;
    public static Timer animTimer;
    public static Timer animTimer2;
    public static Timer countdownTimer;
    public static HotPotato potate;
    public static void main(String[] args) {
        System.out.println("FUEL-UNITS LOW NEED MOAR KNOWLEDGE");
        colorSwitcher = new Timer();
        new Thread(colorSwitcher).start();
        animTimer = new Timer();
        new Thread(animTimer).start();
        animTimer.start();
        animTimer2 = new Timer();
        new Thread(animTimer2).start();
        countdownTimer = new Timer(false, 3000);
        countdownTimer.setDelay(100);
        new Thread(countdownTimer).start();
        potate = new HotPotato();
        new Thread(potate).start();
        potate.setVisible(true);
    }

}
