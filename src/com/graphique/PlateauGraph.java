package com.graphique;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class PlateauGraph extends JPanel implements MouseListener {

    private ArrayList<Hexagone> listHex = new ArrayList<>();
    private ArrayList<Ellipse2D.Double> listEllipse = new ArrayList<>();
    private ArrayList<Ellipse2D.Double> listEllipseUtilise = new ArrayList<>();
    private ArrayList<Line2D.Double> listArrete =  new ArrayList<>();


    private boolean cDelorean = false;
    private boolean cArrete = false;

    public PlateauGraph() {

        this.setBounds(0,0,800,700);
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(800,700));
        this.setSize(new Dimension(800,700));
        this.setBackground(Color.black);
        initList(new Point(400,300), 50, 3);
        this.add(setPan(),BorderLayout.SOUTH);
        addMouseListener(this);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        for(Hexagone h : listHex){
            g2.setColor(Color.CYAN);
            g2.drawPolygon(h.getX(),h.getY(),6);
            g2.drawString(""+h.getNombre(),h.getXCentre(),h.getYCentre()-50);
            try {
                Image img = ImageIO.read(new File(""+h.getRessources()+".jpg"));
                g.drawImage(img, (int)h.getXCentre()-40, (int)h.getYCentre()-40, 70,70, this);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        if(cDelorean){
            g2.setColor(Color.red);
            for (Ellipse2D.Double e : this.listEllipse){
                g2.fill(e);
            }
        }

        if(cArrete){
            g2.setColor(Color.blue);
            for(Line2D.Double l : this.listArrete){
                g2.setStroke(new BasicStroke(5));
                g2.draw(l);
            }
        }

        for(Ellipse2D.Double ell : this.listEllipseUtilise){
            try {
                Image img = ImageIO.read(new File("Delorean.png"));
                g.drawImage(img, (int)ell.getX()-15, (int)ell.getY()-15, 70,70, this);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /*
    * Initialise la liste d'Hexagone, de points et d'arrêtes
     */
    private void initList(Point centre, int rayon, int tours)
    {

        Hexagone h1 =  new Hexagone(centre,rayon,0);
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
            this.listHex.add(new Hexagone(p,rayon,0));
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
                    this.listEllipseUtilise.add(this.listEllipse.get(i));
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
                    System.out.println("ici");
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
}
