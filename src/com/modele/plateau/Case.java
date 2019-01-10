/**
 * Classe permettant de gérer une case de notre plateau
 */

package com.modele.plateau;

import com.modele.ressources.Ressources;
import java.util.Random;

public class Case {

    private int numero;
    private Ressources ressource;
    private boolean caseActive = false;

    /**
     * Constructeur de la classe Case
     */
    public Case() {
        this.numero = nombreRandom(1, 12);
        this.ressource = ressourceRandom();
        System.out.println("numero" + numero + "Ressources " + ressource);
    }

    /**
     * Permet d'obtenir un nombre aleatoire entre 2 bornes
     * @param min nombre minimum
     * @param max nombre maximum
     * @return nombre aleatoire
     */
    private int nombreRandom(int min, int max){
        Random rand = new Random();
        int n = rand.nextInt(max) + min;
        return n;
    } //Fait partie du constructeur

    /**
     * Permet d'obtenir une ressource aleatoire
     * @return Ressouces aleatoire
     */
    private Ressources ressourceRandom(){
        int max = Ressources.values().length;
        Random rand = new Random();
        int n = rand.nextInt(max) + 1;
        return Ressources.values()[n - 1];
    } //Fait partie du constructeur

    /**
     * Permet d'activer la case lorsque le numéro de la case est sorti aux Dés
     */
    public void activerCase(){
        this.caseActive = true;
    }
    /**
     * Permet d'obtenir un numero de case
     * @return numero
     */
    public int getNumero() { return numero; }

    public Ressources getRessource(){return this.ressource;}

    /**
     *  Permet d'obtenir le numero et la ressource de cases
     * @return les numeros et ressources des cases
     */
    public String toString(){
        String s = "";
        s += this.numero + " " + this.ressource;
        s += "\n";
        return s;
    }
}
