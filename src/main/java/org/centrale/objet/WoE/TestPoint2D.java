/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package org.centrale.objet.WoE;
import org.centrale.projetGrp10.objet.Point2D;

/**
 *
 * @author samar
 */
public class TestPoint2D {

    public static void main(String[] args) {
        Point2D O = new Point2D();
        Point2D i = new Point2D(1, 0);
        Point2D j = new Point2D(0, 1);
        Point2D P1 = new Point2D(3,4);
        Point2D P2 = new Point2D(P1);
        System.out.println ("O.Affiche()");
        O.getX();
        O.getY();
        i.Affiche();
        j.Affiche();
        P1.Affiche();
        P2.Affiche();
        P2.setX(2);
        P2.setY(2);
        P2.Affiche();
        
        P2.setPosition(5, 5);
        P2.Affiche();
        
        P2.translate(-2, -2);
        P2.Affiche();
        
        O.distance(P1);         
    }
    
}
