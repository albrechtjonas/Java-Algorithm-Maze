package Entity;

import java.awt.Color;
import java.awt.Graphics2D;

import Interface.Renderable;
import Interface.Tickable;
import Main.Display;

public class Wall extends Entity implements Tickable,Renderable {
	public Wall(Display display,int x,int y) {
		super(display,x,y);
	}
	
	public void tick() {
		if(display.getKeyActionHandler().getRemoveWall()) {
			if(display.getMouseActionHandler().getPressed()) {
				int x=display.getMouseMotionHandler().getX();
				int y=display.getMouseMotionHandler().getY();
				
				if(x>this.x && x<this.x+display.getResolution()) {
					if(y>this.y && y<this.y+display.getResolution()) {
						display.getRunState().getEntities()[x/display.getResolution()][y/display.getResolution()]=null;
					}
				}
				
			}
		}
	}
	
	public void render(Graphics2D g) {
		g.setColor(Color.DARK_GRAY);
		g.fillRect(x,y,display.getResolution(),display.getResolution());	
	}
}
