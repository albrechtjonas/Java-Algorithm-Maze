package Handler;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseActionHandler implements MouseListener {
	
	private boolean pressed;
	
	public MouseActionHandler() {
		
	}

	public void mousePressed(MouseEvent event) {
		pressed=true;
	}

	public void mouseReleased(MouseEvent event) {
		pressed=false;
	}

	public void mouseEntered(MouseEvent event) {
		
	}

	public void mouseExited(MouseEvent event) {
		
	}
	
	public void mouseClicked(MouseEvent event) {
		
	}
	
	public boolean getPressed() {
		return pressed;
	}
	
}
