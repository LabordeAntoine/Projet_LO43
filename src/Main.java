import com.ressources.ListeRessources;
import com.ressources.Ressources;
import com.graphique.Fenetre;
import com.joueur.Joueur;

import java.util.List;

public class Main {
    public static void main(String[] args) {


        Joueur j = new Joueur("Antoine");
        Joueur j1 = new Joueur("Jean");

        j.ajouterRessources(Ressources.BLE);
        j.ajouterRessources(Ressources.BLE);
        j.ajouterRessources(Ressources.BLE);
        j.ajouterRessources(Ressources.BLE);
        j.ajouterRessources(Ressources.BLE);
        j.ajouterRessources(Ressources.BLE);

        j.ajouterRessources(Ressources.BOIS);
        j.ajouterRessources(Ressources.BOIS);
        j.ajouterRessources(Ressources.BOIS);

        j.ajouterRessources(Ressources.FER);
        j.ajouterRessources(Ressources.FER);
        j.ajouterRessources(com.ressources.Ressources.FER);

        j1.ajouterRessources(Ressources.BOIS);
        j1.ajouterRessources(Ressources.BOIS);
        j1.ajouterRessources(Ressources.BOIS);

        j.afficherRessource();
        System.out.println(" ------------------------------");
        //j.creerConvertisseurTemporel();
        //j.creerDelorean();
        //j.echangeJoueur(j1, Ressources.BOIS, Ressources.BLE, 1, 0);

        //j.creerDelorean();
        //j.supprimerRessources(j1.getListeRessources());


        System.out.println(" ------------------------------");
        //j.echangeBanque(com.ressources.Ressources.BOIS, com.ressources.Ressources.FER, 1);;
        //test
        //j1.afficherRessource();

		/*j.piocherCarte();
        j.piocherCarte();
        j.piocherCarte();
        j.jouerCarte();
        j.jouerCarte();

        j.afficherRessource();
        ListeRessources lr = new ListeRessources();
        lr.ajouterRessources(Ressources.BLE, 5);
        ListeRessources lr1 = new ListeRessources();
        lr1.ajouterRessources(Ressources.BOIS, 2);
        j.echangeBanque(lr, lr1);

        //j1.afficherRessource();
        j.afficherRessource();*/
        j.creerDelorean();
        j.creerDelorean();
        j.afficherRessource();
        /*j.creerDelorean();
        j.creerDelorean();
        j.creerConvertisseurTemporel();
        j.creerConvertisseurTemporel();
        j.creerConvertisseurTemporel();*/

        System.out.println(j.toStringConstructions());
        //Fenetre F = new Fenetre(j);
        //F.setVisiBLE(true);

        //j.appelerBiff();

    }
}
