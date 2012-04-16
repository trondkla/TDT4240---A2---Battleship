package tdt4240.a2.states;

import tdt4240.a2.R;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 */
public class GameMenu extends State {
	
	private Button closeButton;
	private Activity activity;

	public GameMenu(Activity activity) {
		super(activity);
		this.activity = activity;
	}

	@Override
	public View getView() {
		//GridView
		View v = null;
		try{
            v = (View) View.inflate(activity, R.layout.game_menu, null);
            closeButton = (Button)v.findViewById(R.id.btn1);
            closeButton.setOnClickListener(new OnClickListener() {
              @Override
              public void onClick(View v) {
            	  System.out.println("CLICKED BUTTON");
            	  push(new GamePause(activity));
              }
            });
        }catch(Exception e){
            System.out.println(" ERR " + e.getMessage()+e.toString());
        }       
		return v;
	}
}
