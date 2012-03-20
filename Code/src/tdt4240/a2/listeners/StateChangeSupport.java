package tdt4240.a2.listeners;

import java.util.ArrayList;

public abstract class StateChangeSupport {
	private ArrayList<StateChangeListener> listeners;
	
	public StateChangeSupport() {
		listeners = new ArrayList<StateChangeListener>();
	}
	
	public void addStateChangeListener(StateChangeListener listener) {
		listeners.add(listener);
	}
	public void removeStateChangeListener(StateChangeListener listener) {
		listeners.remove(listener);
	}
	
	public void fireStateChanged(){
		for(StateChangeListener listener : listeners){
			listener.stateChanged();
		}
	}
}
