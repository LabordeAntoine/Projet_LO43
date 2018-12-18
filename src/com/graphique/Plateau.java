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
          Point centre = new Point(400,300);
          Hexagone h1 =  new Hexagone(centre,100,0);
          int []x = h1.getX();
          int []y = h1.getY();

        for(int i = 0; i<6;i++)
        {
            int x_new = (int)(x[i] - 100*Math.cos(2*Math.PI/6+(Math.PI+i*Math.PI/3)));
            int y_new = (int)(y[i] - 100*Math.sin(2*Math.PI/6+(Math.PI+i*Math.PI/3)));
            Hexagone h =  new Hexagone(new Point(x_new,y_new),100,Math.PI+i*Math.PI/3);
            listHex.add(h);
        }


    }
}
