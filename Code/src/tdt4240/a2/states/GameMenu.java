package tdt4240.a2.states;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.widget.ImageButton;
import tdt4240.a2.R;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import tdt4240.a2.model.OceanSpaceSize;
import tdt4240.a2.variables.StaticVariables;

/**
 */
public class GameMenu extends State {
	
	private Activity activity;
    private View view;

	public GameMenu(final Context context) {
		super(context);
		this.activity = StaticVariables.getInstance().getActivity();
        view = View.inflate(activity, R.layout.gamemenu, null);

        ((Button)view.findViewById(R.id.exit_button)).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.finish();
            }
        });

        ((Button)view.findViewById(R.id.new_game_button)).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog alertDialog = new AlertDialog.Builder(activity).create();
                alertDialog.setTitle("New game");
                LayoutInflater layoutInflater
                        = (LayoutInflater)activity.getApplicationContext().getSystemService(Context
                        .LAYOUT_INFLATER_SERVICE);
                View dialogView=layoutInflater.inflate(R.layout.game_difficulty,null);

                //TextView wonMessage2 = (TextView)dialogView.findViewById(R.id.won_message_bot);
                //wonMessage2.setText("Better luck next time!\n");

                Button easyButton = (Button)dialogView.findViewById(R.id.easy_game_button);
                easyButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        StaticVariables.getInstance().setOceanSpaceSize(OceanSpaceSize.SMALL);
                        push(new GamePreparation(activity.getApplicationContext()));
                        alertDialog.cancel();
                    }
                });

                Button mediumButton = (Button)dialogView.findViewById(R.id.medium_game_button);
                mediumButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        StaticVariables.getInstance().setOceanSpaceSize(OceanSpaceSize.MEDIUM);
                        push(new GamePreparation(activity.getApplicationContext()));
                        alertDialog.cancel();
                    }
                });

                Button hardButton = (Button)dialogView.findViewById(R.id.hard_game_button);
                hardButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        StaticVariables.getInstance().setOceanSpaceSize(OceanSpaceSize.LARGE);
                        push(new GamePreparation(activity.getApplicationContext()));
                        alertDialog.cancel();
                    }
                });

                alertDialog.setCanceledOnTouchOutside(true);
                alertDialog.setView(dialogView);
                alertDialog.show();
            }
        });

        ((ImageButton)view.findViewById(R.id.help_button)).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog alertDialog = new AlertDialog.Builder(activity).create();
                alertDialog.setTitle("Help");
                alertDialog.setMessage("Sorry, you are on your own!");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Ok",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                alertDialog.show();
            }
        });
	}

	@Override
	public View getView() {
		return view;
	}

    @Override
    public boolean onTouch(MotionEvent motionEvent) {
        return true;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onBackPressed() {
        //To change body of implemented methods use File | Settings | File Templates.
        activity.finish();
    }
}
