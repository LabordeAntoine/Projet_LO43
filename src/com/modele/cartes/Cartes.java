/**
 * Interface pour gérer nos cartes
 */

package com.modele.cartes;

import com.modele.joueur.Joueur;

public interface Cartes  {

    /**
     * Action realisees par les cartes
     * @param j Le joueur concerne
     */
    void action(Joueur j);

}