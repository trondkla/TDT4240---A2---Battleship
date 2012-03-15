package tdt4240.a2;


import tdt4240.a2.states.State;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

public class StateMachine {
	
	//private Stack<State> stateStack;
	private View currentView;
	private Context context;
	
	public StateMachine(Context context) {
		//stateStack = new Stack<State>();
		this.context = context;
	}
	
	
	/**
	 * Pushes new state onto top of stack (shows state)
	 * @param state
	 */
	public void push(State state){
//		stateStack.push(state);
//		currentView = stateStack.firstElement().getView();
	}
	
	/**
	 * Pops top state (visible state)
	 */
	public void pop(){
//		stateStack.pop();
//		currentView = stateStack.firstElement().getView();
	}

	public View getContentView() {
//		if(stateStack.empty()){
			TextView tv = new TextView(context);
			tv.setText("No states found");
			return tv;
//		}
//		return currentView;
	}

}
