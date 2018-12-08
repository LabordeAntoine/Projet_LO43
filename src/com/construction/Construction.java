package com.construction;

import com.SimpleException;
import com.ressources.ListeRessources;

abstract class Construction {

    ListeRessources prix = new ListeRessources();

    void creer(ListeRessources lr) throws SimpleException {
        if(lr.assezDeRessources(this.prix))
            lr.supprimerRessources(this.prix);
        else
            throw new SimpleException("Pas assez de ressources pour creer " + this.getClass().getSimpleName());
    }
}
