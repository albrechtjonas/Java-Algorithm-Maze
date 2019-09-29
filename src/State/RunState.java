package State;

import java.awt.Color;
import java.awt.Graphics2D;

import Entity.Entity;
import Entity.Entrance;
import Entity.Exit;
import Entity.Path;
import Entity.Wall;
import Interface.Renderable;
import Interface.Tickable;
import Main.Display;
import Runner.Maker.DFSMaker;
import Runner.Solver.BFSSolver;

public class RunState extends State implements Tickable,Renderable {
	
	private boolean DFSMaking;
	
	private DFSMaker dFSMaker;
	
	private boolean BFSSolving;
	
	private BFSSolver bFSSolver;
	
	private Entity[][] entities;
	
	public RunState(Display display) {
		super(display);
		
		createObject();
	}
	
	private void createObject() {
		entities=new Entity[display.getWidth()/display.getResolution()][display.getHeight()/display.getResolution()];
		
		entities[0][0]=new Entrance(display,0,0);
		
		entities[entities.length-1][entities.length-1]=new Exit(display,(entities.length-1)*display.getResolution(),(entities.length-1)*display.getResolution());
	}
	
	public void tick() {
		
		DFSMakerTick();
		
		BFSSolverTick();
		
		addWallTick();
		
		for(int x=0;x<entities.length;x++) {
			for(int y=0;y<entities[x].length;y++) {
				if(entities[x][y] instanceof Entrance) {
					((Entrance)(entities[x][y])).tick();
				}else if(entities[x][y] instanceof Exit) {
					((Exit)(entities[x][y])).tick();
				}else if(entities[x][y] instanceof Wall) {
					((Wall)(entities[x][y])).tick();
				}
			}
		}
		
	}
	
	private void DFSMakerTick() {
		if(DFSMaking==true) {
			dFSMaker=new DFSMaker(display);
			dFSMaker.start();
			DFSMaking=false;
		}
	}
	
	private void BFSSolverTick() {
		if(BFSSolving==true) {
			bFSSolver=new BFSSolver(display);
			bFSSolver.start();
			BFSSolving=false;
		}
	}
	
	private void addWallTick() {
		if(display.getKeyActionHandler().getAddWall()) {
			if(display.getMouseActionHandler().getPressed()) {
				int x=display.getMouseMotionHandler().getX()/display.getResolution();
				int y=display.getMouseMotionHandler().getY()/display.getResolution();
				
				if(x>=0 && x<=entities.length-1 && y>=0 && y<=entities.length-1) {
					entities[x][y]=new Wall(display,x*display.getResolution(),y*display.getResolution());
				}
			}
		}
	}
	
	
	public void render(Graphics2D g) {
		for(int x=0;x<entities.length;x++) {
			for(int y=0;y<entities[x].length;y++) {
				if(entities[x][y] instanceof Entrance) {
					((Entrance)(entities[x][y])).render(g);;
				}else if(entities[x][y] instanceof Exit) {
					((Exit)(entities[x][y])).render(g);;
				}else if(entities[x][y] instanceof Path) {
					((Path)(entities[x][y])).render(g);
				}else if(entities[x][y] instanceof Wall) {
					((Wall)(entities[x][y])).render(g);
				}
			}
		}
		
		for(int x=0;x<display.getWidth()/display.getResolution();x++) {
			for(int y=0;y<display.getHeight()/display.getResolution();y++) {
				g.setColor(Color.LIGHT_GRAY);
				g.drawRect(x*display.getResolution(),y*display.getResolution(),display.getResolution(),display.getResolution());
			}
		}
	}
	
	public void reset() {
		createObject();
		
		if(dFSMaker!=null) {
			dFSMaker.stop();
			dFSMaker=null;
		}
		
		if(bFSSolver!=null) {
			bFSSolver.stop();
			bFSSolver=null;
		}
	}
	
	public void setDFSMaking(boolean DFSMaking) {
		this.DFSMaking=DFSMaking;
	}
	
	public boolean getDFSMaking() {
		return DFSMaking;
	}
	
	public void setBFSSolving(boolean BFSSolving) {
		this.BFSSolving=BFSSolving;
	}
	
	public boolean getBFSSolving() {
		return BFSSolving;
	}
	
	public Entity[][] getEntities() {
		return entities;
	}
	
	public Entrance getEntrance() {
		for(int x=0;x<entities.length;x++) {
			for(int y=0;y<entities[x].length;y++) {
				if(entities[x][y] instanceof Entrance) {
					return ((Entrance)(entities[x][y]));
				}
			}
		}
		return null;
	}
	
	public Exit getExit() {
		for(int x=0;x<entities.length;x++) {
			for(int y=0;y<entities[x].length;y++) {
				if(entities[x][y] instanceof Exit) {
					return ((Exit)(entities[x][y]));
				}
			}
		}
		return null;
	}
}
