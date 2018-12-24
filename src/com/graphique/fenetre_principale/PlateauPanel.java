package com.graphique.fenetre_principale;


import com.modele.joueur.Joueur;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class PlateauPanel extends JPanel implements MouseListener, ComponentListener {

    private ArrayList<Hexagone> listHex = new ArrayList<>();
    private ArrayList<Ellipse2D.Double> listEllipse = new ArrayList<>();
    private ArrayList<Ellipse2D.Double> listDeloreanes = new ArrayList<>();
    private ArrayList<Line2D.Double> listArrete =  new ArrayList<>();
    private ArrayList<Line2D.Double> listeRoutes =  new ArrayList<>();
    


    private boolean cDelorean = false;
    private boolean cArrete = false;

    public PlateauPanel(Joueur[] listeJoueurs) {
        //this.setBounds(0,0,800,700);
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(800,700));
        //this.setSize(new Dimension(800,700));
        this.setBackground(Color.black);
        System.out.println("" + (int)getSize().getWidth()/2 + (int)getSize().getHeight()/2);
        this.add(setPan(),BorderLayout.SOUTH);
        addMouseListener(this);
        addComponentListener(this);
        initialiserListeHexagones(new Point(400,350), 100, 2);
    }

    public void paintComponent(Graphics g2){
        super.paintComponent(g2);
        Graphics2D g = (Graphics2D)g2;

        //On dessine tous les hexagones
        dessinerHexagones(g);

        //On dessine les boutons
        if (cArrete) dessinerBoutonsRoutes(g);
        if (cDelorean) dessinerBoutonsVilles(g);

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
                g.drawImage(img, (int)ell.getX()-15, (int)ell.getY()-15, 70,70, this);
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


        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("Route.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Line2D route : this.listeRoutes){

            AffineTransform at = new AffineTransform();

            if(route.getX2() < route.getX1()){
                Line2D temp = route;
                route.setLine(temp.getP2(), temp.getP1());
            }


            // 4. translate it to the center of the component
            //at.translate(getWidth() / 2, getHeight() / 2);
            at.translate(route.getX1(), route.getY1());

            // 3. do the actual rotation
            double y = route.getY2() - route.getY1();
            double x = route.getX2() - route.getX1();

            if(Math.atan2(y, x) > 0){
                System.out.println(Math.atan2(y, x));
                at.rotate(Math.PI/3);
            } else if (Math.atan2(y, x) < 0){
                at.rotate(-Math.PI/3);
            } else if (Math.atan2(y, x) == 0){
                at.rotate(0);
            }

            // 2. just a scale because this image is big
            at.scale(0.48, 0.5);

            // 1. translate the object so that you rotate it around the
            //    center (easier :))
            at.translate(0, -image.getHeight()/2);

            // draw the image
            g.drawImage(image, at, null);
        }
    }

    /**
     * Initialise la liste d'Hexagone, de points et d'arrêtes
     * @param centre
     * @param rayon
     * @param tours
     */
    private void initialiserListeHexagones(Point centre, int rayon, int tours)
    {

        Hexagone h1 =  new Hexagone(centre,rayon);
        listHex.add(h1);

        ArrayList<Point> listePositionsHexagones = new ArrayList<>();

        for(int i = 1; i < tours; i++){
            int rayonTemp = rayon * 2;
            Hexagone hexagoneTemp = new Hexagone(centre,Math.cos(Math.PI/6)*rayonTemp*i,Math.PI/2);
            listePositionsHexagones.addAll(Arrays.asList(hexagoneTemp.getPoints()));
            for(int j = 0; j < 5; j++){
                listePositionsHexagones.addAll(Arrays.asList(CalculPoint.split(hexagoneTemp.getPoints()[j], hexagoneTemp.getPoints()[j + 1], i)));
            }
            listePositionsHexagones.addAll(Arrays.asList(CalculPoint.split(hexagoneTemp.getPoints()[5], hexagoneTemp.getPoints()[0], i)));
        }

        for (Point p : listePositionsHexagones){
            this.listHex.add(new Hexagone(p,rayon));
        }

        //Ellipses
        for(Hexagone h : listHex){
            for(int i = 0; i<6 ; i++) {
                Ellipse2D.Double e = new Ellipse2D.Double(h.getX(i)-15, h.getY(i)-15,30,30);
                if(!testDoublon(e))
                    this.listEllipse.add(e);
            }
        }


        for(Hexagone h : listHex){
            for(int i = 0; i<5 ; i++) {
                Line2D.Double l = new Line2D.Double(h.getX(i),h.getY(i),h.getX(i+1),h.getY(i+1));
                listArrete.add(l);
            }
        }
    }

    public JPanel setPan(){
        JButton bDelorean = new JButton("Cliquez-ici pour placer une Delorean");
        bDelorean.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                cDelorean = true;
                cArrete = false;
                repaint();
            }
        });

        JButton bArrete = new JButton("Cliquez-ici pour placer une Route");
        bArrete.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                cArrete = true;
                cDelorean = false;
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

    private boolean testDoublon(Ellipse2D.Double ell){
        for(Ellipse2D.Double e : this.listEllipse)
            if (ell.getX()>= (e.getX()-10) && ell.getX() <= (e.getX()+10) && ell.getY() >= (e.getY()-10) && ell.getY()<=(e.getY()+10)) {
                return true;
            }
        return false;
    }













    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(cDelorean){
            for(int i = 0; i < this.listEllipse.size(); i++){
                if(this.listEllipse.get(i).contains(e.getX(), e.getY())){
                    this.listDeloreanes.add(this.listEllipse.get(i));
                    this.listEllipse.remove(i);
                    repaint();
                }
            }
        }

        if(cArrete){
            int HIT_BOX_SIZE = 10;
            int boxX = e.getX() - HIT_BOX_SIZE / 2;
            int boxY = e.getY() - HIT_BOX_SIZE / 2;

            int width = HIT_BOX_SIZE;
            int height = HIT_BOX_SIZE;

            for(int i = 0; i < this.listArrete.size(); i++){
                if(this.listArrete.get(i).intersects(boxX, boxY, width, height)){
                    this.listeRoutes.add(this.listArrete.get(i));
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
