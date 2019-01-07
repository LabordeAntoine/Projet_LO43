package com.graphique.fenetre_principale;
import com.graphique.fenetre_principale.plateau.Placement;
import com.graphique.fenetre_principale.plateau.PlateauException;
import com.graphique.fenetre_principale.plateau.PlateauPanel;
import com.modele.joueur.Joueur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;


public class FenetrePrincipale extends JFrame implements ActionListener , MouseListener {

    //Composants
    private PlateauPanel plateauPanel;

    private GestionPlateauPanel gestionPlateauPanel;

    private JoueurIndividuelPanel joueurIndividuelPanel1;
    private JoueurIndividuelPanel joueurIndividuelPanel2;
    private JoueurIndividuelPanel joueurIndividuelPanel3;
    private JoueurIndividuelPanel joueurIndividuelPanel4;

    private JLabel labelInformation;


    //Deroulement du jeu
    private ArrayList<Joueur> listeJoueurs;
    private Joueur joueurActif;
    private int joueurActuel;
    private int resultatDe;


    

    /**
     * C'est la classe de la fenetre principale du jeu
     * ici on trouve le plateau, les boutons et les panels des joueurs
     */
    public FenetrePrincipale(ArrayList<Joueur> j ) throws PlateauException {
        //Layout
        this.setLayout(new BorderLayout());

        this.labelInformation = new JLabel("Information : ");
        this.plateauPanel = new PlateauPanel(j.get(0), this.labelInformation);

        JPanel panCentre = new JPanel();
        panCentre.setLayout(new BoxLayout(panCentre, BoxLayout.Y_AXIS));
        panCentre.add(this.plateauPanel);
        panCentre.add(Box.createRigidArea(new Dimension(0,20)));
        this.gestionPlateauPanel = new GestionPlateauPanel();
        panCentre.add(gestionPlateauPanel);

        panCentre.add(Box.createRigidArea(new Dimension(0,20)));
        panCentre.add(this.labelInformation);

        this.labelInformation.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel panDroite = new JPanel(new GridLayout(2,1));
        panDroite.add(this.joueurIndividuelPanel1 = new JoueurIndividuelPanel(j.get(0),1));
        panDroite.add(this.joueurIndividuelPanel2 = new JoueurIndividuelPanel(j.get(1),2));

        JPanel panGauche = new JPanel(new GridLayout(2,1));
        panGauche.add(this.joueurIndividuelPanel3 = new JoueurIndividuelPanel(j.get(2),3));
        panGauche.add(this.joueurIndividuelPanel4 = new JoueurIndividuelPanel(j.get(3),4));


        this.getContentPane().add(panDroite,BorderLayout.EAST);
        this.getContentPane().add(panCentre, BorderLayout.CENTER);
        this.getContentPane().add(panGauche, BorderLayout.WEST);

        //Gestion des boutons
        gestionPlateauPanel.addPublicButtonListener(this);

        plateauPanel.addMouseListener(this);


        //Parametres de la fenetre
        this.setTitle("Back to Catane");
        this.setMinimumSize(new Dimension((int)this.plateauPanel.getMinimumSize().getWidth()  + 550, (int)this.plateauPanel.getMinimumSize().getWidth() + 220));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);



    	
    	this.listeJoueurs = j;
    	this.joueurActif = this.listeJoueurs.get(this.joueurActuel);
    	System.out.println("joueur actif " + this.joueurActif.getName());


        this.gestionPlateauPanel.setBoutons(false);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String buttonName = e.getActionCommand();

        switch (buttonName){
            case "placer delorean" : this.plateauPanel.setPlacement(Placement.DELOREAN); break;
            
            case "placer convertisseur temporel": this.plateauPanel.setPlacement(Placement.CONVERTISSEUR_TEMPOREL); break;
            
            case "placer route": this.plateauPanel.setPlacement(Placement.ROUTE); break;
            
            case "voir plateau": this.plateauPanel.setPlacement(Placement.VIDE);break;

            case "Faire du commerce": Commerce c = new Commerce(); break;
            
            case "fin de tour": 
            	this.listeJoueurNext();
	        	this.gestionPlateauPanel.setBoutons(false);
	        	this.plateauPanel.setPlacement(Placement.VIDE);
	        	this.conditionsJeu();
	        	break;
        	
            case "lancer de":
            	this.resultatDe = this.gestionPlateauPanel.lancerDe();
	            System.out.println("Dé lancé");
	            break;

            default: System.out.println("Erreur bouton - Fenetre Principale");  break;
        }

        this.joueurIndividuelPanel1.rafraichir();
        this.joueurIndividuelPanel2.rafraichir();
        this.joueurIndividuelPanel3.rafraichir();
        this.joueurIndividuelPanel4.rafraichir();
    }
    
    void listeJoueurNext() {
    	if(this.joueurActuel != this.listeJoueurs.size() -1) {
    		this.joueurActuel++;
    	} else {
    		this.joueurActuel = 0;
    	}

    	for (Joueur joueur : this.listeJoueurs) {
    		this.plateauPanel.genererRessources(joueur, this.resultatDe);
    	}

    	this.joueurActif = this.listeJoueurs.get(this.joueurActuel);
    	this.plateauPanel.setJoueurActif(this.joueurActif);


        this.labelInformation.setText("C'est le tour de " + this.joueurActif.getName());
    	
    	
    }
    
    private void conditionsJeu() {
    	for (Joueur j : this.listeJoueurs) {
    		j.actualiserPV();
    		if (j.getPointVictoire() >= 10) {
                JOptionPane.showMessageDialog(null, "" + j.getName() + "a gagné la partie!!");
                this.dispose();
    		}
    	}
    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        this.joueurIndividuelPanel1.rafraichir();
        this.joueurIndividuelPanel2.rafraichir();
        this.joueurIndividuelPanel3.rafraichir();
        this.joueurIndividuelPanel4.rafraichir();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
