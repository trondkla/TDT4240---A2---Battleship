package tdt4240.a2.model;

import android.graphics.Color;
import android.graphics.Paint;

/**
 */
public class Player extends AbstractModel{
    
    private String name;
    private int color;
    private PlayerState playerState;
    
    public Player(String name, int color){
        this.name = name;
        this.color = color;
        this.playerState = PlayerState.FIRE;
    }
    
    public Paint getColor(){
        Paint paint = new Paint();
        paint.setColor(this.color);
        return paint;
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
