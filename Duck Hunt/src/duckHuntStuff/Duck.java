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
        xSpeed = 1;
        ySpeed = 1;
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
}
