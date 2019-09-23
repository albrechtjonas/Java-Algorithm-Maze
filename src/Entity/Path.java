package Entity;

import java.awt.Color;
import java.awt.Graphics2D;

import Interface.Renderable;
import Main.Display;

public class Path extends Entity implements Renderable {
	public Path(Display display,int x,int y) {
		super(display,x,y);
	}
	
	public void render(Graphics2D g) {
		g.setColor(Color.ORANGE);
		g.fillRect(x,y,display.getResolution(),display.getResolution());
	}
}
