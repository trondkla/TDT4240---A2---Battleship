package tdt4240.a2.controller;


import android.graphics.Canvas;
import android.graphics.Rect;
import tdt4240.a2.model.AbstractModel;
import tdt4240.a2.model.WarshipModel;
import tdt4240.a2.model.WarshipType;
import tdt4240.a2.variables.StaticVariables;
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
            return ((WarshipModel) model).bombTile(xCoordinate,yCoordinate);
        }
        return false;
    }

    public void placeShip(int x, int y, boolean horizontal){
        StaticVariables settings = StaticVariables.getInstance();
        // Check if it goes on the outside of the grid
        WarshipModel model = (WarshipModel)this.getRegisteredModel();
        if(model.isHorizontal()){
            if((x+model.getWarshipType().getSize()) > settings.getOceanSpaceSize().getSize()){
                int offset = (x+model.getWarshipType().getSize()) - settings.getOceanSpaceSize().getSize();
                model.placeShip(x-offset,y,horizontal);
            } else {
                model.placeShip(x,y,horizontal);
            }
        } else {
            if((y+model.getWarshipType().getSize()) > settings.getOceanSpaceSize().getSize()){
                int offset = (y+model.getWarshipType().getSize()) - settings.getOceanSpaceSize().getSize();
                model.placeShip(x,y-offset,horizontal);
            } else {
                model.placeShip(x,y,horizontal);
            }
        }
    }

    public void setVisible(){
        ((WarshipModel)this.getRegisteredModel()).setPrepairing(true);
    }

    public boolean isHorizontal(){
        return ((WarshipModel)this.getRegisteredModel()).isHorizontal();
    }
    
    public int getX(){
        return ((WarshipModel)this.getRegisteredModel()).getXPosition();
    }

    public int getY(){
        return ((WarshipModel)this.getRegisteredModel()).getYPosition();
    }

    public void turnShip(){
        WarshipModel model = (WarshipModel)this.getRegisteredModel();
        model.setHorizontal(!model.isHorizontal());
    }

    public void update(Canvas canvas) {
        this.getRegisteredView().draw(canvas);
    }
}
