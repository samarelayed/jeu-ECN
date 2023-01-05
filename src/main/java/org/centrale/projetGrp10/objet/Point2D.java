/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.projetGrp10.objet;

import static java.lang.Math.pow;

/**
 *
 * @author samar
 */
public class Point2D {
    private int x;
    private int y;
    public  Point2D(){
    x=0;
    y=0;
}
    public Point2D(int x,int y){
   this.x=x;
   this.y=y;
    }
    public Point2D(Point2D p){
        this.x=p.x;
        this.y=p.y;
    }
    public void getX(){
        System.out.println("x= "+this.x);
    }
    public void getY(){
        System.out.println("y= "+this.y);
    }
    public int setX(int x){
        this.x=x;
        return x;
    }
    public int setY(int y){
        this.y=y;
        return y;
    }
    public void setPosition(int x,int y){
        this.setY(y);
        this.setX(x);
        
    }
    public void translate(int dx,int dy){
        this.x=this.x+dx;
        this.y=this.y+dy;
    }
    public float distance(Point2D p){
        return(float)pow((pow((this.x-p.x),2)+pow((this.y-p.y),2)),0.5);
    }
    public void Affiche() {
        System.out.println("[" + this.x + ";" + this.y + "]");
    }

    
   
    
}