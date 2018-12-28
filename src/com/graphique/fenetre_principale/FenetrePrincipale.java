package com.graphique.fenetre_principale;
import com.graphique.fenetre_principale.plateau.Placement;
import com.graphique.fenetre_principale.plateau.PlateauException;
import com.graphique.fenetre_principale.plateau.PlateauPanel;
import com.modele.joueur.Joueur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class FenetrePrincipale extends JFrame implements ActionListener {

    private PlateauPanel plateauPanel;
    private Joueur [] listeJoueurs;

    /**
     * C'est la classe de la fenetre principale du jeu
     * ici on trouve le plateau, les boutons et les panels des joueurs
     * @param j1
     * @param j2
     * @param j3
     * @param j4
     */
    public FenetrePrincipale(Joueur j1, Joueur j2, Joueur j3, Joueur j4) throws PlateauException {

        //Permet a "componentResized" de ne pas etre appele trop de fois
        Toolkit.getDefaultToolkit().setDynamicLayout( false );

        //Layout
        this.setLayout(new BorderLayout());

        JPanel panDroite = new JPanel(new GridLayout(2,1));
        panDroite.add(new JoueurIndividuelPanel(j1,1));
        panDroite.add(new JoueurIndividuelPanel(j2,2));

        JPanel panGauche = new JPanel(new GridLayout(2,1));
        panGauche.add(new JoueurIndividuelPanel(j3,3));
        panGauche.add(new JoueurIndividuelPanel(j4,4));

        this.listeJoueurs = new Joueur[]{j1, j2, j3, j4};

        this.plateauPanel = new PlateauPanel(j1);
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
            case "placer convertisseur temporel": this.plateauPanel.setPlacement(Placement.CONVERTISSEUR_TEMPOREL);
            case "placer route": this.plateauPanel.setPlacement(Placement.ROUTE); break;
            case "voir plateau": break;
            case "vider plateau": break;

            default: System.out.println("Erreur bouton - Fenetre Principale");  break;
        }

    }
}
