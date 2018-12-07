import java.util.ArrayList;

public class Biff {

    private int _case;
    private Joueur joueur;
    //MODIFICATION
    //ENCORE UNE MODIFICATION
    //ENCORE UNE AUTRE MODIFICATION


    public void activer(Joueur j){
        this.joueur = j;
        deplacer();
        volerRessource();
        //modifierNombreDeCartes(ArrayList<Joueur> listeDeJoueurs);
    }

    private void deplacer(){
        this._case = Entree.entrerNombreUtilisateur(1, 10,"Entrez la nouvelle position du Biff");
    }

    private void interrompreRessources(){
        //interrompre les ressources
    }

    private void volerRessource(){
        Joueur joueurVolee;
        //joueurVolee.supprimerRessources();
    }

    private void modifierNombreDeCartes(ArrayList<Joueur> listeDeJoueurs){

        for (Joueur j : listeDeJoueurs) {
            if (j.nombreDeRessources() >= 7) {
                for (int i = 0; i < j.nombreDeRessources(); i++) {
                    int ressource = Entree.entrerNombreUtilisateur(1, j.nombreDeRessources(), "Entrez la ressource que vous voulez enlever");
                    //j.supprimerRessources(ressource);
                }
            }
        }
    }
}
