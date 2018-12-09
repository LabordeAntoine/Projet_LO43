package com.graphique;
import com.modele.joueur.Joueur;

import javax.swing.*;
import java.awt.*;


public class Fenetre extends JFrame {

    private Joueur j1 = new Joueur("j1");
    private Joueur j2 = new Joueur("j2");
    private Joueur j3 = new Joueur("j3");
    private Joueur j4 = new Joueur("j4");

    public Fenetre(Joueur j)
    {
        this.setTitle("Grille");
        this.setSize(1300,1000);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.setResizable(false);
        this.setLayout(new BorderLayout());


        JPanel panDroite = new JPanel(new GridLayout(2,1));
        panDroite.add(new JoueurGraph(j1));
        panDroite.add(new JoueurGraph(j2));

        JPanel panGauche = new JPanel(new GridLayout(2,1));
        panGauche.add(new JoueurGraph(j3));
        panGauche.add(new JoueurGraph(j));

        FlowLayout fl = new FlowLayout();
        fl.setVgap(0);
        JPanel panCentre = new JPanel(fl);
        //panCentre.add(new Hexagone());

        JLayeredPane panCentre1 = new JLayeredPane();
        panCentre1.add(new Hexagone(),new Integer(1));
        //panCentre1.add(new Grille(), new Integer(2));

        this.getContentPane().add(panDroite,BorderLayout.EAST);
        this.getContentPane().add(panCentre1, BorderLayout.CENTER);
        this.getContentPane().add(panGauche, BorderLayout.WEST);

        this.setVisible(true);
    }



}
