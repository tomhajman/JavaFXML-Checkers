/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.finalprj;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.Group;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 *
 * @author kryst
 */
public class Piece {
    private Circle pc;
    private Group grp;
    
    public Group getPiece(){
        return this.grp;
    }
    
    public Piece(int type) {
        pc = new Circle(20);
        pc.setStroke(Color.BLACK);
        pc.setStrokeWidth(1);
        
        pc.setFill(type == 2 || type == 5 ? Color.BLACK : Color.WHITE);
        
        grp = new Group();
        grp.getChildren().add(pc);
        
        if(type == 4 || type == 5){
            Text txt = new Text("K");
            txt.setFill(type == 5 ? Color.WHITE : Color.BLACK);
            grp.getChildren().add(txt);
            grp.getChildren().get(1).setLayoutX(-4);
            grp.getChildren().get(1).setLayoutY(3);
        }
        
    }
}
