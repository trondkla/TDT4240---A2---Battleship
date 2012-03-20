package tdt4240.a2.model;

import android.graphics.Color;

/**
 */
public class Player extends AbstractModel{
    
    private String name;
    private Color color;
    private PlayerState playerState;
    
    public Player(String name, Color color){
        this.name = name;
        this.color = color;
        this.playerState = PlayerState.OBSERVE;
    }
    
    public Color getColor(){
        return this.color;
    }
    
    public String getName(){
        return this.name;
    }

    public void swapPlayerState(){
        if(playerState == PlayerState.OBSERVE)
            playerState = PlayerState.FIRE;
        else
            playerState = PlayerState.OBSERVE;
    }

    public PlayerState getPlayerState(){
        return this.playerState;
    }
}
