package tdt4240.a2;

import android.content.Context;
import android.view.Display;
import android.view.WindowManager;
import tdt4240.a2.states.GameMenu;
import android.app.Activity;
import android.os.Bundle;
import tdt4240.a2.states.GameState;
import tdt4240.a2.variables.StaticVariables;

public class ProgarkActivity extends Activity {
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Henter ut skjermstørrelse osv
        Display display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();

        // Lagrer variablene for senere
        StaticVariables variables = StaticVariables.getInstance();
        variables.setCanvasPixelHeight(display.getHeight());
        variables.setCanvasPixelWidth(display.getWidth());

        StateMachine stateMachine = StateMachine.getInstance(this);
        
        stateMachine.push(new GameState(getApplicationContext(),this));
        
        setContentView(stateMachine.getContentView());
        
    }
    
} 