package com.graphique.fenetre_principale;
import com.modele.joueur.Joueur;

import javax.swing.*;
import java.awt.*;


public class FenetrePrincipale extends JFrame {


    public FenetrePrincipale(Joueur j1, Joueur j2, Joueur j3, Joueur j4) {
        //Permet a "componentResized" de ne pas etre appele trop de fois
        Toolkit.getDefaultToolkit().setDynamicLayout( false );
        this.setTitle("Back to Catane");
        this.setSize(1300,1000);
        this.setMinimumSize(new Dimension(700, 500));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.setResizable(false);
        this.setLayout(new BorderLayout());

        JPanel panDroite = new JPanel(new GridLayout(2,1));
        panDroite.add(new JoueurIndividuelPanel(j1,Color.RED,1));
        panDroite.add(new JoueurIndividuelPanel(j2,Color.BLUE,2));

        JPanel panGauche = new JPanel(new GridLayout(2,1));
        panGauche.add(new JoueurIndividuelPanel(j3,Color.green,3));
        panGauche.add(new JoueurIndividuelPanel(j4,Color.magenta,4));

        Joueur [] listeJoueurs = new Joueur[]{j1, j2, j3, j4};

        this.getContentPane().add(panDroite,BorderLayout.EAST);
        this.getContentPane().add(new PlateauPanel(listeJoueurs), BorderLayout.CENTER);
        this.getContentPane().add(panGauche, BorderLayout.WEST);

        this.pack();
        this.setVisible(true);
    }



}
