package com.modele.construction;

import com.modele.ressources.ListeRessources;
import com.modele.ressources.Ressources;

public class Delorean extends Construction {

    /**
     * Permet la creation d'une delorean grace aux ressources du joueur
     * @param lr Liste de ressources du joueur
     * @throws RessourcesInsuffisantesException
     */
    Delorean(ListeRessources lr) throws RessourcesInsuffisantesException {
        super.prix.ajouterRessources(Ressources.ARGILE, 3);
        super.prix.ajouterRessources(Ressources.FER, 2);
        super.creer(lr);
    }

}
