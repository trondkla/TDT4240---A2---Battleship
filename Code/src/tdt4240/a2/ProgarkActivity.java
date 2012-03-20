package tdt4240.a2;

import tdt4240.a2.states.GameMenu;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ProgarkActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        StateMachine stateMachine = new StateMachine(this);
        
        stateMachine.push(new GameMenu(this));
        
        setContentView(stateMachine.getContentView());
    }
} 