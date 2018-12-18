package com.modele.plateau;

import com.modele.joueur.Joueur;

import java.util.Random;

public class Plateau {


    public int annee;
    private Case[] tableauCases;

    private Biff biff;
    private int positionBiff;

    /**
     * Constructeur de la classe com.graphique.Plateau
     * @param annee Annee du plateau de jeu
     */
    public Plateau(int annee) {
        int nombreCases = 7;

        this.annee = annee;
        this.tableauCases = new Case[nombreCases];
        for (int i = 0; i < tableauCases.length; i++)
            tableauCases[i] = new Case();

        Random rand = new Random();
        int n = rand.nextInt(tableauCases.length - 1) + 1;
        deplacerBiff(n);
    }

    /**
     * Permet de deplacer le Biff
     * @param p position a laquelle on veut le deplacer
     */
    public void deplacerBiff(int p){
        biff = Biff.getInstance(tableauCases[p]);
        this.positionBiff = p;
    }

    /**
     * Permet d'activer le Biff
     * @param j Le joueur controlant le Biff
     * @param tableauJoueurs Tableau des joueurs
     */
    public void activerBiff(Joueur j, Joueur[] tableauJoueurs){
        int nouvellePositionBiff = 5; //Il faut recuperer la nouvelle position ou l'on veut placer
        deplacerBiff(nouvellePositionBiff);
        this.biff.activer(j, tableauJoueurs);
        //Personne recoit de ressources
    }

    public String toString(){
        StringBuilder s = new StringBuilder();
        s.append(this.annee);
        s.append("\nPosition Biff : ");
        s.append(this.positionBiff);
        s.append("\n");
        for (Case c : tableauCases)
            s.append(c.toString());
        return s.toString();
    }
}
