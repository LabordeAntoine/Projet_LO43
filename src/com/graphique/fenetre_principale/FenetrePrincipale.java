package com.graphique.fenetre_principale;
import com.modele.joueur.Joueur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class FenetrePrincipale extends JFrame implements ActionListener {

    private PlateauPanel plateauPanel;

    /**
     * C'est la classe de la fenetre principale du jeu
     * ici on trouve le plateau, les boutons et les panels des joueurs
     * @param j1
     * @param j2
     * @param j3
     * @param j4
     */
    public FenetrePrincipale(Joueur j1, Joueur j2, Joueur j3, Joueur j4)  {

        //Permet a "componentResized" de ne pas etre appele trop de fois
        Toolkit.getDefaultToolkit().setDynamicLayout( false );

        //Layout
        this.setLayout(new BorderLayout());

        JPanel panDroite = new JPanel(new GridLayout(2,1));
        panDroite.add(new JoueurIndividuelPanel(j1,Color.RED,1));
        panDroite.add(new JoueurIndividuelPanel(j2,Color.BLUE,2));

        JPanel panGauche = new JPanel(new GridLayout(2,1));
        panGauche.add(new JoueurIndividuelPanel(j3,Color.green,3));
        panGauche.add(new JoueurIndividuelPanel(j4,Color.magenta,4));

        Joueur [] listeJoueurs = new Joueur[]{j1, j2, j3, j4};

        this.plateauPanel = new PlateauPanel(listeJoueurs);
        JPanel panCentre = new JPanel();
        panCentre.setLayout(new BoxLayout(panCentre, BoxLayout.Y_AXIS));
        panCentre.add(this.plateauPanel);
        GestionPlateauPanel gestionPlateauPanel = new GestionPlateauPanel();
        panCentre.add(gestionPlateauPanel);

        this.getContentPane().add(panDroite,BorderLayout.EAST);
        this.getContentPane().add(panCentre, BorderLayout.CENTER);
        this.getContentPane().add(panGauche, BorderLayout.WEST);

        //Gestion des boutons
        gestionPlateauPanel.addPublicButtonListener(this);


        //Parametres de la fenetre
        this.setTitle("Back to Catane");
        this.setSize(1300,1000);
        this.setMinimumSize(new Dimension(700, 500));
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
            case "placer convertisseur temporel": this.plateauPanel.setPlacement(Placement.CONVERTISSEUR_TEMPOREL);
            case "placer route": this.plateauPanel.setPlacement(Placement.ROUTE); break;
            case "voir plateau": this.plateauPanel.setPlacement(Placement.VIDE); break;
            case "vider plateau": break;

            default: System.out.println("Erreur bouton - Fenetre Principale");  break;
        }

    }
}
