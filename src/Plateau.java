public class Plateau {

    private int annee;

    Joueur routeLaPlusLongue(Joueur[] listeDeJoueurs){

        Joueur joueurMax = listeDeJoueurs[0];
        for (Joueur j: listeDeJoueurs){
            if (joueurMax.getNombreDeRoutes() < j.getNombreDeRoutes()){
                joueurMax = j;
            }
        }

        return joueurMax;
    }

}
