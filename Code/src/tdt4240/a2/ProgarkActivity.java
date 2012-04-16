package tdt4240.a2;

import tdt4240.a2.states.GameMenu;
import tdt4240.a2.states.StateMachine;
import android.app.Activity;
import android.os.Bundle;

public class ProgarkActivity extends Activity {
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        StateMachine stateMachine = StateMachine.getInstance(this);
        
        stateMachine.push(new GameMenu(this));
        
        setContentView(stateMachine.getContentView());
        
    }
    
} 