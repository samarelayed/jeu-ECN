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
public abstract class Creature implements Deplacable{
    public int ptVie;
    public int degAtt;
    public int ptPar;
    public int pageAtt;
    public int pagePar;
    
    public Point2D  pos= new Point2D() ;
    public Creature(){
            this.ptVie=100;
            this.degAtt=0;
            this.ptPar=0;
            this.pageAtt=0;
            this.pagePar=0;
            this.pos=new Point2D();
        
    }
    public Creature(Creature c){
            this.ptVie=c.ptVie;
            this.degAtt=c.degAtt;
            this.ptPar=c.ptPar;
            this.pageAtt=c.pageAtt;
            this.pagePar=c.pagePar;
            this.pos=new Point2D(c.pos);
        }
    public Creature(int pV,int dA,int pPar,int paAtt,int paPar,Point2D p ){
            
            this.ptVie=pV;
            this.degAtt=dA;
            this.ptPar=pPar;
            this.pageAtt=paAtt;
            this.ptPar=paPar;
            this.pos= new Point2D (p);
        }
    
    public abstract void Affiche();

    public int getPtVie() {
        return ptVie;
    }

    public int getDegAtt() {
        return degAtt;
    }

    public int getPtPar() {
        return ptPar;
    }

    public int getPageAtt() {
        return pageAtt;
    }

    public int getPagePar() {
        return pagePar;
    }

    public Point2D getPos() {
        return pos;
    }

    public void setPtVie(int ptVie) {
        this.ptVie = ptVie;
    }

    public void setDegAtt(int degAtt) {
        this.degAtt = degAtt;
    }

    public void setPtPar(int ptPar) {
        this.ptPar = ptPar;
    }

    public void setPageAtt(int pageAtt) {
        this.pageAtt = pageAtt;
    }

    public void setPagePar(int pagePar) {
        this.pagePar = pagePar;
    }

    public void setPos(Point2D pos) {
        this.pos = pos;
    }
    @Override
        public void deplacer(World w){
        Point2D posult=this.pos;
        Random alea= new Random();
        try{
            int x=alea.nextInt(3);
            int y=alea.nextInt(3);
             while ((x==1) || (y==1)){
            x=alea.nextInt(3);
            y=alea.nextInt(3);  
            }
            if (w.Mat[x][y]!=1){
                this.pos.setPosition(this.pos.x+x-1,this.pos.y+y-1);
            }
            w.Mat[posult.x][posult.y]=0;
              
                }
        catch(IndexOutOfBoundsException e){
            System.out.println("limite du monde");
        }
            
            
    };
        public abstract void saveToDatabase(Connection connection,int ID_sauvegarde,int i);
        public abstract void getFromDatabase(Connection connection, Integer id,String nom_creature);
}
    

