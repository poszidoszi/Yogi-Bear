/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hu.elte.progtech.bead2.gaf6rk.logic;


/**
 *
 * @author gaf6rk
 */
public class Keeper extends Puppet {
    
    private Step dest;

    public void setDest(Step dest) {
        this.dest = dest;
    }

    public Step getDest() {
        return dest;
    }
    
    public Keeper(int i, int j) {
        this.i = i;
        this.j = j;
        
        dest = Step.randomStep();
    }
    
    @Override
    public String getImg() {
        return "keeper.png";
    }
    
}
