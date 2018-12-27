package com.graphique.fenetre_principale;


import com.modele.construction.NombreLimiteException;
import com.modele.construction.RessourcesInsuffisantesException;
import com.modele.joueur.Joueur;
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

public class PlateauPanel extends JPanel implements MouseListener {

    private ArrayList<Hexagone> listeHexagones = new ArrayList<>();

    private ArrayList<Ellipse2D.Double> listeEllipseBoutons = new ArrayList<>();
    private ArrayList<Line2D.Double> listeArreteBoutons =  new ArrayList<>();

    private ArrayList<Ellipse2D.Double> listeDeloreanes = new ArrayList<>();
    private ArrayList<Ellipse2D.Double> listeConvertisseursTemporels = new ArrayList<>();
    private ArrayList<Line2D.Double> listeRoutes =  new ArrayList<>();

    private Joueur joueurActif;
    private Placement placement;



    public PlateauPanel(Joueur[] listeJoueurs) {
        this.setPreferredSize(new Dimension(800,700));
        this.setBackground(Color.gray);
        System.out.println("" + (int)getSize().getWidth()/2 + (int)getSize().getHeight()/2);
        addMouseListener(this);
        initialiserListeHexagones(new Point2D.Double(400,350), 77, 3);

        this.joueurActif = listeJoueurs[0];
        this.joueurActif.ajouterRessources(Ressources.BLE, 50);
        this.joueurActif.ajouterRessources(Ressources.BOIS, 50);
        this.joueurActif.ajouterRessources(Ressources.FER, 50);
        this.joueurActif.ajouterRessources(Ressources.ARGILE, 50);
        this.placement = Placement.VIDE;

    }

    /**
     * Initialise la liste d'Hexagone, les boutons pour placer les constructions et les routes
     * @param centre Centre des hexagones et donc du plateau
     * @param rayon Rayon de tous les hexagones
     * @param tours Combien il y a d'hexagones autour de l'hexagone du centre
     */
    private void initialiserListeHexagones(Point2D.Double centre, int rayon, int tours) {

        Hexagone h1 =  new Hexagone(centre,rayon);
        listeHexagones.add(h1);

        ArrayList<Point2D.Double> listePositionsHexagones = new ArrayList<>();

        for(int i = 1; i < tours; i++){
            int rayonTemp = rayon * 2;
            Hexagone hexagoneTemp = new Hexagone(centre,Math.cos(Math.PI/6)*rayonTemp*i,Math.PI/2); //Hexagone temp
            listePositionsHexagones.addAll(Arrays.asList(hexagoneTemp.getPoints())); //On ajoute tous les  points de hexagone temp a la listePositionHexagones

            for(int j = 0; j < 5; j++){
                listePositionsHexagones.addAll(Arrays.asList(CalculPoint.split(hexagoneTemp.getPoints()[j], hexagoneTemp.getPoints()[j + 1], i))); //On partage le chasque cote de l'hexagone en plusieurs parties
            }
            listePositionsHexagones.addAll(Arrays.asList(CalculPoint.split(hexagoneTemp.getPoints()[5], hexagoneTemp.getPoints()[0], i))); //On partage le dernier coté
        }


        for (Point2D.Double p : listePositionsHexagones){
            this.listeHexagones.add(new Hexagone(p,rayon)); //Pour chaque point dans la listeHexagoneTemp on cree un nouvel hexagone
        }


        //Ellipses
        for(Hexagone h : listeHexagones){
            for(int i = 0; i<6 ; i++) {
                Ellipse2D.Double e = new Ellipse2D.Double(h.getX(i)-15, h.getY(i)-15, 30 ,30);
                if (testDoublon(e))
                    this.listeEllipseBoutons.add(e);
            }
        }


        for(Hexagone h : listeHexagones){
            for(int i = 0; i<5 ; i++) {
                Line2D.Double l = new Line2D.Double(h.getX(i),h.getY(i),h.getX(i+1),h.getY(i+1));
                if(testDoublon(l))
                    listeArreteBoutons.add(l);
            }
            Line2D.Double l = new Line2D.Double(h.getX(5),h.getY(5),h.getX(0),h.getY(0));
            if(testDoublon(l))
                listeArreteBoutons.add(l);
        }
    }

    private boolean testDoublon(Line2D d){
        int marge = 4;
        Ellipse2D.Double ellipse1 = new Ellipse2D.Double(d.getX1() - (marge/2), d.getY1() - (marge/2), marge, marge);
        Ellipse2D.Double ellipse2 = new Ellipse2D.Double(d.getX2() - (marge/2), d.getY2() - (marge/2), marge, marge);
        for (Line2D l : this.listeArreteBoutons){
            if (ellipse1.contains(l.getX1(), l.getY1()) && ellipse2.contains(l.getX2(), l.getY2()) || ellipse2.contains(l.getX1(), l.getY1()) && ellipse1.contains(l.getX2(), l.getY2())){
                return false;
            }
        }
        return true;
    }

    private boolean testDoublon(Ellipse2D.Double ell){
        for(Ellipse2D.Double e : this.listeEllipseBoutons)
            if(ell.intersects(e.getX(), e.getY(), e.getWidth(), e.getHeight()))
                return false;
        return true;
    }


    public void setJoueurActif(Joueur joueurActif) { this.joueurActif = joueurActif; }

    void setPlacement(Placement placement) { this.placement = placement; repaint(); }

    public void paintComponent(Graphics g2){
        super.paintComponent(g2);
        Graphics2D g = (Graphics2D)g2;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //On dessine tous les hexagones
        dessinerHexagones(g);


        //On dessine les boutons

        switch (this.placement){
            case ROUTE: dessinerBoutonsRoutes(g); break;
            case DELOREAN: dessinerBoutonsVilles(g); break;
            case CONVERTISSEUR_TEMPOREL: dessinerBoutonsVilles(g); break;
            case VIDE: break;
            default: System.out.println("Erreur boutons"); break;
        }

        //On dessine toutes les constructions
        dessinerRoutes(g);
        dessinerDeloreanes(g);
        g.setColor(Color.RED);

    }












    private void dessinerHexagones(Graphics2D g){

        for(Hexagone h : listeHexagones){

            //Dessine les hexagones
            g.setColor(Color.CYAN);
            g.drawPolygon(h.getX(),h.getY(),6);

            //Dessine les nombres

            g.setColor(Color.CYAN);
            g.drawString(""+h.getNombre(),(int)Math.round(h.getXCentre()),(int)Math.round(h.getYCentre()));

            //Dessine les images du fond
            /*try {
                Image img = ImageIO.read(new File(""+h.getRessources()+".jpg"));
                g.drawImage(img, (int)h.getXCentre()-40, (int)h.getYCentre()-40, 70,70, this);
            } catch (IOException e) {
                e.printStackTrace();
            }*/

        }
    }

    private void dessinerBoutonsVilles(Graphics2D g){
        g.setColor(Color.red);
        for (Ellipse2D.Double e : this.listeEllipseBoutons){
            g.fill(e);
        }

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
            g.setStroke(new BasicStroke(15));
            g.setColor(new Color(79, 63, 13));
            g.drawLine((int)Math.round(l.getX1()), (int)Math.round(l.getY1()), (int)Math.round(l.getX2()), (int)Math.round(l.getY2()));

            g.setStroke(new BasicStroke(5,
                    BasicStroke.CAP_ROUND,    // End cap
                    BasicStroke.JOIN_MITER,    // Join style
                    10.0f,            // Miter limit
                    test, // Dash pattern
                    0.0f));

            g.setColor(Color.BLACK);
            g.drawLine((int)Math.round(l.getX1()), (int)Math.round(l.getY1()), (int)Math.round(l.getX2()), (int)Math.round(l.getY2()));
            g.setStroke(strokeDefaut);
        }
    }

    private void dessinerDeloreanes(Graphics2D g){
        for(Ellipse2D.Double ell : this.listeDeloreanes){
            try {
                Image img = ImageIO.read(new File("Delorean.png"));
                g.drawImage(img, (int)Math.round(ell.getX())-15, (int)Math.round(ell.getY())-15, 70,70, this);
            } catch (IOException e) {
                e.printStackTrace();
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
        if(this.placement == Placement.DELOREAN){
            for(int i = 0; i < this.listeEllipseBoutons.size(); i++){
                if(this.listeEllipseBoutons.get(i).contains(e.getX(), e.getY())){
                    try {
                        for (Hexagone h : this.listeHexagones){
                            if (h.intersects(this.listeEllipseBoutons.get(i).getX(), this.listeEllipseBoutons.get(i).getY(), this.listeEllipseBoutons.get(i).getHeight(), this.listeEllipseBoutons.get(i).getWidth()))
                                System.out.println("touche l'hexagone " + h.getNombre());
                        }
                        this.listeDeloreanes.add(this.listeEllipseBoutons.get(i));
                        this.listeEllipseBoutons.remove(i);
                        System.out.println("Delorean Placé");
                    } catch (Exception e1) {
                        System.out.println(e1.getMessage());
                    }

                    repaint();
                }
            }
        }

        if(this.placement == Placement.ROUTE){
            int HIT_BOX_SIZE = 10;

            int boxX = e.getX() - HIT_BOX_SIZE / 2;
            int boxY = e.getY() - HIT_BOX_SIZE / 2;

            for(int i = 0; i < this.listeArreteBoutons.size(); i++){
                if(this.listeArreteBoutons.get(i).intersects(boxX, boxY, HIT_BOX_SIZE, HIT_BOX_SIZE)){

                    try {
                        this.joueurActif.creerRoute();

                        Line2D.Double tempLine = this.listeArreteBoutons.get(i);
                        Point2D.Double tempPoint = CalculPoint.split(new Point2D.Double(tempLine.getX1(), tempLine.getY1()), new Point2D.Double(tempLine.getX2(), tempLine.getY2()), 2)[0];
                        int marge = 5;
                        for (Hexagone h : this.listeHexagones){
                            if (h.intersects(tempPoint.getX() - marge/2, tempPoint.getY() - marge/2, marge, marge))
                                System.out.println("touche l'hexagone " + h.getNombre());
                        }
                        this.listeRoutes.add(this.listeArreteBoutons.get(i));
                        this.listeArreteBoutons.remove(i);
                        System.out.println("Route Placé");

                    } catch (RessourcesInsuffisantesException | NombreLimiteException e1) {
                        System.out.println(e1.getMessage());
                    }
                    repaint();

                }
            }
        }

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
