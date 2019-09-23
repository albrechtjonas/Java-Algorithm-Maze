package Handler;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyActionHandler implements KeyListener {
	
	private boolean moveEntrance;
	
	private boolean moveExit;
	
	private boolean addWall;
	
	private boolean removeWall;
	
	public KeyActionHandler() {
		
	}

	public void keyPressed(KeyEvent event) {
		if(event.getKeyCode()==KeyEvent.VK_1) {
			moveEntrance=true;
			moveExit=false;
			addWall=false;
			removeWall=false;
		}else if(event.getKeyCode()==KeyEvent.VK_2) {
			moveEntrance=false;
			moveExit=true;
			addWall=false;
			removeWall=false;
		}else if(event.getKeyCode()==KeyEvent.VK_3) {
			moveEntrance=false;
			moveExit=false;
			addWall=true;
			removeWall=false;
		}else if(event.getKeyCode()==KeyEvent.VK_4) {
			moveEntrance=false;
			moveExit=false;
			addWall=false;
			removeWall=true;
		}
	}

	public void keyReleased(KeyEvent event) {
		
	}
	
	public void keyTyped(KeyEvent event) {
		
	}
	
	public boolean getMoveEntrance() {
		return moveEntrance;
	}
	
	public boolean getMoveExit() {
		return moveExit;
	}
	
	public boolean getAddWall() {
		return addWall;
	}
	
	public boolean getRemoveWall() {
		return removeWall;
	}
}
