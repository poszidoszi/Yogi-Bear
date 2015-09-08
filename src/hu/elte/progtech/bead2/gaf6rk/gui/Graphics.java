/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.progtech.bead2.gaf6rk.gui;

import hu.elte.progtech.bead2.gaf6rk.logic.Game;
import hu.elte.progtech.bead2.gaf6rk.logic.Puppet;
import hu.elte.progtech.bead2.gaf6rk.logic.Step;
import hu.elte.progtech.bead2.gaf6rk.logic.YogiBear;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

/**
 *
 * @author gaf6rk
 */
public class Graphics extends JFrame implements KeyListener {
    
    private int n = 5;
    private Game game;
    private JPanel[][] table;
    private JPanel startPanel;
    private JPanel gamePanel;

    public Graphics() {
        
        initStartPanel();
       
        setSize(500, 500);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    
    public void initGame() {
        
        Game game = new YogiBear(n);
        this.game = game;
        
        table = new JPanel[n][n];
        gamePanel = new JPanel(new GridLayout(n, n));
        
        
        for (int i = 0; i < n; i++) {

            for (int j = 0; j < n; j++) {
                
                JPanel grid = new ImgPanel();
                table[i][j] = grid;
                grid.setBorder(BorderFactory.createLineBorder(Color.black, 1));
                gamePanel.add(grid);
            }
        }
        
        renderTable();
        
        add(gamePanel);
        
        gamePanel.setFocusable(true);
        gamePanel.requestFocus();
        gamePanel.requestFocusInWindow();
        gamePanel.addKeyListener(this);
    }
    
    private void initStartPanel () {
        startPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        final JTextField textField = new JTextField();
        textField.setColumns(7);
        startPanel.add(textField);
        
        JButton button = new JButton("Start");
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String value = textField.getText();
                n = Integer.parseInt(value);
                
                remove(startPanel);
                initGame();
                validate();
            }
        });
        startPanel.add(button);
        add(startPanel);
    }
    
    
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        
        switch (keyCode) {
            case 37: //bal
                game.step(Step.LEFT);
                break;
            case 38: //fel
                game.step(Step.UP);
                break;
            case 39: //jobb
                game.step(Step.RIGHT);
                break;
            case 40: //le
                game.step(Step.DOWN);
                break;
        }
        
        renderTable();
        
        int opt = -1;
        if (game.hasWon()) {
            opt = JOptionPane.showConfirmDialog(null, "You has won! Replay?", "Won", JOptionPane.YES_NO_OPTION);     
        }
        
        if (game.hasLost()) {
            opt = JOptionPane.showConfirmDialog(null, "You has lost! Replay?", "Lost", JOptionPane.YES_NO_OPTION);
        }
        
        if (opt == JOptionPane.YES_OPTION) {
            game = null;
            remove(gamePanel);
            gamePanel = null;
            
            add(startPanel);
            validate();
            repaint();
        } else if (opt == JOptionPane.NO_OPTION) {
            System.exit(0);
        }
    }

    
    @Override
    public void keyTyped(KeyEvent e) {
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
    }

    private void renderTable() {
        Puppet[][] puppets = game.getTable();
        
        for (int i = 0; i < n; i++) {

            for (int j = 0; j < n; j++) {
                
                ImgPanel grid = (ImgPanel) table[i][j];
                
                Puppet puppet = puppets[i][j];
                
                if (puppet != null) {
                    grid.setImgPath(puppet.getImg());
                } else {
                    grid.setImgPath(null);
                }
            }
        }
        
        repaint();
    }
    
    
}
