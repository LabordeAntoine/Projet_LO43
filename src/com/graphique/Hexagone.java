package com.graphique;

import javax.swing.*;
import java.awt.*;

public class Hexagone extends JPanel {

    public Hexagone() {
        this.setBounds(0,0,800,700);
        this.setPreferredSize(new Dimension(800,700));
        this.setSize(new Dimension(800,700));
        this.setBackground(Color.BLACK);
    }

    public void paintComponent(Graphics g1) {

        super.paintComponent(g1);
        Graphics2D g = (Graphics2D)g1;
        Graphics2D gtest = (Graphics2D) g1;

        g.setColor(Color.CYAN);
        g.setStroke(new BasicStroke(1,BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL));
        g.drawPolygon(positionHexX(0),positionHexY(0),6);
        g.drawPolygon(positionHexX(-200), positionHexY(100), 6);
        g.drawPolygon(positionHexX(-200), positionHexY(300), 6);
        g.drawPolygon(positionHexX(0), positionHexY(400), 6);
        g.drawPolygon(positionHexX(200), positionHexY(300), 6);
        g.drawPolygon(positionHexX(200), positionHexY(100), 6);
        g.drawPolygon(positionHexX(0),positionHexY(200), 6);

       /* gtest.setColor(Color.RED);
        gtest.fillOval(445,245,15,15);*/

        g.setColor(Color.CYAN);
        g.drawString("1", 400, 150);
        g.drawString("2", 200, 250);
        g.drawString("3", 200,450);
        g.drawString("4", 400, 550);
        g.drawString("5", 600, 450);
        g.drawString("6", 600, 250);
        g.drawString("7", 400, 350);
    }


    public int[] positionHexX(int ad){
        int x[] = {450, 350, 250, 350, 450, 550};

        for(int i = 0; i<6;i++)
            x[i]=x[i]+ad;

        return x;
    }

    public int[] positionHexY(int ad){
        int y[] = {250, 250, 150, 50, 50, 150};
        for(int i = 0; i<6;i++)
            y[i]=y[i]+ad;

        return y;
    }



}
