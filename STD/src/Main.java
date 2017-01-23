
public class Main {
	
	public static graphics desu;
	
	public static void main(String[] args) {
		desu = new graphics();
		
		new Thread(desu).start();
		//System.out.println(ReactionTime.run());
	}

}
