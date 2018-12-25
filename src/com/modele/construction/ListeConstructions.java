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
    public Route construireRoute(ListeRessources lr) throws RessourcesInsuffisantesException, NombreLimiteException {
        final int max = 15;

        if (this.nombreDeRoutes < max){
            Route route;
            route = new Route(lr); // Throws RessourcesInsuffisantesException
            add(route);
            this.nombreDeRoutes++;
            return route;
        } else {
            throw new NombreLimiteException("Route", max);
        }
    }

    /**
     * Permet de construire une Delorean
     * @param lr Liste de ressources du joueur
     */
    public Delorean construireDelorean(ListeRessources lr) throws RessourcesInsuffisantesException, NombreLimiteException {
        int max = 5;

        if (this.nombreDeDelorean < max){
            Delorean delorean;
            delorean = new Delorean(lr);
            add(delorean);
            this.nombreDeDelorean++;
            return delorean;
        } else {
            throw new NombreLimiteException("Delorean", max);
        }

    }


    /**
     * Permet de construire un convertisseur temporel
     * @param lr Liste de ressources du joueur
     */
    public ConvertisseurTemporel construireConvertisseursTemporels(ListeRessources lr) throws RessourcesInsuffisantesException, NombreLimiteException {
        int max = 5;

        if (this.nombreDeConvertisseursTemporels < max){
            ConvertisseurTemporel convertisseurTemporel;
            convertisseurTemporel = new ConvertisseurTemporel(lr);
            add(convertisseurTemporel);
            this.nombreDeConvertisseursTemporels++;
            return convertisseurTemporel;
        } else {
            throw new NombreLimiteException("Convetisseur Temporel", max);
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
