package com.modele.cartes;
import com.modele.joueur.Joueur;

public class ProgresConstructionRouteGratuite implements Cartes {

    /**
     * Constructeur de la classe ProgresConstructionRouteGratuite
     */
    public ProgresConstructionRouteGratuite(){}

    /**
     * Action realise par la carte ProgresConstructionRouteGratuite
     * @param j Le joueur concerne
     */
    public void action(Joueur j) {
        //Ajouter au joueur les ressources nécessaire pour compenser la creation de la route
        j.creerRoute();
        System.out.println("Joue ConstructionRouteGratuite");
    }

}