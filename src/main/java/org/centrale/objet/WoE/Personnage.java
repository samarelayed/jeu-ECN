/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;

import java.util.Random;


/**
 *class Personnage permet de créer et de définir las caractéristiques d'un personnage
 *@author samar
 *
 * 
 */
public abstract class Personnage extends Creature {

    /**
     * le nom du personnage
     */
    public String nom;


    /**
     * distance d'attaque maximale
     */
    public int distAttMax;
    
    /**
     *permet d'attribuer des caractéristiques à un personnage
     * @param n le nom du personnage
     * @param pV les points de vie du personnage
     * @param dA degré d'attaque
     * @param pPar pourcentage de parade
     * @param paAtt pourcentage d'attaque
     * @param paPar pourcentage de parade 
     * @param dMax distance maximale d'attaque
     * @param p position du personnage
     */
    public Personnage(String n,int pV,int dA,int pPar,int paAtt,int paPar,int dMax,Point2D p ){
        super(pV,dA,pPar,paAtt,paPar,p );    
        this.nom=n;
        this.distAttMax=dMax;
        }

    /**
     *permet de définir un personnage à partir d'un existant
     * @param p personnage
     */
    public Personnage(Personnage p){
            super(p);
            this.nom=p.nom;
            this.distAttMax=p.distAttMax;
            
        }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDistAttMax(int distAttMax) {
        this.distAttMax = distAttMax;
    }

    public String getNom() {
        return nom;
    }

    public int getDistAttMax() {
        return distAttMax;
    }

    /**
     * personnage par défaut
     */
    public Personnage(){
            
            this.ptVie=100;
            this.degAtt=0;
            this.ptPar=0;
            this.pageAtt=0;
            this.pagePar=0;
            this.distAttMax=20;
            this.pos=new Point2D();
        }
    

    /**
     * permet d'afficher les caractéristique d'un personnage
     */
    @Override
    public void Affiche(){
            System.out.println("nom_personne: "+this.nom);
            System.out.println(" ptVie: "+ this.ptVie);
            System.out.println("degAtt: "+this.degAtt);
            System.out.println("point parade"+this.ptPar);
            System.out.println("pourcentage d'attaque"+this.pageAtt);
            System.out.println("pourcentage de parade "+this.pagePar);
            System.out.println("distance max"+this.distAttMax);
            this.pos.Affiche();
        }
        public void deplacer(World w){
        Point2D posult=this.pos;
        Random alea= new Random();
            int x=alea.nextInt(3);
            int y=alea.nextInt(3);
             while ((x==1) || (y==1)){
            x=alea.nextInt(3);
            y=alea.nextInt(3);  
            }
            if (w.Mat[x][y]!=1){
                this.pos.setPosition(this.pos.x+x-1,this.pos.y+x-1);
            }
            w.Mat[posult.x][posult.y]=0;
            
    };

    
        
}
