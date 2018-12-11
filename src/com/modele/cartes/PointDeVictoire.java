package com.modele.cartes;
import com.modele.joueur.Joueur;

public class PointDeVictoire implements Cartes {

    /**
     * Constructeur de la classe PointDeVictoire
     */
    public PointDeVictoire(){}


    /**
     * Action realise par la carte PointDeVictoire
     * @param j Le joueur concerne
     */
    public void action(Joueur j) {
        j.ajouterPDV();
        System.out.println("Joue PdV");
    }
}
