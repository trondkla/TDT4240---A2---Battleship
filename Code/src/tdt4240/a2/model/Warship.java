package tdt4240.a2.model;

import tdt4240.a2.model.WarshipType;

/**
 */
public class Warship {
    private WarshipType warshipType;
    private boolean[] isFloating;
    private boolean horizontal; // for drawing
    private int xPosition; //
    private int yPosition; //

    public Warship(WarshipType warshipType){
        this.warshipType = warshipType;
        isFloating = new boolean[warshipType.getSize()];
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

    public boolean[] getFloating() {
        return isFloating;
    }
}
