package com.graphique.fenetre_principale;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GestionPlateauPanel extends JPanel {

    //Tout les boutons qui se trouvent dans ce panel
    private JButton placerDeloreanBouton;
    private JButton placerConvertisseurTemporelBouton;
    private JButton placerRouteBouton;
    private JButton voirPlateauBouton;
    private JButton viderPlateauBouton;
    private JButton lancerDe;
    private int resultatDe;
    /**
     * C'est la panel ou se trouvent tout les boutons pour gerer PlateauPanel
     */
    GestionPlateauPanel() {

        //On cree un layout
        BoxLayout boxLayout = new BoxLayout(this, BoxLayout.X_AXIS);
        this.add(Box.createRigidArea(new Dimension(0,5)));
        this.setLayout(boxLayout);

        //On cree les boutons
        this.placerConvertisseurTemporelBouton = new JButton("Placer Convertisseur Temporel");
        this.placerDeloreanBouton = new JButton("Placer Delorean");
        this.placerRouteBouton = new JButton("Placer Route");
        this.voirPlateauBouton = new JButton("Voir Plateau");
        this.viderPlateauBouton = new JButton("Tout effacer");
        this.lancerDe = new JButton("Lancer le De");
        JLabel de = new JLabel();
        lancerDe.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e ){
                resultatDe = lancerDe();
                de.setText(""+ resultatDe);
            }
        });

        //On ajoute tout les boutons dans le panel avec un espace entre eux
        this.add(this.placerConvertisseurTemporelBouton);
        this.add(Box.createRigidArea(new Dimension(5,5)));
        this.add(this.placerDeloreanBouton);
        this.add(Box.createRigidArea(new Dimension(5,5)));
        this.add(this.placerRouteBouton);
        this.add(Box.createRigidArea(new Dimension(5,5)));
        this.add(this.viderPlateauBouton);
        this.add(Box.createRigidArea(new Dimension(5,5)));
        this.add(this.voirPlateauBouton);
        this.add(Box.createRigidArea(new Dimension(5,5)));
        this.add(this.lancerDe);
        this.add(Box.createRigidArea(new Dimension(5,5)));
        this.add(de);

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

    int lancerDe() {
        Random r = new Random();
        int n = r.nextInt(12)+1;
        return n;
    }

    public int resultatLancerDe(){ return resultatDe; }
}
