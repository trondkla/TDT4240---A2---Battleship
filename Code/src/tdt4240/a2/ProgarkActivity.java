package tdt4240.a2;

import android.content.Context;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import tdt4240.a2.model.OceanSpaceSize;
import tdt4240.a2.states.GameMenu;
import tdt4240.a2.states.GamePreparation;
import tdt4240.a2.states.StateMachine;
import android.app.Activity;
import android.os.Bundle;
import tdt4240.a2.states.GameState;
import tdt4240.a2.variables.StaticVariables;

public class ProgarkActivity extends Activity {

    private StateMachine stateMachine;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Henter ut skjermstørrelse osv
        Display display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();

        // Lagrer variablene for senere
        StaticVariables variables = StaticVariables.getInstance();
        variables.setCanvasPixelHeight(display.getHeight());
        variables.setCanvasPixelWidth(display.getWidth());

        variables.setResources(getResources());
        variables.setActivity(this); // Activity set

        stateMachine = StateMachine.getInstance(this); // initialize instance with the activity
        stateMachine.push(new GameMenu(getApplicationContext()));

        setContentView(stateMachine.getContentView());

    }

    public boolean onTouchEvent(MotionEvent motionEvent){
        return stateMachine.onTouch(motionEvent);
    }
} 