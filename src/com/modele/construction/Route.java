package com.modele.construction;

import com.modele.ressources.ListeRessources;
import com.modele.ressources.Ressources;

class Route extends Construction{

    Route(ListeRessources lr) throws SimpleException {
        super.prix.ajouterRessources(Ressources.BLE, 3);
        super.prix.ajouterRessources(Ressources.BOIS, 2);
        super.creer(lr);
    }

}
