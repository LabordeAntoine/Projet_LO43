package com.modele.cartes;
import com.modele.construction.NombreLimiteException;
import com.modele.construction.RessourcesInsuffisantesException;
import com.modele.joueur.Joueur;

public interface Cartes  {

    /**
     * Action realisees par les cartes
     * @param j Le joueur concerne
     */
    void action(Joueur j);

}