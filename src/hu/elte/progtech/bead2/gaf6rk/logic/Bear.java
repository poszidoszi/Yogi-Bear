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
public class Bear extends Puppet {

    public Bear(int i, int j) {
        this.i = i;
        this.j = j;
    }
    
    @Override
    public String getImg() {
        return "yoggi.png";
    }
    
}
