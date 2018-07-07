 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class Plateau {
    private ArrayList<Case> plateau= new ArrayList<Case>();
    private ArrayList<Joueur> joueurs= new ArrayList<Joueur>();
    private long currenttime= System.currentTimeMillis();
    //Constructeur Plateau
    public Plateau(){
        this.plateau.add(new Debut("A1", "DEBUT"));
        this.plateau.add(new Multiplication("B1", "MULTIPLICATION"));
        this.plateau.add(new Addition("C1", "ADDITION"));
        this.plateau.add(new Division("D1", "DIVISION"));
        this.plateau.add(new Soustraction("E1", "SOUSTRACTION"));
        this.plateau.add(new Multiplication("F1", "MULTIPLICATION"));
        this.plateau.add(new Division("G1", "DIVISION"));
        this.plateau.add(new Bond2("H1", "BOND2")); 
        this.plateau.add(new Addition("I1", "ADDITION"));
        this.plateau.add(new Division("J1", "DIVISION"));
        //2
        this.plateau.add(new Multiplication("J2","MULTIPLICATION")); 
        this.plateau.add(new Division("J3", "DIVISION"));
        this.plateau.add(new Soustraction("J4", "SOUSTRACTION")); 
        this.plateau.add(new Addition("J5","ADDITION"));
        this.plateau.add(new Bond3("J6", "BOND3")); 
        this.plateau.add(new Soustraction("J7", "SOUSTRACTION"));
        this.plateau.add(new Division("J8", "DIVISION")); 
        this.plateau.add(new Multiplication("J9", "MULTIPLICATION")); 
        this.plateau.add(new Bond1("J10", "BOND1"));
        //3
        this.plateau.add(new Addition("I10", "ADDITION"));
        this.plateau.add(new Bond3("H10", "BOND3"));
        this.plateau.add(new Multiplication("G10", "MULTIPLICATION"));
        this.plateau.add(new Soustraction("F10", "SOUSTRACTION"));
        this.plateau.add(new Division("E10", "DIVISION"));
        this.plateau.add(new Soustraction("D10", "SOUSTRACTION"));
        this.plateau.add(new Bond2("C10", "BOND2"));
        this.plateau.add(new Division("B10", "DIVISION"));
        this.plateau.add(new Multiplication("A10", "MULTIPLICATION"));
        //4
        this.plateau.add(new Multiplication("A9", "MULTIPLICATION"));
        this.plateau.add(new Division("A8", "DIVISION"));
        this.plateau.add(new Multiplication("A7", "MULTIPLICATION"));
        this.plateau.add(new Division("A6", "DIVISION"));
        this.plateau.add(new Bond3("A5", "BOND3"));
        this.plateau.add(new Addition("A4", "ADDITION"));
        this.plateau.add(new Division("A3", "DIVISION"));
        this.plateau.add(new Fin("A2", "FIN"));
        this.joueurs.add(new Joueur("Joueur 1", 1));
        this.joueurs.add(new Joueur("Joueur 2", 2));   
    }
    //Methode qui affiche le menu principal
    public int afficherMenu(){
        int choix = 0;
        System.out.println("MENU DE JEU:");
        System.out.println("1 : Changer le nom des joueurs");
        System.out.println("2 : Afficher la position et le statut des joueurs");
        System.out.println("3 : Lancer les dés");
        System.out.println("4 : Quitter");
        while((choix != 1) && (choix != 2) && (choix != 3)&& (choix != 4)){
            Scanner scanner= new Scanner(System.in);
            choix = scanner.nextInt();
    }
        return choix;
    }
    //Methode pour modifier le nom des deux Joueurs
    public void modifierNomJoueur(){
        int i= 1;
        for (Joueur j: joueurs){
            System.out.println("Veuillez entrer le nom du joueur No: "+i);
            Scanner scanner= new Scanner(System.in);
            String nom= scanner.nextLine();
            j.setNomJoueur(nom);
            ++i;
        }
    }
    //Methode pour afficher le plateau
    public void afficherPlateau(){
        System.out.println("AFFICHAGE DU PLATEAU DE JEU");
        for(Case e: plateau) {
           System.out.print(e.getPosition()+" ");
        }
        System.out.println("");
    }    
    //Methode pour afficher la position et le statut des joueurs
    public void afficherJoueurs(){
        afficherPlateau();
        int i=1;
        for (Joueur j: joueurs){
            System.out.println("INFORMATIONS JOUEUR NO: "+i);
            System.out.println(j);
            ++i;
        }
    }
    //Methode qui calcule les cases restantes pour un Joueur
    public String calculerCasesRestantes(Case caseA){
        return plateau.size()-getIndexofCase(caseA)+"/"+plateau.size();
    }
    //Getter utilisé pour lancer les dés
    public long getCurrenttime() {
        return System.currentTimeMillis();
    } 
    //Methode qui retourne l'index d'une Case dans le plateau
    public int getIndexofCase(Case caseA){
        int i= 0, position= -1;
        for(Case c: plateau){
           if (caseA.getPosition().equals(c.getPosition())){
               position= i;
           }else{
               ++i;
           }
        } 
        return position;
    }
    //Methode qui fait la permitation de joueurs apres chaque lancement des dés
    public void permuterJoueur(Joueur j){
        if (j.getNumeroJoueur()==1){
            this.joueurs.get(0).setActif(false);
            this.joueurs.get(1).setActif(true);
        }
        if (j.getNumeroJoueur()==2){
            this.joueurs.get(1).setActif(false);
            this.joueurs.get(0).setActif(true);
        }
    }
    //Methode qui retourne la case suivante si toutefois un joueur s'y trouve
    public Case retournerCaseSuivante(Case caseA){
       Case caseB= caseA;
       boolean trouve= false;
       for(Joueur j: this.joueurs){
           if(j.getPosition()== caseA){
               trouve= true;
           }  
       }
       if(trouve== true){
           caseB= this.plateau.get(getIndexofCase(caseA)+1);
           //Cas ou la case de destination est un Bond
            switch (getIndexofCase(caseB)) {
                case 7:
                case 14:
                case 18:
                case 20:
                case 25:
                    int bond= this.plateau.get(getIndexofCase(caseB)).retournerBond();
                    caseB= plateau.get(getIndexofCase(caseB)+bond);
                    break;
                default:
                    break;
            }
       }
       return caseB;
    }
    //Methode qui retourne a un moment donné le joueur qui est actif
    public Joueur getJoueurActif(){
      Joueur joueurActif= null;
        if (!this.joueurs.get(0).isActif()&&!this.joueurs.get(1).isActif()){
            joueurActif= this.joueurs.get(0);
        }
        if (this.joueurs.get(0).isActif()&&!this.joueurs.get(1).isActif()){
            joueurActif= this.joueurs.get(0);
        }
        if(!this.joueurs.get(0).isActif()&&this.joueurs.get(1).isActif()){
            joueurActif= this.joueurs.get(1);
        }
        return joueurActif;
    }
    //Methodes pour afficher les messages lors du jeu
    public void afficherMessageBond(long valeurDes, Joueur joueurActif, Case caseApres, int bond){
        System.out.println("VALEUR DES DÉS: "+valeurDes);
        System.out.println("BRAVO!!! VOUS VENEZ DE GAGNER UN BOND: B"+bond);
        System.out.println("LE JOUEUR: " +joueurActif.getNomJoueur().toUpperCase()+" SE TROUVE MAINTENANT A LA CASE: "+caseApres.getPosition()+", CASES RESTANTES: "+calculerCasesRestantes(caseApres));
        System.out.println("CHANGEMENT DE JOUEUR");   
    }
    public void afficherMessageBond(long valeurDes, Joueur joueurActif, int bond){
        System.out.println("VALEUR DES DÉS: "+valeurDes);
        System.out.println("BRAVO!!! VOUS VENEZ DE GAGNER UN BOND: B"+bond);
        System.out.println("BINGO!!! LE JOUEUR: " +joueurActif.getNomJoueur().toUpperCase()+" A GAGNÉ"); 
    }
    public void affficherMessageMauvaiseReponse(Joueur joueurActif, Case caseAvant){
        System.out.println("OUPS!!! MAUVAISE RÉPONSE");
        System.out.println("LE JOUEUR: " +joueurActif.getNomJoueur().toUpperCase()+" SE TROUVE A LA MÊME CASE: "+caseAvant.getPosition()+", CASES RESTANTES: "+calculerCasesRestantes(caseAvant));
        System.out.println("CHANGEMENT DE JOUEUR");
    }
    public void afficherMessageBonneReponse(Joueur joueurActif, Case caseApres){
        System.out.println("BRAVO!!! BONNE RÉPONSE");
        System.out.println("LE JOUEUR: " +joueurActif.getNomJoueur().toUpperCase()+" SE TROUVE MAINTENANT A LA CASE: "+caseApres.getPosition()+", CASES RESTANTES: "+calculerCasesRestantes(caseApres));
    }
    public void afficherMessageFin(long valeurDes, Joueur joueurActif){
        System.out.println("VALEUR DES DÉS: "+valeurDes);
        System.out.println("BINGO!!! LE JOUEUR: " +joueurActif.getNomJoueur().toUpperCase()+" A GAGNÉ");
    }
    //Methode pour retourner les valeurs aléatoires
    public int[] retournerValeursAleatoires(){
        int[] resultat= {0,0};
        Random random= new Random();
        Scanner scanner= new Scanner(System.in);                
        resultat[0]= random.nextInt(5)+1; //Valeur1 aléatoire entre 1 a 5
        resultat[1]= random.nextInt(5)+1; //Valeur2 aléatoire entre 1 a 5
        return resultat;
    }
    //Methode principale pour lancer les dés et de répondre aux Opérations mathématiques
    public void lancerDe(){
        Joueur joueurActif= getJoueurActif();
        System.out.println(">->->->-> C'EST VOTRE TOUR: "+joueurActif.getNomJoueur().toUpperCase());
        joueurActif.setActif(true);
        long valeurDes= ((System.currentTimeMillis()-this.currenttime)%6)+1;
        Case caseAvant= joueurActif.getPosition();
        int positionAvant= getIndexofCase(caseAvant); 
        //Gestion de l'exception NullPointerException
        while(positionAvant+(int)valeurDes>plateau.size()){
           valeurDes= ((System.currentTimeMillis()-this.currenttime)%6)+1;
        }
        Case caseApres= plateau.get(positionAvant+(int)valeurDes);
        int positionApres= getIndexofCase(caseApres);
        //Gestion des cases de Bond
        switch (positionApres) {
            case 7:
            case 14:
            case 18:
            case 20:
            case 25:
                //Methode Polymorphe: retournerBond
                int bond= this.plateau.get(positionApres).retournerBond();
                caseApres= plateau.get(positionApres+bond);
                //Cas ou il y a deja un joueur sur la case de destination
                caseApres= retournerCaseSuivante(caseApres);
                joueurActif.setPosition(caseApres);
                permuterJoueur(joueurActif);
                afficherMessageBond(valeurDes, joueurActif, caseApres, bond);
                break;
            case 32:
                afficherMessageBond(valeurDes, joueurActif, bond= 3);
                quitter();
            //Cas ou les cases ne sont pas des bonds que les joueurs doivent faire les Opérations aritnmétiques
            default:
                if (getIndexofCase(caseApres)==35){
                    afficherMessageFin(valeurDes, joueurActif);
                    quitter();
                }
                System.out.println("VALEUR DES DÉS: "+valeurDes+" LE JOUEUR: " +joueurActif.getNomJoueur().toUpperCase()+" SE TROUVE TEMPORAIREMENT A LA CASE: "+caseApres.getPosition()+"("+caseApres.getNomCase()+")");
                Random random= new Random();               
                int[] valAleatoires= retournerValeursAleatoires();
                //Affichage pour tester le programme
                System.out.println("VOICI LES DEUX VALEURS ALEATOIRES: "+valAleatoires[0]+" ; "+valAleatoires[1]);
                //Methode Polymorphe: faireOperation
                int resultatRandom= this.plateau.get(positionApres).faireOperation(valAleatoires);
                System.out.print("VEUILLEZ DONNER LE RÉSULTAT DE L'OPÉRATION: ");
                Scanner scanner= new Scanner(System.in); 
                int resultatJoueur= scanner.nextInt();
                if (resultatRandom!= resultatJoueur){
                    joueurActif.setPosition(caseAvant);
                    permuterJoueur(joueurActif);
                    affficherMessageMauvaiseReponse(joueurActif, caseAvant);
                }else{
                    //Cas ou il y a deja un joueur sur la case de destination
                    caseApres= retournerCaseSuivante(caseApres);
                    joueurActif.setPosition(caseApres);
                    permuterJoueur(joueurActif);
                    afficherMessageBonneReponse(joueurActif, caseApres);
                    if (getIndexofCase(caseApres)==35){
                        afficherMessageFin(valeurDes, joueurActif);
                        quitter();
                    }else {                            
                        System.out.println("CHANGEMENT DE JOUEUR");
                    }
                }                   
        }
    }
                                              
    public void quitter() {
        System.out.println("MERCI D'AVOIR JOUÉ AVEC NOUS !!!");
        System.exit(0);
    }
}
