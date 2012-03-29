package tdt4240.a2.model;

import android.graphics.Rect;
import android.util.Log;
import tdt4240.a2.variables.StaticVariables;

/**
 */
public class Warship extends AbstractModel {
    private WarshipType warshipType;
    private WarshipState[] warshipTiles;
    private boolean horizontal; // for drawing
    private int xPosition; // x - position in oceanspace(tile)
    private int yPosition; // y - position in oceanspace(tile)

    public Warship(WarshipType warshipType){
        this.warshipType = warshipType;
        warshipTiles = new WarshipState[warshipType.getSize()];
        // INIT isFloating
        for(WarshipState state : warshipTiles){
            state = WarshipState.NOT_HIT; // Setting all to floating as initial value
        }
    }

    /**
     *
     * @param xPosition
     * @param yPosition
     * @param horizontal
     */
    public void placeShip(int xPosition, int yPosition, boolean horizontal){
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.horizontal = horizontal;
    }

    public boolean isHorizontal() {
        return horizontal;
    }

    public int getXPosition() {
        return xPosition;
    }

    public int getYPosition() {
        return yPosition;
    }

    /**
     * Rectangle anchored to pixel correct coordinates on the screen.
     * @return
     */
    public Rect getRect(){
        int pixelPerTile = StaticVariables.getInstance().getPixelPerTile();
        if(horizontal)
            return new Rect(xPosition*pixelPerTile, yPosition*pixelPerTile, xPosition*pixelPerTile + warshipType
                    .getSize()*pixelPerTile, yPosition*pixelPerTile +  pixelPerTile);
        else
            return new Rect(xPosition*pixelPerTile, yPosition*pixelPerTile, xPosition*pixelPerTile + pixelPerTile,
                    yPosition*pixelPerTile + warshipType.getSize()*pixelPerTile);
    }
    
    public Rect getTileRect(){
        if(horizontal)
            return new Rect(xPosition, yPosition, xPosition + warshipType.getSize(), yPosition + 1);
        else
            return new Rect(xPosition, yPosition, xPosition + 1, yPosition + warshipType.getSize());
    }

    /**
     * Takes in parameters where the shot is fired on the grid and registers the hit
     * @param x
     * @param y
     */
    public void bombTile(int x, int y){
        try{
            if(isHorizontal()){
                warshipTiles[x - xPosition] = WarshipState.HIT;
            }else{
                warshipTiles[y - yPosition] = WarshipState.HIT;
            }
        } catch (IndexOutOfBoundsException e){
            Log.d("LaHAW ERROR","Warship("+yPosition+","+xPosition+","+isHorizontal()+").bombTile("+x+"," +
                    ""+y+") - Index out of bounds.");
        }
    }

}
