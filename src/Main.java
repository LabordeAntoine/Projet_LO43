import com.graphique.FenetreLancement;
import com.modele.GameController;
import com.modele.ressources.Ressources;
import com.modele.joueur.Joueur;

import com.graphique.Fenetre;

public class Main {
    public static void main(String[] args) {

        FenetreLancement F = new FenetreLancement();
        F.setVisible(true);

        System.out.println(" ------------------------------");

        GameController gc = new GameController();
        System.out.println(gc.toString());


    }
}
