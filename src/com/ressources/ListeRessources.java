package com.ressources;

import java.util.ArrayList;

public class ListeRessources extends ArrayList<ListeRessources.Paire_RessourceNombre>{

    public ListeRessources() {
        add(new Paire_RessourceNombre(Ressources.BLE));
        add(new Paire_RessourceNombre(Ressources.BOIS));
        add(new Paire_RessourceNombre(Ressources.FER));
        add(new Paire_RessourceNombre(Ressources.MINERAI));
        add(new Paire_RessourceNombre(Ressources.LAINE));
        add(new Paire_RessourceNombre(Ressources.ARGILE));
    }

    //Ajouter
    public void ajouterRessources(Ressources r) {
        Paire_RessourceNombre tr = new Paire_RessourceNombre(r);

        for(Paire_RessourceNombre cr : this){
            if(cr.getRessource().equals(tr.getRessource()))
            {
                cr.plusNombre();
            }
        }

    }
    public void ajouterRessources(Ressources r, int nombre) {
        for(int i= 0; i<nombre; i++)
            this.ajouterRessources(r);
    }
    public void ajouterRessources(ListeRessources lr){

        if (this == lr){
            //Ajouter la liste a elle meme cree des erreurs
            System.out.println("Exception \"ajouterRessources(ListeRessources lr)\", ne peut pas ajouter une lise a soi meme");
            return;
        }

        for (int i = 0; i < this.size(); i++)
                this.get(i).plusNombre(lr.get(i).getNombre());
    }

    //Supprimer
    public void supprimerRessources(Ressources r) {
        Paire_RessourceNombre tr = new Paire_RessourceNombre(r);

        for(Paire_RessourceNombre cr : this){
            if(cr.getRessource().equals(tr.getRessource()))
            {
                cr.moinsNombre();
            }
        }

    }
    public void supprimerRessources(Ressources r, int nombre) {
        for(int i = 0; i<nombre; i++)
            this.supprimerRessources(r);
    }
    public void supprimerRessources(ListeRessources lr){

        if (this.assezDeRessources(lr)) {
            for (int i = 0; i < this.size(); i++)
                if (lr.get(i).getNombre() > 0)
                    this.get(i).moinsNombre(lr.get(i).getNombre());
        } else
            System.out.println("Exception \"supprimerRessources(ListeRessources lr)\", pas assez de ressources");

    }

    //Outils
    public boolean assezDeRessources(ListeRessources lr) {

        for(int i = 0; i<this.size(); i++)
        {
            if(this.get(i).getNombre() < lr.get(i).getNombre()) {
                return false;
            }
        }
        return true;
    }
    public void afficherRessource() {
        for(Paire_RessourceNombre cr : this)
            cr.afficher();
    }
    public String toString() {
        StringBuilder temp = new StringBuilder();
        for(Paire_RessourceNombre cr : this)
            temp.append(cr.toString()).append("\n");
        return temp.toString();
    }



    //Classe de deux elements, <RESSOURCE, NOMBRE>
    protected class Paire_RessourceNombre {
        private Ressources r;
        private int nombre;

        Paire_RessourceNombre(Ressources r) { this(r, 0); }
        Paire_RessourceNombre(Ressources r, int nombre) {
            this.r = r;
            this.nombre = nombre;
        }

        Ressources getRessource() { return this.r; }
        int getNombre() { return this.nombre; }

        void plusNombre()
        {
            this.nombre++;
        }
        void plusNombre(int c) {
            for (int i = 0; i < c; i++)
                plusNombre();
        }

        void moinsNombre() {
            if(this.nombre > 0 ) //A voir si on envoie une exception ou pas
                this.nombre--;
        }

        void moinsNombre(int c) {
            for (int i = 0; i < c; i++)
                moinsNombre();
        }

        void afficher() { System.out.println("Ressource : " + this.r + " " +this.nombre); }
        public String toString() { return "" + this.r + " "+ this.nombre + "\n"; }
        public boolean equals(Paire_RessourceNombre c) { return this.r.equals(c.getRessource()); } //A quoi sert cette méthode?
    }
}
