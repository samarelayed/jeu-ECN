/* --------------------------------------------------------------------------------
 * WoE Tools
 * 
 * Ecole Centrale Nantes - Septembre 2022
 * Equipe pédagogique Informatique et Mathématiques
 * JY Martin
 * -------------------------------------------------------------------------------- */
package org.centrale.objet.WoE;

import java.sql.*;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.centrale.objet.WoE.World;

/**
 *
 * @author ECN
 */
public class DatabaseTools {

    private String login;
    private String password;
    private String url;
    private Connection connection;

    /**
     * Load infos
     */
    public DatabaseTools() {
        try {
            // Get Properties file
            String path = DatabaseTools.class.getPackage().getName() + ".database";
            ResourceBundle properties = ResourceBundle.getBundle(path);

            // USE config parameters
            login = properties.getString("login");
            password = properties.getString("password");
            String server = properties.getString("server");
            String database = properties.getString("database");
            url = "jdbc:postgresql://" + server + "/" + database;

            // Mount driver
            Driver driver = DriverManager.getDriver(url);
            if (driver == null) {
                Class.forName("org.postgresql.Driver");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DatabaseTools.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.connection = null;
    }

    /**
     * Get connection to the database
     */
    public void connect() {
        if (this.connection == null) {
            try {
                this.connection = DriverManager.getConnection(url, login, password);
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseTools.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Disconnect from database
     */
    public void disconnect() {
        if (this.connection != null) {
            try {
                this.connection.close();
                this.connection = null;
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseTools.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * get Player ID
     *
     * @param nomJoueur
     * @param password
     * @return
     */
    public Integer getPlayerID(String nomJoueur, String password) {
        try {
            String Query = "select ID_joueur from joueur where (nom_joueur='" + nomJoueur + "') and (mdp='" + password + "')";
            PreparedStatement stm = connection.prepareStatement(Query);
            ResultSet rs = stm.executeQuery();
            rs.next();
            if (rs.next()) {
                return rs.getInt("ID_joueur");
            } else {
                return null;
            }

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseTools.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    /**
     * save world to database
     *
     * @param idJoueur
     * @param nomPartie
     * @param nomSauvegarde
     * @param monde
     */
    public void saveWorld(Integer idJoueur, String nomPartie, String nomSauvegarde, World monde,Joueur J) {

        try {
            if (nomSauvegarde == null) {
                nomSauvegarde = "sauvegarde rapide";
            }
            String Query = "insert into sauvegarde(nom_sauvegarde) values ('" + nomSauvegarde + "')";
            PreparedStatement stm = connection.prepareStatement(Query);
            stm.executeUpdate();
            String Query1 = "insert into Partie(nom_partie,ID_joueur) values ('" + nomPartie + "'," + idJoueur + " )";
            PreparedStatement stm1 = connection.prepareStatement(Query1);
            stm1.executeUpdate();
            String Query5 = "select id_partie from Partie where (ID_joueur='" + idJoueur + "') and (nom_partie='" + nomPartie + "')";
            PreparedStatement stm5 = connection.prepareStatement(Query5);
            ResultSet rs = stm5.executeQuery();
            String Query8 = "select max(id_sauvegarde)as max_id from sauvegarde ";
            PreparedStatement stm8 = connection.prepareStatement(Query8);
            ResultSet rs8 = stm8.executeQuery();
            rs8.next();
            rs.next();
            System.out.println("id_sauvegarde"+ rs8.getInt("max_id"));
            String Query2 = "insert into est_sauvegarde(ID_partie,ID_sauvegarde) values ((" + rs.getInt("id_partie") + ")," + rs8.getInt("max_id") + ")";
            PreparedStatement stm2 = connection.prepareStatement(Query2);
            stm2.executeUpdate();
            monde.saveToDatabase(connection, nomPartie, nomSauvegarde, rs8.getInt("max_id"));
            J.saveToDatabase(connection,idJoueur, rs8.getInt("max_id"));
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseTools.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    /**
     * get world from database
     *
     * @param idJoueur
     * @param nomPartie
     * @param nomSauvegarde
     * @param monde
     */
    public void readWorld(Integer idJoueur, String nomPartie, String nomSauvegarde, World monde,Joueur J) {
        try {
            if (nomSauvegarde == null) {
                nomSauvegarde = "sauvegarde rapide";
            }
            String Query1 = "select ID_partie from Partie where ID_joueur='" + idJoueur + "' and nom_partie='" + nomPartie + "'";
            PreparedStatement stm1 = connection.prepareStatement(Query1);
            ResultSet rs1 = stm1.executeQuery();
            rs1.next();
            String Query = "select ID_sauvegarde from est_sauvegarde where ID_partie=" + rs1.getInt("ID_partie");
            PreparedStatement stm = connection.prepareStatement(Query);
            ResultSet rs = stm.executeQuery();
            rs.next();
            System.out.println("Id_sauvegarde est"+rs.getInt("ID_sauvegarde"));
            /*String Query2="select Nom_humanoide from contient_humanoide where ID_sauvegarde="+rs.getInt("ID_sauvegarde") ;
        PreparedStatement stm2=connection.prepareStatement(Query2);
        ResultSet rs2 = stm2.executeQuery();*/
            monde.getFromDatabase(connection, nomPartie, nomSauvegarde, rs.getInt("ID_sauvegarde"));
            J.getFromDatabase(connection, nomPartie, nomSauvegarde, rs.getInt("ID_sauvegarde"));
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseTools.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
}
