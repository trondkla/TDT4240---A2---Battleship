package tdt4240.a2.states;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import tdt4240.a2.R;
import tdt4240.a2.controller.OceanSpace;
import tdt4240.a2.controller.Warship;
import tdt4240.a2.model.OceanSpaceSize;
import tdt4240.a2.model.Player;
import tdt4240.a2.model.WarshipType;

import java.util.concurrent.TimeUnit;

public class GameState extends State{

    private boolean running;

    private OceanSpace oceanSpace;
    private Player player;
    private Activity activity;

    private GameLoop gameLoop;

    public GameState(Context context,Activity activity){
        //player.addPropertyChangeListener(); // Listen for change of player state (fire / observe)
        super(context,activity);
        this.activity = activity;

        Warship[] warships = new Warship[3];
        warships[0] = new Warship(WarshipType.AIRCRAFT_CARRIER);
        ((tdt4240.a2.model.Warship)warships[0].getRegisteredModel()).placeShip(1,1,false);
        warships[1] = new Warship(WarshipType.BATTLESHIP);
        ((tdt4240.a2.model.Warship)warships[1].getRegisteredModel()).placeShip(3,8,true);
        warships[2] = new Warship(WarshipType.SUBMARINE);
        ((tdt4240.a2.model.Warship)warships[2].getRegisteredModel()).placeShip(5,3,true);

        oceanSpace = new OceanSpace(OceanSpaceSize.LARGE, new Player("HORE", Color.RED),warships);
        gameLoop = new GameLoop();
    }

    @Override
    public View getView() {
        return this;
    }

    protected void onDraw(Canvas canvas){
        oceanSpace.update(canvas);
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
                    Log.d("LaHAWDEBUG","SOVER OG TEGNER");
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
}
