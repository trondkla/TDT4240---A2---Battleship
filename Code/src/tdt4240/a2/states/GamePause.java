
package tdt4240.a2.states;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import tdt4240.a2.R;
import tdt4240.a2.variables.StaticVariables;

/**
 */
public class GamePause extends State {

	private Activity activity;
    private View view;

	public GamePause(Context context) {
		super(context);
		this.activity = StaticVariables.getInstance().getActivity();
        view = View.inflate(activity, R.layout.gamepaused, null);

        ((Button)view.findViewById(R.id.exit_game_button)).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog alertDialog = new AlertDialog.Builder(activity).create();
                alertDialog.setTitle("Oops..");
                alertDialog.setMessage("Are you sure you want to return to main menu?");
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Yes",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        pop(); // poping gamepaused of the stack
                        pop(); // poping gamestate of the stack
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
        });

        ((Button)view.findViewById(R.id.resume_game_button)).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                pop(); // popping game paused state
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
        pop(); // going back to the game.
    }
}
