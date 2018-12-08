package com.construction;

import com.SimpleException;
import com.ressources.ListeRessources;
import com.ressources.Ressources;

class Delorean extends Construction {

    Delorean(ListeRessources lr) throws SimpleException {
        super.prix.ajouterRessources(Ressources.ARGILE, 3);
        super.prix.ajouterRessources(Ressources.FER, 2);
        super.creer(lr);
    }

}
