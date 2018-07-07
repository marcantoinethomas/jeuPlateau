/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import modele.Plateau;

public class Jeu_Plateau {

    public static void main(String[] args) {
        
        Plateau plateau= new Plateau();
        
        int choix= 0;
        do { 
          choix = plateau.afficherMenu();
          switch (choix) {
          case 1:
                plateau.modifierNomJoueur();break;
          case 2:
               plateau.afficherJoueurs(); break;
          case 3:
                plateau.lancerDe();break;      
          case 4:
                plateau.quitter();break;               
          }
        } while (choix< 4); 
    }    
}
