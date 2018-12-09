package com.modele.construction;

import com.modele.ressources.ListeRessources;
import com.modele.ressources.Ressources;

class Delorean extends Construction {

    Delorean(ListeRessources lr) throws SimpleException {
        super.prix.ajouterRessources(Ressources.ARGILE, 3);
        super.prix.ajouterRessources(Ressources.FER, 2);
        super.creer(lr);
    }

}
