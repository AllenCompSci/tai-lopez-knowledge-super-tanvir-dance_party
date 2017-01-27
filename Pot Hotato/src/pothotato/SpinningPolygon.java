package pothotato;

import knowledge.Constants;
import knowledge.Timer;

import java.awt.*;

/**
 * Created by mario on 1/16/2017.
 */
public class SpinningPolygon {
	private int points, period, center_x, center_y, radius;
	private Color innerColor, outerColor;
	private Timer timer;
	private double theta;

	/**
	 * Creates a spinning polygon to draw on screen.
	 *
	 * @param numPoints  The number of points on the polygon, e.g 3 = triangle
	 * @param spinPeriod The time it takes for the polygon to make one full (360 degree) rotation, in milliseconds
	 * @param centerX    x-coordinate of the polygon's center
	 * @param centerY    y-coordinate of the polygon's center
	 * @param color      color of the inside of the polygon (to disable, set color to transparent)
	 * @param maxRadius  distance from the center to a vertex
	 * @param timer      the timer used to calculate spin
	 */
	public SpinningPolygon(int numPoints, int spinPeriod, int centerX, int centerY, Color color, int maxRadius, Timer timer) {
		points = numPoints;
		period = spinPeriod;
		center_x = centerX;
		center_y = centerY;
		innerColor = color;
		outerColor = Color.BLACK;
		radius = maxRadius;
		this.timer = timer;
	}

	public SpinningPolygon(int numPoints, int spinPeriod, int centerX, int centerY, Color fillColor, Color borderColor, int maxRadius, Timer timer) {
		points = numPoints;
		period = spinPeriod;
		center_x = centerX;
		center_y = centerY;
		innerColor = fillColor;
		outerColor = borderColor;
		radius = maxRadius;
		this.timer = timer;
	}

	public void draw(Graphics2D g2d) {

		double theta_deg = (Mayne.animTimer.getMS() * 360 / period);//angle in degrees
		theta = Math.toRadians(theta_deg);
		double[] angles = new double[points];
		for (int i = 0; i < points; i++) {
			angles[i] = theta + (double) i * (2d * Constants.pi) / (double) points;
		}
		int[] pointsX = new int[points];
		int[] pointsY = new int[points];
		for (int i = 0; i < points; i++) {
			pointsX[i] = center_x + (int) Math.round(radius * Math.cos(angles[i]));
			pointsY[i] = center_y + (int) Math.round(radius * Math.sin(angles[i]));
		}
		g2d.setColor(innerColor);
		g2d.fillPolygon(pointsX, pointsY, points);
		g2d.setColor(outerColor);
		g2d.drawPolygon(pointsX, pointsY, points);
	}

	public void setCenter(int x, int y) {
		center_x = x;
		center_y = y;
	}

	public double getAngle() {
		return theta;
	}

	public Color getInnerColor() {
		return innerColor;
	}
}
