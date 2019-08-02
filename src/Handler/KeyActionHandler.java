package Handler;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyActionHandler implements KeyListener {
	
	private boolean moveMaker=false;
	
	private boolean moveExit=false;
	
	private boolean addWall=false;
	
	private boolean removeWall=false;
	
	public KeyActionHandler() {
		
	}

	public void keyPressed(KeyEvent event) {
		if(event.getKeyCode()==KeyEvent.VK_1) {
			moveMaker=true;
			moveExit=false;
			addWall=false;
			removeWall=false;
		}
		
		if(event.getKeyCode()==KeyEvent.VK_2) {
			moveExit=true;
			moveMaker=false;
			addWall=false;
			removeWall=false;
		}
		
		if(event.getKeyCode()==KeyEvent.VK_3) {
			addWall=true;
			moveExit=false;
			moveMaker=false;
			removeWall=false;
		}
		
		if(event.getKeyCode()==KeyEvent.VK_4) {
			removeWall=true;
			moveExit=false;
			moveMaker=false;
			addWall=false;
		}
	}

	public void keyReleased(KeyEvent event) {
		
	}
	
	public void keyTyped(KeyEvent event) {
		
	}
	
	public boolean getMoveMaker() {
		return moveMaker;
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
