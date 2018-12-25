package com.graphique.fenetre_principale;


import com.modele.construction.NombreLimiteException;
import com.modele.construction.RessourcesInsuffisantesException;
import com.modele.construction.Route;
import com.modele.joueur.Joueur;
import com.modele.construction.Construction;
import com.modele.ressources.Ressources;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class PlateauPanel extends JPanel implements MouseListener, ComponentListener {

    private ArrayList<Hexagone> listHex = new ArrayList<>();
    private ArrayList<Ellipse2D.Double> listEllipse = new ArrayList<>();
    private ArrayList<Ellipse2D.Double> listDeloreanes = new ArrayList<>();
    private ArrayList<Line2D.Double> listArrete =  new ArrayList<>();
    private ArrayList<Line2D.Double> listeRoutes =  new ArrayList<>();
    private HashMap<Point, Construction> listeDePoints = new HashMap<>();
    private Joueur joueurActif;
    private Placement placement;
    

    public PlateauPanel(Joueur[] listeJoueurs) {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(800,700));
        this.setBackground(Color.black);
        System.out.println("" + (int)getSize().getWidth()/2 + (int)getSize().getHeight()/2);
        this.add(setPan(),BorderLayout.SOUTH);
        addMouseListener(this);
        addComponentListener(this);
        initialiserListeHexagones(new Point(400,350), 100, 2);

        this.joueurActif = listeJoueurs[0];
        this.joueurActif.ajouterRessources(Ressources.BLE, 50);
        this.joueurActif.ajouterRessources(Ressources.BOIS, 50);
        this.joueurActif.ajouterRessources(Ressources.FER, 50);
        this.joueurActif.ajouterRessources(Ressources.ARGILE, 50);
        this.placement = Placement.VIDE;

    }

    public void setJoueurActif(Joueur joueurActif) {
        this.joueurActif = joueurActif;
    }

    public void paintComponent(Graphics g2){
        super.paintComponent(g2);
        Graphics2D g = (Graphics2D)g2;

        //On dessine tous les hexagones
        dessinerHexagones(g);

        //On dessine les boutons

        switch (this.placement){
            case ROUTE: dessinerBoutonsRoutes(g); break;
            case DELOREAN: dessinerBoutonsVilles(g); break;
            case CONVERTISSEUR_TEMPOREL: dessinerBoutonsVilles(g); break;
            default: System.out.println("Erreur boutons"); break;
        }

        //On dessine toutes les constructions
        dessinerRoutes(g);
        dessinerDeloreanes(g);

    }
    
    private void dessinerHexagones(Graphics2D g){

        for(Hexagone h : listHex){

            //Dessine les hexagones
            g.setColor(Color.CYAN);
            g.drawPolygon(h.getX(),h.getY(),6);

            //Dessine les nombres

            g.setColor(Color.CYAN);
            g.drawString(""+h.getNombre(),h.getXCentre(),h.getYCentre());

            //Dessine les images du fond
            /*try {
                Image img = ImageIO.read(new File(""+h.getRessources()+".jpg"));
                g.drawImage(img, (int)h.getXCentre()-40, (int)h.getYCentre()-40, 70,70, this);
            } catch (IOException e) {
                e.printStackTrace();
            }*/

        }
    }

    private void dessinerDeloreanes(Graphics2D g){
        for(Ellipse2D.Double ell : this.listDeloreanes){
            try {
                Image img = ImageIO.read(new File("Delorean.png"));
                g.drawImage(img, (int)Math.round(ell.getX())-15, (int)Math.round(ell.getY())-15, 70,70, this);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private void dessinerBoutonsVilles(Graphics2D g){
        g.setColor(Color.red);
        for (Ellipse2D.Double e : this.listEllipse){
            g.fill(e);
        }

    }

    private void dessinerBoutonsRoutes(Graphics2D g){
        g.setColor(Color.blue);
        for(Line2D.Double l : this.listArrete){
            g.setStroke(new BasicStroke(5));
            g.draw(l);
        }
    }
    
    private void dessinerRoutes(Graphics2D g){

        float test[] = new float[] { 25.0f, 25.0f };
        Stroke strokeDefaut = g.getStroke();

        for(Line2D.Double l : this.listeRoutes){
            g.setStroke(new BasicStroke(15));
            g.setColor(Color.GRAY);
            g.drawLine((int)Math.round(l.getX1()), (int)Math.round(l.getY1()), (int)Math.round(l.getX2()), (int)Math.round(l.getY2()));
            g.setStroke(new BasicStroke(5,
                    BasicStroke.CAP_SQUARE,    // End cap
                    BasicStroke.JOIN_MITER,    // Join style
                    10.0f,                     // Miter limit
                    test, // Dash pattern
                    0.0f));

            g.setColor(Color.BLACK);
            g.drawLine((int)Math.round(l.getX1()), (int)Math.round(l.getY1()), (int)Math.round(l.getX2()), (int)Math.round(l.getY2()));
            g.setStroke(strokeDefaut);
        }
    }

    /**
     * Initialise la liste d'Hexagone, de points et d'arrêtes
     * @param centre Centre des hexagones et donc du plateau
     * @param rayon Rayon de tous les hexagones
     * @param tours Combien il y a d'hexagones autour de l'hexagone du centre
     */
    private void initialiserListeHexagones(Point centre, int rayon, int tours) {

        Hexagone h1 =  new Hexagone(centre,rayon);
        listHex.add(h1);

        ArrayList<Point> listePositionsHexagones = new ArrayList<>();

        for(int i = 1; i < tours; i++){
            int rayonTemp = rayon * 2;
            Hexagone hexagoneTemp = new Hexagone(centre,Math.cos(Math.PI/6)*rayonTemp*i,Math.PI/2); //Hexagone temp
            listePositionsHexagones.addAll(Arrays.asList(hexagoneTemp.getPoints())); //On ajoute tous les  points de hexagone temp a la listePositionHexagones

            for(int j = 0; j < 5; j++){
                listePositionsHexagones.addAll(Arrays.asList(CalculPoint.split(hexagoneTemp.getPoints()[j], hexagoneTemp.getPoints()[j + 1], i))); //On partage le chasque cote de l'hexagone en plusieurs parties
            }
            listePositionsHexagones.addAll(Arrays.asList(CalculPoint.split(hexagoneTemp.getPoints()[5], hexagoneTemp.getPoints()[0], i))); //On partage le dernier coté
        }

        for (Point p : listePositionsHexagones){
            this.listHex.add(new Hexagone(p,rayon)); //Pour chaque point dans la listeHexagoneTemp on cree un nouvel hexagone
        }

        //Ellipses
        for(Hexagone h : listHex){
            for(int i = 0; i<6 ; i++) {
                Point p = new Point(h.getX(i), h.getY(i));
                if (testDoublon(p))
                    this.listeDePoints.put(p, null);
                /*Ellipse2D.Double e = new Ellipse2D.Double(h.getX(i)-15, h.getY(i)-15,30,30);
                if(!testDoublon(e))
                    this.listEllipse.add(e);*/
            }
        }

        for (Map.Entry<Point, Construction> m : this.listeDePoints.entrySet()){
            Ellipse2D.Double e = new Ellipse2D.Double(m.getKey().getX()-15, m.getKey().getY()-15, 30 ,30);
            this.listEllipse.add(e);
        }


        for(Hexagone h : listHex){
            for(int i = 0; i<5 ; i++) {
                Line2D.Double l = new Line2D.Double(h.getX(i),h.getY(i),h.getX(i+1),h.getY(i+1));
                listArrete.add(l);
            }
        }
    }

    private boolean testDoublon(Point p){
        int marge = 1;
        for (Map.Entry<Point, Construction> m : this.listeDePoints.entrySet()){
            if (p.getX() >= (m.getKey().getX()-marge) && p.getX() <= (m.getKey().getX()+marge) && p.getY() >= (m.getKey().getY()-marge) && p.getY()<=(m.getKey().getY()+marge))
                return false;
        }
        return true;
    }


    public JPanel setPan(){
        JButton bDelorean = new JButton("Cliquez-ici pour placer une Delorean");
        bDelorean.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                placement = Placement.DELOREAN;
                repaint();
            }
        });

        JButton bArrete = new JButton("Cliquez-ici pour placer une Route");
        bArrete.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                placement = Placement.ROUTE;
                repaint();
            }
        });

        JLabel lab = new JLabel(new ImageIcon("dice1.jpg"));

        JPanel p1 = new JPanel();
        p1.setLayout(new BorderLayout());
        p1.setPreferredSize(new Dimension(800,200));
        p1.add(bDelorean,BorderLayout.EAST);
        p1.add(bArrete, BorderLayout.WEST);
        p1.add(lab, BorderLayout.CENTER);
        return p1;
    }







    private Point ellipseToPoint(Ellipse2D e){
        return new Point((int)Math.round(e.getX()), (int)Math.round(e.getY()));
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
            for(int i = 0; i < this.listEllipse.size(); i++){
                if(this.listEllipse.get(i).contains(e.getX(), e.getY())){
                    System.out.println(this.joueurActif.getListeConstructions());

                    try {
                        this.listeDePoints.put(ellipseToPoint(this.listEllipse.get(i)), this.joueurActif.creerDelorean());
                        this.listDeloreanes.add(this.listEllipse.get(i));
                        this.listEllipse.remove(i);
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

            for(int i = 0; i < this.listArrete.size(); i++){
                if(this.listArrete.get(i).intersects(boxX, boxY, HIT_BOX_SIZE, HIT_BOX_SIZE)){
                    this.listeRoutes.add(this.listArrete.get(i));

                    try {
                        Route route = this.joueurActif.creerRoute();

                        this.listeDePoints.put(new Point((int)Math.round(this.listArrete.get(i).getX1()), (int)Math.round(this.listArrete.get(i).getY1())), route);
                        this.listeDePoints.put(new Point((int)Math.round(this.listArrete.get(i).getX2()), (int)Math.round(this.listArrete.get(i).getY2())), route);

                        this.listeRoutes.add(this.listArrete.get(i));

                    } catch (RessourcesInsuffisantesException | NombreLimiteException e1) {
                        e1.printStackTrace();
                    }
                    this.listArrete.remove(i);
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

    @Override
    public void componentResized(ComponentEvent e) {
        System.out.println(e.paramString());
        System.out.println(getSize().getHeight());
        System.out.println("resized");
        repaint();
    }

    @Override
    public void componentMoved(ComponentEvent e) {

    }

    @Override
    public void componentShown(ComponentEvent e) {

    }

    @Override
    public void componentHidden(ComponentEvent e) {

    }
}
