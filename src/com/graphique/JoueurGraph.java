package com.graphique;

import com.modele.ressources.Ressources;
import com.modele.joueur.Joueur;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JoueurGraph extends JPanel {

    public JoueurGraph(Joueur j, Color couleur){
        EmptyBorder marge = new EmptyBorder(10,10,10,10);

        this.setLayout(new GridLayout(2,1));
        this.setSize(new Dimension(60,20));
        this.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.black));
        this.setBounds(0,0,600,400);
        this.setPreferredSize(new Dimension(250,250));
        JLabel label = new JLabel();
        label.setText(j.getName());
        label.setForeground(couleur);
        JTextArea ressource = new JTextArea();
        ressource.setBorder(marge);
        ressource.setEditable(false);
        ressource.setText(j.toStringRessources());

        this.add(label);
        this.add(ressource);
    }
}
