package com.modele.construction;

import com.modele.ressources.ListeRessources;
import com.modele.ressources.Ressources;

class Route extends Construction{

    /**
     * Permet la creation d'une route grace aux ressources du joueur
     * @param lr Liste de ressources du joueur
     * @throws SimpleException
     */
    Route(ListeRessources lr) throws SimpleException {
        super.prix.ajouterRessources(Ressources.BLE, 3);
        super.prix.ajouterRessources(Ressources.BOIS, 2);
        super.creer(lr);
    }

}
