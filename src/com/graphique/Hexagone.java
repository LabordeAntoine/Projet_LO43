package com.graphique;

import javax.swing.*;
import java.awt.*;

public class Hexagone extends JPanel {



    public Hexagone() {
        this.setBounds(0,0,800,700);
        //this.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.black));
        this.setPreferredSize(new Dimension(800,700));
        this.setSize(new Dimension(800,700));
        this.setBackground(Color.BLUE);

    }


    public void paintComponent(Graphics g1) {

        super.paintComponent(g1);
        Graphics2D g = (Graphics2D)g1;
        GradientPaint gp;
        int x[] = {450, 350, 250, 350, 450, 550};
        int y[] = {250, 250, 150, 50, 50, 150};
        g.drawRect(0,0,800,700);


        g.setColor(Color.red);
        g.fillPolygon(x,y,6);
        g.setColor(Color.BLACK);
        g.drawString("1", 400, 150);

        int x1[] = {250, 150, 50, 150, 250, 350};
        int y1[] = {350, 350, 250, 150, 150, 250};
        g.setColor(Color.CYAN);
        g.fillPolygon(x1, y1, 6);
        g.setColor(Color.BLACK);
        g.drawString("2", 200, 250);

        int x2[] = {250,150, 50, 150, 250, 350};
        int y2[] = {550, 550, 450, 350, 350, 450};

        g.setColor(Color.YELLOW);
        g.fillPolygon(x2, y2, 6);
        g.setColor(Color.BLACK);
        g.drawString("3", 200,450);



        int x3[] = {450, 350, 250, 350, 450, 550};
        int y3[] = {650,650, 550, 450, 450, 550};

        g.setColor(Color.magenta);
        g.fillPolygon(x3, y3, 6);
        g.setColor(Color.black);
        g.drawString("4", 400, 550);

        int x4[] = {650, 550, 450, 550, 650, 750};
        int y4[] = {550, 550, 450, 350, 350, 450};
        g.setColor(Color.green);
        g.fillPolygon(x4, y4, 6);
        g.setColor(Color.BLACK);
        g.drawString("5", 600, 450);

        int x5[] = {650, 550, 450, 550, 650, 750};
        int y5[] = {350, 350, 250, 150, 150, 250};

        g.setColor(Color.yellow);
        g.fillPolygon(x5, y5, 6);
        g.setColor(Color.BLACK);
        g.drawString("6", 600, 250);

        g.setColor(Color.RED);
        g.drawString("7", 400, 350);

        /*try{
            Image img = ImageIO.read(new File("fiches_Epi_de_ble.jpg"));
            g.drawImage(img,0,0, this.getWidth(),this.getHeight(), this);
        }catch(IOException e) {
            e.printStackTrace();
        }*/


    }



}
