package com.graphique;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class pointGraph extends JPanel implements MouseListener {

    private Color color = new Color(128,128,128);
    public pointGraph()
    {
        this.setBounds(0,0,800,700);
        this.setPreferredSize(new Dimension(800,700));
        this.setSize(new Dimension(800,700));
        this.setOpaque(false);
        addMouseListener(this);

    }

    public void paintComponent(Graphics g1) {

        super.paintComponent(g1);
        Graphics2D g = (Graphics2D)g1;

        g.setColor(color);

        g.fillOval(440,40,20,20);
        g.fillOval(340,40,20,20);
        g.fillOval(440,240,20,20);
        g.fillOval(340,240,20,20);
        g.fillOval(240,140,20,20);
        g.fillOval(540,140,20,20);

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        this.color = new Color(255,0,0);
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e)
    {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
