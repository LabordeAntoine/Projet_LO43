package com.graphique;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.Desktop;

public class FenetreLancement extends JFrame {

        public FenetreLancement() {

            this.setTitle("Back To Catane");
            this.setSize(700, 400);
            this.setLocationRelativeTo(null);
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


            JButton b1 = new JButton("Quitter");
            b1.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });

            b1.setBounds(600,350,90,35);


            JButton b2 = new JButton("Jouer");
            b2.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    FenetreJoueurs F = new FenetreJoueurs();
                    F.setVisible(true);
                    dispose();
                }
            });
            b2.setBounds(10,350,90,35);


            JButton b3 = new JButton("Regles");
            b3.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    Desktop d = Desktop.getDesktop();
                    try{
                    d.open(new File("Regles.pdf"));}
                    catch(Exception ex){
                        ex.printStackTrace();
                    }
                }
            });
            b3.setBounds(300,350,90,35);

            l.add(b1,new Integer(1));
            l.add(b2,new Integer(1));
            l.add(b3,new Integer(1));
            this.add(l);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.pack();

            this.setVisible(true);
        }
}
