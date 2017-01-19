import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static Giraffics giraffe;
    public static Chamber russian;

    public static void main(String[] args) {
        int RoundSleep = 1000;
        Scanner sc = new Scanner(System.in);

        int numVictims ;//= sc.nextInt();

        giraffe = new Giraffics();
        new Thread(giraffe).start();
        while(!giraffe.getFuel()){
            try {
                Thread.sleep(10);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
        numVictims = giraffe.getFuelUnits();

        System.out.println(numVictims);
        russian = new Chamber(numVictims);
        giraffe.initNumPlayers();
        int i = 0; // playerNumber
        System.out.println(russian.toString());
        /*while (russian.numPlayers() > 1) {
            if (i == russian.numPlayers()) {
                i = 0;
            }


            String pew = russian.pullTheTrigger(); //set pew to either bang or no bang
            System.out.println(playerNames.get(i) + " turn : " + pew);

            if (pew.equals("YOUR RUMPUS GOT THUMPUS'D")) { //if shot
                System.out.println(playerNames.get(i) + " eliminated");
                int k = (i + 1) == russian.numPlayers() ? 0 : i + 1;

                System.out.println("Pick up the gun " + playerNames.get(k) + " and wipe off the ......");

                playerNames.remove(i);
                russian.reset();
                i--;
                System.out.println(russian.toString());
            }
            i++;
        }*/
        //TODO Auto-generated method stub
        //System.out.println(playerNames.get(0) + " is bloody but wins!!!");

    }


}