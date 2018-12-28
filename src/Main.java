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
        System.out.println(j1.getListeRessources().toString());

        try {
            new FenetrePrincipale(j1,j2,j3,j4);
        } catch (PlateauException e) {
            e.printStackTrace();
        }

        System.out.println(" ------------------------------");

        /*GameController gc = new GameController();
        System.out.println(gc.toString());*/


    }
}
