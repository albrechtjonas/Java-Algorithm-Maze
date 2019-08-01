package Entity;

import java.awt.Graphics2D;

import Main.Display;

public abstract class Entity {
	protected Display display;
	
	protected int x;
	
	protected int y;
	
	public Entity(Display display,int x,int y) {
		this.display=display;
		this.x=x;
		this.y=y;
	}
	
	public abstract void tick();
	
	public abstract void render(Graphics2D g);
	
	public void setX(int x) {
		this.x=x;
	}
	
	public int getX() {
		return x;
	}
	
	public void setY(int y) {
		this.y=y;
	}
	
	public int getY() {
		return y;
	}
	
}
