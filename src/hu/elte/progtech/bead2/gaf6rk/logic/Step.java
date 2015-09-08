/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hu.elte.progtech.bead2.gaf6rk.logic;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 *
 * @author gaf6rk
 */
public enum Step {
    
    UP {

        @Override
        public Step getOpposite() {
            return DOWN;
        }
    }, 
    DOWN {

        @Override
        public Step getOpposite() {
            return UP;
        }
    },
    RIGHT {

        @Override
        public Step getOpposite() {
            return LEFT;
        }
    },
    LEFT {

        @Override
        public Step getOpposite() {
            return RIGHT;
        }
    };
    
    
    private static final List<Step> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random r = new Random();
    
    public static Step randomStep() {
        return VALUES.get(r.nextInt(SIZE));
    }
    
    public abstract Step getOpposite();
}
