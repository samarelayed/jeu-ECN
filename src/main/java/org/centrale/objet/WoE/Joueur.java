/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author samar
 */
public class Joueur {

    public String type;
    public String nom;
    Personnage p;
    String mdp;
    int id_joueur;

    public Joueur() {
        this.type = "";
    }
    public void saveToDatabase(Connection connection,int id_joueur,int id){
        try {
            String Query = "insert into joueur(nom_joueur,x,y)values('" + this.nom + "'," + this.p.pos.x + "," + this.p.pos.y +") ";
            PreparedStatement stm = connection.prepareStatement(Query);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseTools.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
    public void getFromDatabase(Connection connection, String gameName, String saveName, int id_sauvegarde) {
        try {
            String Query = "select nom_joueur,x,y from joueur inner join partie using(id_joueur) inner join est_sauvegarde using(id_partie) inner join sauvegarde using(id_sauvegarde) where id_sauvegarde=" + id_sauvegarde;
            PreparedStatement stm = connection.prepareStatement(Query);
            ResultSet rs = stm.executeQuery();
            rs.next();
            this.nom=rs.getString("nom_joueur");
            this.p.pos.x=rs.getInt("x");
            this.p.pos.y=rs.getInt("y");
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseTools.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

    public void choisir() {

        String str = "";
        String str1 = "";
        boolean test = false;
        while (!test) {
            Scanner sc = new Scanner(System.in);
            System.out.println("choisit ton personnage: soit Guerrier soit Archer");
            str = sc.nextLine();
            if (("Guerrier".equalsIgnoreCase(str)) || ("Archer".equalsIgnoreCase(str))) {
                test = true;
            }
        }
        System.out.println("choisit le nom de ton personnage");
        Scanner sc = new Scanner(System.in);
        str1 = sc.nextLine();
        this.nom = str1;
        if ("Guerrier".equalsIgnoreCase(str)) {
            this.p = new Guerrier();

        }
        if ("Archer".equalsIgnoreCase(str)) {
            this.p = new Archer();

        }
        this.p.pos = new Point2D(0, 0);
    }

}
