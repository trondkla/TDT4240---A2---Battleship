package tdt4240.a2.states;

import android.app.Activity;
import android.content.Context;
import android.view.MotionEvent;
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

    @Override
    public boolean onTouch(MotionEvent motionEvent) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onBackPressed() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
