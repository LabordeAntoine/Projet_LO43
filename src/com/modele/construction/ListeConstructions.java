package com.modele.construction;

import com.modele.ressources.ListeRessources;

import java.util.ArrayList;

public class ListeConstructions extends ArrayList<Construction> {

    private int nombreDeRoutes;
    private int nombreDeDelorean;
    private int nombreDeConvertisseursTemporels;

    /**
     * Constructeur de la classe ListeConstruction
     */
    public ListeConstructions() {
        this.nombreDeRoutes = 0;
        this.nombreDeDelorean = 0;
        this.nombreDeConvertisseursTemporels = 0;
    }

    /**
     * Permet d'obtenir le nombre de routes
     * @return int Nombre de routes
     */
    public int getNombreDeRoutes() {
        return nombreDeRoutes;
    }

    /**
     * Permet de construire une route
     * @param lr Liste de ressources du joueur
     */
    public void construireRoute(ListeRessources lr){
        int max = 15;

        if (this.nombreDeRoutes < max){
            Route route;


            try {

                route = new Route(lr);
                add(route);
                this.nombreDeRoutes++;

            } catch (SimpleException e) {
                System.out.println(e.getMessage());
            }

        } else {
            System.out.println("Exception \"construireRoute()\", max atteint");
        }

    }

    /**
     * Permet de construire une Delorean
     * @param lr Liste de ressources du joueur
     */
    public void construireDelorean(ListeRessources lr){
        int max = 5;

        if (this.nombreDeDelorean < max){
            Delorean delorean;


            try {

                delorean = new Delorean(lr);
                add(delorean);
                this.nombreDeDelorean++;

            } catch (SimpleException e) {
                System.out.println(e.getMessage());
            }

        } else {
            System.out.println("Exception \"construireDelorean()\", max atteint");
        }

    }


    /**
     * Permet de construire un convertisseur temporel
     * @param lr Liste de ressources du joueur
     */
    public void construireConvertisseursTemporels(ListeRessources lr){
        int max = 5;

        if (this.nombreDeConvertisseursTemporels < max){
            ConvertisseurTemporel convertisseurTemporel;


            try {

                convertisseurTemporel = new ConvertisseurTemporel(lr);
                add(convertisseurTemporel);
                this.nombreDeConvertisseursTemporels++;

            } catch (SimpleException e) {
                System.out.println(e.getMessage());
            }


        } else {
            System.out.println("Exception \"construireConvertisseursTemporels()\", max atteint");
        }

    }

    /**
     *
     * @return
     */
    public String toString(){

        String resultat = "";

        for (Construction c : this){
            resultat += c.getClass().getSimpleName() + "\n";
        }

        return resultat;
    }

}