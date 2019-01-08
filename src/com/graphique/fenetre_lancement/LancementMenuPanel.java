package com.graphique.fenetre_lancement;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class LancementMenuPanel extends JPanel {


    //Tout les boutons qui vont se trouver dans la fenetre de lancement
    private JButton quitterBouton = new JButton("Quitter");
    private JButton jouerBouton = new JButton("Jouer");
    private JButton reglesBouton = new JButton("Regles");


    /**
     * Genere le premier panel qui apparait quand on ouvre FenetreLancement
     */
    LancementMenuPanel() {
        this.setLayout(new BorderLayout());

        //On donne un ID pout tout les bouttons
        this.quitterBouton.setActionCommand("quitter");
        this.jouerBouton.setActionCommand("jouer");
        this.reglesBouton.setActionCommand("regles");

        //On cree un JPanel ou on ajoute tout les boutons et on ajoute ce dernier en bas
        JPanel boutonsPanel = new JPanel();
        this.add(boutonsPanel, BorderLayout.SOUTH);
        boutonsPanel.setOpaque(false);
        boutonsPanel.add(jouerBouton);
        boutonsPanel.add(reglesBouton);
        boutonsPanel.add(quitterBouton);
    }


    /**
     * On ajouter les boutons a l'interface ActinListener
     * @param a
     */
    void addPublicButtonListener(ActionListener a){
        this.quitterBouton.addActionListener(a);
        this.jouerBouton.addActionListener(a);
        this.reglesBouton.addActionListener(a);
    }

    /**
     * On charge l'image du fond
     * @param g
     */
    public void paintComponent(Graphics g) {
        try {
            Image img = ImageIO.read(new File("BackToCatan.jpg"));
            g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

