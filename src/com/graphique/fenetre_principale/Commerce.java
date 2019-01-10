/**
 * Gestion de la Frame pour le commerce (echange de ressources entre joueur et banque)
 */

package com.graphique.fenetre_principale;

import com.modele.joueur.Joueur;
import javax.swing.*;

public class Commerce extends JFrame {

    public Commerce(Joueur j){
        this.setTitle("Commerce");
        this.setSize(300, 300);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);

        RessourcesDispoEchange(j);
    }

    private void RessourcesDispoEchange(Joueur j){
        JPanel panRessourceJ = new JPanel();
        JLabel labRessourceJ = new JLabel();

        labRessourceJ.setText(j.toStringRessources());
        panRessourceJ.add(labRessourceJ);
        this.add(panRessourceJ);
    }
}
