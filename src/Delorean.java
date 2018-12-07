import java.util.ArrayList;

public class Delorean extends Construction{


    public Delorean() {
        super.lr.ajouterRessources(Ressources.bois, 3);
        super.lr.ajouterRessources(Ressources.fer, 2);
    }

    public void creer(ListeRessources lr)
    {
        super.creer(lr);
    }
}
