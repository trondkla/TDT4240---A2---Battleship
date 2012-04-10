package tdt4240.a2.controller;


import android.graphics.Canvas;
import android.util.Log;
import tdt4240.a2.model.*;
import tdt4240.a2.view.OceanSpaceView;

/**
 */
public class OceanSpaceController extends AbstractController{

    private WarshipModel[] warshipModelModels;
    private WarshipController[] warshipControllerControllers;

    public OceanSpaceController(OceanSpaceSize oceanSpaceSize, Player player,
                                WarshipController[] warshipControllerControllers){
        super();
        this.warshipModelModels = warshipModelModels;
        this.warshipControllerControllers = warshipControllerControllers;
        this.addModel(new OceanSpaceModel(oceanSpaceSize, player, warshipModelModels));
        this.addView(new OceanSpaceView((OceanSpaceModel)this.getRegisteredModel()));
    }

    /*
    public void bombOceanTile(int x, int y){
        tdt4240.a2.model.OceanSpaceModel model = (tdt4240.a2.model.OceanSpaceModel)this.getRegisteredModel();
        if(model.getOceanTile(x, y) == OceanTile.EMPTY){
            model.setOceanTile(OceanTile.EMPTY_BOMBED, x, y);
        }else if(model.getOceanTile(x, y) == OceanTile.OCCUPIED){
            for(tdt4240.a2.model.WarshipModel warship : warships){
                if(warship.getTileRect().contains(x,y)){
                    warship.bombTile(x,y);
                }
            }
        }else if(model.getOceanTile(x, y) == OceanTile.EMPTY_BOMBED){

        }
    }
    */

    public void update(Canvas canvas){
        getRegisteredView().draw(canvas);
        for(WarshipController warshipController : warshipControllerControllers){
            warshipController.update(canvas);
        }
    }

}
