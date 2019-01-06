package com.graphique.fenetre_principale;
import com.graphique.fenetre_principale.plateau.Placement;
import com.graphique.fenetre_principale.plateau.PlateauException;
import com.graphique.fenetre_principale.plateau.PlateauPanel;
import com.modele.joueur.Joueur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class FenetrePrincipale extends JFrame implements ActionListener {

    private PlateauPanel plateauPanel;

    /**
     * C'est la classe de la fenetre principale du jeu
     * ici on trouve le plateau, les boutons et les panels des joueurs
     */
    public FenetrePrincipale(ArrayList<Joueur> j ) throws PlateauException {

        //Layout
        this.setLayout(new BorderLayout());

        this.plateauPanel = new PlateauPanel(j.get(0));
        JPanel panCentre = new JPanel();
        panCentre.setLayout(new BoxLayout(panCentre, BoxLayout.Y_AXIS));
        panCentre.add(this.plateauPanel);
        GestionPlateauPanel gestionPlateauPanel = new GestionPlateauPanel();
        panCentre.add(gestionPlateauPanel);

        JPanel panDroite = new JPanel(new GridLayout(2,1));
        panDroite.add(new JoueurIndividuelPanel(j.get(0),1));
        panDroite.add(new JoueurIndividuelPanel(j.get(1),2));

        JPanel panGauche = new JPanel(new GridLayout(2,1));
        panGauche.add(new JoueurIndividuelPanel(j.get(2),3));
        panGauche.add(new JoueurIndividuelPanel(j.get(3),4));

        this.getContentPane().add(panDroite,BorderLayout.EAST);
        this.getContentPane().add(panCentre, BorderLayout.CENTER);
        this.getContentPane().add(panGauche, BorderLayout.WEST);

        //Gestion des boutons
        gestionPlateauPanel.addPublicButtonListener(this);


        //Parametres de la fenetre
        this.setTitle("Back to Catane");
        this.setMinimumSize(new Dimension((int)this.plateauPanel.getMinimumSize().getWidth()  + 500, (int)this.plateauPanel.getMinimumSize().getWidth() + 200));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String buttonName = e.getActionCommand();

        switch (buttonName){
            case "placer delorean" : this.plateauPanel.setPlacement(Placement.DELOREAN); break;
            case "placer convertisseur temporel": this.plateauPanel.setPlacement(Placement.CONVERTISSEUR_TEMPOREL); break;
            case "placer route": this.plateauPanel.setPlacement(Placement.ROUTE); break;
            case "voir plateau": break;
            case "vider plateau": break;

            default: System.out.println("Erreur bouton - Fenetre Principale");  break;
        }

    }
}
