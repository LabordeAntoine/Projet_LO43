import com.graphique.fenetre_lancement.FenetreLancement;
import com.graphique.fenetre_principale.FenetrePrincipale;
import com.graphique.fenetre_principale.plateau.PlateauException;
import com.modele.GameController;
import com.modele.joueur.Joueur;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        FenetreLancement f = new FenetreLancement();
        f.setVisible(true);


        //new GameController();
        //System.out.println(j1.getListeRessources().toString());
    	/*ArrayList<Joueur> listeJoueur = new ArrayList<>();
        Joueur j1 = new Joueur("Antoine ", Color.MAGENTA);
        Joueur j2 = new Joueur("Ewan", Color.BLUE);
        Joueur j3 = new Joueur("Yosef", Color.RED);
        Joueur j4 = new Joueur("TEST", Color.GREEN);
        listeJoueur.add(j1);
        listeJoueur.add(j2);
        listeJoueur.add(j3);
        listeJoueur.add(j4);
        
        try {
            new FenetrePrincipale(listeJoueur);
        } catch (PlateauException e) {
            e.printStackTrace();
        }

        System.out.println(" ------------------------------");*/

        /*GameController gc = new GameController();
        System.out.println(gc.toString());*/


    }
}
