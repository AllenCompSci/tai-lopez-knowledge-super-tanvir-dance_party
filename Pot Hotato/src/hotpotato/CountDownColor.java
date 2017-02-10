package hotpotato;



import knowledge.Background;
import knowledge.Timer;

import java.awt.*;

/**
 * @author onContentStop
 */
public class CountDownColor extends Background {
	private static Color initialColor = Color.white;
	private static Color destinationColor = Color.green;
	private static Color currentColor = Color.white;
	private static int colorIntervalMS = 1000;
	private static Timer timer = Mayne.countdownTimer;
	public static void setInitialColor(Color c) {
		initialColor = c;
	}
	public static void setDestinationColor(Color c) {
		destinationColor = c;
	}
	public static Color getCurrentColor() {
		return currentColor;
	}
	public static void updateColor() {
		if (timer != null) {
			if (equalColors(currentColor, destinationColor)) {
				destinationColor = Color.green;
				initialColor = Color.white;
				//timer.restart();
			}
			int currMS = (timer.countingUp() ? timer.getMS() : colorIntervalMS - (timer.getMS() % colorIntervalMS == 0 ? colorIntervalMS : timer.getMS() % colorIntervalMS));
			currMS %= colorIntervalMS;
			double progress = (double) currMS / (double) colorIntervalMS;
			if (progress > 1d) {
				progress = 1d;
			}
			int currentR = getNumberBetween(initialColor.getRed(), destinationColor.getRed(), progress);
			int currentG = getNumberBetween(initialColor.getGreen(), destinationColor.getGreen(), progress);
			int currentB = getNumberBetween(initialColor.getBlue(), destinationColor.getBlue(), progress);
			currentColor = new Color(currentR, currentG, currentB);
		}
	}
	private static int getNumberBetween(int n1, int n2, double progress) {
//		double progressFunction = /*(4d / Constants.pi) * */ Math.sqrt(-1d * Math.pow(progress - 1, 2) + 1);
		double progressFunction = progress;
		long out = Math.round((double) n1 * (1 - progressFunction) + (double) n2 * progressFunction);
		if (progress >= 1.0) {
			return n2;
		}
		return (int) out;
	}
}
