/**
 * Panel pour le placement des boutons
 */

package com.graphique.fenetre_principale;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GestionPlateauPanel extends JPanel implements ActionListener{

    //Tout les boutons qui se trouvent dans ce panel
    private JButton placerDeloreanBouton;
    private JButton placerConvertisseurTemporelBouton;
    private JButton placerRouteBouton;
    private JButton voirPlateauBouton;
    private JButton deBouton;
    private JButton faireCommerce;
    private JButton finDeTour;
    private int resultatDe;
    JLabel de = new JLabel();

    /**
     * C'est la panel ou se trouvent tout les boutons pour gerer PlateauPanel
     */
    GestionPlateauPanel() {

        //On cree un layout
        BoxLayout boxLayout = new BoxLayout(this, BoxLayout.X_AXIS);
        GridLayout gridLayout = new GridLayout(5, 2);
        this.setLayout(gridLayout);

        //On cree les boutons
        this.placerConvertisseurTemporelBouton = new JButton("Placer Convertisseur Temporel");
        this.placerDeloreanBouton = new JButton("Placer Delorean");
        this.placerRouteBouton = new JButton("Placer Route");
        this.voirPlateauBouton = new JButton("Voir Plateau");
        this.deBouton = new JButton("Lancer le De");
        this.faireCommerce = new JButton("Faire du commerce");
        this.finDeTour = new JButton("Fin de tour");
       

        //On ajoute tout les boutons dans le panel avec un espace entre eux
        this.add(this.placerConvertisseurTemporelBouton);
        this.add(this.placerDeloreanBouton);
        this.add(this.placerRouteBouton);
        this.add(this.voirPlateauBouton);
        this.add(this.deBouton);
        this.add(this.faireCommerce);
        this.add(this.de);
        this.add(this.finDeTour);

        //On donne un ID a chaque bouton
        this.placerConvertisseurTemporelBouton.setActionCommand("placer convertisseur temporel");
        this.placerDeloreanBouton.setActionCommand("placer delorean");
        this.placerRouteBouton.setActionCommand("placer route");
        this.voirPlateauBouton.setActionCommand("voir plateau");
        this.faireCommerce.setActionCommand("Faire du commerce");
        this.deBouton.setActionCommand("lancer de");
        this.finDeTour.setActionCommand("fin de tour");

        this.addPublicButtonListener(this);
    }

    void addPublicButtonListener(ActionListener a){
        this.voirPlateauBouton.addActionListener(a);
        this.placerRouteBouton.addActionListener(a);
        this.placerDeloreanBouton.addActionListener(a);
        this.placerConvertisseurTemporelBouton.addActionListener(a);
        this.faireCommerce.addActionListener(a);
        this.deBouton.addActionListener(a);
        this.finDeTour.addActionListener(a);
    }

    int lancerDe() {
        Random r = new Random();
        int n = r.nextInt(12)+1;
        this.de.setText("Résultat Dé : " + n);
        return n;
    }

	@Override
	public void actionPerformed(ActionEvent e) {

        String buttonName = e.getActionCommand();
        if(buttonName.equals("lancer de")){
            this.setBoutons(true);
        }
	}
    void setBoutons(boolean b) {
    	this.placerDeloreanBouton.setEnabled(b);
    	this.placerRouteBouton.setEnabled(b);
    	this.placerConvertisseurTemporelBouton.setEnabled(b);
    	this.finDeTour.setEnabled(b);
    	this.deBouton.setEnabled(!b);
    	this.faireCommerce.setEnabled(b);
    }
    
    public void setLabel(int n) {
        this.de.setText("Resultat dé : "+ resultatDe);
    }
}
