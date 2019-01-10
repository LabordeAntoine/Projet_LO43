/**
 * Carte GagnerFer permet de gagner des ressouces de Fer
 */

package com.modele.cartes;
import com.modele.joueur.*;
import com.modele.ressources.Ressources;

public class GagnerFer implements Cartes {

    /**
     * Constructeur de la classe GagnerFer
     */
    public GagnerFer(){}

    /**
     * Action realise par la carte GagnerFer
     * @param j Le joueur concerne
     */
    public void action(Joueur j) {
        j.ajouterRessources(Ressources.FER,1 + (int) (Math.random() * (4 - 1)));
        System.out.println("Joue GagnerFer");
    }

}
