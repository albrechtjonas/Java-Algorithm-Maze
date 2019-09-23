package Entity;

import java.awt.Color;
import java.awt.Graphics2D;

import Interface.Renderable;
import Interface.Tickable;
import Main.Display;

public class Exit extends Entity implements Tickable,Renderable {
	public Exit(Display display,int x,int y) {
		super(display,x,y);
	}
	
	public void tick() {
		if(display.getKeyActionHandler().getMoveExit()) {
			if(display.getMouseActionHandler().getPressed()) {
				int x=display.getMouseMotionHandler().getX()/display.getResolution();
				int y=display.getMouseMotionHandler().getY()/display.getResolution();
				
				this.x=x*display.getResolution();
				this.y=y*display.getResolution();
			}
		}
	}
	
	public void render(Graphics2D g) {
		
		Entrance entrance=display.getRunState().getEntrance();
		
		if(entrance.getX()==x && entrance.getY()==y) {
			g.setColor(Color.GREEN);
		}else {
			g.setColor(Color.BLUE);
		}
		g.fillRect(x,y,display.getResolution(),display.getResolution());
	}
}
