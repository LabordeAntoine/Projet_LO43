package com.graphique.fenetre_principale;

import com.modele.joueur.Joueur;
import com.modele.ressources.ListeRessources;
import com.modele.ressources.Ressources;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class JoueurIndividuelPanel extends JPanel {

    private JTextArea ressources;
    private Joueur joueur;

    public JoueurIndividuelPanel(Joueur j, int nb){

        EmptyBorder marge = new EmptyBorder(10,10,10,10);

        BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(boxLayout);
        this.setSize(new Dimension(60,20));
        this.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.black));
        this.setBounds(0,0,600,400);
        this.setPreferredSize(new Dimension(250,250));

        JLabel nom = new JLabel ();
        nom.setText("Joueur n°" + nb+" :");

        JLabel label = new JLabel();
        label.setText(j.getName());
        label.setForeground(j.getCouleur());

        this.joueur = j;
        this.ressources = new JTextArea();
        ressources.setBorder(marge);
        ressources.setEditable(false);
        rafraichir();

        JList cartes = new JList(j.getListCartes());
        cartes.getFixedCellWidth();
        cartes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        DefaultListModel model = new DefaultListModel();

        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        cartes.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                JList list = (JList)evt.getSource();
                if (evt.getClickCount() == 2 && j.getListCartes().length != 0) {
                    // Double-click detected
                    int index = list.locationToIndex(evt.getPoint());
                    j.jouerCarte(cartes.getSelectedIndex());
                    cartes.setListData(j.getListCartes());
                    rafraichir();
                } else if (evt.getClickCount() == 3) {
                    // Triple-click detected
                    int index = list.locationToIndex(evt.getPoint());
                }
            }
        });

        JButton piocherCarte = new JButton("Piocher Cartes");
        piocherCarte.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                ListeRessources prix = new ListeRessources();

                prix.ajouterRessources(Ressources.BLE, 3);
                prix.ajouterRessources(Ressources.BOIS, 2);
                if(j.getListeRessources().assezDeRessources(prix)) {
                    j.piocherCarte();
                    cartes.setListData(j.getListCartes());
                    rafraichir();
                }
                else
                {JOptionPane.showMessageDialog(null,"Vous n'avez pas assez de ressources","Attention",JOptionPane.WARNING_MESSAGE);}
            }
        });

        this.add(nom);
        this.add(label);
        this.add(ressources);
        this.add(cartes);
        this.add(piocherCarte);
    }

    public void rafraichir(){
        this.ressources.setText(this.joueur.toStringRessources() + "\n" + this.joueur.toStringConstructions() + "\n" + "Points de V : " + this.joueur.getPointVictoire());
    }

}
