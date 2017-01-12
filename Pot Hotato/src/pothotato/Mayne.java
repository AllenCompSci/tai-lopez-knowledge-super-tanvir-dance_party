package pothotato;


/**
 * Created by 223671 on 1/12/2017.
 */
public class Mayne {
	public static Timer colorSwitcher;
	public static void main(String[] args) {
		System.out.println("FUEL-UNITS LOW NEED MOAR KNOWLEDGE");
		HotPotate potate = new HotPotate();
		new Thread(potate).start();
		colorSwitcher = new Timer();
		new Thread(colorSwitcher).start();
	}

}
