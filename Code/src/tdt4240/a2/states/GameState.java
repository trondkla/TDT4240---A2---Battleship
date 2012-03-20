package tdt4240.a2.states;

import android.view.View;
import tdt4240.a2.view.OceanSpace;

public class GameState extends State{

    private boolean running;

    private OceanSpace oceanSpace;

    public GameState(){
        running = true;

    }

    private void gameLoop(){
        while(running){

        }
    }

    @Override
    public View getView() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
