package com.modele.ressources;

import java.util.ArrayList;

public class ListeRessources extends ArrayList<ListeRessources.Paire_RessourceNombre>{

    /**
     * Constructeur de la class ListeRessources
     */
    public ListeRessources() {
        add(new Paire_RessourceNombre(Ressources.BLE));
        add(new Paire_RessourceNombre(Ressources.BOIS));
        add(new Paire_RessourceNombre(Ressources.FER));
        add(new Paire_RessourceNombre(Ressources.MINERAI));
        add(new Paire_RessourceNombre(Ressources.LAINE));
        add(new Paire_RessourceNombre(Ressources.ARGILE));
    }

    //Ajouter

    /**
     * Permet d'ajouter une ressource a un joueur
     * @param r La ressource a ajouter
     */
    public void ajouterRessources(Ressources r) {
        Paire_RessourceNombre tr = new Paire_RessourceNombre(r);

        for(Paire_RessourceNombre cr : this){
            if(cr.getRessource().equals(tr.getRessource()))
            {
                cr.plusNombre();
            }
        }

    }

    /**
     * Permet d'ajouter une certaine quantite d'une ressource a un joueur
     * @param r La ressource a ajouter
     * @param nombre Le quantite a ajouter
     */
    public void ajouterRessources(Ressources r, int nombre) {
        for(int i= 0; i<nombre; i++)
            this.ajouterRessources(r);
    }

    /**
     * Permet d'ajouter une liste de ressources a un joueur
     * @param lr Liste de ressources a ajouter
     */
    public void ajouterRessources(ListeRessources lr){

        if (this == lr){
            //Ajouter la liste a elle meme cree des erreurs
            System.out.println("Exception \"ajouterRessources(ListeRessources lr)\", ne peut pas ajouter une liste a soi meme");
            return;
        }

        for (int i = 0; i < this.size(); i++)
                this.get(i).plusNombre(lr.get(i).getNombre());
    }

    //Supprimer

    /**
     * Permet de supprimer une ressource a un joueur
     * @param r La ressource a supprimer
     */
    public void supprimerRessources(Ressources r) {
        Paire_RessourceNombre tr = new Paire_RessourceNombre(r);

        for(Paire_RessourceNombre cr : this){
            if(cr.getRessource().equals(tr.getRessource()))
            {
                cr.moinsNombre();
            }
        }

    }

    /**
     * Permet de supprimer une certaine quantite d'une ressource a un joueur
     * @param r La ressource a supprimer
     * @param nombre La quantite a supprimer
     */
    public void supprimerRessources(Ressources r, int nombre) {
        for(int i = 0; i<nombre; i++)
            this.supprimerRessources(r);
    }

    /**
     * Permet de supprimer une liste de ressources a un joueur
     * @param lr La liste de ressources a supprimer
     */
    public void supprimerRessources(ListeRessources lr){

        if (this.assezDeRessources(lr)) {
            for (int i = 0; i < this.size(); i++)
                if (lr.get(i).getNombre() > 0)
                    this.get(i).moinsNombre(lr.get(i).getNombre());
        } else
            System.out.println("Exception \"supprimerRessources(ListeRessources lr)\", pas assez de ressources");

    }

    //Outils

    /**
     * Permet de savoir sur le joueur a assez de ressources
     * @param lr La liste de ressources a tester
     * @return True si assez, False sinon
     */
    public boolean assezDeRessources(ListeRessources lr) {

        for(int i = 0; i<this.size(); i++)
        {
            if(this.get(i).getNombre() < lr.get(i).getNombre()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Permet de savoir sur le joueur a assez de ressources
     * @param r Ressource a tester
     * @return True si assez, False sinon
     */
    public boolean assezDeRessources(Ressources r){
        ListeRessources temp = new ListeRessources();
        temp.ajouterRessources(r);
        return assezDeRessources(temp);
    }

    /**
     * Permet d'obtenir le nombre de ressources
     * @return nombre de ressources
     */
    public int nombreDeRessources(){
        int resultat = 0;
        for (Paire_RessourceNombre p : this){
            resultat += p.nombre;
        }
        return resultat;
    }

    /**
     * Permet d'afficher les ressources poddedees par un joueur
     */
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

        /**
         * Constructeur de la class Paire_RessourceNombre
         * @param r La ressource
         */
        Paire_RessourceNombre(Ressources r) { this(r, 0); }

        /**
         * Constructeur de la class Paire_RessourceNombre
         * @param r La ressource
         * @param nombre La quatite de cette ressource
         */
        Paire_RessourceNombre(Ressources r, int nombre) {
            this.r = r;
            this.nombre = nombre;
        }

        /**
         * Permet d'obtenir une ressource
         * @return La ressource
         */
        Ressources getRessource() { return this.r; }

        /**
         * Permet d'obtenir la quantite d'une ressource
         * @return la quantite
         */
        int getNombre() { return this.nombre; }

        /**
         * Permet d'ajouter 1 a la quantite
         */
        void plusNombre()
        {
            this.nombre++;
        }

        /**
         * Permet d'ajouter une certaine quantite
         * @param c La quantite
         */
        void plusNombre(int c) {
            for (int i = 0; i < c; i++)
                plusNombre();
        }

        /**
         * Permet de soustraire 1 a la quantite
         */
        void moinsNombre() {
            if(this.nombre > 0 ) //A voir si on envoie une exception ou pas
                this.nombre--;
        }

        /**
         * Permet de soustraire une certaine quantite
         * @param c La quantite
         */
        void moinsNombre(int c) {
            for (int i = 0; i < c; i++)
                moinsNombre();
        }

        /**
         * Permet d'afficher les ressources
         */
        void afficher() { System.out.println("Ressource : " + this.r + " " +this.nombre); }
        public String toString() { return "" + this.r + " "+ this.nombre + "\n"; }

        /**
         *
         * @param c
         * @return
         */
        public boolean equals(Paire_RessourceNombre c) { return this.r.equals(c.getRessource()); } //A quoi sert cette méthode?
    }
}
