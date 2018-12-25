package com.modele.construction;

public class NombreLimiteException extends Exception {
    public NombreLimiteException(String construction, int max) {
        super("Le nombre max de " + construction + " (" + max + ") est atteint");
    }
}
