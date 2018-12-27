package com.graphique.fenetre_principale;

import com.modele.plateau.Case;
import com.modele.ressources.Ressources;


import java.awt.*;
import java.awt.geom.Point2D;

public class Hexagone extends Polygon {

    private Point2D.Double centre;
    private Case case1 = new Case();
    private double rayon;
    private double angle;
    private double [] x;
    private double [] y;

    /**
     * Cette classe est ce qui permet a notre plateau d'avoir des hexagones, elle represente un seul et unique hexagone
     * Elle contient un liste de tout les Points de tout les sommets
     * @param centre Le centre de l'hexagone
     * @param rayon Le rayon ou la taille de l'hexagone
     * @param angle Un angle si on veut le tourner
     */
    public Hexagone(Point2D.Double centre, double rayon,double angle)
    {
        super();
        this.centre = centre;
        this.rayon = rayon;
        this.angle = angle;
        this.x = computeX();
        this.y = computeY();
        this.npoints = 6;
        this.xpoints = this.getX();
        this.ypoints = this.getY();
    }


    /**
     * Constructeur sans le parametre "angle", l'angle est a 0 par defaut
     * @param centre
     * @param rayon
     */
    public Hexagone(Point2D.Double centre, double rayon) {
        this(centre, rayon, 0);
    }

    private double [] computeX(){

        double [] cX = new double[6];
        for(int i =0; i <6; i++)
        {
            cX[i] = ((centre.getX() + rayon * Math.cos(2*i*Math.PI/6+angle)));
        }
        return cX;
    }

   private double [] computeY(){

       double[] cY = new double[6];
        for(int i =0; i <6; i++)
        {
            cY[i] = ((centre.getY() + rayon * Math.sin(2*i*Math.PI/6+angle)));
        }
        return cY;
    }

    public int[] getX(){
        int [] resultat = new int[this.x.length];
        for (int i = 0; i < this.x.length; i++){
            resultat[i] = (int)Math.round(this.x[i]);
        }
        return resultat;
    }
    public int[] getY(){
        int [] resultat = new int[this.y.length];
        for (int i = 0; i < this.y.length; i++){
            resultat[i] = (int)Math.round(this.y[i]);
        }
        return resultat;
    }

    public Point2D.Double[] getPoints(){
        Point2D.Double listePoints[] = new Point2D.Double[6];
        for(int i = 0; i < 6; i++)
            listePoints[i] = new Point2D.Double(this.x[i], this.y[i]);
        return listePoints;
    }

    public double getX(int index){
        return this.x[index];
    }
    public double getY(int index){
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
    public double getXCentre(){return (centre.getX());}
    public double getYCentre(){return (centre.getY());}


}
