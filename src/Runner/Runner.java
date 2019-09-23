package Runner;

import Main.Display;

public abstract class Runner implements Runnable {
	
	protected Display display;
	
	public Runner(Display display) {
		this.display=display;
	}
	
	public abstract void start();
	
	public abstract void stop();
}	
