package com.graphique.fenetre_principale;

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

    /**
     * Cette classe est ce qui permet a notre plateau d'avoir des hexagones, elle represente un seul et unique hexagone
     * Elle contient un liste de tout les Points de tout les sommets
     * @param centre Le centre de l'hexagone
     * @param rayon Le rayon ou la taille de l'hexagone
     * @param angle Un angle si on veut le tourner
     */
    public Hexagone(Point centre, double rayon,double angle)
    {
        this.centre = centre;
        this.rayon = rayon;
        this.angle = angle;
        this.x = computeX();
        this.y = computeY();
    }

    /**
     * Constructeur sans le parametre "angle", l'angle est a 0 par defaut
     * @param centre
     * @param rayon
     */
    public Hexagone(Point centre, double rayon) {
        this(centre, rayon, 0);
    }

    private int [] computeX(){

        int[] cX = new int[6];
        for(int i =0; i <6; i++)
        {
            cX[i] = (int)Math.round((centre.getX() + rayon * Math.cos(2*i*Math.PI/6+angle)));
        }
        return cX;
    }

   private int [] computeY(){

        int[] cY = new int[6];
        for(int i =0; i <6; i++)
        {
            cY[i] = (int)Math.round((centre.getY() + rayon * Math.sin(2*i*Math.PI/6+angle)));
        }
        return cY;
    }

    public int[] getX(){
        return this.x;
    }
    public int[] getY(){
        return this.y;
    }

    public Point[] getPoints(){
        Point listePoints[] = new Point[6];
        for(int i = 0; i < 6; i++)
            listePoints[i] = new Point(this.x[i], this.y[i]);
        return listePoints;
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

    public double getRayon() {
        return rayon;
    }

    public Ressources getRessources(){return case1.getRessource();}
    public int getNombre (){return case1.getNumero();}
    public int getXCentre(){return (int)Math.round(centre.getX());}
    public int getYCentre(){return (int)Math.round(centre.getY());}


}
