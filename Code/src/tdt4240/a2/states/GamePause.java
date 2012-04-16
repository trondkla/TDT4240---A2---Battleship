package tdt4240.a2.states;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

/**
 */
public class GamePause extends State {

	private Activity activity;

	public GamePause(Context context) {
		super(context);
		this.activity = activity;
	}

	@Override
	public View getView() {
		TextView tv = new TextView(activity);
		tv.setText("PAuse");
		return tv;
	}
}
