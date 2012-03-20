package tdt4240.a2.model;

import android.graphics.Rect;

import java.beans.PropertyChangeSupport;

/**
 */
public class OceanSpace extends AbstractModel{

    private OceanSpaceSize oceanSpaceSize;
    private OceanTile oceanSpace[][];
    private Player player;
    private String propertyName = "OceanSpace";

    public OceanSpace(OceanSpaceSize oceanSpaceSize, Player player){
        this.oceanSpaceSize = oceanSpaceSize;
        this.player = player;
        oceanSpace = new OceanTile[oceanSpaceSize.getSize()][oceanSpaceSize.getSize()];

    }
    
    public OceanTile[][] getOceanSpace(){
        return oceanSpace;
    }

    public OceanTile getOceanTile(int x, int y){
        if((x < oceanSpaceSize.getSize() && y < oceanSpaceSize.getSize()) && (x >= 0 && y >= 0)){
            return oceanSpace[x][y];
        }else{
            throw new IndexOutOfBoundsException();
        }
    }

    public void setOceanTile(OceanTile oceanTile, int x, int y){
        OceanTile oldValue = oceanSpace[x][y];
        oceanSpace[x][y] = oceanTile;
        firePropertyChangeEvent(oceanTile.getPropertyName(), oldValue, oceanTile);
    }

    public Rect getRect(){
        return new Rect(0,0,10,10); //TODO
    }
}
