package com.modele.cartes;
import com.modele.joueur.Joueur;

public class ProgresVolerDesRessources implements Cartes {

    /**
     * Constructeur de la classe ProgresVolerDesRessources
     */
    public ProgresVolerDesRessources(){}

    /**
     * Action realise par la carte ProgresVolerDesRessources
     * @param j Le joueur concerne
     */
    public void action(Joueur j) {
        //Voler une ressource
        System.out.println("Joue VolerDesRessources");
    }

}
