/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

public abstract class Case {
    private String nomCase;
    private String position;

    public Case(){   
    };
    
    public Case(String position, String nomCase) {
        this.position = position;
        this.nomCase= nomCase;
    }
    
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getNomCase() {
        return nomCase;
    }

    public void setNomCase(String nomCase) {
        this.nomCase = nomCase;
    }
    
    public abstract int faireOperation(int[] tabEntier);
    
    public abstract int retournerBond();
}
