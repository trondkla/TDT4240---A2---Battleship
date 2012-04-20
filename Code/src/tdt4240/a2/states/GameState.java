package tdt4240.a2.states;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import tdt4240.a2.controller.OceanSpaceController;
import tdt4240.a2.controller.WarshipController;
import tdt4240.a2.model.Player;
import tdt4240.a2.model.PlayerState;
import tdt4240.a2.model.WarshipModel;
import tdt4240.a2.variables.StaticVariables;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class GameState extends State{

    private OceanSpaceController playerOneOceanSpaceController;
    private OceanSpaceController playerTwoOceanSpaceController;
    private Player playerOne;
    private Player playerTwo;
    private StaticVariables variables = StaticVariables.getInstance();
    private GameLoop gameLoop;
    private Thread enemy;

    public GameState(Context context, WarshipController[] warshipControllers){
        super(context);

        playerOne = new Player("Player One", Color.RED);
        playerTwo = new Player("Player Two", Color.GREEN);

        playerOneOceanSpaceController = new OceanSpaceController(variables.getOceanSpaceSize(), playerOne,
                warshipControllers);

        WarshipController[] enemyWarshipControllers = new WarshipController[warshipControllers.length];
        for(int i=0; i < enemyWarshipControllers.length; i++){
            enemyWarshipControllers[i] = new WarshipController(((WarshipModel)warshipControllers[i]
                    .getRegisteredModel()).getWarshipType());
            enemyWarshipControllers[i].placeShip(warshipControllers[i].getX(),warshipControllers[i].getY(),
                    warshipControllers[i].isHorizontal());
            enemyWarshipControllers[i].setVisible();
        }

        playerTwoOceanSpaceController = new OceanSpaceController(variables.getOceanSpaceSize(), playerTwo,
                enemyWarshipControllers);
        gameLoop = new GameLoop();
        enemy = new Thread(new Runnable() {
            public void run() {
                Random randy = new Random();
                while(true){
                    try {
                        TimeUnit.SECONDS.sleep(1);
                        if(playerOne.getPlayerState() == PlayerState.OBSERVE){
                            playerTwoOceanSpaceController.bombOceanTile(randy.nextInt(variables.getOceanSpaceSize().getSize()-1),
                                    randy.nextInt(variables.getOceanSpaceSize().getSize()-1));
                            TimeUnit.SECONDS.sleep(2);
                            playerOne.swapPlayerState();
                        }
                    } catch (InterruptedException e) {
                        Log.e("LaHAW","EnemyFireLoop Sleeperror");
                    }
                }
            }
        });
        enemy.start();
    }

    @Override
    public View getView() {
        return this;
    }

    protected void onDraw(Canvas canvas){
        if(playerOne.getPlayerState() == PlayerState.FIRE)
            playerOneOceanSpaceController.update(canvas);
        else{
            playerTwoOceanSpaceController.update(canvas);
        }

        if(playerOneOceanSpaceController.isGameOver() || playerTwoOceanSpaceController.isGameOver()){
            pop();
            push(new GameOver(variables.getActivity().getApplicationContext()));
        }
        // Draw pause button
        Paint p1 = new Paint();
        p1.setColor(Color.BLACK);
        canvas.drawRect(new Rect(10,0,120,45),p1);
        Paint p = new Paint();
        p.setColor(Color.WHITE);
        canvas.drawText("Pause game",(float)35,(float)25,p);
        // end draw pause button

        // Draw text.
        if(playerOne.getPlayerState() == PlayerState.FIRE)
            canvas.drawText(playerOne.getName()+"'s turn.", 130, 40, playerOne.getColor());
        else
            canvas.drawText(playerTwo.getName()+"'s turn.", 130, 40, playerTwo.getColor());
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
        if(motionEvent.getAction() == MotionEvent.ACTION_DOWN && motionEvent.getY() < 45 && motionEvent.getX() < 120){
            push(new GamePause(variables.getActivity().getApplicationContext()));
            return true;
        }
        if(playerOne.getPlayerState() == PlayerState.FIRE){
            boolean hitValidSpot = playerOneOceanSpaceController.handleTouchEvent(motionEvent);
            if(hitValidSpot){
                playerOne.swapPlayerState();
            }
            return true;
        } else
            return true;
    }

    public void onBackPressed() {
        // Ignore menu item and show game paused state
        push(new GamePause(variables.getActivity().getApplicationContext()));
    }
}
