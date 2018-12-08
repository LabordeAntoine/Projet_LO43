package test.ressources;

import java.util.ArrayList;

public class ListeRessources extends ArrayList<CaseRessources>{

    public ListeRessources() {
        add(new CaseRessources(Ressources.ble));
        add(new CaseRessources(Ressources.bois));
        add(new CaseRessources(Ressources.fer));
        add(new CaseRessources(Ressources.minerai));
        add(new CaseRessources(Ressources.laine));
        add(new CaseRessources(Ressources.argile));
    }

    public void ajouterRessources(Ressources r)
    {
        CaseRessources tr = new CaseRessources(r);

        for(CaseRessources cr : this){
            if(cr.getRessource().equals(tr.getRessource()))
            {
                cr.plusNombre();
            }
        }

    }
    public void ajouterRessources(Ressources r, int nombre)
    {
        for(int i= 0; i<nombre; i++)
            this.ajouterRessources(r);
    }
    public void supprimerRessources(Ressources r)
    {
        CaseRessources tr = new CaseRessources(r);

        for(CaseRessources cr : this){
            if(cr.getRessource().equals(tr.getRessource()))
            {
                cr.moinsNombre();
            }
        }

    }
    public void supprimerRessources(Ressources r, int nombre)
    {
        for(int i = 0; i<nombre;i++)
            this.supprimerRessources(r);
    }
    public void afficherRessource()
    {
        for(CaseRessources cr : this)
            cr.afficher();
    }
    public String toString()
    {
        String temp = new String();
        for(CaseRessources cr : this)
            temp += cr.toString() + "\n";
        return temp;
    }
    public boolean assezDeRessources(ListeRessources lr)
    {

        for(int i = 0; i<this.size(); i++)
        {

            if(this.get(i).getNombre() < lr.get(i).getNombre()) {
                return false;
            }

        }
        return true;
    }
}
