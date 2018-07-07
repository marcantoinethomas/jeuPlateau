/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

public class Joueur{
    private String nomJoueur;
    private Case position;
    private boolean actif;
    private int numeroJoueur;

    public Joueur(String nomJoueur, int numeroJoueur){
        this.nomJoueur = nomJoueur;
        this.position = new Debut("A1","DEBUT");
        this.actif= false;
        this.numeroJoueur= numeroJoueur;
    }

    public int getNumeroJoueur() {
        return numeroJoueur;
    }

    public void setNumeroJoueur(int numeroJoueur) {
        this.numeroJoueur = numeroJoueur;
    }
        
    public boolean isActif() {
        return actif;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
    }

    public String getNomJoueur() {
        return nomJoueur;
    }

    public void setNomJoueur(String nomJoeur) {
        this.nomJoueur = nomJoeur;
    }

    public Case getPosition() {
        return position;
    }

    public void setPosition(Case position) {
        this.position = position;
    }
    
    public String toString() {
        String conversion="";
        conversion+="Nom du joueur: "+this.getNomJoueur()+"; ";
        conversion+="Position du joueur: "+this.position.getPosition()+"("+this.position.getNomCase()+"); ";
        conversion+= "Actif: "+(this.isActif()?"Oui":"Non"); 
        return conversion.toUpperCase();
    }   
}
