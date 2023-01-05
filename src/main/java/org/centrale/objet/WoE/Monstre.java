/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;
import java.util.Random;

/**
 * classe monstre permet de créer et de modifier les caractéristiques d'un monstre
 * @author samar
 */
public abstract class Monstre extends Creature{

  
 
    /**
     *
     * @param pV points de vie
     * @param dA degré d'attaque
     * @param pPar points de parade
     * @param paAtt pourcentage d'attaque
     * @param paPar pourcentage de parade
     * @param p position 
     */
    public Monstre(int pV,int dA,int pPar,int paAtt,int paPar,Point2D p){
        super(pV,dA,pPar,paAtt,paPar,p);
    }

    /**
     * prmet d'attribuer les caractéristiques d'un monstre à partir d'un autre existant
     * @param m monste 
     */
    public Monstre(Monstre m){
        super(m);
    }

    /**
     *permet d'attribuer des caractéristiques par défaut à un monstre
     */
    public  Monstre(){
        this.degAtt=50;
        this.ptVie=100;
        this.ptPar=80;
        this.pageAtt=0;
        this.pagePar=0;
        this.pos= new Point2D(-50,-10);
    }

    /**
     * permet de déplacer un monstre aléatoirement vers une case adjacentes
     */
    

    /**
     * peremt d'afficher les caractéristiques d'un monstre
     */
    @Override
     public void Affiche(){
            System.out.println(" ptVie: "+ this.ptVie);
            System.out.println("degAtt: "+this.degAtt);
            System.out.println("point parade"+this.ptPar);
            System.out.println("pourcentage d'attaque"+this.pageAtt);
            System.out.println("pourcentage de parade "+this.pagePar);
            this.pos.Affiche();
        }
}
