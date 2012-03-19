package tdt4240.a2.model;

import android.graphics.Rect;

/**
 */
public class Warship {
    private WarshipType warshipType;
    private WarshipState[] isFloating;
    private boolean horizontal; // for drawing
    private int xPosition; //
    private int yPosition; //

    public Warship(WarshipType warshipType){
        this.warshipType = warshipType;
        isFloating = new WarshipState[warshipType.getSize()];
        // INIT isFloating
        for(WarshipState state : isFloating){
            state = WarshipState.FLOATING; // Setting all to floating as initial value
        }
    }

    public void placeShip(int xPosition, int yPosition, boolean horizontal){
        // Sett values
    }

    public boolean isHorizontal() {
        return horizontal;
    }

    public int getxPosition() {
        return xPosition;
    }

    public int getyPosition() {
        return yPosition;
    }

    public Rect getRect(){
        return new Rect();
    }
}
