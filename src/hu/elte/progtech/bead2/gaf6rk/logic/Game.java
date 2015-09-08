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
public interface Game {
    
    void step(Step s);

    public Puppet[][] getTable();
    
    boolean hasWon();
    boolean hasLost();
}
