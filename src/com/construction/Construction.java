package com.construction;

import com.ressources.ListeRessources;

public abstract class Construction {

    protected int nombre;
    protected ListeRessources lr = new ListeRessources();

    public Construction()
    {
        this.nombre =0;
    }

    public void creer(ListeRessources lrj)
    {
        if(lrj.assezDeRessources(this.lr)) {
            System.out.println("Creation possible");
        }
        else
            System.out.println("Tu peux pas créer");
    }
}
