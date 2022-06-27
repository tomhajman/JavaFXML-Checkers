/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.finalprj;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author kryst
 */
public class Highlight {
    private final int SIDE = 48;
    private Rectangle rectangle;
    
    public Highlight () {
        rectangle = new Rectangle(SIDE, SIDE);
        rectangle.setFill(Color.TEAL);
        rectangle.setOpacity(0.4);
    }
    
    public Rectangle getHighlight() {
        return this.rectangle;
    }
}
