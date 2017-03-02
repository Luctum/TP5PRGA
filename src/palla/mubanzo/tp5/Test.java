package palla.mubanzo.tp5;

/**
 * Created by luctu on 02/03/2017.
 */
public class Test {

    public static void main(String[] d){

        ChargerGrille s = new ChargerGrille();
        MotsCroisesTP5 mc = s.extraireGrille();
        System.out.println(mc.toString());
    }
}
