package testing;

import java.awt.*;

/**
 * Created by mario on 1/12/2017.
 */
public class Main {
	private static pothotato.Timer timer;
	public static void main(String[] args) throws InterruptedException {
		timer = new pothotato.Timer();
		new Thread(timer).start();
		int interval = 10000;
		Color c1 = new Color(78, 34, 30);
		Color c2 = new Color(255, 255, 100);
		int r1 = c1.getRed();
		int r2 = c2.getRed();
		timer.start();
		while(timer.getMS() < interval) {
			double progress = (double)timer.getMS() / (double)interval;
			System.out.print("" + Math.round(
					(double)r1 * (1 - progress)
					+ (double)r2 * progress
			) + ", ");
			Thread.sleep(100);
		}
		timer.reset();
		System.exit(0);
	}
}
