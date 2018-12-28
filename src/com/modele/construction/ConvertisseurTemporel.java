package com.modele.construction;

import com.modele.ressources.ListeRessources;
import com.modele.ressources.Ressources;

import java.awt.geom.Point2D;

public class ConvertisseurTemporel extends Construction {

    /**
     * Permet la creation d'un convertisseur temporel grace aux ressources du joueur
     * @param lr Liste de ressources du joueur
     * @throws RessourcesInsuffisantesException
     */
    ConvertisseurTemporel(ListeRessources lr, Point2D.Double position) throws RessourcesInsuffisantesException {
        super.prix.ajouterRessources(Ressources.BOIS, 3);
        super.prix.ajouterRessources(Ressources.FER, 2);
        super.creer(lr, position);
    }

}