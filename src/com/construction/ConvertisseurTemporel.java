package com.construction;

import com.SimpleException;
import com.ressources.ListeRessources;
import com.ressources.Ressources;

class ConvertisseurTemporel extends Construction {

    ConvertisseurTemporel(ListeRessources lr) throws SimpleException{
        super.prix.ajouterRessources(Ressources.BOIS, 3);
        super.prix.ajouterRessources(Ressources.FER, 2);
        super.creer(lr);
    }

}