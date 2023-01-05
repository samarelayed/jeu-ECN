/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;

import java.sql.Connection;
import java.util.Random;


/**
 *
 * @author samar
 */
public abstract class Objet implements Utilisable{
    
    Point2D pos=new Point2D();
    String nom;
    int score;
    

    public Objet( Point2D pos,String nom,int score) {
       
        this.pos = pos;
        this.nom=nom;
        this.score=score;
    }

    public Objet() {
        
    }

    public Point2D getPos() {
        return pos;
    }

    public void setPos(Point2D pos) {
        this.pos = pos;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
     Objet[][] Mat=new Objet[World.tailleW[0]][World.tailleW[1]];
    
     
    

    public void genererObjet() {
        int i;
        Random alea = new Random();
        int j;

        for (i = 0; i < World.tailleW[0]; i++) {
            for (j = 0; j <World.tailleW[1]; j++) {
                int x=alea.nextInt(4);
                switch(x){
                    case 0 :{
                         PotionSoin ps=new PotionSoin();
                        ps.genererPotionSoin(); 
                        Mat[i][j]=ps;
                        break;
                    }
                    case 1:{
                        Epee ep=new Epee();
                        ep.genererEpee();
                        Mat[i][j]=ep;
                        break;
                    }
                    case 2:{
                         Nourriture N=new Nourriture();
                        N.genererNourriture(i, j);
                        Mat[i][j]=N;
                        break;
                    }
                    case 3:{
                        Mat[i][j]=null;
                        break;
                    }
                    default : {
                        break;
                    }
                }
            }
        }
    }
    @Override
    public void utilisable(){
        
    }
    public abstract void saveToDatabase(Connection connection,int ID_sauvegarde,int id_inventaire,int i);
    public abstract void getFromDatabase(Connection connection, Integer id,int id_inventaire,String nom_objet);
    
}

