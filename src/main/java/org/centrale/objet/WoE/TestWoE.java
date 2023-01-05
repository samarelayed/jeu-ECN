/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;

import java.util.Scanner;
import static org.centrale.objet.WoE.World.tailleW;

/**
 *
 * @author samar
 */
public class TestWoE {

    String[][] Mat = new String[tailleW[0]][tailleW[1]];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        World w = new World();
        Joueur J=new Joueur();
        w.creerMondeAlea();
        w.genererObjet();
        System.out.println("Ar:arme;PS:potionSoin; cho:chocolat;A:archer; P:paysa: LO:loup; LA:Lapin; G:Guerrier; J:joueur ");
        boolean quitter = true;
        DatabaseTools database = new DatabaseTools();
        database.connect();
        Integer playerId = database.getPlayerID("Saegusa", "Mayumi");
        System.out.println(playerId);
        
        
        System.out.println("est ce que tu veux reprendre un jeu? [y/n]");
        String str2 = sc.nextLine();
        if ("y".equalsIgnoreCase(str2)) {
            database.readWorld(playerId, "Test Game 1", "Start", w,J);
            System.out.println(w.inventaire.size());
            for (int i = 0; i < w.inventaire.size(); i++) {
                    System.out.println("nom inventaire " + w.inventaire.get(i).nom + " x:" + w.inventaire.get(i).pos.x + " y:" + w.inventaire.get(i).pos.y + " score: " + w.inventaire.get(i).score);
                }
        }
        if ("n".equalsIgnoreCase(str2)) {
            J = w.creationJoueur();
        }
        while ((J.p.ptVie != 0) && (quitter)) {
            Grille g = new Grille(w, w.tailleW[0], w.tailleW[1], J);
            //g.Afficher(w);
            
            w.tourDeJeu(J);
            //J.p.Affiche();

            System.out.println("est ce que tu veux quitter [y/n]");
            String str = sc.nextLine();
            if ("y".equalsIgnoreCase(str)) {
                quitter = false;
                //System.out.println("est ce que tu veux enregistrer la partie [y/n]");
                //String str1 = sc.nextLine();
                //if ("y".equalsIgnoreCase(str1)) {
                //   database.saveWorld(playerId, "Test Game 1", "Start", w,J);
                //}
            }

        }

        //System.out.println("nombre de créatures"+w.Listecrea.size());
        //System.out.println("nombre d'objets dans l'inventaire"+w.inventaire.size());
        database.disconnect();
    }

}
