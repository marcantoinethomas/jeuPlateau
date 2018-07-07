/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

public class Division extends Case{
 
    public Division() {
    }
    
    public Division(String position, String nomCase){
       super(position, nomCase); 
    }
    
    @Override
    public int faireOperation(int[] tabEntier){
        return tabEntier[0]/tabEntier[1];
    }

    @Override
    public int retournerBond() {
        return 0;
    }
    
    
}
