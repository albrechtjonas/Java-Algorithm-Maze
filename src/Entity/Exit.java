package Entity;

import java.awt.Color;
import java.awt.Graphics2D;

import Main.Display;

public class Exit extends Entity {
	
	private final Color color=Color.BLUE;
	
	public Exit(Display display,int x,int y) {
		super(display,x,y);
	}
	
	public void tick() {
		if(display.getMouseActionHandler().getPressed()) {
			if(display.getKeyActionHandler().getMoveExit()==true) {
				int x=display.getMouseMotionHandler().getX()/display.getResolution();
				int y=display.getMouseMotionHandler().getY()/display.getResolution();
				setX(x*display.getResolution());
				setY(y*display.getResolution());
			}
		}
	}
	
	public void render(Graphics2D g) {
		if(display.getRunState().getMaker().getX()==x && display.getRunState().getMaker().getY()==y) {
			g.setColor(Color.GREEN);
		}else {
			g.setColor(color);
		}
		g.fillRect(x,y,display.getResolution(),display.getResolution());
	}
}
