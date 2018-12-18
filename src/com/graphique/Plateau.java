package com.graphique;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;


public class Plateau extends JPanel implements MouseListener {

    private ArrayList<Hexagone> listHex = new ArrayList<>();
    private ArrayList<Ellipse2D.Double> listEllipse = new ArrayList<>();

    public Plateau() {

        this.setBounds(0,0,800,700);
        this.setPreferredSize(new Dimension(800,700));
        this.setSize(new Dimension(800,700));
        this.setBackground(Color.black);
        initList(new Point(400,300), 100);
        addMouseListener(this);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        for(Hexagone h : listHex){
            g2.setColor(Color.green);
            g2.drawPolygon(h.getX(),h.getY(),6);
        }

        g2.setColor(Color.red);
        for (Ellipse2D.Double e : this.listEllipse){
            g2.fill(e);
        }

    }

    private void initList(Point centre, int rayon)
    {

          Hexagone h1 =  new Hexagone(centre,rayon,0);
          int []x = h1.getX();
          int []y = h1.getY();

        for(int i = 0; i<6;i++)
        {
            int x_new = (int)(x[i] - 100*Math.cos(2*Math.PI/6+(Math.PI+i*Math.PI/3)));
            int y_new = (int)(y[i] - 100*Math.sin(2*Math.PI/6+(Math.PI+i*Math.PI/3)));
            Hexagone h =  new Hexagone(new Point(x_new,y_new),100,Math.PI+i*Math.PI/3);
            listHex.add(h);
        }

        //Ellipses
        for(Hexagone h : listHex){
            for(int i = 0; i<6 ; i++) {
                this.listEllipse.add(new Ellipse2D.Double(h.getX(i) - 15, h.getY(i) - 15, 30, 30));
            }
        }


    }

    @Override
    public void mouseClicked(MouseEvent e) {
        for(int i = 0; i < this.listEllipse.size(); i++){
            if(this.listEllipse.get(i).contains(e.getX(), e.getY())){
                this.listEllipse.remove(i);
                repaint();
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
