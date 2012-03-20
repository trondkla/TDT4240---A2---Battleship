package tdt4240.a2.states;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

/**
 */
public class GameMenu extends State {
	
	public GameMenu(Context context) {
		super(context);
		
	}

	@Override
	public View getView() {
		//GridView
		TextView tv = new TextView(context);
		tv.setText("There is a state");
		return tv;
	}
}
