package tdt4240.a2.controller;


import tdt4240.a2.model.OceanTile;
import tdt4240.a2.model.Player;

import java.beans.PropertyChangeSupport;

/**
 */
public class OceanSpace extends AbstractController{

    private tdt4240.a2.model.OceanSpace model;
    private tdt4240.a2.view.OceanSpace view;


    public OceanSpace(tdt4240.a2.model.OceanSpaceSize oceanSpaceSize, Player player){
        super();
        this.model = new tdt4240.a2.model.OceanSpace(oceanSpaceSize, player);
        this.view = new tdt4240.a2.view.OceanSpace(this.model);
    }

    public void bombOceanTile(int x, int y){
        if(model.getOceanTile(x, y) == OceanTile.EMPTY){
            model.setOceanTile(OceanTile.EMPTY_BOMBED, x, y);
        }else if(model.getOceanTile(x, y) == OceanTile.OCCUPIED){

        }else if(model.getOceanTile(x, y) == OceanTile.EMPTY_BOMBED){

        }

    }



}
