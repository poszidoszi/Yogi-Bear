/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.progtech.bead2.gaf6rk.logic;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author gaf6rk
 */
public class YogiBear implements Game {

    private int n;
    private Puppet[][] pups;
    private List<Keeper> keepers = new LinkedList<Keeper>();
    private Bear yogi;
    private int numberOfBuskets;
    private boolean lost = false;

    public YogiBear(int n) {
        this.n = n;
        pups = new Puppet[n][n];

        Random r = new Random();

        generatePuppets(5, 0.2, r, "tree");
        generatePuppets(2, 0.08, r, "keeper");
        generatePuppets(6, 0.3, r, "basket");

        yogi = new Bear(0, 0);
        pups[0][0] = yogi;
        
        for (Puppet[] row : pups) {
            
            for (Puppet p : row) {
                if (p instanceof Basket) {
                    numberOfBuskets++;
                }
            }
        }
    }

    private void generatePuppets(int def, double rate, Random r, String type) {

        int numberOfPuppets = 0;

        if (5 <= n && n <= 7) {
            numberOfPuppets = def;
        } else if (7 < n) {
            numberOfPuppets = (int) (n * n * rate);
        }

        for (int i = 0; i < numberOfPuppets; i++) {
            
            
            int x = r.nextInt(n);
            int y = r.nextInt(n);
            
            if (pups[x][y] != null) {
                continue;
            }

            Puppet t = null;

            switch (type) {
                case "keeper":
                    t = new Keeper(x, y);
                    keepers.add((Keeper) t);
                    break;
                case "tree":
                    t = new Tree();
                    break;
                case "basket":
                    t = new Basket();
                    break;
            }

            pups[x][y] = t;
        }
    }

    @Override
    public void step(Step s) {
        int i = yogi.getI();
        int j = yogi.getJ();

        switch (s) {
            case LEFT: //bal

                j--;
                if (j < 0) {
                    return;
                }
                
                break;
                
            case UP: //fel
                
                i--;
                if (i < 0) {
                    return;
                }
                
                break;
                
            case RIGHT: //jobb
                
                j++;
                if (j >= n) {
                    return;
                }
                
                break;
                
            case DOWN: //le
                
                i++;
                if (i >= n) {
                    return;
                }
                
                break;
        }

        Puppet p = pups[i][j];
        if (p instanceof Tree || p instanceof Keeper) {
            return;
        }
        if (p instanceof Basket) {
            numberOfBuskets--;
        }
        
        pups[yogi.getI()][yogi.getJ()] = null;
        pups[i][j] = yogi;
        yogi.setI(i);
        yogi.setJ(j);
        
        stepWithKeepers();
        
    }

    @Override
    public Puppet[][] getTable() {
        return pups;
    }

    private void stepWithKeepers() {
        
        for (Keeper k : keepers) {
            stepWithKeeper(k);
        }
    }

    @Override
    public boolean hasWon() {
       return numberOfBuskets == 0;
    }

    @Override
    public boolean hasLost() {
        return lost;
    }

    private void stepWithKeeper(Keeper k) {
        
        int i = k.getI();
        int j = k.getJ();

        testForYogi(i, j);
        
        switch (k.getDest()) {
            case LEFT: //bal

                j--;
                if (j < 0) {
                    k.setDest(k.getDest().getOpposite());
                    return;
                }
                
                break;
                
            case UP: //fel
                
                i--;
                if (i < 0) {
                    k.setDest(k.getDest().getOpposite());
                    return;
                }
                
                break;
                
            case RIGHT: //jobb
                
                j++;
                if (j >= n) {
                    k.setDest(k.getDest().getOpposite());
                    return;
                }
                
                break;
                
            case DOWN: //le
                
                i++;
                if (i >= n) {
                    k.setDest(k.getDest().getOpposite());
                    return;
                }
                
                break;
        }

        Puppet p = pups[i][j];
        if (p instanceof Tree || p instanceof Keeper || p instanceof Basket) {
            k.setDest(k.getDest().getOpposite());
            return;
        }
        
        pups[k.getI()][k.getJ()] = null;
        pups[i][j] = k;
        k.setI(i);
        k.setJ(j);
        
        testForYogi(i, j);
        
    }

    private void testForYogi(int i, int j) {
        
        int x = yogi.getI();
        int y = yogi.getJ();
        
        int deltaI = Math.abs(i - x);
        int deltaJ = Math.abs(j - y);
        
        if (deltaI <= 1 && deltaJ <= 1) {
            lost = true;
        }
    }

}
