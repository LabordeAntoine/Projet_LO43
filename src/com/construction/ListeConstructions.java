package com.construction;

import com.SimpleException;
import com.ressources.ListeRessources;

import java.util.ArrayList;

public class ListeConstructions extends ArrayList<Construction> {

    private int nombreDeRoutes;
    private int nombreDeDelorean;
    private int nombreDeConvertisseursTemporels;

    public ListeConstructions() {
        this.nombreDeRoutes = 0;
        this.nombreDeDelorean = 0;
        this.nombreDeConvertisseursTemporels = 0;
    }

    public int getNombreDeRoutes() {
        return nombreDeRoutes;
    }

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

    public String toString(){

        String resultat = "";

        for (Construction c : this){
            resultat += c.getClass().getSimpleName() + "\n";
        }

        return resultat;
    }

}
