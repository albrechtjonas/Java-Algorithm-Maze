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
		
	}
	
	public void render(Graphics2D g) {
		g.setColor(Color.DARK_GRAY);
		g.fillRect(x,y,display.getResolution(),display.getResolution());
	}
}
