package tdt4240.a2.model;

import android.graphics.Color;

/**
 */
public class Player extends AbstractModel{
    
    private String name;
    private Color color;
    
    public Player(String name, Color color){
        this.name = name;
        this.color = color;
    }
    
    public Color getColor(){
        return this.color;
    }
    
    public String getName(){
        return this.name;
    }
}
