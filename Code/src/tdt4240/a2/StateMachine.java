package tdt4240.a2;


import java.util.Stack;

import tdt4240.a2.listeners.StateChangeSupport;
import tdt4240.a2.states.State;
import android.app.Activity;
import android.view.View;
import android.widget.TextView;

public class StateMachine extends StateChangeSupport {
	
	private Stack<State> stateStack;
	private View currentView;
	private Activity activity;
	private static StateMachine __instance = null;
	
	public StateMachine(Activity activity) {
		stateStack = new Stack<State>();
		this.activity = activity;
	}
	
	public static StateMachine getInstance(Activity activity){
		if(__instance == null){
			__instance = new StateMachine(activity);
		}
		return __instance;
	}
	
	
	/**
	 * Pushes new state onto top of stack (shows state)
	 * @param state
	 */
	public void push(State state){
		stateStack.push(state);
		currentView = stateStack.firstElement().getView();
		
		activity.setContentView(getContentView());
		fireStateChanged();
	}
	
	/**
	 * Pops top state (visible state)
	 */
	public void pop(){
		if(stateStack.empty() == false){
			stateStack.pop();
		}

		if(stateStack.empty()){
			TextView tv = new TextView(activity);
			tv.setText("ERROR: No more states to pop");
			currentView = tv;
		} else {
			currentView = stateStack.firstElement().getView();
		}
		
		activity.setContentView(getContentView());
		fireStateChanged();
	}

	public View getContentView() {
		if(stateStack.empty()){
			TextView text = new TextView(activity);
			text.setText("No more states");
			return text;
		}
		return currentView;
	}

}
