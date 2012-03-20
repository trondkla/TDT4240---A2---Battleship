package tdt4240.a2.model;

import android.graphics.Color;

/**
 */
public class Player extends AbstractModel{
    
    private String name;
    private Color color;
    //add more smart shit
    
    public Player(String name, Color color){
        this.name = name;
        this.color = color;
    }
}
