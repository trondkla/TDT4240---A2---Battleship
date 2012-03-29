package tdt4240.a2.controller;


import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import tdt4240.a2.model.*;
import tdt4240.a2.model.Warship;
import tdt4240.a2.view.AbstractView;

import java.beans.PropertyChangeSupport;

/**
 */
public class OceanSpace extends AbstractController{

    private tdt4240.a2.model.Warship[] warshipModels;
    private tdt4240.a2.controller.Warship[] warshipControllers;

    public OceanSpace(OceanSpaceSize oceanSpaceSize, Player player,
                       tdt4240.a2.controller.Warship[] warshipControllers){
        super();
        this.warshipModels = warshipModels;
        this.warshipControllers = warshipControllers;
        this.addModel(new tdt4240.a2.model.OceanSpace(oceanSpaceSize, player, warshipModels));
        this.addView(new tdt4240.a2.view.OceanSpace((tdt4240.a2.model.OceanSpace)this.getRegisteredModel()));
    }

    /*
    public void bombOceanTile(int x, int y){
        tdt4240.a2.model.OceanSpace model = (tdt4240.a2.model.OceanSpace)this.getRegisteredModel();
        if(model.getOceanTile(x, y) == OceanTile.EMPTY){
            model.setOceanTile(OceanTile.EMPTY_BOMBED, x, y);
        }else if(model.getOceanTile(x, y) == OceanTile.OCCUPIED){
            for(tdt4240.a2.model.Warship warship : warships){
                if(warship.getTileRect().contains(x,y)){
                    warship.bombTile(x,y);
                }
            }
        }else if(model.getOceanTile(x, y) == OceanTile.EMPTY_BOMBED){

        }
    }
    */

    public void update(Canvas canvas){
        Log.d("LaHAWDEBUG","Controller?");
        getRegisteredView().draw(canvas);
        for(tdt4240.a2.controller.Warship warship : warshipControllers){
            warship.update(canvas);
        }
    }

}
