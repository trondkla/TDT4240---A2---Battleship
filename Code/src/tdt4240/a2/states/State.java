package tdt4240.a2.states;

import android.app.Activity;
import android.view.View;

public abstract class State {

	private StateMachine stateMachine;

	public State(Activity activity) {
		this.stateMachine = StateMachine.getInstance(activity);
	}

	public abstract View getView();
	
	public void pop(){
		stateMachine.pop();
	}
	
	public void push(State state){
		stateMachine.push(state);
	}

}
