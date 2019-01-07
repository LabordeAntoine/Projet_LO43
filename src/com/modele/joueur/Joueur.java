package com.modele.joueur;

import com.modele.cartes.*;
import com.modele.cartes.Robot;
import com.modele.construction.*;
import com.modele.ressources.ListeRessources;
import com.modele.ressources.Ressources;


import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Scanner;

public class Joueur {

    private String nom;
    private int pointVictoire;
    private Color couleur;

    private ListeRessources listeRessources = new ListeRessources();
    private ListeConstructions listeConstructions = new ListeConstructions();
    private ArrayList<Cartes> listCartes = new ArrayList<>();


    /**
     * Constructeur de la classe joueur
     * @param n nom du joueur
     */
    public Joueur(String n, Color couleur) {
        this.nom = n;
        this.pointVictoire = 0;
        this.couleur = couleur;

        this.ajouterRessources(Ressources.BLE, 5);
        this.ajouterRessources(Ressources.ARGILE, 5);
        this.ajouterRessources(Ressources.FER, 5);
        this.ajouterRessources(Ressources.BOIS, 5);
        this.ajouterRessources(Ressources.PLUTONIUM, 5);
        this.ajouterRessources(Ressources.MINERAI, 5);
    }

    public String getName() { return this.nom; }

    public Color getCouleur() { return couleur; }
    //POINTS DE VICTOIRE

    /**
     * Permet d'ajouter un point de victoire au joueur
     */
    public void ajouterPDV(){
        this.pointVictoire++;
    }

    /**
     * Permet d'obtenir le nombre de points de victoire du joueur
     * @return Le nombre de points de victoire
     */
    public int getPointVictoire(){return this.pointVictoire;}

    //RESSOURCES
    public void ajouterRessources(Ressources r){ this.listeRessources.ajouterRessources(r); }
    public void ajouterRessources(Ressources r, int nombre){ this.listeRessources.ajouterRessources(r,nombre); }
    public void ajouterRessources(ListeRessources lr){ this.listeRessources.ajouterRessources(lr); }

    public void supprimerRessources(Ressources r){ this.listeRessources.supprimerRessources(r); }
    public void supprimerRessources(Ressources r, int nombre){ this.listeRessources.supprimerRessources(r,nombre); }
    public void supprimerRessources(ListeRessources lr){ listeRessources.supprimerRessources(lr); }

    public boolean assezDeRessources(ListeRessources lr){ return this.listeRessources.assezDeRessources(lr); }
    public boolean assezDeRessources(Ressources r){ return this.listeRessources.assezDeRessources(r); }

    public int nombreDeRessources() { return listeRessources.nombreDeRessources(); }

    public void afficherRessource(){ System.out.println(this.getName()); this.listeRessources.afficherRessource(); }
    public String toStringRessources(){ return this.listeRessources.toString(); }

    public ListeRessources getListeRessources() { return listeRessources; }



    //CONSTRUCTIONS
    public Route creerRoute(Point2D.Double position) throws RessourcesInsuffisantesException, NombreLimiteException { return this.listeConstructions.construireRoute(this.listeRessources, position); }
    public ConvertisseurTemporel creerConvertisseurTemporel(Point2D.Double position) throws NombreLimiteException, RessourcesInsuffisantesException { return this.listeConstructions.construireConvertisseursTemporels(this.listeRessources, position); }
    public Delorean creerDelorean(Point2D.Double position) throws NombreLimiteException, RessourcesInsuffisantesException { return this.listeConstructions.construireDelorean(this.listeRessources, position); }

    public ListeConstructions getListeConstructions() { return listeConstructions; }

    public int getNombreDeRoutes(){ return this.listeConstructions.getNombreDeRoutes(); }
    public String toStringConstructions(){ return this.listeConstructions.toString(0) + this.listeConstructions.toString(1) + this.listeConstructions.toString(2); }



    //COMMERCE
    /**
     * Echanger des ressources entre joueurs
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

    /**
     * Echange de ressources entre un joueur et le jeu
     * @param lrDonnee Ressource(s) que l'on echange
     * @param lrRecevoir Ressource(s) que l'on recoit
     */
    public void echangeBanque(ListeRessources lrDonnee, ListeRessources lrRecevoir){

        //A VOIR : coeff d'echange

        if (this.assezDeRessources(lrDonnee)){

            this.supprimerRessources(lrDonnee);
            this.ajouterRessources(lrRecevoir);

        } else {
            System.out.println("Exception \"echangeBanque(ListeRessources lrDonnee, ListeRessources lrRecevoir)\", pas assez de ressources");
        }

    }

    public void actualiserPV(){
        this.pointVictoire = this.listeConstructions.size();
    }

    //CARTES

    public String[] getListCartes(){
        String[] Listetemp = new String[this.listCartes.size()];

        for (int i = 0; i < this.listCartes.size(); i++) {

            Listetemp[i] = this.listCartes.get(i).getClass().getSimpleName();
        }

        String[] Listetemp2 = Listetemp;

        return Listetemp;

    }
    
    /**
     * Permet au joueur de piocher une carte
     */
    public void piocherCarte() {

        int resultat;
        resultat = resultatAleatoire();

        switch (resultat) {
            case 1:
                this.listCartes.add(new ProgresConstructionRouteGratuite());
                break;

            case 2:
                this.listCartes.add(new ProgresVolerDesRessources());
                break;

            case 3:
                this.listCartes.add(new Robot());
                break;

            case 4:
                this.listCartes.add(new PointDeVictoire());
                break;

            default:
                System.out.println("PROBLEME");

        }

        this.supprimerRessources(Ressources.BOIS,2);
        this.supprimerRessources(Ressources.BLE,3);

    }

    /**
     * Permet au joueur de jouer une de ses cartes
     */
    public void jouerCarte(int index) {

        if(listCartes.size()>0) {
            if (index >= 0) {
                this.listCartes.get(index).action(this);
                this.listCartes.remove(index);
            } else
                System.out.println("PB");

        }
        else
            System.out.println(">> ERREUR : Vous n'avez pas de carte");

    }

    /**
     * Permet de generer un nombre aleatoire entre 1 et 4
     * @return Le resultat du tirage aleatoire
     */
    protected int resultatAleatoire() {

        return 1 + (int) (Math.random() * (5 - 1));
    }

    public void appelerBiff(){
        //Biff biff = Biff.getInstance();
        //biff.activer(this);
    }
}
