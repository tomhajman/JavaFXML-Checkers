/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.finalprj;

/**
 *
 * @author kryst
 */
public class Move {
    // Normal = 0, Kill = 1, Kill Multiple = 2
    private int type;
    private int tarColumn;
    private int tarRow;
    private int killColumn;
    private int killRow;
    
    public Move(int type, int tarColumn, int tarRow){
        this.type = type;
        this.tarColumn = tarColumn;
        this.tarRow = tarRow;
    }
    
    public Move(int type, int tarColumn, int tarRow, int killColumn, int killRow) {
        this.type = type;
        this.tarColumn = tarColumn;
        this.tarRow = tarRow;
        this.killColumn = killColumn;
        this.killRow = killRow;
    }

    /**
     * @return the type
     */
    public int getType() {
        return type;
    }

    /**
     * @return the tarColumn
     */
    public int getTarColumn() {
        return tarColumn;
    }

    /**
     * @return the tarRow
     */
    public int getTarRow() {
        return tarRow;
    }

    /**
     * @return the killColumn
     */
    public int getKillColumn() {
        return killColumn;
    }

    /**
     * @return the killRow
     */
    public int getKillRow() {
        return killRow;
    }
}
