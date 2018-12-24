package com.graphique.fenetre_lancement;

import com.graphique.fenetre_principale.FenetrePrincipale;
import com.modele.joueur.Joueur;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LancementJoueursPanel extends JPanel implements ActionListener{

    private JButton retourBouton = new JButton("Retour");
    private JTextField[] listeTextField = new JTextField[4];

    /**
     * Panel ou les joueurs entrent leurs noms
     */
    LancementJoueursPanel() {

        //Parametres du panel
        setLayout(new GridLayout(6, 2, 10, 10));
        setBorder(new EmptyBorder(70, 200 , 70, 200));


        //On initialise les JLabel
        JLabel[] listeLabel =  new JLabel[]{
                creerLabel("Joueur Rouge :", Color.RED),
                creerLabel("Joueur Bleu :", Color.BLUE),
                creerLabel("Joueur Vert :", Color.GREEN),
                creerLabel("Joueur Rose :", Color.MAGENTA)
        };


        //On initialise tout les JTextField, et on ajoute tout les JTextField et tout les JLabels dans la GridLayout
        for (int i = 0; i < 4; i++){
            this.add(listeLabel[i]);
            listeTextField[i] = new JTextField();
            this.add(listeTextField[i]);
        }


        //On ajoute tout les boutons
        this.retourBouton.setActionCommand("retour");
        JButton validerBouton = new JButton("Valider");

        this.add(this.retourBouton);
        this.add(validerBouton);

        validerBouton.addActionListener(this);
    }


    /**
     * On ajouter les boutons a l'interface ActinListener
     * @param a
     */
    void addPublicButtonListener(ActionListener a){
        this.retourBouton.addActionListener(a);
    }


    /**
     * Helper Method
     * methode unqique a cette classe qui sert a creer un JLabel en fonction du texte et de la couleur donnee
     * @param Text Texte du JLabel
     * @param c Couleur du texte
     * @return
     */
    private JLabel creerLabel(String Text, Color c)
    {
        JLabel Temp = new JLabel(Text);
        Temp.setForeground(c);
        Temp.setBackground(new Color(0,0,0,0));
        return Temp;
    }


    /**
     * Verifie le Texte
     * @return
     */
    public boolean verifText(){
        Pattern patt1 = Pattern.compile("^[^,:;=?@#|'<>.*^()%!&&[^ ]]");
        for(JTextField t : this.listeTextField){
            Matcher m = patt1.matcher(t.getText());
            if (m.find() || !t.getText().isEmpty())
                return true;
        }
        return false;

    }


    /**
     * Cette methode sert a definir le comportement du bouton "Valider", il est pas obliger de passer par "FenetreLancement", donc on le laisse ici
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (verifText()) {
            new FenetrePrincipale(
                    new Joueur(this.listeTextField[0].getText()),
                    new Joueur(this.listeTextField[1].getText()),
                    new Joueur(this.listeTextField[2].getText()),
                    new Joueur(this.listeTextField[3].getText())
            );
        }
        else{
            JOptionPane.showMessageDialog(null,"Les espaces et caractères spéciaux ne sont pas acceptés et les noms ne peuvent pas être vide","Attention",JOptionPane.WARNING_MESSAGE);
        }
    }
}
