package tdt4240.a2.controller;


import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;
import tdt4240.a2.model.*;
import tdt4240.a2.variables.StaticVariables;
import tdt4240.a2.view.OceanSpaceView;

/**
 */
public class OceanSpaceController extends AbstractController{

    private WarshipModel[] warshipModelModels;
    private WarshipController[] warshipControllerControllers;
    private StaticVariables variables = StaticVariables.getInstance();

    public OceanSpaceController(OceanSpaceSize oceanSpaceSize, Player player,
                                WarshipController[] warshipControllerControllers){
        super();
        this.warshipModelModels = new WarshipModel[warshipControllerControllers.length];
        for(int i=0; i < warshipControllerControllers.length; i++)
            this.warshipModelModels[i] = (WarshipModel)warshipControllerControllers[i].getRegisteredModel();
        
        this.warshipControllerControllers = warshipControllerControllers;
        this.addModel(new OceanSpaceModel(oceanSpaceSize, player, warshipModelModels));
        this.addView(new OceanSpaceView((OceanSpaceModel)this.getRegisteredModel()));
    }


    public void bombOceanTile(int x, int y){
        OceanSpaceModel model = (OceanSpaceModel)this.getRegisteredModel();
        if(model.getOceanTile(x, y) == OceanTile.EMPTY){
            model.setOceanTile(OceanTile.EMPTY_BOMBED, x, y);
        }else if(model.getOceanTile(x, y) == OceanTile.OCCUPIED){
            for(WarshipController warship : warshipControllerControllers){
                if(warship.checkHit(x,y)){
                    break;
                }
            }
        }else if(model.getOceanTile(x, y) == OceanTile.EMPTY_BOMBED){
            // Do nothing?!
        }


    }

    public boolean handleTouchEvent(MotionEvent motionEvent){
        // calculate what tile is pushed
        if(motionEvent.getY() > variables.getGridOffset() && motionEvent.getY() < variables.getCanvasPixelHeight()
                -variables.getGridOffset()){
            // should now be inside the grid
            int gridY = (int)((motionEvent.getY() - variables.getGridOffset())/variables.getPixelPerTile());
            int gridX = (int)(motionEvent.getX()/variables.getPixelPerTile());
            bombOceanTile(gridX,gridY);
        }
        return true;
    }

    public void update(Canvas canvas){
        getRegisteredView().draw(canvas);
        for(WarshipController warshipController : warshipControllerControllers){
            warshipController.update(canvas);
        }
    }

}
