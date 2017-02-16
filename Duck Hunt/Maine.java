public class Maine {
	public static Graphics girrafe;
	public static void main (String[] args) {

		girrafe = new Girrafics();
		girrafe.setVisible(true);
		new Thread(girrafe).start();
	}
}
