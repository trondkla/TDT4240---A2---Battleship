package tdt4240.a2.states;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import tdt4240.a2.controller.OceanSpaceController;
import tdt4240.a2.controller.WarshipController;
import tdt4240.a2.model.OceanSpaceSize;
import tdt4240.a2.model.Player;
import tdt4240.a2.model.WarshipModel;
import tdt4240.a2.model.WarshipType;
import tdt4240.a2.variables.StaticVariables;

import java.util.concurrent.TimeUnit;

public class GameState extends State{

    private OceanSpaceController oceanSpaceController;
    private Player player;
    private StaticVariables variables = StaticVariables.getInstance();

    private GameLoop gameLoop;

    public GameState(Context context, WarshipController[] warshipControllers){
        //player.addPropertyChangeListener(); // Listen for change of player state (fire / observe)
        super(context);

        oceanSpaceController = new OceanSpaceController(variables.getOceanSpaceSize(), new Player("WHORE", Color.RED),
                warshipControllers);
        gameLoop = new GameLoop();
    }

    @Override
    public View getView() {
        return this;
    }

    protected void onDraw(Canvas canvas){
        oceanSpaceController.update(canvas);
        if(oceanSpaceController.isGameOver()){
            pop();
            push(new GameOver(variables.getActivity().getApplicationContext()));
        }
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

    public void onBackPressed() {
        // Ignore menu item and show game paused state
        push(new GamePause(variables.getActivity().getApplicationContext()));
        variables.getActivity().setContentView(StateMachine.getInstance(null).getContentView());
    }
}
