package com.graphique.fenetre_principale;

import com.modele.ressources.Ressources;
import com.modele.construction.NombreLimiteException;
import com.modele.construction.RessourcesInsuffisantesException;
import com.modele.joueur.Joueur;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;

public class JoueurIndividuelPanel extends JPanel{
	private JTextArea ressource = new JTextArea();;
	private Joueur j;

    public JoueurIndividuelPanel(Joueur j, int nb){
    

    	EmptyBorder marge = new EmptyBorder(10,10,10,10);

        ressource.setBorder(marge);
        ressource.setEditable(false);
        ressource.setText(j.toStringRessources());

        this.setLayout(new GridLayout(2,1));
        this.setSize(new Dimension(60,20));
        this.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.black));
        this.setBounds(0,0,600,300);
        this.setPreferredSize(new Dimension(250,250));
        JLabel nom = new JLabel ();
        nom.setText("Joueur n°" + nb+" :");
        JLabel label = new JLabel();
        label.setText(j.getName());
        label.setForeground(j.getCouleur());

        this.j = j;
        //ressource.setText(j.toStringRessources() + "\n" + j.toStringConstructions() + "\n" + "Points de V : " + j.getPointVictoire());

        /*JList cartes = new JList(j.getListCartes());
        cartes.getFixedCellWidth();
        cartes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        DefaultListModel model = new DefaultListModel();

        cartes.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                JList list = (JList)evt.getSource();
                if (evt.getClickCount() == 2 && j.getListCartes().length != 0) {
                    // Double-click detected
                    int index = list.locationToIndex(evt.getPoint());
                    try {
						j.jouerCarte(cartes.getSelectedIndex());
					} catch (RessourcesInsuffisantesException | NombreLimiteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                    cartes.setListData(j.getListCartes());
                } else if (evt.getClickCount() == 3) {

                    // Triple-click detected
                    int index = list.locationToIndex(evt.getPoint());
                }
            }
        });
        */
        
        this.add(nom);
        this.add(label);
        this.add(ressource);
        //this.add(cartes);
        
        
    }
    
    public void rafraichir() {
        this.ressource.setText(this.j.toStringRessources());
    }
}
