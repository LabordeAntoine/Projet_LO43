import com.graphique.fenetre_lancement.FenetreLancement;
import com.graphique.fenetre_principale.FenetrePrincipale;
import com.graphique.fenetre_principale.plateau.PlateauException;
import com.modele.joueur.Joueur;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        //new FenetreLancement();
        //FenetreLancementOld F1 = new FenetreLancementOld();
        //F1.setVisible(true);
        //F.setVisible(true);
        //FenetreJoueurs F2 = new FenetreJoueurs();
        System.setProperty("awt.useSystemAAFontSettings","on");
        System.setProperty("swing.aatext", "true");
        Joueur j1 = new Joueur("Antoine ", Color.MAGENTA);
        Joueur j2 = new Joueur("Ewan", Color.MAGENTA);
        Joueur j3 = new Joueur("Yosef", Color.MAGENTA);
        Joueur j4 = new Joueur("TEST", Color.MAGENTA);

        try {
            new FenetrePrincipale(j1,j2,j3,j4);
        } catch (PlateauException e) {
            e.printStackTrace();
        }

        HashMap<Point2D.Double, String> test = new HashMap<>();
        test.put(new Point2D.Double(20, 20), "value");
        test.put(new Point2D.Double(20, 20), "other value");
        for (Map.Entry<Point2D.Double, String> e : test.entrySet()){
            System.out.println(e.getKey());
        }

        System.out.println(" ------------------------------");

        /*GameController gc = new GameController();
        System.out.println(gc.toString());*/


    }
}
