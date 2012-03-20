package tdt4240.a2.controller;


import tdt4240.a2.model.OceanTile;
import tdt4240.a2.model.Player;

import java.beans.PropertyChangeSupport;

/**
 */
public class OceanSpace extends AbstractController{

    private tdt4240.a2.model.OceanSpace model;
    private tdt4240.a2.view.OceanSpace view;
    private tdt4240.a2.model.Warship[] warships;


    public OceanSpace(tdt4240.a2.model.OceanSpaceSize oceanSpaceSize, Player player, tdt4240.a2.model.Warship[] warships){
        super();
        this.warships = warships;
        this.model = new tdt4240.a2.model.OceanSpace(oceanSpaceSize, player, warships);
        this.view = new tdt4240.a2.view.OceanSpace(this.model);
    }

    public void bombOceanTile(int x, int y){
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



}
