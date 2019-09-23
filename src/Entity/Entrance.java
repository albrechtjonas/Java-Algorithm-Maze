package Entity;

import java.awt.Color;
import java.awt.Graphics2D;

import Interface.Renderable;
import Interface.Tickable;
import Main.Display;

public class Entrance extends Entity implements Tickable,Renderable {
	
	public Entrance(Display display,int x,int y) {
		super(display,x,y);
	}
	
	public void tick() {
		if(display.getKeyActionHandler().getMoveEntrance()) {
			if(display.getMouseActionHandler().getPressed()) {
				int x=display.getMouseMotionHandler().getX()/display.getResolution();
				int y=display.getMouseMotionHandler().getY()/display.getResolution();
				
				this.x=x*display.getResolution();
				this.y=y*display.getResolution();
			}
		}
	}
	
	public void render(Graphics2D g) {
		g.setColor(Color.RED);
		g.fillRect(x,y,display.getResolution(),display.getResolution());
	}
}
