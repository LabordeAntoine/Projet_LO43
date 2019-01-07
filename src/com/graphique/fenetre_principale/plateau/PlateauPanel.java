package com.graphique.fenetre_principale.plateau;


import com.graphique.fenetre_principale.JoueurIndividuelPanel;
import com.modele.construction.Construction;
import com.modele.construction.ListeConstructions;
import com.modele.construction.NombreLimiteException;
import com.modele.construction.RessourcesInsuffisantesException;
import com.modele.joueur.Joueur;
import com.modele.ressources.ListeRessources;
import com.modele.ressources.Ressources;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class PlateauPanel extends JPanel implements MouseListener {

    private ArrayList<Hexagone> listeHexagones = new ArrayList<>();

    private ArrayList<Ellipse2D.Double> listeEllipseBoutons = new ArrayList<>();
    private ArrayList<Line2D.Double> listeArreteBoutons =  new ArrayList<>();

    private ArrayList<Point2D.Double> listeDeloreanes = new ArrayList<>();
    private ArrayList<Point2D.Double> listeConvertisseursTemporels = new ArrayList<>();
    private ArrayList<Line2D.Double> listeRoutes =  new ArrayList<>();

    private Joueur joueurActif;
    private Placement placement;
    private int annee;
    
    private JLabel labelInformations;

    public PlateauPanel(Joueur joueur, JLabel labelInformations) throws PlateauException{

        int rayon = 60;
        int tours = 3;


        //Parametres de taille
        this.setMinimumSize(new Dimension(2* rayon * ((tours*2)-2) + 20,  2*rayon * ((tours*2)-1) - rayon));
        this.setMaximumSize(this.getMinimumSize());
        this.setPreferredSize(this.getMinimumSize());
        
        this.setBackground(Color.DARK_GRAY);

        //On initialise
    
        
        initialiserListeHexagones(new Point2D.Double(this.getMinimumSize().getWidth() /2,this.getMinimumSize().getHeight()/2), rayon, tours);
        this.joueurActif = joueur;
        this.placement = Placement.VIDE;
        this.annee = 1985;
        

        this.labelInformations = labelInformations;
        
        //Listener
        addMouseListener(this);

    }

    public void setJoueurActif(Joueur joueurActif) { this.joueurActif = joueurActif; }

    public void setPlacement(Placement placement) { this.placement = placement; repaint(); }

    private Point2D.Double ellipseToPoint(Ellipse2D e){ return new Point2D.Double(e.getCenterX(), e.getCenterY()); }

    private Point2D.Double lineToPoint(Line2D.Double l){ return CalculPoint.split(new Point2D.Double(l.getX1(), l.getY1()), new Point2D.Double(l.getX2(), l.getY2()), 2)[0]; }

    /**
     * Initialise la liste d'Hexagone, les boutons pour placer les constructions et les routes
     * @param centre Centre des hexagones et donc du plateau
     * @param rayon Rayon de tous les hexagones
     * @param tours Combien il y a d'hexagones autour de l'hexagone du centre
     */
    private void initialiserListeHexagones(Point2D.Double centre, int rayon, int tours) {

        //On initialise l'hexagone du centre
        Hexagone h1 =  new Hexagone(centre,rayon);
        listeHexagones.add(h1);

        //On initialise l'hexagone TEMP, qui nous donnera les coordonnees pour placer les autres hexagones autours
        ArrayList<Point2D.Double> listePositionsHexagones = new ArrayList<>(); //On cree une liste pour toutes les coordoonees

        for(int i = 1; i < tours; i++){
            int rayonTemp = rayon * 2;
            Hexagone hexagoneTemp = new Hexagone(centre,Math.cos(Math.PI/6)*rayonTemp*i,Math.PI/2); //Hexagone temp
            listePositionsHexagones.addAll(Arrays.asList(hexagoneTemp.getPoints())); //On ajoute tous les  points de hexagone temp a la listePositionHexagones

            for(int j = 0; j < 5; j++){
                listePositionsHexagones.addAll(Arrays.asList(CalculPoint.split(hexagoneTemp.getPoints()[j], hexagoneTemp.getPoints()[j + 1], i))); //On partage le chasque cote de l'hexagone en plusieurs parties
            }
            listePositionsHexagones.addAll(Arrays.asList(CalculPoint.split(hexagoneTemp.getPoints()[5], hexagoneTemp.getPoints()[0], i))); //On partage le dernier coté
        }

        //On initialise tous les hexagones avec les points stockés dans la liste "listePositionsHexagones" qu'on a cree precedemment
        for (Point2D.Double p : listePositionsHexagones){
            this.listeHexagones.add(new Hexagone(p,rayon)); //Pour chaque point dans la listeHexagoneTemp on cree un nouvel hexagone
        }

        //On initialise les boutons ou on pourra cliquer dessus pour placer les constructions
        for(Hexagone h : listeHexagones){
            for(int i = 0; i<6 ; i++) {
                Ellipse2D.Double e = new Ellipse2D.Double(h.getX(i)-15, h.getY(i)-15, 30 ,30);
                if (!testDoublon(e))
                    this.listeEllipseBoutons.add(e);
            }
        }

        //On initialise les boutons ou on pourra cliquer dessus pour placer les routes
        for(Hexagone h : listeHexagones){
            for(int i = 0; i<5 ; i++) {
                Line2D.Double l = new Line2D.Double(h.getX(i),h.getY(i),h.getX(i+1),h.getY(i+1));
                if(!testDoublon(l))
                    listeArreteBoutons.add(l);
            }
            Line2D.Double l = new Line2D.Double(h.getX(5),h.getY(5),h.getX(0),h.getY(0));
            if(!testDoublon(l))
                listeArreteBoutons.add(l);
        }
    }

    /**
     * Sert a tester si un bouton a deja été placé
     * @param ellipse2D Ellipse a teste
     * @return Vrai si il y a un bouton deja placé
     */
    private boolean testDoublon(Ellipse2D.Double ellipse2D){
        for(Ellipse2D.Double e : this.listeEllipseBoutons)
            if(ellipse2D.intersects(e.getX(), e.getY(), e.getWidth(), e.getHeight()))
                return true;
        return false;
    }

    /**
     * Sert a tester si un bouton a deja été placé
     * @param line2D Ligne a tester
     * @return Vrai si il y a un bouton deja placé
     */
    private boolean testDoublon(Line2D line2D){
        int marge = 4;
        Ellipse2D.Double ellipse1 = new Ellipse2D.Double(line2D.getX1() - (marge/2), line2D.getY1() - (marge/2), marge, marge);
        Ellipse2D.Double ellipse2 = new Ellipse2D.Double(line2D.getX2() - (marge/2), line2D.getY2() - (marge/2), marge, marge);
        for (Line2D l : this.listeArreteBoutons){
            if (ellipse1.contains(l.getX1(), l.getY1()) && ellipse2.contains(l.getX2(), l.getY2()) || ellipse2.contains(l.getX1(), l.getY1()) && ellipse1.contains(l.getX2(), l.getY2())){
                return true;
            }
        }
        return false;
    }

    /**
     * PaintComponenent va dessiner notre plateau
     * @param g2
     */
    public void paintComponent(Graphics g2){
        super.paintComponent(g2);
        Graphics2D g = (Graphics2D)g2;

        //Anti-aliasing
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //Fond
        //setBackground(Color.DARK_GRAY);

        dessinerRessources(g);

        //On dessine tous les hexagones
        dessinerHexagones(g);

        //On dessine toutes les constructions
        dessinerRoutes(g);
        dessinerDeloreanes(g);
        dessinerConvertisseurTemporel(g);
        g.drawString(""+this.annee, 10, 50);

        //On dessine les boutons si ils sont demandés, pour ca on verifie l'etat de la variable "placement"
        switch (this.placement){
            case ROUTE: dessinerBoutonsRoutes(g); break;
            case DELOREAN: dessinerBoutonsVilles(g); break;
            case CONVERTISSEUR_TEMPOREL: dessinerBoutonsVilles(g); break;
            case VIDE: break;
            default: System.out.println("Erreur boutons"); break;
        }
    }

    private void dessinerHexagones(Graphics2D g){

        for(Hexagone h : listeHexagones){

            //Dessine les hexagones
            g.setColor(Color.YELLOW);
            g.drawPolygon(h.getX(),h.getY(),h.npoints);

            //Dessine les nombres
            int taillePolice = 20;
            String stylePolice = "Arial";
            Font font = new Font(stylePolice, Font.PLAIN, taillePolice);
            g.setFont(font);
            g.setColor(Color.RED);
            g.drawString(""+h.getNombre(),(int)Math.round(h.getXCentre() - 5 - taillePolice/5),(int)Math.round(h.getYCentre() - 45) + taillePolice/5);

        }
    }

    private void dessinerRessources(Graphics2D g){

        //Dessine les images du fond
        for(Hexagone h : listeHexagones){
            try {
                Image img = ImageIO.read(new File("Image_Ressources/"+h.getRessources()+".jpg"));
                g.drawImage(img, (int)h.getXCentre()-35, (int)h.getYCentre()-35, 70,70, this);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void dessinerBoutonsVilles(Graphics2D g){

        Stroke strokeDefaut = g.getStroke();
        g.setStroke(new BasicStroke(5));

        for (Ellipse2D.Double e : this.listeEllipseBoutons){
            g.setColor(Color.red);
            g.fill(e);
            g.setColor(new Color(121, 0, 38));
            g.draw(e);
        }

        g.setStroke(strokeDefaut);
    }

    private void dessinerBoutonsRoutes(Graphics2D g){
        g.setColor(Color.blue);
        for(Line2D.Double l : this.listeArreteBoutons){
            g.setStroke(new BasicStroke(5));
            g.draw(l);
        }
    }
    
    private void dessinerRoutes(Graphics2D g){

        float test[] = new float[] { 5f, 25.0f };
        Stroke strokeDefaut = g.getStroke();

        for(Line2D.Double l : this.listeRoutes){
            g.setStroke(new BasicStroke(20,
                BasicStroke.CAP_ROUND,
                BasicStroke.JOIN_MITER));
            g.setColor(new Color(79, 63, 13));
            g.drawLine((int)Math.round(l.getX1()), (int)Math.round(l.getY1()), (int)Math.round(l.getX2()), (int)Math.round(l.getY2()));

            g.setStroke(new BasicStroke(7,
                    BasicStroke.CAP_ROUND,     // End cap
                    BasicStroke.JOIN_MITER,    // Join style
                    10.0f,            // Miter limit
                    test,                      // Dash pattern
                    0.0f));
            g.setColor(Color.BLACK);
            g.drawLine((int)Math.round(l.getX1()), (int)Math.round(l.getY1()), (int)Math.round(l.getX2()), (int)Math.round(l.getY2()));
            g.setStroke(strokeDefaut);
        }
    }

    private void dessinerDeloreanes(Graphics2D g){
        int width = 70;
        int height = 40;

        for(Point2D.Double p : this.listeDeloreanes){
            try {
                Image img = ImageIO.read(new File("Delorean.png"));
                g.drawImage(img, (int)Math.round(p.getX()) - width/2, (int)Math.round(p.getY()) - height/2, width,height, this);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void dessinerConvertisseurTemporel(Graphics2D g){
        int width = 50;
        int height = 70;

        for(Point2D.Double p : this.listeConvertisseursTemporels){
            try {
                Image img = ImageIO.read(new File("ConvertisseurTemporel.png"));
                g.drawImage(img, (int)Math.round(p.getX()) - width/2, (int)Math.round(p.getY()) - height/2, width,height, this);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void genererRessources(Joueur joueur, int resultatDe){ joueur.ajouterRessources(this.listeDeRessourcesAProximite(joueur.getListeConstructions(), resultatDe));}
    
    public ListeRessources listeDeRessourcesAProximite(ListeConstructions listeConstructions, int resultatDe){
        ListeRessources listeRessources = new ListeRessources();
        int marge = 5;
        for (Construction construction : listeConstructions){
            Point2D.Double position = construction.getPosition();
            for (Hexagone h : this.listeHexagones){
                if (h.intersects(position.getX() - marge/2, position.getY() - marge/2, marge, marge) && h.getNombre() == resultatDe){
                    System.out.println("touche l'hexagone " + h.getNombre());
                    listeRessources.ajouterRessources(h.getRessources());
                }
            }
        }
        return listeRessources;
    }

    

    
    void chanagementPlateau() {
    	for (Hexagone hexagone : this.listeHexagones) {
    		hexagone.resetCase();
    		while (hexagone.getRessources() == Ressources.MINERAI) {
    			hexagone.resetCase();
    		}
    	}
    }
    
    void changerAnnee() {
    	switch(this.annee) {
    	case 2015 : this.annee = 1885;break;
    	case 1885 : this.annee = 1955;break;
    	case 1955 : this.annee = 1985; break;
    	case 1985 : this.annee = 2015; break;
    	default: this.annee = 1985; break;
    	}
        
    }
    
    
    
    
    @Override
    public void mouseClicked(MouseEvent e) { }

    @Override
    public void mousePressed(MouseEvent e) { }

    @Override
    public void mouseReleased(MouseEvent e) {

        int position;

        switch (this.placement){

            case DELOREAN:
                position = getEllipsePosition(e);
                if (position != -1) {
                    try {
                        this.joueurActif.creerDelorean(this.ellipseToPoint(this.listeEllipseBoutons.get(position)));
                        this.listeDeloreanes.add(ellipseToPoint(this.listeEllipseBoutons.get(position)));
                        this.listeEllipseBoutons.remove(position);
                    } catch (Exception e1) {
                    	this.labelInformations.setText(e1.getMessage());
                    } finally {
                        this.placement = Placement.VIDE;
                    }
                }
                break;

            case CONVERTISSEUR_TEMPOREL:
                position = getEllipsePosition(e);
                if (position != -1) {
                    try {
                        this.joueurActif.creerConvertisseurTemporel(this.ellipseToPoint(this.listeEllipseBoutons.get(position)));
                        this.listeConvertisseursTemporels.add(ellipseToPoint(this.listeEllipseBoutons.get(position)));
                        this.listeEllipseBoutons.remove(position);
                        this.chanagementPlateau();
                        this.changerAnnee();
                    } catch (Exception e1) {
                    	this.labelInformations.setText(e1.getMessage());
                    } finally {
                        this.placement = Placement.VIDE;
                    }
                }
                break;

            case ROUTE:
                position = getRoutePosition(e);
                if (position != -1) {
                    try {
                        this.joueurActif.creerRoute(this.lineToPoint(this.listeArreteBoutons.get(position)));
                        this.listeRoutes.add(this.listeArreteBoutons.get(position));
                        this.listeArreteBoutons.remove(position);
                    } catch (RessourcesInsuffisantesException | NombreLimiteException e1) {
                    	this.labelInformations.setText(e1.getMessage());
                    } finally {
                        this.placement = Placement.VIDE;
                    }
                }

            case VIDE: break;
            default: System.out.println("public void mouseReleased(MouseEvent e)"); break;
        }
        repaint();

    }

    private int getEllipsePosition(MouseEvent e) {
        int i;
        for (i = 0; i < this.listeEllipseBoutons.size(); i++) {
            if (this.listeEllipseBoutons.get(i).contains(e.getX(), e.getY()))
                return i;
        }
        return -1;
    }
    private int getRoutePosition(MouseEvent e){
        int HIT_BOX_SIZE = 10;

        int boxX = e.getX() - HIT_BOX_SIZE / 2;
        int boxY = e.getY() - HIT_BOX_SIZE / 2;

        for(int i = 0; i < this.listeArreteBoutons.size(); i++){

            if(this.listeArreteBoutons.get(i).intersects(boxX, boxY, HIT_BOX_SIZE, HIT_BOX_SIZE))
                return i;
        }
        return  -1;
    }

    @Override
    public void mouseEntered(MouseEvent e) { }

    @Override
    public void mouseExited(MouseEvent e) { }


}
