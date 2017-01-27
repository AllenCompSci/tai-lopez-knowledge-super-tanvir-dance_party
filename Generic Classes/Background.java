package physics;

import java.awt.*;

/**
 * Created by mario on 1/12/2017.
 */
public class Background {
	private static Color initialColor = Color.white;
	private static Color destinationColor = Color.white;
	private static Color currentColor = Color.white;
	private static int colorIntervalMS = 10000;
	public static Color getCurrentColor() {
		return currentColor;
	}

	public static Color getInitialColor() {
		return initialColor;
	}

	public static Color getDestinationColor() {
		return destinationColor;
	}

	public static int getColorIntervalMS() {
		return colorIntervalMS;
	}
	public static void updateColor() {
		if(Main.colorSwitcher != null) {
			if (equalColors(currentColor, destinationColor)) {
				do {
					destinationColor = RandomColorGenerator.getOpaqueColor();
				} while (destinationColor.getRed() + destinationColor.getBlue() + destinationColor.getGreen() < 350);
				initialColor = currentColor;
				Main.colorSwitcher.restart();
			}
			double progress = (double) Main.colorSwitcher.getMS() / (double) colorIntervalMS;
			if (progress > 1d) {
				progress = 1d;
			}
			int currentR = getNumberBetween(initialColor.getRed(), destinationColor.getRed(), progress);
			int currentG = getNumberBetween(initialColor.getGreen(), destinationColor.getGreen(), progress);
			int currentB = getNumberBetween(initialColor.getBlue(), destinationColor.getBlue(), progress);
			currentColor = new Color(currentR, currentG, currentB);
		}
	}
	private static boolean equalColors(Color c1, Color c2) {
		return c1.getRed() == c2.getRed()
				&& c1.getGreen() == c2.getGreen()
				&& c1.getBlue() == c2.getBlue()
				&& c1.getAlpha() == c2.getAlpha();
	}
	private static int getNumberBetween(int n1, int n2, double progress) {
		double k = 3.5;
		double progressFunction = 1.06 / (1d + Math.pow(Constants.e, - 1d * k * (2 * progress - 1))) - 0.03;//logistic function with k being variable, x being progress (multiplied by 2 to condense the function), and x_o being 1
		long out = Math.round((double) n1 * (1 - progressFunction) + (double) n2 * progressFunction);
		if (progress >= 1.0) {
			return n2;
		}
		return (int)out;
	}
}
