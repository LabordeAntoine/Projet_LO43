package com.graphique;

import com.modele.plateau.Case;
import com.modele.ressources.Ressources;


import java.awt.*;

public class Hexagone{

    private Point centre;
    private Case case1 = new Case();
    private double rayon;
    private double angle;
    private int[] x;
    private int[] y;

    public Hexagone(Point centre, double rayon,double angle)
    {
        this.centre = centre;
        this.rayon = rayon;
        this.angle = angle;
        this.x = computeX(this.centre, this.rayon,this.angle);
        this.y = computeY(this.centre,this.rayon,this.angle);
    }

    private int [] computeX(Point centre, double rayon,double angle){

        int[] cX = new int[6];
        for(int i =0; i <6; i++)
        {
            cX[i] = (int)(centre.getX() + rayon * Math.cos(2*i*Math.PI/6+angle));
        }
        return cX;
    }

   private int [] computeY(Point centre, double ray,double angle){

        int[] cY = new int[6];
        for(int i =0; i <6; i++)
        {
            cY[i] = (int)(centre.getY()+ ray * Math.sin(2*i*Math.PI/6+angle));
        }
        return cY;
    }

    public int[] getX(){
        return this.x;
    }
    public int[] getY(){
        return this.y;
    }

    public int getX(int index){
        return this.x[index];
    }
    public int getY(int index){
        return this.y[index];
    }

    public void affiche()
    {
        for(int i = 0; i<6;i++)
            System.out.println("x:"+x[i]+"y:"+y[i]);
    }

    public Ressources getRessources(){return case1.getRessource();}
    public int getNombre (){return case1.getNumero();}
    public int getXCentre(){return (int)centre.getX();}
    public int getYCentre(){return (int)centre.getY();}


}
