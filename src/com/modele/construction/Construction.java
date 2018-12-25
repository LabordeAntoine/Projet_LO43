package com.modele.construction;

import com.modele.ressources.ListeRessources;

public abstract class Construction {

    ListeRessources prix = new ListeRessources();

    /**
     * Creer une construction a partir des ressources du joueur
     * @param lr Liste de ressources posédée
     * @throws RessourcesInsuffisantesException
     */
    void creer(ListeRessources lr) throws RessourcesInsuffisantesException {
        if(lr.assezDeRessources(this.prix))
            lr.supprimerRessources(this.prix);
        else
            throw new RessourcesInsuffisantesException("Pas assez de ressources pour creer " + this.getClass().getSimpleName());
    }
}
