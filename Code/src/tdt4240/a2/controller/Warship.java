package tdt4240.a2.controller;


import android.graphics.Rect;
import tdt4240.a2.model.AbstractModel;

/**
 * Created by IntelliJ IDEA.
 * User: tk
 * Date: 15.03.12
 * Time: 11.21
 * To change this template use File | Settings | File Templates.
 */
public class Warship extends AbstractController {

    public Warship(){
        super();
    }

    /**
     *
     * @param xCoordinate Raw coordinate? or tilecoordinate?
     * @param yCoordinate Raw coordinate? or tilecoordinate?
     * @return
     */
    public boolean checkHit(int xCoordinate, int yCoordinate){
        // if raw(screen coordinates) do nothing
        // if tile calculate raw
        // use getRect from the model and check if the coordinate is in the rectangle
        for(AbstractModel model : this.getRegisteredModels()){
            Rect boundingBox = ((tdt4240.a2.model.Warship)model).getTileRect();
            if(boundingBox.contains(xCoordinate, yCoordinate))
                return true;
        }
        return false;
    }
}
