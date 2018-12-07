import java.util.ArrayList;

public class ConvertisseurTemporel extends Construction { //Chercher avec get.indexOf.getNombre

    /*pour créer 3 bois 2 blé*/
    private int ble = 3;
    private int bois = 2;

    public void creer(ArrayList<CaseRessources> l)
    {
        boolean testble = false;
        boolean testbois = false;

        for(CaseRessources cr : l)
        {
            if(cr.equals(new CaseRessources(Ressources.ble)))
                if(cr.getNombre() == this.ble)
                    testble = true;
            if(cr.equals(new CaseRessources(Ressources.bois)))
                if(cr.getNombre()==this.bois)
                    testbois = true;
        }
        if(testble && testbois)
        {
            System.out.println("WOUHOU");
        }
        else
        {
            if(!testble)
                System.out.println("Il te manque du blé");
            else if(!testbois)
                System.out.println("Il te manque du bois frère");
            else if (!testble && !testbois)
                System.out.println("TU peux pas ");
        }
    }
    //public boolean testblé()
    //public boolean testbois()
}