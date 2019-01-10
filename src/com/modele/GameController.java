package com.modele;

import java.util.ArrayList;

import com.modele.joueur.Joueur;
import com.modele.plateau.Plateau;

public class GameController {
    private final int nombreDeJoueurs = 4;
    private final int nombreDePlateaux = 3;


    private ArrayList<Joueur> tableauJoueurs = new ArrayList();
    private Plateau[] tableauPlateaux;

    public GameController() {
        //FenetreLancement fenetreLancement = new FenetreLancement(tableauJoueurs);
        
        demarrerPartie();
    }

    private void demarrerPartie(){

    }

    private void activerBiff(){
        //tableauPlateaux[0].activerBiff(tableauJoueurs[0], tableauJoueurs);
    }

    public String toString(){
        return tableauPlateaux[0].toString() + tableauPlateaux[1].toString();
    }
}
