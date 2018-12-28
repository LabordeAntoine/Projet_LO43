package com.modele.construction;

import com.modele.ressources.ListeRessources;

import java.awt.geom.Point2D;

public abstract class Construction {

    ListeRessources prix = new ListeRessources();
    Point2D.Double position;


    /**
     * Creer une construction a partir des ressources du joueur
     * @param lr Liste de ressources posédée
     * @throws RessourcesInsuffisantesException
     */
    void creer(ListeRessources lr, Point2D.Double position) throws RessourcesInsuffisantesException {
        if(lr.assezDeRessources(this.prix)){
            lr.supprimerRessources(this.prix);
            this.position = position;
        }
        else
            throw new RessourcesInsuffisantesException("Pas assez de ressources pour creer " + this.getClass().getSimpleName());
    }

    
    public Point2D.Double getPosition() {
        return position;
    }
}
