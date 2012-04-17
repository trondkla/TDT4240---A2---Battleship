package tdt4240.a2.model;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import tdt4240.a2.variables.StaticVariables;

/**
 */
public class WarshipModel extends AbstractModel {
    private WarshipType warshipType;
    private WarshipState[] warshipTiles;
    private boolean horizontal; // for drawing
    private int xPosition; // x - position in oceanspace(tile)
    private int yPosition; // y - position in oceanspace(tile)
    private boolean isPrepairing;
    private boolean isSelected;

    public WarshipModel(WarshipType warshipType){
        this.warshipType = warshipType;
        warshipTiles = new WarshipState[warshipType.getSize()];
        isSelected = false;
        // INIT isFloating
        for(int i=0; i < this.warshipTiles.length; i++){
            this.warshipTiles[i] = WarshipState.NOT_HIT; // Setting all to floating as initial value
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

    public void setHorizontal(boolean horizontal) {
        this.horizontal = horizontal;
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
        StaticVariables variables = StaticVariables.getInstance();
        int pixelPerTile = variables.getPixelPerTile();
        if(isFloating() && !isPrepairing())
            return new Rect();
        else {
            Rect shipRect;
            if(horizontal)
                shipRect = new Rect(xPosition*pixelPerTile, yPosition*pixelPerTile + variables.getGridOffset(),
                        xPosition*pixelPerTile + warshipType
                        .getSize()*pixelPerTile, yPosition*pixelPerTile +  pixelPerTile + variables.getGridOffset());
            else
                shipRect = new Rect(xPosition*pixelPerTile, yPosition*pixelPerTile + variables.getGridOffset(),
                        xPosition*pixelPerTile + pixelPerTile,
                        yPosition*pixelPerTile + warshipType.getSize()*pixelPerTile + variables.getGridOffset());

            // Checking if the ship is displayed.

            // Return the ship for painting.
            return shipRect;
        }
    }
       
    public Rect getTileRect(){
        if(horizontal)
            return new Rect(xPosition, yPosition, xPosition + warshipType.getSize(), yPosition + 1);
        else
            return new Rect(xPosition, yPosition, xPosition + 1, yPosition + warshipType.getSize());
    }
    
    public Rect[] getBombedTiles(){
        StaticVariables variables = StaticVariables.getInstance();
        int pixelPerTile = variables.getPixelPerTile();
        Rect[] bombedTiles = new Rect[warshipType.getSize()];
        int i = 0;
        for(WarshipState state : warshipTiles){
            if(state == WarshipState.NOT_HIT)
                bombedTiles[i] = null;
            else{
                if(isHorizontal())
                    bombedTiles[i] = new Rect(xPosition*pixelPerTile + i*pixelPerTile,
                            yPosition*pixelPerTile + variables.getGridOffset(),
                            xPosition*pixelPerTile + i*pixelPerTile + pixelPerTile,
                            yPosition*pixelPerTile +  pixelPerTile + variables.getGridOffset());
                else
                    bombedTiles[i] = new Rect(xPosition*pixelPerTile,
                            yPosition*pixelPerTile + pixelPerTile*i + variables.getGridOffset(),
                            xPosition*pixelPerTile + pixelPerTile,
                            yPosition*pixelPerTile + pixelPerTile*i + pixelPerTile + variables.getGridOffset());
            }
            i++;
        }
        return bombedTiles;
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
            Log.d("LaHAW ERROR","WarshipModel("+yPosition+","+xPosition+","+isHorizontal()+").bombTile("+x+"," +
                    ""+y+") - Index out of bounds.");
        }
    }

    public WarshipType getWarshipType(){
        return warshipType;
    }

    public boolean isFloating(){
        boolean floating = false;
        for(WarshipState state : warshipTiles){
            if(state == WarshipState.NOT_HIT){
                floating = true;
                break;
            }
        }
        return floating;
    }

    public boolean isPrepairing() {
        return isPrepairing;
    }

    public void setPrepairing(boolean prepairing) {
        isPrepairing = prepairing;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
