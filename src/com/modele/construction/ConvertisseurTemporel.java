package com.modele.construction;

import com.modele.ressources.ListeRessources;
import com.modele.ressources.Ressources;

class ConvertisseurTemporel extends Construction {

    /**
     * Permet la creation d'un convertisseur temporel grace aux ressources du joueur
     * @param lr Liste de ressources du joueur
     * @throws SimpleException
     */
    ConvertisseurTemporel(ListeRessources lr) throws SimpleException{
        super.prix.ajouterRessources(Ressources.BOIS, 3);
        super.prix.ajouterRessources(Ressources.FER, 2);
        super.creer(lr);
    }

}