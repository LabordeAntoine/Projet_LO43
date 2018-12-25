package com.graphique.fenetre_principale;

import java.awt.*;

public class CalculPoint {

    public static Point mutiplier(Point p, int n){ return new Point((int)Math.round(p.getX()) * n, (int)Math.round(p.getY() * n)); }

    public static Point divise(Point p, int n){ return new Point((int)Math.round(p.getX()) / n, (int)Math.round(p.getY()) / n); }

    public static Point additionner(Point  p1, Point p2){ return new Point((int)Math.round(p1.getX()) + (int)Math.round(p2.getX()), (int)Math.round(p1.getY()) + (int)Math.round(p2.getY())); }

    public static Point soustraire(Point p1, Point p2){ return new Point((int)Math.round(p1.getX()) - (int)Math.round(p2.getX()), (int)Math.round(p1.getY()) - (int)Math.round(p2.getY())); }

    public static Point[] split(Point p1, Point p2, int n){

        int tailleTableau = n-1;

        if (n > 0) {
            Point listePoint[] = new Point[tailleTableau];
            Point temp = soustraire(p1, p2);
            for (int i = 0; i < tailleTableau; i++){
                listePoint[i] = additionner(p1, mutiplier(divise(temp, n), -(i+1)));
            }
            return listePoint;
        }
        return new Point[0];
    }

}
