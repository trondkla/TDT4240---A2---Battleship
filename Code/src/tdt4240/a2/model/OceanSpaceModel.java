package tdt4240.a2.model;

import android.graphics.Rect;
import tdt4240.a2.variables.StaticVariables;

/**
 */
public class OceanSpaceModel extends AbstractModel{

    private OceanSpaceSize oceanSpaceSize;
    private OceanTile oceanSpace[][];
    private Player player;
    private String propertyName = "OceanSpaceModel";
    private WarshipModel[] warshipModels;

    public OceanSpaceModel(OceanSpaceSize oceanSpaceSize, Player player, WarshipModel[] warshipModels){
        this.oceanSpaceSize = oceanSpaceSize;
        this.player = player;
        this.warshipModels = warshipModels;
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
        StaticVariables variables = StaticVariables.getInstance();
        variables.setPixelPerTile(variables.getCanvasPixelWidth()/oceanSpaceSize.getSize());
        return new Rect(0,0,variables.getCanvasPixelWidth(),variables.getCanvasPixelHeight()); //TODO
    }

    /**
     * Returns an enum with size fields
     * @return OceanSpaceSize enum
     */
    public OceanSpaceSize getOceanSpaceSize(){
        return oceanSpaceSize;
    }
}
