package tdt4240.a2.model;

import android.graphics.Rect;
import android.util.Log;
import tdt4240.a2.variables.StaticVariables;

import java.util.ArrayList;

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
        for(int i=0; i < oceanSpace.length; i++){
            for(int j=0; j < oceanSpace.length; j++){
                oceanSpace[i][j] = OceanTile.EMPTY;
            }
        }
        oceanSpace[9][9] = OceanTile.EMPTY_BOMBED;
        oceanSpace[8][8] = OceanTile.EMPTY_BOMBED;
        oceanSpace[8][9] = OceanTile.EMPTY_BOMBED;
        oceanSpace[9][8] = OceanTile.EMPTY_BOMBED;
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

    public Rect[] getBombedTiles(){

        ArrayList<Rect> rectList = new ArrayList<Rect>();
        StaticVariables variables = StaticVariables.getInstance();
        
        for(int i=0; i < oceanSpace.length; i++){
            for(int j=0; j < oceanSpace.length; j++){
                if(oceanSpace[i][j] == OceanTile.EMPTY_BOMBED)
                    rectList.add(new Rect(i*variables.getPixelPerTile(),
                            j*variables.getPixelPerTile() + variables.getGridOffset(),
                            i*variables.getPixelPerTile() + variables.getPixelPerTile(),
                            j*variables.getPixelPerTile() + variables.getPixelPerTile() + variables.getGridOffset()));
            }
        }

        Rect[] rects = new Rect[rectList.size()];
        for(int i=0; i < rectList.size(); i++)
            rects[i] = rectList.get(i);

        return rects;
    }

    /**
     * Returns an enum with size fields
     * @return OceanSpaceSize enum
     */
    public OceanSpaceSize getOceanSpaceSize(){
        return oceanSpaceSize;
    }
}
