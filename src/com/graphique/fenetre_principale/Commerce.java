package com.graphique.fenetre_principale;

import com.modele.joueur.Joueur;
import javax.swing.*;

public class Commerce extends JFrame {


    public Commerce(Joueur j){
        this.setTitle("Commerce");
        this.setSize(300, 300);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        RessourcesDispoEchange(j);
        this.setVisible(true);

    }

    private void RessourcesDispoEchange(Joueur j){
        JPanel panRessourceJ = new JPanel();
        JTextArea textRessource = new JTextArea("test");
        textRessource.setEditable(false);
        textRessource.setText(j.toStringRessources());

        panRessourceJ.add(textRessource);
        JButton echangerBanque = new JButton("Echanger (ne fait rien)");
        panRessourceJ.add(echangerBanque);
        this.add(panRessourceJ);

    }

}
