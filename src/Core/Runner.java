package Core;

import Main.Display;

public abstract class Runner {
	
	protected Display display;
	
	public Runner(Display display) {
		this.display=display;
	}
	
	public abstract void start();
	
	public abstract void stop();
	
}
