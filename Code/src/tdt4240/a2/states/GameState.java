package tdt4240.a2.states;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import tdt4240.a2.R;
import tdt4240.a2.controller.OceanSpaceController;
import tdt4240.a2.controller.WarshipController;
import tdt4240.a2.model.OceanTile;
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

    public GameState(Context context, WarshipController[] warshipControllers){
        super(context);

        // Create the player objects and give them color
        playerOne = new Player(variables.getPlayerOneName(), variables.getPlayerOneColor());
        playerTwo = new Player(variables.getPlayerTwoName(), variables.getPlayerTwoColor());

        // Create oceanspace
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

        // Enemy thread where the enemy shoots randomly at the board you have laid out for him.
        Thread enemy = new Thread(new Runnable() {
            public void run() {
                Random randy = new Random();
                boolean running = true;
                while (running) {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                        if (playerOne.getPlayerState() == PlayerState.OBSERVE) {
                            playerTwoOceanSpaceController.bombOceanTile(randy.nextInt(variables.getOceanSpaceSize().getSize() - 1),
                                    randy.nextInt(variables.getOceanSpaceSize().getSize() - 1));
                            TimeUnit.SECONDS.sleep(2);
                            playerOne.swapPlayerState();
                        }
                    } catch (InterruptedException e) {
                        running = false;
                        Log.e("LaHAW", "EnemyFireLoop Sleeperror");
                    }
                }
            }
        });
        // Start the thread
        enemy.start();
    }

    @Override
    public View getView() {
        return this;
    }

    protected void onDraw(Canvas canvas){
        // Evaluate what to paint.
        if(playerOne.getPlayerState() == PlayerState.FIRE)
            playerOneOceanSpaceController.update(canvas);
        else{
            playerTwoOceanSpaceController.update(canvas);
        }

        // Check if the game is over
        if(playerOneOceanSpaceController.isGameOver() || playerTwoOceanSpaceController.isGameOver()){
            if(playerOneOceanSpaceController.isGameOver())
                variables.setGameOverText("You won a victory!");
            else
                variables.setGameOverText("You lost a victory.");
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

        // Draw text
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

    /**
     * Function called when a motionevent has occured.
     * @param motionEvent
     * @return boolean
     */
    public boolean onTouch(MotionEvent motionEvent){
        // Check if the pausebutton is pressed
        if(motionEvent.getAction() == MotionEvent.ACTION_DOWN && motionEvent.getY() < 45 && motionEvent.getX() < 120){
            push(new GamePause(variables.getActivity().getApplicationContext()));
            return true;
        }
        if(playerOne.getPlayerState() == PlayerState.FIRE){
            // Check if the hit is valid
            boolean hitValidSpot = playerOneOceanSpaceController.handleTouchEvent(motionEvent);
            // if not the player state stays the same
            if(hitValidSpot){
                OceanTile tile = playerOneOceanSpaceController.getOceanTile((int) (motionEvent.getX() / variables
                        .getPixelPerTile
                        ()),
                        (int) ((motionEvent.getY() - variables.getGridOffset()) / variables.getPixelPerTile()));
                MediaPlayer mediaPlayer;
                if(tile == OceanTile.OCCUPIED){
                    mediaPlayer = MediaPlayer.create(variables.getActivity(), R.raw.bomb);
                } else {
                    mediaPlayer = MediaPlayer.create(variables.getActivity(), R.raw.water);
                }
                // play sound
                mediaPlayer.start();
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mp.release();
                    }

                });
                playerOne.swapPlayerState();
            }
            return true;
        } else
            return true;
    }

    /**
     * Function called when the back button is pressed.
     */
    public void onBackPressed() {
        // Ignore menu item and show game paused state
        push(new GamePause(variables.getActivity().getApplicationContext()));
    }
}
