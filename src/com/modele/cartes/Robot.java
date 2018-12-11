package com.modele.cartes;
import com.modele.joueur.Joueur;

public class Robot implements Cartes {

    /**
     * Constructeur de la classe Robot
     */
    public Robot(){}

    /**
     * Action realise par la carte Robot
     * @param j Le joueur concerne
     */
    public void action(Joueur j){
        //Déplacer le Biff
        //Voler une carte
        //Incremente le nombre de robot possede
        System.out.println("Joue Robot");
    }


}
