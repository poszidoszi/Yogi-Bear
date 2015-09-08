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
public abstract class Puppet {
    
    protected int i;
    protected int j;

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }
    
    public void setI(int i) {
        this.i = i;
    }

    public void setJ(int j) {
        this.j = j;
    }
    
    public abstract String getImg();
}
