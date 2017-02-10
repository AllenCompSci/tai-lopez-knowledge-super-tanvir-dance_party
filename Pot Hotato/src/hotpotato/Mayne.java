package hotpotato;


import knowledge.*;

/**
 * @author onContentStop
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
	public static void init() {
		System.out.println("FUEL-UNITS LOW NEED MOAR KNOWLEDGE");
		colorSwitcher = new Timer();
		new Thread(colorSwitcher).start();
		animTimer = new Timer();
		new Thread(animTimer).start();
		animTimer.start();
		animTimer2 = new Timer();
		new Thread(animTimer2).start();
		countdownTimer = new Timer(false, 3000);
		countdownTimer.setDelay(10);
		new Thread(countdownTimer).start();
	}
}
