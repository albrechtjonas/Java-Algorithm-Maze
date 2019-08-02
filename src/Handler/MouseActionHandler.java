package Handler;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import Entity.Wall;
import Main.Display;

public class MouseActionHandler implements MouseListener {
	
	private Display display;
	
	private boolean pressed=false;
	
	public MouseActionHandler(Display display) {
		this.display=display;
	}

	public void mousePressed(MouseEvent event) {
		pressed=true;
		
		if(display.getKeyActionHandler().getAddWall()) {
		
			int x=event.getX()/display.getResolution();
		
			int y=event.getY()/display.getResolution();
		
			display.getRunState().addWall(new Wall(display,x*display.getResolution(),y*display.getResolution()));
		
		}
	}

	public void mouseReleased(MouseEvent event) {
		pressed=false;
	}

	public void mouseClicked(MouseEvent event) {
		
	}
	
	public void mouseEntered(MouseEvent event) {
		
	}

	public void mouseExited(MouseEvent event) {
		
	}
	
	public boolean getPressed() {
		return pressed;
	}
	
}
