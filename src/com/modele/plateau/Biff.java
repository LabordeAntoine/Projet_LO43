package com.modele.plateau;


import com.modele.joueur.Joueur;
import com.modele.ressources.ListeRessources;
import com.modele.ressources.Ressources;

import java.util.ArrayList;

public class Biff {

    private static Case uneCase;
    private Joueur joueur;
    private Joueur[] tableauJoueurs;
    private static Biff instanceUnique = null;

    private Biff() { }
    public static Biff getInstance(Case c){
        if (instanceUnique == null)
            instanceUnique = new Biff();
        uneCase = c;
        return instanceUnique;
    }

    /**
     * Permet d'obtenir la position du Biff
     * @return La case sur laquelle le Biff se trouve
     */
    public Case getPosition(){
        return uneCase;
    }


    public void activer(Joueur j, Joueur[] tableauJoueurs){
        this.joueur = j;
        this.tableauJoueurs = tableauJoueurs;
        volerUneRessource(); //Au choix
        modifierNombreDeCartes(); //Au choix
        this.joueur = null;
        this.tableauJoueurs = null;
    }

    /**
     * Permet de de supprimer la moitier des ressources d'un joueur
     */
    private void modifierNombreDeCartes(){

        for (Joueur j : tableauJoueurs) {
            if (j.nombreDeRessources() >= 7) {
                ListeRessources lr = new ListeRessources();
                lr.ajouterRessources(Ressources.BOIS, 2);//Il faut recuperer les ressources que l'on veut enlever
                if (lr.nombreDeRessources() > (j.nombreDeRessources()/2)) //Si les ressources que l'on veut enlever est superieur a la moitie on peut continuer
                    j.supprimerRessources(lr);
                else
                    System.out.println("Exception\"private void modifierNombreDeCartes()\", les ressources choisis sont inferieur a la moitie");
            }
        }
    }

    /**
     * Permet de voler une ressource à un joueur
     */
    public void volerUneRessource(){
        int joueurVolee = 2; //Il faut recuperer le Joueur qui se fait voler
        Ressources ressourcesAVoler = Ressources.BLE; //Il faut recuperer la ressource a voler
        if (this.tableauJoueurs[joueurVolee].assezDeRessources(ressourcesAVoler)){
            this.tableauJoueurs[joueurVolee].supprimerRessources(ressourcesAVoler);
            this.joueur.ajouterRessources(ressourcesAVoler);
        } else {
            System.out.println("Exception \"private void volerUneRessource()\", pas assez de ressources");
        }
    }
}
