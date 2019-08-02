package State;

import java.awt.Graphics2D;

import Main.Display;

public abstract class State {
	public static State currentState=null;
	
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
	
	public abstract void tick();
	
	public abstract void render(Graphics2D g);
}
