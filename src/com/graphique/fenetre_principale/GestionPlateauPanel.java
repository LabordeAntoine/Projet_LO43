package com.graphique.fenetre_principale;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GestionPlateauPanel extends JPanel {

    //Tout les boutons qui se trouvent dans ce panel
    private JButton placerDeloreanBouton;
    private JButton placerConvertisseurTemporelBouton;
    private JButton placerRouteBouton;
    private JButton voirPlateauBouton;
    private JButton viderPlateauBouton;

    /**
     * C'est la panel ou se trouvent tout les boutons pour gerer PlateauPanel
     */
    GestionPlateauPanel() {

        //On cree un layout
        BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.add(Box.createRigidArea(new Dimension(0,5)));
        this.setLayout(boxLayout);

        //On cree les boutons
        this.placerConvertisseurTemporelBouton = new JButton("Placer Convertisseur Temporel");
        this.placerDeloreanBouton = new JButton("Placer Delorean");
        this.placerRouteBouton = new JButton("Placer Route");
        this.voirPlateauBouton = new JButton("Voir Plateau");
        this.viderPlateauBouton = new JButton("Tout effacer");

        //On ajoute tout les boutons dans le panel avec un espace entre eux
        this.add(this.placerConvertisseurTemporelBouton);
        this.add(Box.createRigidArea(new Dimension(0,5)));
        this.add(this.placerDeloreanBouton);
        this.add(Box.createRigidArea(new Dimension(0,5)));
        this.add(this.placerRouteBouton);
        this.add(Box.createRigidArea(new Dimension(0,5)));
        this.add(this.viderPlateauBouton);
        this.add(Box.createRigidArea(new Dimension(0,5)));
        this.add(this.voirPlateauBouton);

        //On donne un ID a chaque bouton
        this.placerConvertisseurTemporelBouton.setActionCommand("placer convertisseur temporel");
        this.placerDeloreanBouton.setActionCommand("placer delorean");
        this.placerRouteBouton.setActionCommand("placer route");
        this.viderPlateauBouton.setActionCommand("vider plateau");
        this.voirPlateauBouton.setActionCommand("voir plateau");
    }

    void addPublicButtonListener(ActionListener a){
        this.viderPlateauBouton.addActionListener(a);
        this.voirPlateauBouton.addActionListener(a);
        this.placerRouteBouton.addActionListener(a);
        this.placerDeloreanBouton.addActionListener(a);
        this.placerConvertisseurTemporelBouton.addActionListener(a);
    }
}
