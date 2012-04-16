package tdt4240.a2.states;

import android.view.MotionEvent;
import tdt4240.a2.StateMachine;
import android.app.Activity;
import android.content.Context;
import android.view.View;

public abstract class State extends View{

	private StateMachine stateMachine;

	public State(Context context) {
        super(context);
		this.stateMachine = StateMachine.getInstance(null);
	}

    public abstract View getView();
	
	public void pop(){
		stateMachine.pop();
	}
	
	public void push(State state){
		stateMachine.push(state);
	}

    public boolean onTouch(MotionEvent motionEvent){
        return true; // Placeholder
    }
}
