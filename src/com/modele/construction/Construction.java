package com.modele.construction;

import com.modele.ressources.ListeRessources;

abstract class Construction {

    ListeRessources prix = new ListeRessources();

    /**
     * Creer une construction a partir des ressources du joueur
     * @param lr Liste de ressources posédée
     * @throws SimpleException
     */
    void creer(ListeRessources lr) throws SimpleException {
        if(lr.assezDeRessources(this.prix))
            lr.supprimerRessources(this.prix);
        else
            throw new SimpleException("Pas assez de ressources pour creer " + this.getClass().getSimpleName());
    }
}
