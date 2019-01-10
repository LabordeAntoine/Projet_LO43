package com.graphique.fenetre_principale.plateau;

import com.modele.plateau.Case;
import com.modele.ressources.Ressources;
import java.awt.*;
import java.awt.geom.Point2D;

public class Hexagone extends Polygon {

    private Point2D.Double centre;
    private Case uneCase = new Case();
    private double rayon;
    private double angle;
    private double [] x;
    private double [] y;

    /**
     * Cette classe est ce qui permet a notre plateau d'avoir des hexagones, elle represente un seul et unique hexagone
     * Elle contient un liste de tout les Points de tout les sommets, elle a donc
     *  - toutes les coordonnées en double (car la classe Point2D fonctionne avec des doubles)
     *  - toutes les coordonnées en int (car la classe Polygon fonctionne avec des int)
     * @param centre Le centre de l'hexagone
     * @param rayon Le rayon ou la taille de l'hexagone
     * @param angle Un angle si on veut le tourner
     */
    Hexagone(Point2D.Double centre, double rayon, double angle) {
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
     * Constructeur sans le parametre "angle", l'angle est à 0 par defaut
     * @param centre
     * @param rayon
     */
    Hexagone(Point2D.Double centre, double rayon) { this(centre, rayon, 0); }

    //double

    /**
     * Sert a calculer toutes les coordonnées de l'hexagone (pour x)
     * @return Les coordonnées x (en double)
     */
    private double [] computeX(){
        double [] cX = new double[6];
        for(int i =0; i <6; i++)
            cX[i] = ((centre.getX() + rayon * Math.cos(2*i*Math.PI/6+angle)));
        return cX;
    }

    /**
     * Sert a calculer toutes les coordonnées de l'hexagone (pour y)
     * @return Les coordonnées y (en double)
     */
    private double [] computeY(){
        double[] cY = new double[6];
        for(int i =0; i <6; i++)
            cY[i] = ((centre.getY() + rayon * Math.sin(2*i*Math.PI/6+angle)));
        return cY;
    }

    /**
     * Transforme toutes les coordonnées de double en int
     * @return Les coordonnées x (en int)
     */
    int[] getX(){
        int [] resultat = new int[this.x.length];
        for (int i = 0; i < this.x.length; i++)
            resultat[i] = (int)Math.round(this.x[i]);
        return resultat;
    }

    /**
     * Transforme toutes les coordonnées de double en int
     * @return Les coordonnées y (en int)
     */
    int[] getY(){
        int [] resultat = new int[this.y.length];
        for (int i = 0; i < this.y.length; i++)
            resultat[i] = (int)Math.round(this.y[i]);
        return resultat;
    }

    /**
     * Cree une liste de Point2D a partir des coordonnées doubles
     * @return Les coordonnées y (en int)
     */
    Point2D.Double[] getPoints(){
        Point2D.Double listePoints[] = new Point2D.Double[6];
        for(int i = 0; i < 6; i++)
            listePoints[i] = new Point2D.Double(this.x[i], this.y[i]);
        return listePoints;
    }
    
    public void resetCase() {
    	this.uneCase = new Case();
    }

    double getX(int index){ return this.x[index]; }
    double getY(int index){ return this.y[index]; }
    double getRayon() { return rayon; }
    public Ressources getRessources(){return uneCase.getRessource();}
    int getNombre(){return uneCase.getNumero();}
    double getXCentre(){return (centre.getX());}
    double getYCentre(){return (centre.getY());}


}
