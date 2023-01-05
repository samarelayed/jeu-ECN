/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author samar
 */

public class Epee extends Objet implements Utilisable{
int score;
    public void genererEpee(){
        this.nom="arme";
        Random alea = new Random();
        this.score= alea.nextInt(1,20);
    }
    
            /**
     *
     * @param connection
     * @param ID_sauvegarde
     * @param id_inventaire
     * @param i
     */
    @Override
    public void saveToDatabase(Connection connection,int ID_sauvegarde,int id_inventaire,int i) {
        try{
            String Query = "insert into Epee(nom_epee,score,x,y)values('" + this.nom+i + "'," + this.score+',' + this.pos.x+',' + this.pos.y + ") ";
            PreparedStatement stm = connection.prepareStatement(Query);
            stm.executeUpdate();
           String Query2="insert into contient_epee(nom_epee,id_inventaire)values('"+this.nom+i+"',"+id_inventaire+") " ;
           PreparedStatement stm2=connection.prepareStatement(Query2);
           stm2.executeUpdate();
           System.out.println("Epee nom:"+this.nom+i+"a la position:["+this.pos.x+","+this.pos.y+']');
        }
      catch (SQLException ex) {
            Logger.getLogger(DatabaseTools.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    }

    /**
     *
     * @param connection
     * @param id
     * @param id_inventaire
     */
    @Override
    public void getFromDatabase(Connection connection, Integer id,int id_inventaire, String nom_epee) {
        try{
        String Query = "select nom_epee,score,x,y from Epee inner join contient_epee using(nom_epee) inner join inventaire using(id_inventaire) inner join contient_inventaire using(id_inventaire) where id_sauvegarde= " + id+"and id_inventaire="+id_inventaire+" and nom_epee='"+nom_epee+"'";
        PreparedStatement stm = connection.prepareStatement(Query);
        ResultSet rs = stm.executeQuery();
        rs.next();
        
        this.nom=rs.getString("nom_epee");
        this.pos.x=rs.getInt("x");
        this.pos.y=rs.getInt("y");
        this.score=rs.getInt("score");
        System.out.println("nom epee est: "+this.nom+ " x: "+this.pos.x+" y: "+this.pos.y+" score: "+this.score);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseTools.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    }
    
}
