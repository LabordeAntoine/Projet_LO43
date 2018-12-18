package com.graphique;

import javax.swing.*;
import java.awt.*;


public class Plateau extends JPanel {

    private  Hexagone h1 =  new Hexagone(new Point(400,300),100,0);
    private  Hexagone h2 =  new Hexagone(new Point(400,127),100,0);
    private  Hexagone h3 =  new Hexagone(new Point(250,213),100,0);
    private  Hexagone h4 =  new Hexagone(new Point(550,213),100,0);


    public Plateau() {

        this.setBounds(0,0,800,700);
        this.setPreferredSize(new Dimension(800,700));
        this.setSize(new Dimension(800,700));
        this.setBackground(Color.black);
        h1.affiche();
        System.out.println("-------------------");
        h2.affiche();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        g2.setColor(Color.green);
        g2.fillPolygon(h1.getX(),h1.getY(),6);
        g2.drawPolygon(h2.getX(),h2.getY(),6);
        g2.drawPolygon(h3.getX(),h3.getY(),6);
        g2.drawPolygon(h4.getX(),h4.getY(),6);

        g.setColor(Color.red);
        for(int i = 0; i<6 ; i++)
        g2.fillOval(h1.getX(i)-15,h1.getY(i)-15, 30,30);
        for(int i = 0; i<6 ; i++)
            g2.fillOval(h2.getX(i)-15,h2.getY(i)-15, 30,30);
        for(int i = 0; i<6 ; i++)
            g2.fillOval(h3.getX(i)-15,h3.getY(i)-15, 30,30);
        for(int i = 0; i<6 ; i++)
            g2.fillOval(h4.getX(i)-15,h4.getY(i)-15, 30,30);

        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(5));
        g2.drawLine(h1.getX(1),h1.getY(1), h1.getX(2),h1.getY(2));


    }
}
