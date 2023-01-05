/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author samar
 */
public class Lapin extends Monstre {

    public Lapin(int pV, int dA, int pPar, int paAtt, int paPar, Point2D p) {
        super(pV, dA, pPar, paAtt, paPar, p);
    }

    public Lapin(Lapin m) {
        super(m);
    }

    public Lapin() {
        this.degAtt = 50;
        this.ptVie = 100;
        this.ptPar = 80;
        this.pageAtt = 0;
        this.pagePar = 0;
        this.pos = new Point2D(-50, -10);
    }

    /**
     *
     * @param connection
     * @param ID_sauvegarde
     */
    @Override
    public void saveToDatabase(Connection connection, int ID_sauvegarde, int i) {
        try {
            String Query = "insert into monstre(nom_monstre,x,y,Nom_metier)values('lap" + i + "'," + this.pos.x + "," + this.pos.y + ",'Lapin'" + ") ";
            PreparedStatement stm = connection.prepareStatement(Query);
            stm.executeUpdate();
            String Query1 = "insert into contient_monstre(nom_monstre,ID_sauvegarde) values('lap" + i + "'," + ID_sauvegarde + ") ";
            PreparedStatement stm1 = connection.prepareStatement(Query1);
            stm1.executeUpdate();
            System.out.println("Lapin nom:lap" + i);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseTools.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

    /**
     *
     * @param connection
     * @param id
     */
    @Override
    public void getFromDatabase(Connection connection, Integer id, String nom_monstre) {
        try {

            String Query = "select x,y from monstre inner join contient_monstre using(nom_monstre) where id_sauvegarde= " + id + "and nom_type='Lapin' and nom_monstre='" + nom_monstre + "'";
            PreparedStatement stm = connection.prepareStatement(Query);
            ResultSet rs = stm.executeQuery();
            rs.next();
            while (rs.next()) {
                this.pos.x = rs.getInt("x");
                this.pos.y = rs.getInt("y");
                System.out.println("nom_monstre: " + nom_monstre + " x: " + this.pos.x + " y: " + this.pos.y);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseTools.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

}
