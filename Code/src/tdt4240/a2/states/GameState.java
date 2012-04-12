package tdt4240.a2.states;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import tdt4240.a2.controller.OceanSpaceController;
import tdt4240.a2.controller.WarshipController;
import tdt4240.a2.model.OceanSpaceSize;
import tdt4240.a2.model.Player;
import tdt4240.a2.model.WarshipModel;
import tdt4240.a2.model.WarshipType;

import java.util.concurrent.TimeUnit;

public class GameState extends State{

    private boolean running;

    private OceanSpaceController oceanSpaceController;
    private Player player;

    private GameLoop gameLoop;

    public GameState(Context context,Activity activity){
        //player.addPropertyChangeListener(); // Listen for change of player state (fire / observe)
        super(context,activity);

        WarshipController[] warshipControllers = new WarshipController[3];
        warshipControllers[0] = new WarshipController(WarshipType.AIRCRAFT_CARRIER);
        ((WarshipModel) warshipControllers[0].getRegisteredModel()).placeShip(1,1,false);
        warshipControllers[1] = new WarshipController(WarshipType.BATTLESHIP);
        ((WarshipModel) warshipControllers[1].getRegisteredModel()).placeShip(3,8,true);
        warshipControllers[2] = new WarshipController(WarshipType.SUBMARINE);
        ((WarshipModel) warshipControllers[2].getRegisteredModel()).placeShip(5,3,true);

        oceanSpaceController = new OceanSpaceController(OceanSpaceSize.SMALL, new Player("HORE", Color.RED),
                warshipControllers);
        gameLoop = new GameLoop();
    }

    @Override
    public View getView() {
        return this;
    }

    protected void onDraw(Canvas canvas){
        oceanSpaceController.update(canvas);
        gameLoop.start();
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
        return oceanSpaceController.handleTouchEvent(motionEvent);
    }
}
