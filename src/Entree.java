import java.util.Scanner;

public class Entree {

    public static int entrerNombreUtilisateur( int min, int max, String message){

        System.out.println(message);

        Scanner sc = new Scanner(System.in);
        int reponse = sc.nextInt();

        while (reponse <= min || reponse >= max) {
            System.out.println(" >> ERREUR : Veuillez rentrer une bonne valeur !");
            reponse = sc.nextInt();
        }

        return reponse;
    }

    public static String entrerMessageUtilisateur(){
        String reponse = null;
        return reponse;
    }
}
