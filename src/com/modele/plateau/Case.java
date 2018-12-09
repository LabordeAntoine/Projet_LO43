package com.modele.plateau;

import com.modele.ressources.Ressources;

import java.util.Random;

public class Case {

    private int numero;
    private Ressources ressource;

    public Case() {
        this.numero = nombreRandom(1, 5);
        this.ressource = ressourceRandom();
    }

    private int nombreRandom(int min, int max){
        Random rand = new Random();
        int n = rand.nextInt(max) + min;
        return n;
    } //Fait partie du constructeur
    private Ressources ressourceRandom(){
        int max = Ressources.values().length;
        Random rand = new Random();
        int n = rand.nextInt(max) + 1;
        return Ressources.values()[n - 1];
    } //Fait partie du constructeur


    public int getNumero() { return numero; }
    public String toString(){
        String s = "";
        s += this.numero + " " + this.ressource;
        s += "\n";
        return s;
    }
}
