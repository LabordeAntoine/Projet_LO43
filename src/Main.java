import com.modele.GameController;
import com.modele.ressources.Ressources;
import com.modele.joueur.Joueur;

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
        j.ajouterRessources(com.modele.ressources.Ressources.FER);

        j1.ajouterRessources(Ressources.BOIS);
        j1.ajouterRessources(Ressources.BOIS);
        j1.ajouterRessources(Ressources.BOIS);

        j.afficherRessource();
        System.out.println(" ------------------------------");
        //Fenetre F = new Fenetre(j);
        //F.setVisiBLE(true);


        System.out.println(" ------------------------------");

        GameController gc = new GameController();
        System.out.println(gc.toString());

    }
}
