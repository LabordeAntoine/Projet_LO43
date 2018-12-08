package com.construction;

import com.SimpleException;
import com.ressources.ListeRessources;
import com.ressources.Ressources;

class Route extends Construction{

    Route(ListeRessources lr) throws SimpleException {
        super.prix.ajouterRessources(Ressources.BLE, 3);
        super.prix.ajouterRessources(Ressources.BOIS, 2);
        super.creer(lr);
    }

}
