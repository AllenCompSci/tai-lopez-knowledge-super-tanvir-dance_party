package mainmenu;


/**
 * Created by 223671 on 1/23/2017.
 */
public class MainMenu {
	static MenuWindow window;
	public static Timer colorSwitcher;

	public static void main(String[] args) {
		colorSwitcher = new Timer();
		new Thread(colorSwitcher).start();
		window = new MenuWindow();
		new Thread(window).start();

	}
}
