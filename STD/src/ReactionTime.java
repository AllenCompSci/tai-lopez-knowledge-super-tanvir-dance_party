//import java.util.Scanner;

//public class ReactionTime implements Runnable{

	//@Override
	//public void run() {
		//System.out.println("fuck");
		
	//}
	
	//public static void main(String args[]){
	//	(new Thread(new ReactionTime())).start();
	//}
	
	
	
	
	
	
//}
/*
	//private int scores[];
	private static int randomtime;
	private static int start;
	
	
	@SuppressWarnings("resource")
	public static int run(){
		Scanner in = new Scanner(System.in);
		randomtime = (int) Math.random()*10 + 1;
		
		while(true){
			if(randomtime - (int)System.currentTimeMillis() == 0){
				start = (int)System.currentTimeMillis();
				System.out.println("Time Start!");
				while(true){
					System.out.println("Press the damn button.");
					String s = in.next();
					if(s == ""){
						return start - (int)System.currentTimeMillis();
					}
				}
			}
		}
	}
}
*/