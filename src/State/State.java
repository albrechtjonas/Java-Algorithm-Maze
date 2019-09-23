package State;

import Main.Display;

public abstract class State {
	public static State currentState;
	
	public static void setState(State state) {
		currentState=state;
	}
	
	public static State getState() {
		return currentState;
	}
	
	protected Display display;
	
	public State(Display display) {
		this.display=display;
	}
}
