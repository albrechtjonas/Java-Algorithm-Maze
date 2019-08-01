package Entity;

import java.awt.Color;
import java.awt.Graphics2D;

import Main.Display;

public class Maker extends Entity {
	
	private final Color color=Color.RED;
	
	private int index=0;
	
	private boolean loop=true;
	
	public Maker(Display display,int x,int y) {
		super(display,x,y);
	}
	
	public void tick() {
		if(display.getMouseActionHandler().getPressed()) {
			if(display.getKeyActionHandler().getMoveMaker()==true) {
				int mouseX=display.getMouseMotionHandler().getX()/display.getResolution();
				int mouseY=display.getMouseMotionHandler().getY()/display.getResolution();
				x=mouseX*display.getResolution();
				y=mouseY*display.getResolution();
			}
		}
		
		if(display.getRunState().getBFSSolver()!=null) {
			if(display.getRunState().getBFSSolver().getDone()) {
				if(index!=display.getRunState().getBFSSolver().getShortestPath().size()) {
					int number=display.getRunState().getBFSSolver().getShortestPath().get(index);
					
					if(number==1) {
						y-=display.getResolution();
					}else if(number==2) {
						y+=display.getResolution();
					}else if(number==3) {
						x-=display.getResolution();
					}else if(number==4) {
						x+=display.getResolution();
					}
					
					if(loop==true) {
					display.getRunState().addPath(new Path(display,x,y));
					}
					index++;
				}else {
					x=0;
					y=0;
					index=0;
					
					loop=false;
				}
			}
		}
	}
	
	public void render(Graphics2D g) {
		g.setColor(color);
		g.fillRect(x,y,display.getResolution(),display.getResolution());
	}
}
