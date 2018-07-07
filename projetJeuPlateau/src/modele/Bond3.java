/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

public class Bond3 extends Case{

    public Bond3() {
    }
    
    public Bond3(String position, String nomCase){
       super(position, nomCase); 
    }

    @Override
    public int retournerBond() {
        return 3;
    }
    
    @Override
    public int faireOperation(int[] tabEntier){
        return tabEntier[0]+tabEntier[1];
    }

    
}
