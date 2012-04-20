package tdt4240.a2.states;

import android.app.Activity;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import tdt4240.a2.R;
import tdt4240.a2.variables.StaticVariables;

/**
 */
public class GameOver extends State{

    private Activity activity;
    private View view;

    public GameOver(Context context) {
        super(context);
        this.activity = StaticVariables.getInstance().getActivity();
        view = View.inflate(activity, R.layout.gameover, null);
        
        ((TextView)view.findViewById(R.id.gameover_text)).setText(StaticVariables.getInstance().getGameOverText());

        ((Button)view.findViewById(R.id.mainmenu_button)).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                pop();
            }
        });
    }

    @Override
    public View getView() {
        return view;
    }

    @Override
    public boolean onTouch(MotionEvent motionEvent) {
        return true;
    }

    @Override
    public void onBackPressed() {
        // DO nothing
    }
}
