package redlightgreenlight;
import java.awt.Point;
/**
 * Created by 219305 on 12/2/2016.
 */
public class Player {
    private Point position;
    private int MoveSpeed;
    private int Penalty;
    private boolean HOLDME;

    public Player(int x, int y){
        position = new Point();
        Penalty = 0;
        position.x = x;
        position.y = y;
        MoveSpeed = 5;
        HOLDME = false;
    }

    public void Update(int currTime){
        MoveSpeed = (int) (currTime * 1.85);
    }
    public void MoveX(){
        position.x += MoveSpeed;
        HOLDME = true;
        //System.out.println(position.x);
    }
    public void MoveUp(){
        position.y -= 50;
        HOLDME = true;
        //System.out.println(position.x);
    }
    public void MoveDown(){
        position.y +=50;
        HOLDME = true;
    }

    public void Penalty(){
        position.x -= Math.pow(MoveSpeed, Penalty++/8);
        if(position.x < 0){
            position.x = 0;
        }
    }
    public void getJackedTitanic(){
        HOLDME = false;
    }
    public boolean neverLetGo(){
        return HOLDME;
    }
    public int getX(){
        return position.x;
    }
    public int getY(){
        return position.y;
    }
}
