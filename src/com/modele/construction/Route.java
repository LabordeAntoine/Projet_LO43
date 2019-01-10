/**
 * class pour gérer création de routes
 */

package com.modele.construction;

import com.modele.ressources.ListeRessources;
import com.modele.ressources.Ressources;

import java.awt.geom.Point2D;

public class Route extends Construction{

    /**
     * Permet la creation d'une route grace aux ressources du joueur
     * @param lr Liste de ressources du joueur
     * @throws RessourcesInsuffisantesException
     */
    Route(ListeRessources lr, Point2D.Double position) throws RessourcesInsuffisantesException {
        super.prix.ajouterRessources(Ressources.BLE, 3);
        super.prix.ajouterRessources(Ressources.BOIS, 2);
        super.creer(lr, position);
    }

}
