/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hu.elte.progtech.bead2.gaf6rk.gui;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author gaf6rk
 */
public class ImgPanel extends JPanel {
    private String imgPath;

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        if (imgPath == null) {
            return;
        }
        
        File file = new File(ImgPanel.class.getResource("/hu/elte/progtech/bead2/gaf6rk/gui/images/" + imgPath).getPath());
        
        Image img = null;
        try {
            img = ImageIO.read(file);
        } catch (IOException ex) {
            Logger.getLogger(ImgPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        g.drawImage(img, 0, 0, getSize().width, getSize().height, null);
    }
    
    
}
