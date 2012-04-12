package tdt4240.a2.controller;


import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.MotionEvent;
import tdt4240.a2.model.*;
import tdt4240.a2.variables.StaticVariables;
import tdt4240.a2.view.OceanSpaceView;

/**
 */
public class OceanSpaceController extends AbstractController{

    private WarshipModel[] warshipModels;
    private WarshipController[] warshipControllers;
    private StaticVariables variables = StaticVariables.getInstance();

    public OceanSpaceController(OceanSpaceSize oceanSpaceSize, Player player,
                                WarshipController[] warshipControllers){
        super();
        this.warshipModels = new WarshipModel[warshipControllers.length];
        this.addModel(new OceanSpaceModel(oceanSpaceSize, player, warshipModels));
        this.addView(new OceanSpaceView((OceanSpaceModel)this.getRegisteredModel()));

        for(int i=0; i < warshipControllers.length; i++){
            this.warshipModels[i] = (WarshipModel) warshipControllers[i].getRegisteredModel();
            Rect warshipRect = this.warshipModels[i].getTileRect();
            if(this.warshipModels[i].isHorizontal()){
                for(int j=0; j < this.warshipModels[i].getWarshipType().getSize(); j++)
                    ((OceanSpaceModel)this.getRegisteredModel()).setOceanTile(OceanTile.OCCUPIED,warshipRect.left+j,
                            warshipRect.top);
            } else {
                for(int j=0; j < this.warshipModels[i].getWarshipType().getSize(); j++)
                    ((OceanSpaceModel)this.getRegisteredModel()).setOceanTile(OceanTile.OCCUPIED,warshipRect.left,
                            warshipRect.top+j);
            }
        }
        
        this.warshipControllers = warshipControllers;
    }


    public void bombOceanTile(int x, int y){
        OceanSpaceModel model = (OceanSpaceModel)this.getRegisteredModel();
        if(model.getOceanTile(x, y) == OceanTile.EMPTY){
            model.setOceanTile(OceanTile.EMPTY_BOMBED, x, y);
        }else if(model.getOceanTile(x, y) == OceanTile.OCCUPIED){
            for(WarshipController warship : warshipControllers){
                if(warship.checkHit(x,y)){
                    break;
                }
            }
        }else if(model.getOceanTile(x, y) == OceanTile.EMPTY_BOMBED){
            // Do nothing?!
        }


    }

    public boolean handleTouchEvent(MotionEvent motionEvent){
        if(motionEvent.getAction() == MotionEvent.ACTION_UP){
            // calculate what tile is pushed
            if(motionEvent.getY() > variables.getGridOffset() && motionEvent.getY() < variables.getCanvasPixelHeight()
                    -variables.getGridOffset()){
                // should now be inside the grid
                int gridY = (int)((motionEvent.getY() - variables.getGridOffset())/variables.getPixelPerTile());
                int gridX = (int)(motionEvent.getX()/variables.getPixelPerTile());
                bombOceanTile(gridX,gridY);
            }
        }
        return true;
    }

    public void update(Canvas canvas){
        getRegisteredView().draw(canvas);
        for(WarshipController warshipController : warshipControllers){
            warshipController.update(canvas);
        }
    }
}
