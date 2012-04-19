package tdt4240.a2.states;


import java.util.Stack;

import android.view.Menu;
import android.view.MotionEvent;
import tdt4240.a2.listeners.StateChangeSupport;
import android.app.Activity;
import android.view.View;
import android.widget.TextView;

public class StateMachine extends StateChangeSupport {
	
	private Stack<State> stateStack;
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
		
		updateView();
	}
	
	/**
	 * Pops top state (visible state)
	 */
	public void pop(){
		if(!stateStack.empty()){
			stateStack.pop();
		}
		updateView();
	}
	
	public void updateView(){
		activity.setContentView(getContentView());
		fireStateChanged();
	}

    public boolean isEmpty(){
        return stateStack.isEmpty();
    }

	public View getContentView() {
		if(stateStack.empty()){
			TextView text = new TextView(activity);
			text.setText("No more states");
			return text;
		}
		return stateStack.peek().getView();
	}

    public boolean onTouch(MotionEvent event){
        return stateStack.peek().onTouch(event);
    }

    public void onBackPressed(){
        stateStack.peek().onBackPressed();
    }
}
