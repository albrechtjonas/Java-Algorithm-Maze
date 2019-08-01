package Entity;

import java.awt.Color;
import java.awt.Graphics2D;

import Main.Display;

public class Path extends Entity {
	
	private final Color color=Color.PINK;
	
	public Path(Display display,int x,int y) {
		super(display,x,y);
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics2D g) {
		g.setColor(color);
		g.fillRect(x,y,display.getResolution(),display.getResolution());
	}
}
