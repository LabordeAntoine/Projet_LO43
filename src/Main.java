import com.graphique.fenetre_lancement.FenetreLancement;
import com.graphique.fenetre_principale.FenetrePrincipale;
import com.modele.joueur.Joueur;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        //new FenetreLancement();
        //FenetreLancementOld F1 = new FenetreLancementOld();
        //F1.setVisible(true);
        //F.setVisible(true);
        //FenetreJoueurs F2 = new FenetreJoueurs();
        Joueur j1 = new Joueur("Antoine ");
        Joueur j2 = new Joueur("Ewan");
        Joueur j3 = new Joueur("Yosef");
        Joueur j4 = new Joueur("TEST");

        new FenetrePrincipale(j1,j2,j3,j4);

        HashMap<Point, String> test = new HashMap<>();
        test.put(new Point(20, 20), "value");
        test.put(new Point(20, 20), "other value");
        for (Map.Entry<Point, String> e : test.entrySet()){
            System.out.println(e.getKey());
        }

        System.out.println(" ------------------------------");

        /*GameController gc = new GameController();
        System.out.println(gc.toString());*/


    }
}
