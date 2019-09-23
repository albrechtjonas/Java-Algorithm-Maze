package Handler;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MouseMotionHandler implements MouseMotionListener {

	private int x;
	
	private int y;
	
	public MouseMotionHandler() {
		
	}
	
	public void mouseDragged(MouseEvent event) {
		x=event.getX();
		y=event.getY();
	}

	public void mouseMoved(MouseEvent event) {
		x=event.getX();
		y=event.getY();
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
}
