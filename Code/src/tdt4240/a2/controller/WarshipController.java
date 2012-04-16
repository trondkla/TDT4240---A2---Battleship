package tdt4240.a2.controller;


import android.graphics.Canvas;
import android.graphics.Rect;
import tdt4240.a2.model.AbstractModel;
import tdt4240.a2.model.WarshipModel;
import tdt4240.a2.model.WarshipType;
import tdt4240.a2.view.WarshipView;

/**
 * Created by IntelliJ IDEA.
 * User: tk
 * Date: 15.03.12
 * Time: 11.21
 * To change this template use File | Settings | File Templates.
 */
public class WarshipController extends AbstractController {

    public WarshipController(WarshipType warshipType){
        super();
        this.addModel(new WarshipModel(warshipType));
        this.addView(new WarshipView((WarshipModel)this.getRegisteredModel()));
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
        AbstractModel model = this.getRegisteredModel();
        Rect boundingBox = ((WarshipModel)model).getTileRect();
        if(boundingBox.contains(xCoordinate, yCoordinate)){
            ((WarshipModel) model).bombTile(xCoordinate,yCoordinate);
            return true;
        }
        return false;
    }

    public void update(Canvas canvas) {
        this.getRegisteredView().draw(canvas);
    }
}
