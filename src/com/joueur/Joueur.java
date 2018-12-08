package com.joueur;

import com.cartes.*;
import com.construction.*;
import com.ressources.ListeRessources;
import com.ressources.Ressources;


import java.util.ArrayList;
import java.util.Scanner;

public class Joueur {

    private String nom;
    private int pointVictoire;
    private ListeRessources listeRessources = new ListeRessources();
    private ListeConstructions listeConstructions = new ListeConstructions();
    private ArrayList<Cartes> c = new ArrayList<Cartes>();


    /**
     *
     * @param n nom du joueur
     */
    public Joueur(String n) {
        this.nom = n;
        this.pointVictoire = 0;
    }
    public String getName()
    {
        return this.nom;
    }



    //RESSOURCES
    public ListeRessources getListeRessources() { return listeRessources; }

    public void ajouterRessources(Ressources r){ this.listeRessources.ajouterRessources(r); }
    public void ajouterRessources(Ressources r, int nombre){ this.listeRessources.ajouterRessources(r,nombre); }
    public void ajouterRessources(ListeRessources lr){ this.listeRessources.ajouterRessources(lr); }

    public void supprimerRessources(Ressources r){ this.listeRessources.supprimerRessources(r); }
    public void supprimerRessources(Ressources r, int nombre){ this.listeRessources.supprimerRessources(r,nombre); }
    public void supprimerRessources(ListeRessources lr){ listeRessources.supprimerRessources(lr); }

    public boolean assezDeRessources(ListeRessources lr){ return this.listeRessources.assezDeRessources(lr); }

    public void afficherRessource(){ System.out.println(this.getName()); this.listeRessources.afficherRessource(); }
    public String toStringRessources(){ return this.listeRessources.toString(); }




    //CONSTRUCTIONS
    public void creerRoute(){ this.listeConstructions.construireRoute(this.listeRessources); }
    public void creerConvertisseurTemporel() { this.listeConstructions.construireConvertisseursTemporels(this.listeRessources); }
    public void creerDelorean() { this.listeConstructions.construireDelorean(this.listeRessources); }

    public int getNombreDeRoutes(){ return this.listeConstructions.getNombreDeRoutes(); }
    public String toStringConstructions(){ return this.listeConstructions.toString(); }




    //COMMERCE
    /**
     *
     * @param lr Ressources que l'appelant va donner
     * @param j2 Joueur appelee
     * @param lrj2 Ressources que l'appelant va recevoir
     */
    public void echangeJoueur(ListeRessources lr, Joueur j2,  ListeRessources lrj2){

        if (this == j2) {
            System.out.println("Fait pas le malin, pq tu change avec toi meme??");
            return;
        }

        if (this.assezDeRessources(lr) && j2.assezDeRessources(lrj2)){

            j2.ajouterRessources(lr);
            this.supprimerRessources(lr);

            this.ajouterRessources(lrj2);
            j2.supprimerRessources(lrj2);

        } else {
            System.out.println("Erreur \"echangeJoueur(ListeRessources lr, Joueur j2,  ListeRessources lrj2)\", pas assez de ressources");
        }
    }
    public void echangeBanque(ListeRessources lrDonnee, ListeRessources lrRecevoir){

        //A VOIR : coeff d'echange

        if (this.assezDeRessources(lrDonnee)){

            this.supprimerRessources(lrDonnee);
            this.ajouterRessources(lrRecevoir);

        } else {
            System.out.println("Exception \"echangeBanque(ListeRessources lrDonnee, ListeRessources lrRecevoir)\", pas assez de ressources");
        }

    }


    //CARTES
    public void piocherCarte() {

        int resultat;
        resultat = resultatAleatoire();

        switch (resultat) {
            case 1:
                this.c.add(new ProgresConstructionRouteGratuite());
                break;

            case 2:
                this.c.add(new ProgresVolerDesRessources());
                break;

            case 3:
                this.c.add(new Robot());
                break;

            case 4:
                this.c.add(new PointDeVictoire());
                break;

            default:
                System.out.println("PROBLEME");

        }

    }

    public void jouerCarte() {

        if(c.size()>0) {

            System.out.println("Quelle carte jouer?");
            ArrayList<Cartes> temp = this.c;

            int cmp = 0;

            for (int i = 0; i < temp.size(); i++) {
                System.out.println("Tapez " + cmp + " pour jouer la carte " + temp.get(cmp).getClass().getName());
                cmp++;
            }


            Scanner sc = new Scanner(System.in);
            int rep = sc.nextInt();

            while (rep < 0 || rep > c.size() - 1) {
                System.out.println(">> ERREUR : Veuillez rentrer un des numero propose !");
                rep = sc.nextInt();
            }

            if (rep >= 0) {
                this.c.get(rep).action();
                this.c.remove(rep);
            } else
                System.out.println("PB");

        }
        else
            System.out.println(">> ERREUR : Vous n'avez pas de carte");

    }

    protected int resultatAleatoire() {

        return 1 + (int) (Math.random() * (5 - 1));
    }

    public void appelerBiff(){
        Biff biff = new Biff();
        biff.activer(this);
    }

    public int nombreDeRessources() {
        return listeRessources.size();
    }
}
