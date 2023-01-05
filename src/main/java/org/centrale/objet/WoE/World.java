/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.centrale.objet.WoE.DatabaseTools;

/**
 * Classe World : permet la creation et la manipulation d'un monde
 *
 * @author ebous
 */
public class World {

    /**
     * Liste des creatures qui existent dans le monde
     */
    ArrayList<Creature> Listecrea = new ArrayList<>();

    public Map<Integer, Objet> effets = new HashMap<>();

    ArrayList<Objet> inventaire = new ArrayList<>();
    String nom;

    /**
     * La taille du monde
     */
    public static final int[] tailleW = {50, 50};
    int[][] Mat = new int[tailleW[0]][tailleW[1]];
    Objet[][] Mat1 = new Objet[World.tailleW[0]][World.tailleW[1]];

    PotionSoin ps = new PotionSoin();
    Nourriture N = new Nourriture();

    /**
     * Constructeur par defaut
     */
    public World() {
        Random alea = new Random();
        int nb_de_C = 50;
        int t = 0;
        while (t < nb_de_C) {
            t++;
            int type = alea.nextInt(0, 4);
            switch (type) {
                case 0: {
                    Archer A = new Archer();
                    this.Listecrea.add(A);
                    break;
                }
                case 1: {
                    Guerrier G = new Guerrier();
                    this.Listecrea.add(G);
                    break;
                }
                case 2: {
                    Paysan P = new Paysan();
                    this.Listecrea.add(P);
                    break;
                }
                case 3: {
                    Loup L = new Loup();
                    this.Listecrea.add(L);
                    break;
                }
                case 4: {
                    Lapin La = new Lapin();
                    this.Listecrea.add(La);
                    break;
                }
                default: {
                    break;
                }

            }
        }
        for (int i = 0; i < tailleW[0]; i++) {
            for (int j = 0; j < tailleW[1]; j++) {
                Mat[i][j] = 0;

            }
        }
    }

    /**
     * ajout d'un personnage dans le monde
     *
     * @param C Creature
     */
    public void ajout(Creature C) {
        if (Mat[C.pos.x][C.pos.y] == 1) {
            System.out.println("Impossible la case est occupé");
        } else {
            this.Listecrea.add(C);
            Mat[C.pos.x][C.pos.y] = 1;
            System.out.println("La creature est ajouté avec succes !");
        }

    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * permet de positionner ces protagonistes de manière aléatoire
     */
    public void creerMondeAlea() {

        Random alea = new Random();
        int n = this.Listecrea.size() - 1;
        //Listecrea.get(0).pos.x = alea.nextInt(tailleW[0]);
        //Listecrea.get(0).pos.y = alea.nextInt(tailleW[1]);
        Mat[0][0] = 1;
        for (int h = 1; h <= n; h++) {
            boolean test = true;
            Creature c = Listecrea.get(h);
            while (test) {
                c.pos.x = alea.nextInt(tailleW[0]);
                c.pos.y = alea.nextInt(tailleW[1]);
                test = false;
                for (int j = 0; j < h; j++) {

                    if (Mat[c.pos.x][c.pos.y] == 1) {
                        test = true;
                    }
                }
            }
        }
        for (Creature C : this.Listecrea) {
            Mat[C.pos.x][C.pos.y] = 1;
        }
    }

    public void genererObjet() {
        int i;
        Random alea = new Random();
        int j;

        for (i = 0; i < World.tailleW[0]; i++) {
            for (j = 0; j < World.tailleW[1]; j++) {
                int x = alea.nextInt(4);
                switch (x) {
                    case 0: {
                        PotionSoin ps = new PotionSoin();
                        ps.genererPotionSoin();
                        Mat1[i][j] = ps;
                        ps.pos.x = i;
                        ps.pos.y = j;
                        break;
                    }
                    case 1: {
                        Epee ep = new Epee();
                        ep.genererEpee();
                        Mat1[i][j] = ep;
                        ep.pos.x = i;
                        ep.pos.y = j;
                        break;
                    }
                    case 2: {
                        Nourriture N = new Nourriture();
                        N.genererNourriture(i, j);
                        Mat1[i][j] = N;
                        N.pos.x = i;
                        N.pos.y = j;
                        break;
                    }
                    case 3: {
                        Mat1[i][j] = null;
                        break;
                    }
                    default: {
                        break;
                    }
                }
            }
        }
    }

    /**
     * Permet d'afficher les informations du Monde
     */
    public void afficheWorld() {
        for (Creature c : this.Listecrea) {
            c.Affiche();
        }
        System.out.println("Nombres de creature present : " + this.Listecrea.size());

    }

    public Joueur creationJoueur() {
        Joueur J = new Joueur();
        J.choisir();
        return (J);
    }

    int k = 0;

    public void tourDeJeu(Joueur J) {
        int ptvieJ = J.p.ptVie;
        int ptparJ = J.p.ptPar;
        int degattJ = J.p.degAtt;
        int pageparJ = J.p.pagePar;
        int pageattJ = J.p.pageAtt;

        Scanner sc = new Scanner(System.in);
        k += 1;
        for (Integer f : effets.keySet()) {

            Objet u = effets.get(f);
            effets.replace(f - 1, u);
            effets.remove(0);
            System.out.println("temps" + f + "utilisable " + u);
        }

        //se déplacer
        //position ultrieur du joueur
        Point2D posult = J.p.pos;
        System.out.println("x avant déplacement: " + J.p.pos.x + "/ y avant déplacement: " + J.p.pos.y);
        System.out.println("où est ce que tu veux te déplacer: x");
        String str1 = sc.nextLine();
        System.out.println("où est ce que tu veux te déplacer: y");
        String str2 = sc.nextLine();
        int i = Integer.parseInt(str1);
        int j = Integer.parseInt(str2);

        while ((i >= 50) || (j >= 50) || (Mat[i][j] == 1)) {
            System.out.println("position invalide");
            System.out.println("où est ce que tu veux te déplacer: x");
            str1 = sc.nextLine();
            System.out.println("où est ce que tu veux te déplacer: y");
            str2 = sc.nextLine();
            i = Integer.parseInt(str1);
            j = Integer.parseInt(str2);
        }
        J.p.pos.setPosition(i, j);
        System.out.println("x après déplacement: " + J.p.pos.x + "/ y après déplacement: " + J.p.pos.y);
        Mat[i][j] = 1;
        Mat[posult.x][posult.y] = 0;

        //objet collectionné
        if (Mat1[i][j] != null) {
            System.out.println("t'as un objet dans ta case : " + Mat1[i][j].nom + " est ce que tu veux le prendre? [y/n]");
            String stri = sc.nextLine();
            if ("y".equals(stri)) {
                inventaire.add(Mat1[J.p.pos.x][J.p.pos.y]);
            }
        }

        //choisir d'utiliser un objet de son inventaire 
        if (inventaire != null) {
            System.out.println("vous avez ces objets:");
            for (int a = 0; a < inventaire.size(); a++) {
                System.out.println(a + ": " + inventaire.get(a).nom);
            }
            System.out.println("Quel objet vous voulez utiliser? écrivez son num. Si rien écrit n");
            String str3 = sc.nextLine();
            if (!"n".equals(str3)) {
                int w = Integer.parseInt(str3);
                //remplir le hashmap avec l'objet activé et le temps d'activation
                if (inventaire.get(w) instanceof PotionSoin) {
                    effets.put(1, inventaire.get(w));
                }
                if (inventaire.get(w) instanceof Epee) {
                    effets.put(1, inventaire.get(w));
                }
                if (inventaire.get(w) instanceof Nourriture) {
                    effets.put(1, inventaire.get(w));
                }
                inventaire.remove(w);
            }

        }

        //calcul des effets des objets activés sur le personnage
        // ça change à chaque fois: termine!!!!
        for (Integer f : effets.keySet()) {
            Objet u = effets.get(f);
            if (u instanceof PotionSoin) {
                J.p.ptVie = ptvieJ + ((PotionSoin) u).score;
            }
            if (u instanceof Nourriture) {
                J.p.pageAtt = pageattJ + ((Nourriture) u).malus;
                J.p.pagePar = pageparJ + ((Nourriture) u).bonus;
            }
            if (u instanceof Epee) {
                J.p.degAtt = degattJ + ((Epee) u).score;
            }
        }
        for (Integer f : effets.keySet()) {
            System.out.println(f);
        }
        //combattre avec une créature aléatoire
        System.out.println("est ce que tu veux combattre: y/n");
        String str = sc.nextLine();
        Random alea = new Random();
        int p = alea.nextInt(Listecrea.size());
        if ("y".equalsIgnoreCase(str)) {
            if (J.p instanceof Guerrier) {
                ((Guerrier) J.p).combattre(this.Listecrea.get(p));
            }
            if (J.p instanceof Archer) {
                ((Archer) J.p).combattre(this.Listecrea.get(p));
            }
        }
        J.p.ptVie = ptvieJ;
        J.p.pageAtt = pageattJ;
        J.p.pagePar = pageparJ;
        J.p.degAtt = degattJ;
        J.p.ptPar = ptparJ;
    }

    /**
     * Save world to database
     *
     * @param connection
     * @param gameName
     * @param saveName
     * @param ID_sauvegarde
     */
    public void saveToDatabase(Connection connection, String gameName, String saveName, int ID_sauvegarde) {
        if (connection != null) {
            // Get Player ID
            try {
                int i = 0;
                // Save world for Player ID
                for (Creature e : Listecrea) {
                    i++;
                    e.saveToDatabase(connection, ID_sauvegarde, i);
                }

                String Query1 = "insert into inventaire(nom_monde)values('" + gameName + "') ";
                PreparedStatement stm1 = connection.prepareStatement(Query1);
                stm1.executeUpdate();
                String Query2 = "select max(id_inventaire) as max_id_inv from inventaire ";
                PreparedStatement stm2 = connection.prepareStatement(Query2);
                ResultSet rs2 = stm2.executeQuery();
                rs2.next();

                String Query = "insert into contient_inventaire(id_inventaire,id_sauvegarde)values(" + (rs2.getInt("max_id_inv")) + "," + ID_sauvegarde + ") ";
                PreparedStatement stm = connection.prepareStatement(Query);
                stm.executeUpdate();
                int j = 0;

                for (Objet ob : inventaire) {
                    j++;
                    ob.saveToDatabase(connection, ID_sauvegarde, (rs2.getInt("max_id_inv")), j);
                }
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseTools.class.getName()).log(Level.SEVERE, null, ex);

            }
        }
    }

    /**
     * Get world from database
     *
     * @param connection
     * @param gameName
     * @param saveName
     * @param id_sauvegarde
     */
    public void getFromDatabase(Connection connection, String gameName, String saveName, int id_sauvegarde) {
        if (connection != null) {
            // Remove old data
            try {
                String Query = "select id_inventaire from contient_inventaire inner join sauvegarde using(id_sauvegarde) where id_sauvegarde=" + id_sauvegarde;
                PreparedStatement stm = connection.prepareStatement(Query);
                ResultSet rs = stm.executeQuery();
                rs.next();
                while (rs.next()) {
                    System.out.println("id_inventaire est " + rs.getInt("id_inventaire"));
                }
                /*String Query2 = "select nom_monde from monde inner join partie using(nom_monde) inner join est_sauvegarde using(id_partie) where id_sauvegarde=" + id_sauvegarde;
                PreparedStatement stm2 = connection.prepareStatement(Query2);
                ResultSet rs2 = stm2.executeQuery();
                rs2.next();
                this.setNom(rs2.getString("nom_monde"));*/

                String Query5 = "select count(*) as x from humanoide inner join contient_humanoide using(nom_humanoide) inner join sauvegarde using(id_sauvegarde) where nom_metier='Guerrier' and id_sauvegarde=" + id_sauvegarde;
                PreparedStatement stm5 = connection.prepareStatement(Query5);
                ResultSet rs5 = stm5.executeQuery();
                rs5.next();
                while (rs5.next()) {
                    int o3 = rs5.getInt("x");
                    System.out.println("numero des Guerriers: " + o3);
                }
                String Query10 = "select nom_humanoide from humanoide inner join contient_humanoide using(nom_humanoide) inner join sauvegarde using(id_sauvegarde) where nom_metier='Guerrier' and id_sauvegarde=" + id_sauvegarde;
                PreparedStatement stm10 = connection.prepareStatement(Query10);
                ResultSet rs10 = stm10.executeQuery();
                rs10.next();
                ResultSetMetaData rs10meta = rs10.getMetaData();
                Guerrier g = new Guerrier();
                g.getFromDatabase(connection, id_sauvegarde, rs10.getString("nom_humanoide"));
                Listecrea.add(g);
                while (rs10.next()) {
                    for (int i = 1; i <= rs10meta.getColumnCount(); i++) {
                        Guerrier g1 = new Guerrier();
                        g1.getFromDatabase(connection, id_sauvegarde, rs10.getObject(i).toString());
                        Listecrea.add(g);
                    }
                }

                String Query6 = "select count(*) as x from humanoide inner join contient_humanoide using(nom_humanoide) inner join sauvegarde using(id_sauvegarde) where nom_metier='Archer' and id_sauvegarde=" + id_sauvegarde;
                PreparedStatement stm6 = connection.prepareStatement(Query6);
                ResultSet rs6 = stm6.executeQuery();
                rs6.next();
                while (rs6.next()) {
                    int o4 = rs6.getInt("x");
                    System.out.println("numero des Archers: " + o4);
                }
                String Query11 = "select nom_humanoide from humanoide inner join contient_humanoide using(nom_humanoide) inner join sauvegarde using(id_sauvegarde) where nom_metier='Archer' and id_sauvegarde=" + id_sauvegarde;
                PreparedStatement stm11 = connection.prepareStatement(Query11);
                ResultSet rs11 = stm11.executeQuery();
                rs11.next();
                ResultSetMetaData rs11meta = rs11.getMetaData();
                Archer a1 = new Archer();
                a1.getFromDatabase(connection, id_sauvegarde, rs11.getString("nom_humanoide"));
                Listecrea.add(g);
                while (rs11.next()) {
                    for (int i = 1; i <= rs11meta.getColumnCount(); i++) {
                        Archer a = new Archer();
                        a.getFromDatabase(connection, id_sauvegarde, rs11.getObject(i).toString());
                        Listecrea.add(a);
                    }
                }

                String Query7 = "select count(*) as x from humanoide inner join contient_humanoide using(nom_humanoide) inner join sauvegarde using(id_sauvegarde) where nom_metier='Paysan' and id_sauvegarde=" + id_sauvegarde;
                PreparedStatement stm7 = connection.prepareStatement(Query7);
                ResultSet rs7 = stm7.executeQuery();
                rs7.next();
                while (rs7.next()) {
                    int o5 = rs7.getInt("x");
                    System.out.println("numero des Paysan: " + o5);
                }
                String Query12 = "select nom_humanoide from humanoide inner join contient_humanoide using(nom_humanoide) inner join sauvegarde using(id_sauvegarde) where nom_metier='Paysan' and id_sauvegarde=" + id_sauvegarde;
                PreparedStatement stm12 = connection.prepareStatement(Query12);
                ResultSet rs12 = stm12.executeQuery();
                rs12.next();
                ResultSetMetaData rs12meta = rs12.getMetaData();
                Paysan p1 = new Paysan();
                p1.getFromDatabase(connection, id_sauvegarde, rs12.getString("nom_humanoide"));
                Listecrea.add(p1);
                while (rs12.next()) {
                    for (int i = 1; i <= rs12meta.getColumnCount(); i++) {
                        Paysan p = new Paysan();
                        p.getFromDatabase(connection, id_sauvegarde, rs12.getObject(i).toString());
                        Listecrea.add(p);
                    }
                }
                String Query8 = "select count(*) as x from monstre inner join contient_monstre using(nom_monstre) inner join sauvegarde using (id_sauvegarde) where nom_type='Loup' and id_sauvegarde=" + id_sauvegarde;
                PreparedStatement stm8 = connection.prepareStatement(Query8);
                ResultSet rs8 = stm8.executeQuery();
                rs8.next();
                while (rs8.next()) {
                    int o6 = rs8.getInt("x");
                    System.out.println("nombre des Loup: " + o6);
                }
                String Query13 = "select nom_monstre from monstre inner join contient_monstre using(nom_monstre) inner join sauvegarde using (id_sauvegarde) where nom_type='Loup' and id_sauvegarde=" + id_sauvegarde;
                PreparedStatement stm13 = connection.prepareStatement(Query13);
                ResultSet rs13 = stm13.executeQuery();
                rs13.next();
                ResultSetMetaData rs13meta = rs13.getMetaData();
                Loup l1 = new Loup();
                l1.getFromDatabase(connection, id_sauvegarde, rs13.getString("nom_monstre"));
                Listecrea.add(l1);
                while (rs13.next()) {
                    for (int i = 1; i <= rs13meta.getColumnCount(); i++) {
                        Loup l = new Loup();
                        l.getFromDatabase(connection, id_sauvegarde, rs13.getObject(i).toString());
                        Listecrea.add(l);
                    }
                }

                String Query9 = "select count(*) as x from monstre inner join contient_monstre using(nom_monstre) inner join sauvegarde using (id_sauvegarde) where nom_type='Lapin' and id_sauvegarde=" + id_sauvegarde;
                PreparedStatement stm9 = connection.prepareStatement(Query9);
                ResultSet rs9 = stm9.executeQuery();
                rs9.next();
                while (rs9.next()) {
                    int o7 = rs9.getInt("x");
                    System.out.println("nombre des Lapins: " + o7);
                }
                String Query14 = "select nom_monstre from monstre inner join contient_monstre using(nom_monstre) inner join sauvegarde using (id_sauvegarde) where nom_type='Loup' and id_sauvegarde=" + id_sauvegarde;
                PreparedStatement stm14 = connection.prepareStatement(Query14);
                ResultSet rs14 = stm14.executeQuery();
                rs14.next();
                ResultSetMetaData rs14meta = rs14.getMetaData();

                while (rs14.next()) {
                    Lapin lp1 = new Lapin();
                    lp1.getFromDatabase(connection, id_sauvegarde, rs14.getString("nom_monstre"));
                    Listecrea.add(lp1);
                    for (int i = 1; i <= rs14meta.getColumnCount(); i++) {
                        Lapin lp = new Lapin();
                        lp.getFromDatabase(connection, id_sauvegarde, rs14.getObject(i).toString());
                        Listecrea.add(lp);
                    }
                }

                //ajout get objets
                String Query2 = "select count(*) as x from Epee inner join contient_epee using(nom_epee) inner join inventaire using(id_inventaire) inner join contient_inventaire using(id_inventaire) inner join sauvegarde using(id_sauvegarde) where id_sauvegarde=" + id_sauvegarde;
                PreparedStatement stm2 = connection.prepareStatement(Query2);
                ResultSet rs2 = stm2.executeQuery();
                rs2.next();
                int o = rs2.getInt("x");
                System.out.println("nombre des epées: " + o);

                String Query15 = "select nom_epee from epee inner join contient_epee using(nom_epee) inner join inventaire using(id_inventaire) inner join contient_inventaire using(id_inventaire) inner join sauvegarde using (id_sauvegarde) where id_sauvegarde=" + id_sauvegarde;
                PreparedStatement stm15 = connection.prepareStatement(Query15);
                ResultSet rs15 = stm15.executeQuery();
                rs15.next();
                ResultSetMetaData rs15meta = rs15.getMetaData();

                while (rs15.next()) {
                    Objet ob1 = new Epee();
                    ob1.getFromDatabase(connection, id_sauvegarde, rs.getInt("id_inventaire"), rs15.getString("nom_epee"));
                    inventaire.add(ob1);
                    for (int i = 1; i <= rs15meta.getColumnCount(); i++) {
                        Objet ob = new Epee();
                        ob.getFromDatabase(connection, id_sauvegarde, rs.getInt("id_inventaire"), rs15.getString("nom_epee"));
                        inventaire.add(ob);
                    }
                }

                String Query3 = "select count(*) as x from Nourriture inner join contient_nourriture using(nom_nourriture) inner join inventaire using(id_inventaire) inner join contient_inventaire using(id_inventaire) inner join sauvegarde using(id_sauvegarde) where id_sauvegarde=" + id_sauvegarde;
                PreparedStatement stm3 = connection.prepareStatement(Query3);
                ResultSet rs3 = stm3.executeQuery();
                rs3.next();
                int o1 = rs3.getInt("x");
                System.out.println("nombre des nourritures: " + o1);
                String Query16 = "select nom_nourriture from nourriture inner join contient_nourriture using(nom_nourriture) inner join inventaire using(id_inventaire) inner join contient_inventaire using(id_inventaire) inner join sauvegarde using (id_sauvegarde) where id_sauvegarde=" + id_sauvegarde;
                PreparedStatement stm16 = connection.prepareStatement(Query16);
                ResultSet rs16 = stm16.executeQuery();
                rs16.next();

                ResultSetMetaData rs16meta = rs16.getMetaData();
                while (rs15.next()) {
                    Objet ob2 = new Nourriture();
                    ob2.getFromDatabase(connection, id_sauvegarde, rs.getInt("id_inventaire"), rs16.getString("nom_nourriture"));
                    inventaire.add(ob2);
                    for (int i = 1; i <= rs16meta.getColumnCount(); i++) {
                        Objet ob = new Nourriture();
                        ob.getFromDatabase(connection, id_sauvegarde, rs.getInt("id_inventaire"), rs16.getString("nom_nourriture"));
                        inventaire.add(ob);
                    }
                }

                String Query4 = "select count(*) as x from potionsoin inner join contient_potionsoin using(nom_potion) inner join inventaire using(id_inventaire) inner join contient_inventaire using(id_inventaire) inner join sauvegarde using(id_sauvegarde) where id_sauvegarde=" + id_sauvegarde;
                PreparedStatement stm4 = connection.prepareStatement(Query4);
                ResultSet rs4 = stm4.executeQuery();
                rs4.next();
                int o2 = rs4.getInt("x");
                System.out.println("nombre des PotionSoins: " + o2);

                String Query17 = "select nom_potion from potionsoin inner join contient_potionsoin using(nom_potion) inner join inventaire using(id_inventaire) inner join contient_inventaire using(id_inventaire) inner join sauvegarde using (id_sauvegarde) where id_sauvegarde=" + id_sauvegarde;
                PreparedStatement stm17 = connection.prepareStatement(Query17);
                ResultSet rs17 = stm17.executeQuery();
                rs17.next();
                ResultSetMetaData rs17meta = rs17.getMetaData();

                while (rs17.next()) {
                    Objet ob3 = new PotionSoin();
                    ob3.getFromDatabase(connection, id_sauvegarde, rs.getInt("id_inventaire"), rs17.getString("nom_potion"));
                    inventaire.add(ob3);
                    for (int i = 1; i <= rs17meta.getColumnCount(); i++) {
                        Objet ob = new PotionSoin();
                        ob.getFromDatabase(connection, id_sauvegarde, rs.getInt("id_inventaire"), rs17.getString("nom_potion"));
                        inventaire.add(ob);
                    }
                }
                for (int i = 0; i < inventaire.size(); i++) {
                    System.out.println("nom inventaire " + inventaire.get(i).nom + " x:" + inventaire.get(i).pos.x + " y:" + inventaire.get(i).pos.y + " score: " + inventaire.get(i).score);
                }
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseTools.class.getName()).log(Level.SEVERE, null, ex);

            }

            // Get Player ID
            // get world for Player ID
        }
    }
}
