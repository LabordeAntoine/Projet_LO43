package com.graphique;
import com.modele.joueur.Joueur;

import javax.swing.*;
import java.awt.*;


public class Fenetre extends JFrame {


    public Fenetre(Joueur j1, Joueur j2, Joueur j3, Joueur j4)
    {
        this.setTitle("Grille");
        this.setSize(1300,1000);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(new BorderLayout());

        JPanel panDroite = new JPanel(new GridLayout(2,1));
        panDroite.add(new JoueurGraph(j1,Color.RED,1));
        panDroite.add(new JoueurGraph(j2,Color.BLUE,2));

        JPanel panGauche = new JPanel(new GridLayout(2,1));
        panGauche.add(new JoueurGraph(j3,Color.green,3));
        panGauche.add(new JoueurGraph(j4,Color.magenta,4));

        this.getContentPane().add(panDroite,BorderLayout.EAST);
        this.getContentPane().add(new PlateauGraph(), BorderLayout.CENTER);
        this.getContentPane().add(panGauche, BorderLayout.WEST);

        this.setVisible(true);
    }



}
