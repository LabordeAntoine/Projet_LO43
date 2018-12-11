package com.graphique;
import com.modele.joueur.Joueur;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class FenetreLancement extends JFrame {

        private JButton boutonQuitter = new JButton("Quitter");
        private Joueur j1 = new Joueur("Antoine");

        public FenetreLancement() {
            //Définit un titre pour notre fenêtre
            this.setTitle("Back To Catane");
            //Définit sa taille : 700 pixels de large et 400 pixels de haut
            this.setSize(700, 400);
            //Nous demandons maintenant à notre objet de se positionner au centre
            this.setLocationRelativeTo(null);
            //Termine le processus lorsqu'on clique sur la croix rouge
            //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            //Annule la possiblite de redimensionner la fenetre
            this.setResizable(false);

            JLayeredPane l = new JLayeredPane();
            l.setPreferredSize(new Dimension(700,400));

            JPanel pi = new JPanel() {
                public void paintComponent(Graphics g) {
                    try {
                        Image img = ImageIO.read(new File("BackToCatane.jpg"));
                        //Pour une image de fond
                        g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };

            pi.setBounds(0,0,700,400);
            l.add(pi,new Integer(0));

            JPanel pq = new JPanel();
            pq.setBackground(new Color(0,0,0,0));
            JButton b1 = new JButton("Quitter");
            b1.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });
            pq.add(b1);
            pq.setBounds(600,350,90,35);

            JPanel pj = new JPanel();
            pj.setBackground(new Color(0,0,0,0));
            JButton b2 = new JButton("Jouer");
            b2.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    Fenetre F = new Fenetre(j1);
                    F.setVisible(true);
                }
            });
            pj.add(b2);
            pj.setBounds(10,350,90,35);

            l.add(pq,new Integer(1));
            l.add(pj,new Integer(1));
            this.add(l);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.pack();
            //Et enfin, la rendre visible
            this.setVisible(true);
        }
}
