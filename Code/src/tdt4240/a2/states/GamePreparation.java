package tdt4240.a2.states;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.*;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
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
    private Context context;

    public GamePreparation(Context context){
        super(context);
        this.context = context;
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
            warshipControllers[i].placeShip(0, i, true);
            ((WarshipModel)warshipControllers[i].getRegisteredModel()).setPrepairing(true);
        }

        oceanSpaceController = new OceanSpaceController(variables.getOceanSpaceSize(), new Player("WHORE",
                Color.RED), warshipControllers);

    }

    @Override
    protected void onDraw(Canvas canvas){
        // Draw all the ships
        oceanSpaceController.update(canvas);
        Paint p1 = new Paint();
        p1.setColor(Color.BLACK);
        canvas.drawRect(new Rect(10,0,120,45),p1);
        Paint p = new Paint();
        p.setColor(Color.WHITE);
        canvas.drawText("Start game",(float)35,(float)25,p);
        gameLoop.start();
    }
    
    @Override
    public View getView() {
        return this;
    }

    private boolean checkGameBoard(){
        boolean isReadyToStart = true;

        for(WarshipController warship : warshipControllers){
            for(WarshipController warshipTwo : warshipControllers){
                if(warship != warshipTwo){
                    if(((WarshipModel)warship.getRegisteredModel()).getRect().intersect(((WarshipModel)warshipTwo
                            .getRegisteredModel()).getRect())){
                        isReadyToStart = false;
                        break;
                    }
                }
            }
        }
        
        return isReadyToStart;
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

        // check if the finger is going down
        if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
            // if pressed start
            if(motionEvent.getY() < 45 && motionEvent.getX() < 120){
                if(checkGameBoard()){
                    for(int i=0; i < warshipControllers.length; i++)
                        ((WarshipModel)warshipControllers[i].getRegisteredModel()).setPrepairing(false);
                    pop();
                    push(new GameState(this.context,warshipControllers));
                } else{
                    Toast.makeText(getContext(),"Some of your ships are overlapping",Toast.LENGTH_LONG);
                }
            } else {
                // is there a ship there lock it to your finger
                for(int i=0; i < warshipControllers.length; i++){
                    if(((WarshipModel)warshipControllers[i].getRegisteredModel()).getTileRect().contains(gridX, gridY)){
                        shipLockedToFingerNr = i;
                        ((WarshipModel)warshipControllers[shipLockedToFingerNr].getRegisteredModel()).setSelected(true);
                        break;
                    }
                }
            }
            // Check if the finger is going up
        } else if(motionEvent.getAction() == MotionEvent.ACTION_UP) {
            if(shipLockedToFingerNr != -1){
                if(gridX < variables.getOceanSpaceSize().getSize() &&  gridY < variables.getOceanSpaceSize().getSize
                        () && gridX > -1 && gridY > -1){
                    warshipControllers[shipLockedToFingerNr].placeShip(gridX, gridY,
                            ((WarshipModel) warshipControllers[shipLockedToFingerNr].getRegisteredModel()).isHorizontal());
                    ((WarshipModel)warshipControllers[shipLockedToFingerNr].getRegisteredModel()).setSelected(false);
                }
                    shipLockedToFingerNr = -1;
            }
            // check if the second finger is going up
        } else if(motionEvent.getAction() == 262) {
            if(motionEvent.getAction() == 262){
                if(shipLockedToFingerNr != -1){
                    warshipControllers[shipLockedToFingerNr].turnShip();
                }
            }
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        AlertDialog alertDialog = new AlertDialog.Builder(variables.getActivity()).create();
        alertDialog.setTitle("Oops..");
        alertDialog.setMessage("Are you sure you want to cancel the game?");
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Yes",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                pop();
                dialogInterface.cancel();
            }
        });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "No",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();
    }
}
