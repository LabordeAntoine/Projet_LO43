package com.modele.construction;

import com.modele.ressources.ListeRessources;
import com.modele.ressources.Ressources;

class ConvertisseurTemporel extends Construction {

    ConvertisseurTemporel(ListeRessources lr) throws SimpleException{
        super.prix.ajouterRessources(Ressources.BOIS, 3);
        super.prix.ajouterRessources(Ressources.FER, 2);
        super.creer(lr);
    }

}