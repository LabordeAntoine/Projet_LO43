import graphique.Fenetre;
import joueur.Joueur;
import test.ressources.Ressources;

public class Main {
    public static void main(String[] args) {


        Joueur j = new Joueur("Antoine");
        Joueur j1 = new Joueur("Jean");

        j.ajouterRessources(Ressources.ble);
        j.ajouterRessources(Ressources.ble);
        j.ajouterRessources(Ressources.ble);
        j.ajouterRessources(Ressources.ble);
        j.ajouterRessources(Ressources.ble);
        j.ajouterRessources(Ressources.ble);

        j.ajouterRessources(Ressources.bois);
        j.ajouterRessources(Ressources.bois);
        j.ajouterRessources(Ressources.bois);

        j.ajouterRessources(Ressources.fer);
        //j.ajouterRessources(test.ressources.Ressources.fer);

        j1.ajouterRessources(Ressources.bois);
        j1.ajouterRessources(Ressources.bois);
        j1.ajouterRessources(Ressources.bois);
        j1.ajouterRessources(Ressources.bois);
        j1.ajouterRessources(Ressources.bois);
        j1.ajouterRessources(Ressources.bois);
        j1.ajouterRessources(Ressources.bois);
        j1.ajouterRessources(Ressources.bois);

        j.afficherRessource();
        System.out.println(" ------------------------------");
        //j.creerConvertisseurTemporel();
        //j.creerDelorean();
        j.echangeJoueur(j1, Ressources.bois, Ressources.ble, 1, 0);

        //j.creerDelorean();
        j.afficherRessource();


        System.out.println(" ------------------------------");
        //j.echangeBanque(test.ressources.Ressources.bois, test.ressources.Ressources.fer, 1);
        //j.afficherRessource();

		/*j.piocherCarte();
        j.piocherCarte();
        j.piocherCarte();
        j.jouerCarte();
        j.jouerCarte();*/

        j.afficherRessource();
        Fenetre F = new Fenetre(j);
        F.setVisible(true);

        //j.appelerBiff();

    }
}
