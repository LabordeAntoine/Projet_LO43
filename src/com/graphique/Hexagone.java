package com.graphique;

import java.awt.*;

public class Hexagone{

    private Point centre;
    private double rayon;
    private int[] x;
    private int[] y;

    public Hexagone(Point centre, double rayon)
    {
        this.centre = centre;
        this.rayon = rayon;
        this.x = computeX(this.centre, this.rayon);
        this.y = computeY(this.centre,this.rayon);
    }

    public int [] computeX(Point centre, double rayon){

        int[] cX = new int[6];
        for(int i =0; i <6; i++)
        {
            cX[i] = (int)(centre.getX() + rayon * Math.cos(2*i*Math.PI/6));
        }
        return cX;
    }

    public int [] computeY(Point centre, double ray){

        int[] cY = new int[6];
        for(int i =0; i <6; i++)
        {
            cY[i] = (int)(centre.getX()+ ray * Math.sin(2*i*Math.PI/6));
        }
        return cY;
    }

    public int[] getX(){
        return this.x;
    }
    public int[] getY(){
        return this.y;
    }


}
