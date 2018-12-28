package com.modele;

import com.modele.joueur.Joueur;
import com.modele.plateau.Plateau;

public class GameController {
    private final int nombreDeJoueurs = 4;
    private final int nombreDePlateaux = 3;


    private Joueur[] tableauJoueurs;
    private Plateau[] tableauPlateaux;

    public GameController() {
        this.tableauJoueurs = new Joueur[nombreDeJoueurs];
        for (int i = 0; i < tableauJoueurs.length; i++)

        this.tableauPlateaux = new Plateau[nombreDePlateaux];
        for (int i = 0; i < tableauPlateaux.length; i++){
            tableauPlateaux[i] = new Plateau(1985);
        }
        demarrerPartie();
    }

    private void demarrerPartie(){

    }

    private void activerBiff(){
        tableauPlateaux[0].activerBiff(tableauJoueurs[0], tableauJoueurs);
    }

    public String toString(){
        return tableauPlateaux[0].toString() + tableauPlateaux[1].toString();
    }
}
