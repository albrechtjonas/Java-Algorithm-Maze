package Entity;

import java.awt.Color;
import java.awt.Graphics2D;

import Main.Display;

public class Wall extends Entity {
	
	private final Color color=Color.GRAY;
	
	public Wall(Display display,int x,int y) {
		super(display,x,y);
	}
	
	public void tick() {
		if(display.getMouseActionHandler().getPressed()) {
			if(display.getKeyActionHandler().getRemoveWall()) {
				int mouseX=display.getMouseMotionHandler().getX()/display.getResolution();
				
				int mouseY=display.getMouseMotionHandler().getY()/display.getResolution();
				
				if(x==mouseX*display.getResolution() && y==mouseY*display.getResolution()) {
					display.getRunState().removeWall(this);
				}
			}
		}
	}
	
	public void render(Graphics2D g) {
		g.setColor(color);
		g.fillRect(x,y,display.getResolution(),display.getResolution());
	}
}
