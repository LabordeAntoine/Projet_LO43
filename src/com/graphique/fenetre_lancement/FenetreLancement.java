package com.graphique.fenetre_lancement;


import javax.swing.*;

import com.modele.joueur.Joueur;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class FenetreLancement extends JFrame implements ActionListener {


    private JPanel cardLayoutPanel;
    private CardLayout cardLayout;

    /**
     * FenetrePrincipale de Lancement, elle contient 2 Panels,
     *  - une pour le menu du jeu
     *  - une ou les joueurs entrent leurs noms
     */
    public FenetreLancement(){

        //CardLayout est ce qui permet de changer de JPanel facilement
        this.cardLayoutPanel = new JPanel();


        //On cree les deux panels
        LancementMenuPanel lancementMenuPanel = new LancementMenuPanel();
        LancementJoueursPanel lancementJoueursPanel = new LancementJoueursPanel();


        //On ajoute les listeners
        lancementMenuPanel.addPublicButtonListener(this);
        lancementJoueursPanel.addPublicButtonListener(this);


        //Parametres fenetres
        this.setTitle("Back To Catane");
        this.setSize(700, 400);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        //Configuration CardLayouts
        //CardLayout est un layout qui permet de stocker deux ou plusieurs panels et de changer entre eux facilements
        this.setContentPane(this.cardLayoutPanel);
        this.cardLayoutPanel.setLayout(new CardLayout());
        this.cardLayoutPanel.add(lancementMenuPanel, "lancementMenuPanel");
        this.cardLayoutPanel.add(lancementJoueursPanel, "lancementJoueursPanel");

        cardLayout = (CardLayout) this.cardLayoutPanel.getLayout();
        cardLayout.show(this.cardLayoutPanel, "lancementMenuPanel");


    }

    /**
     * C'est ici on va capturer tout les evenements
     * et on decide toutes les actions des boutons
     * L'ID est donnée a la creation des panels
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        String buttonName = e.getActionCommand();

        switch (buttonName){
            case "quitter":
                this.dispose();
                break;

            case "jouer":
                this.cardLayout.next(this.cardLayoutPanel);
                break;

            case "regles":
                Desktop d = Desktop.getDesktop();
                try{ d.open(new File("Regles.pdf")); }
                catch(Exception ex){ ex.printStackTrace(); }
                break;

            case "retour":
                this.cardLayout.next(this.cardLayoutPanel);
                break;

            default:
                System.out.println("erreur");
                break;
        }
    }
}
