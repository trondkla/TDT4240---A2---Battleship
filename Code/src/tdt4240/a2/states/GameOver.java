package tdt4240.a2.states;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import tdt4240.a2.R;
import tdt4240.a2.variables.StaticVariables;

/**
 */
public class GameOver extends State{

    public GameOver(Context context) {
        super(context);
        TextView text = new TextView(StaticVariables.getInstance().getActivity());
        text.setText("Game over! Somebody won!");
    }

    @Override
    public View getView() {
        View v = null;
        try{
            v = (View) View.inflate(StaticVariables.getInstance().getActivity(), R.layout.gameover, null);
        }catch(Exception e){
            System.out.println(" ERR " + e.getMessage()+e.toString());
        }
        return v;
    }

    @Override
    public boolean onTouch(MotionEvent motionEvent) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onBackPressed() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
