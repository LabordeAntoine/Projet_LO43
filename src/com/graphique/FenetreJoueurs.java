package com.graphique;
import com.modele.joueur.Joueur;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JPanel;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class FenetreJoueurs extends JFrame {
    public FenetreJoueurs() {

        this.setTitle("Back To Catane");
        this.setSize(400, 300);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        JLayeredPane l = new JLayeredPane();
        l.setPreferredSize(new Dimension(this.getWidth(), this.getHeight()));


        JButton b1 = new JButton("Retour");
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                FenetreLancement F = new FenetreLancement();
                F.setVisible(true);
            }
        });
        b1.setBounds(300, 260, 90, 35);

        JPanel pj = new JPanel();
        pj.setBackground(new Color(0,0,0,0));
        JTextField t1 = new JTextField();
        t1.setColumns(10);
        JTextField t2 = new JTextField();
        t2.setColumns(10);
        JTextField t3 = new JTextField();
        t3.setColumns(10);
        JTextField t4 = new JTextField();
        t4.setColumns(10);

        pj.add(t1);
        pj.add(t2);
        pj.add(t3);
        pj.add(t4);

        pj.setBounds(200,30,120,100);


        JButton b2 = new JButton("Valider");
        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (verifText(t1.getText(),t2.getText(),t3.getText(),t4.getText())) {
                    dispose();
                    Fenetre F = new Fenetre(new Joueur(t1.getText()), new Joueur(t2.getText()), new Joueur(t3.getText()), new Joueur(t4.getText()));
                    F.setVisible(true);
                }
                else{
                    JOptionPane Erreur = new JOptionPane();
                    Erreur.showMessageDialog(null,"Les espaces et caractères spéciaux ne sont pas acceptés","Attention",JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        b2.setBounds(10, 260, 90, 35);

        JLabel LabelJ1 = creerLabel("Joueur Rouge :",100,35,Color.RED);
        JLabel LabelJ2 = creerLabel("Joueur Bleu :",100,60,Color.BLUE);
        JLabel LabelJ3 = creerLabel("Joueur Vert :",100,85,Color.green);
        JLabel LabelJ4 = creerLabel("Joueur Rose :",100,110,Color.magenta);

        l.add(LabelJ1,new Integer(1));
        l.add(LabelJ2,new Integer(1));
        l.add(LabelJ3,new Integer(1));
        l.add(LabelJ4,new Integer(1));
        l.add(b1,new Integer(1));
        l.add(pj,new Integer(1));
        l.add(b2,new Integer(1));

        this.add(l);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

        this.setVisible(true);
    }

    public JLabel creerLabel(String Text,int x,int y,Color c)
    {
        JLabel Temp = new JLabel(Text);
        Temp.setBounds(x,y,110,20);
        Temp.setForeground(c);
        Temp.setBackground(new Color(0,0,0,0));

        return Temp;
    }

    public boolean verifText(String S1, String S2, String S3, String S4){
        Pattern patt1 = Pattern.compile("^[^,:;=?@#|'<>.*^()%!&&[^ ]]");
        Matcher m1 = patt1.matcher(S1);
        Matcher m2 = patt1.matcher(S2);
        Matcher m3 = patt1.matcher(S3);
        Matcher m4 = patt1.matcher(S4);

        return (m1.find()) && (m2.find()) && (m3.find()) && (m4.find());

    }
}

