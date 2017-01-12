package pothotato;

import java.awt.*;

/**
 * Created by mario on 1/12/2017.
 */
public class Background {
	private static Color initialColor = new Color(0, 0,0);
	private static Color destinationColor = new Color(0, 0,0);
	private static Color currentColor = new Color(0, 0, 0);
	private static int colorIntervalMS = 10000;
	public static Color getCurrentColor() {
		return currentColor;
	}
	public static void updateColor() {
		if(Mayne.colorSwitcher != null) {
			if (equalColors(currentColor, destinationColor)) {
				destinationColor = RandomColorGenerator.getOpaqueColor();
				initialColor = currentColor;
				Mayne.colorSwitcher.restart();
			}
			double progress = (double) Mayne.colorSwitcher.getMS() / (double) colorIntervalMS;
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
		double n1d = n1;
		double n2d = n2;
		long out = Math.round(n1d * (1 - progress) + n2d * progress);
		return (int)out;
	}
}
