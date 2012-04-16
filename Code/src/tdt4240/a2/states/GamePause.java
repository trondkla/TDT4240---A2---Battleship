package tdt4240.a2.states;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import tdt4240.a2.R;
import tdt4240.a2.variables.StaticVariables;

/**
 */
public class GamePause extends State{
    private View view;
    private StaticVariables variables = StaticVariables.getInstance();

    public GamePause(Context context) {
        super(context);
        view = inflate(StaticVariables.getInstance().getActivity(), R.layout.gamepaused, null);

        // Implement onclicklistener for resume_button
        ((Button)view.findViewById(R.id.resume_button)).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                pop(); // Pop the paused state
            }
        });

        ((Button)view.findViewById(R.id.exit_game_button)).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                pop(); // pop pausedstate
                pop(); // pop gamestate
                push(new GameMenu(variables.getActivity().getApplicationContext()));
            }
        });
    }

    @Override
    public View getView() {
        return view; // returns the inflated view
    }
}
