package tdt4240.a2.states;

import android.content.Context;
import android.graphics.*;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;
import tdt4240.a2.R;
import tdt4240.a2.controller.OceanSpaceController;
import tdt4240.a2.controller.WarshipController;
import tdt4240.a2.model.Player;
import tdt4240.a2.model.WarshipModel;
import tdt4240.a2.model.WarshipType;
import tdt4240.a2.variables.StaticVariables;

import java.util.concurrent.TimeUnit;

/**
 */
public class GamePreparation extends State {

    private StaticVariables variables = StaticVariables.getInstance();
    private OceanSpaceController oceanSpaceController;
    private WarshipController[] warshipControllers;
    private GameLoop gameLoop = new GameLoop();
    private int shipLockedToFingerNr = -1;

    public GamePreparation(Context context){
        super(context);

        // Fill warship controller in top left
        warshipControllers = new WarshipController[8];

        warshipControllers[0] = new WarshipController(WarshipType.AIRCRAFT_CARRIER);
        warshipControllers[1] = new WarshipController(WarshipType.BATTLESHIP);
        warshipControllers[2] = new WarshipController(WarshipType.BATTLESHIP);
        warshipControllers[3] = new WarshipController(WarshipType.SUBMARINE);
        warshipControllers[4] = new WarshipController(WarshipType.DESTROYER);
        warshipControllers[5] = new WarshipController(WarshipType.PATROL_BOAT);
        warshipControllers[6] = new WarshipController(WarshipType.PATROL_BOAT);
        warshipControllers[7] = new WarshipController(WarshipType.PATROL_BOAT);
        
        for(int i=0; i < warshipControllers.length; i++){
            ((WarshipModel)warshipControllers[i].getRegisteredModel()).placeShip(0, i, true);
            ((WarshipModel)warshipControllers[i].getRegisteredModel()).setPrepairing(true);
        }

        oceanSpaceController = new OceanSpaceController(variables.getOceanSpaceSize(), new Player("WHORE",
                Color.RED), warshipControllers);
    }

    @Override
    protected void onDraw(Canvas canvas){
        // Draw all the ships
        oceanSpaceController.update(canvas);
        gameLoop.start();
    }
    
    @Override
    public View getView() {
        return this;
    }

    private class GameLoop extends Thread
    {
        private volatile boolean running = true;

        public void run()
        {
            while(running)
            {
                try {
                    TimeUnit.MILLISECONDS.sleep(1);
                    postInvalidate();
                    pause();
                }
                catch(InterruptedException ex)
                {
                    running = false;
                }
            }
        }

        public void pause()
        {
            running = false;
        }
        public void start()
        {
            running = true;
            run();
        }
    }

    public boolean onTouch(MotionEvent motionEvent){
        int gridY = (int)((motionEvent.getY() - variables.getGridOffset())/variables.getPixelPerTile());
        int gridX = (int)(motionEvent.getX()/variables.getPixelPerTile());

        if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
            // is there a ship there lock it to your finger
            for(int i=0; i < warshipControllers.length; i++){
                if(((WarshipModel)warshipControllers[i].getRegisteredModel()).getTileRect().contains(gridX, gridY)){
                    shipLockedToFingerNr = i;
                    ((WarshipModel)warshipControllers[shipLockedToFingerNr].getRegisteredModel()).setSelected(true);
                    break;
                }
            }
        } else if(motionEvent.getAction() == MotionEvent.ACTION_UP) {
            if(shipLockedToFingerNr != -1){
                if(gridX < variables.getOceanSpaceSize().getSize() &&  gridY < variables.getOceanSpaceSize().getSize
                        () && gridX > -1 && gridY > -1){
                    warshipControllers[shipLockedToFingerNr].placeShip(gridX,gridY,
                        ((WarshipModel)warshipControllers[shipLockedToFingerNr].getRegisteredModel()).isHorizontal());
                    ((WarshipModel)warshipControllers[shipLockedToFingerNr].getRegisteredModel()).setSelected(false);
                }
                    shipLockedToFingerNr = -1;
            }
        } else if(motionEvent.getAction() == 262) {
            if(motionEvent.getAction() == 262){
                if(shipLockedToFingerNr != -1){
                    warshipControllers[shipLockedToFingerNr].turnShip();
                }
            }
        }
        return true;
    }
}
