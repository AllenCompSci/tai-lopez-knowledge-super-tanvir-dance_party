package duckHuntStuff;

/**
 * Created by 239480 on 1/31/2017.
 */
public class Maine {
    public static void main (String[] args) {
        Duck duck1 = new Duck();

        long start = System.currentTimeMillis();

        while (System.currentTimeMillis() - start < 10000){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (Math.random() < 0.1 && !duck1.isOnScreen()) {
                duck1.setxPos(1080);
                duck1.setyPos((int)(Math.random() * 540));
            }
            if (duck1.isOnScreen()) {
                duck1.updatePosition();
            }

            System.out.println("(" + duck1.getxPos() + ", " + duck1.getyPos() + ")\n");
        }
    }
}