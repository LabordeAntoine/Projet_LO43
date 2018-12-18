package com.graphique;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class Plateau extends JPanel {

    private ArrayList<Hexagone> listHex = new ArrayList<>();

    public Plateau() {

        this.setBounds(0,0,800,700);
        this.setPreferredSize(new Dimension(800,700));
        this.setSize(new Dimension(800,700));
        this.setBackground(Color.black);
        initList();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        for(Hexagone h : listHex){
            g2.setColor(Color.green);
            g2.drawPolygon(h.getX(),h.getY(),6);
            for(int i = 0; i<6 ; i++) {
                g2.setColor(Color.red);
                g2.fillOval(h.getX(i) - 15, h.getY(i) - 15, 30, 30);
            }
        }

    }

    private void initList()
    {
          Hexagone h1 =  new Hexagone(new Point(400,300),100,0);
        Hexagone h2 =  new Hexagone(new Point(400,127),100,0);
         Hexagone h3 =  new Hexagone(new Point(250,213),100,0);
        Hexagone h4 =  new Hexagone(new Point(550,213),100,0);
        listHex.add(h1);
        listHex.add(h2);
        listHex.add(h3);
        listHex.add(h4);
    }
}
