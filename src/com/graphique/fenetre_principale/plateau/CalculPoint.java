package com.graphique.fenetre_principale.plateau;

import java.awt.geom.Point2D;

public class CalculPoint {

    public static Point2D.Double mutiplier(Point2D.Double p, int n){ return new Point2D.Double((p.getX()) * n, (p.getY() * n));  }

    public static Point2D.Double divise(Point2D.Double p, int n){ return new Point2D.Double((p.getX()) / n, (p.getY()) / n); }

    public static Point2D.Double additionner(Point2D.Double  p1, Point2D.Double p2){ return new Point2D.Double((p1.getX()) + (p2.getX()), (p1.getY()) + (p2.getY())); }

    public static Point2D.Double soustraire(Point2D.Double p1, Point2D.Double p2){ return new Point2D.Double((p1.getX()) - (p2.getX()), (p1.getY()) - (p2.getY())); }

    public static Point2D.Double[] split(Point2D.Double p1, Point2D.Double p2, int n){

        int tailleTableau = n-1;

        if (n > 0) {
            Point2D.Double listePoint[] = new Point2D.Double[tailleTableau];
            Point2D.Double temp = soustraire(p1, p2);
            for (int i = 0; i < tailleTableau; i++){
                listePoint[i] = additionner(p1, mutiplier(divise(temp, n), -(i+1)));
            }
            return listePoint;
        }
        return new Point2D.Double[0];
    }

}
