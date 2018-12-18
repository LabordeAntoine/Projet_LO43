package com.graphique;

import javax.swing.*;
import java.awt.*;


public class Plateau extends JPanel {

   private  Hexagone h1 =  new Hexagone(new Point(400,350),50);

    public Plateau() {

        this.setBounds(0,0,800,700);
        this.setPreferredSize(new Dimension(800,700));
        this.setSize(new Dimension(800,700));
        this.setBackground(Color.white);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        g2.setColor(Color.black);
        g.fillPolygon(h1.getX(),h1.getY(),6);

    }
}
