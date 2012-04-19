package tdt4240.a2.states;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import tdt4240.a2.R;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import tdt4240.a2.variables.StaticVariables;

/**
 */
public class GameMenu extends State {
	
	private Button closeButton;
	private Activity activity;
    private View view;

	public GameMenu(Context context) {
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
                AlertDialog alertDialog = new AlertDialog.Builder(activity).create();
                alertDialog.setTitle("New game");
                LayoutInflater layoutInflater
                        = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View dialogView=layoutInflater.inflate(R.layout.game_difficulty,null);

                //TextView wonMessage2 = (TextView)dialogView.findViewById(R.id.won_message_bot);
                //wonMessage2.setText("Better luck next time!\n");

                Button easyButton = (Button)dialogView.findViewById(R.id.easy_game_button);
                easyButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Settings.getInstance().setMaxAttempts(15);
                        Intent intent = new Intent().setClass(MainActivity.this, SinglePlayerGameActivity.class);
                        //intent.addFlags(Intent.FLAG);
                        startActivity(intent);
                        finish();
                    }
                });

                Button mediumButton = (Button)dialogView.findViewById(R.id.medium_game_button);
                mediumButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Settings.getInstance().setMaxAttempts(11);
                        Intent intent = new Intent().setClass(MainActivity.this, SinglePlayerGameActivity.class);
                        //intent.addFlags(Intent.FLAG);
                        startActivity(intent);
                        finish();
                    }
                });

                Button hardButton = (Button)dialogView.findViewById(R.id.hard_game_button);
                hardButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Settings.getInstance().setMaxAttempts(7);
                        Intent intent = new Intent().setClass(MainActivity.this, SinglePlayerGameActivity.class);
                        //intent.addFlags(Intent.FLAG);
                        startActivity(intent);
                        finish();
                    }
                });

                alertDialog.setCanceledOnTouchOutside(true);
                alertDialog.setView(dialogView);
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
