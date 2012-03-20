package tdt4240.a2.states;

import android.content.Context;
import android.view.View;

public abstract class State {

	protected Context context;

	public State(Context context) {
		this.context = context;
	}

	public abstract View getView();

}
