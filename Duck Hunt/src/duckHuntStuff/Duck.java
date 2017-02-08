package duckHuntStuff;

/**
 * Created by 239480 on 2/2/2017.
 */
public class Duck {
    private int xPos;
    private int yPos;
    private int xSpeed;
    private int ySpeed;

    public Duck() {
        xPos = 0;
        yPos = 0;
        xSpeed = 100;
        ySpeed = 100;
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

    public boolean isOnScreen() {
        return (xPos > 0 && xPos < 1920 && yPos > 0 && yPos < 1080);
    }
}
