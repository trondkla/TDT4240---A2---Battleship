package tdt4240.a2.states;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

/**
 */
public class GameOver extends State{

    public GameOver(Context context, Activity activity) {
        super(context, activity);
        TextView text = new TextView(activity);
        text.setText("Game over! Somebody won!");
    }

    @Override
    public View getView() {
        return this;
    }
}
