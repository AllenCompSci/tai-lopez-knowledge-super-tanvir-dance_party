import java.awt.*;

public class Duck {
	private int xPos;
	private int yPos;
	private int xSpeed;
	private int ySpeed;
	private Color[] birdc;
	public Duck() {
		birdc = new Color[10];
		birdc[0] = new Color(0,0,0);
		birdc[1] = new Color(255,255,255);
		birdc[2] = new Color(242,236,47);
		birdc[3] = new Color(251,187,19);
		birdc[4] = new Color(242,107,61);
		xPos = 0;
		yPos = 0;
		xSpeed = 3;
		ySpeed = 3;
	}

	public Duck(int x, int y, int xs, int ys){
		xPos = x;
		yPos = y;
		xSpeed = xs;
		ySpeed = ys;
	}

	public int getxPos() {
		return xPos;
	}

	public int getyPos() {
		return yPos;
	}

	public int getxSpeed() {
		return xSpeed;
	}

	public int getySpeed() {
		return ySpeed;
	}

	public void setxPos(int x) {
		xPos = x;
	}

	public void setyPos(int y) {
		yPos = y;
	}

	public void setxSpeed(int xs) {
		xSpeed = xs;
	}

	public void setySpeed(int ys) {
		ySpeed = ys;
	}

	public void updatePosition() {
		xPos += xSpeed;
		yPos += ySpeed;
	}

	public void changeDirection() {
		double check = Math.random();

		if (check > 0.66) {
			xSpeed *= -1;
		}
		else if (check < 0.33) {
			ySpeed *= -1;
		}
		else{
			xSpeed *= -1;
			ySpeed *=-1;
		}
	}
	public boolean isOnScreen() {
		return (xPos >= 0 && xPos <= 800 && yPos >= 0 && yPos <= 600);
	}

	public void fixDirection(){
		if (yPos < 0) {
			ySpeed = 5;
		}
		else if (yPos > 542) {
			ySpeed = -5;
		}

		if (xPos < 0) {
			xSpeed = 5;
		} else if (xPos > 1102) {
			xSpeed = -5;
		}
	}
	public boolean isHit(int x, int y){
		boolean hit = false;
		/*
		if(Colorpt.equals(birdc[0]) ||Colorpt.equals(birdc[1]) ||Colorpt.equals(birdc[2]) ||Colorpt.equals(birdc[3]) ||Colorpt.equals(birdc[4])){
			hit = true;
		}*/
		if(((x >= xPos) && (x <= xPos + 178)) && ((y >= yPos) && (y <= yPos + 178))){
			hit = true;
		}
		else{
			hit = false;
		}
		return hit;
	}
}