package tdt4240.a2.model;

import android.graphics.Rect;
import tdt4240.a2.variables.StaticVariables;

/**
 */
public class Warship extends AbstractModel {
    private WarshipType warshipType;
    private WarshipState[] isFloating;
    private boolean horizontal; // for drawing
    private int xPosition; // x - position in oceanspace(tile)
    private int yPosition; // y - position in oceanspace(tile)

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
        int pixelPerTile = StaticVariables.getInstance().getPixelPerTile();
        if(horizontal)
            return new Rect(xPosition*pixelPerTile, yPosition*pixelPerTile, warshipType.getSize()*pixelPerTile, pixelPerTile);
        else
            return new Rect(xPosition*pixelPerTile, yPosition*pixelPerTile, pixelPerTile, warshipType.getSize()*pixelPerTile);
    }
}
