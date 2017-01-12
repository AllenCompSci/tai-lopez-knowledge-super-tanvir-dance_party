package pothotato;


/**
 * Created by 223671 on 1/12/2017.
 */
public class Mayne {
    public static void main(String[] args) {
        HotPotate potate = new HotPotate();
        new Thread(potate).start();
    }

}
