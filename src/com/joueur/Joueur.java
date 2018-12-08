package com.joueur;

import com.cartes.*;
import com.construction.Construction;
import com.construction.ConvertisseurTemporel;
import com.construction.Delorean;
import com.construction.Route;
import com.ressources.CaseRessources;
import com.ressources.ListeRessources;
import com.ressources.Ressources;


import java.util.ArrayList;
import java.util.Scanner;

public class Joueur {

    private String nom;
    private int pointVictoire;
    private ListeRessources lr = new ListeRessources();
    private ArrayList<Cartes> c = new ArrayList<Cartes>();

    private ArrayList<Construction> listConstruction = new ArrayList<Construction>();

    /**
     *
     * @param n nom du joueur
     */
    public Joueur(String n) {
        this.nom = n;
        this.pointVictoire = 0;
        this.listConstruction.add(new Delorean());
        this.listConstruction.add(new ConvertisseurTemporel());
        this.listConstruction.add(new Route());
    }

    public String getName()
    {
        return this.nom;
    }
    public int getNombreDeRoutes(){

        int nombre = 0;
        for (Construction c : listConstruction){
            if(c instanceof Route){
                nombre++;
            }
        }
        return nombre;
    }

    public void ajouterRessources(Ressources r){ this.lr.ajouterRessources(r);}
    public void ajouterRessources(Ressources r, int nombre){this.lr.ajouterRessources(r,nombre);}
    public void supprimerRessources(Ressources r){this.lr.supprimerRessources(r);}
    public void supprimerRessources(Ressources r, int nombre){this.lr.supprimerRessources(r,nombre);}
    public void afficherRessource(){this.lr.afficherRessource();}
    public String toStringRessources() {return this.lr.toString();}


    public void creerConvertisseurTemporel()
    {
        ConvertisseurTemporel ct = new ConvertisseurTemporel();
        ct.creer(lr);
    }
    public void creerDelorean()
    {
        Construction delorean = new Delorean();
        delorean.creer(lr);
    }

    public boolean testerAssezRessource(Ressources r, int nombre)
    {
        for(CaseRessources cr : lr)
        {
            if(cr.equals(new CaseRessources(r)))
                if(cr.getNombre() >= nombre)
                    return true;
        }
        return false;
    }

    public void echangeJoueur(Joueur j, Ressources r1, Ressources r2, int nombre1, int nombre2)//1 joueur appelé pour échange, 2 joueur appelant à l'échange
    {
        if(j.testerAssezRessource(r1, nombre1) && this.testerAssezRessource(r2, nombre2))
        {
            j.supprimerRessources(r1, nombre1);
            j.ajouterRessources(r2, nombre2);
            this.supprimerRessources(r2, nombre2);
            this.ajouterRessources(r1, nombre1);
        }
        else
        {
            System.out.println("Echange impossible !");
        }
    }

    public void echangeBanque(Ressources r1, Ressources r2, int nombre1)
    {
        if(this.testerAssezRessource(r1, nombre1*4))
        {
            this.supprimerRessources(r1, nombre1*4);
            this.ajouterRessources(r2,nombre1);
        }


    }

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
        return lr.size();
    }
}
