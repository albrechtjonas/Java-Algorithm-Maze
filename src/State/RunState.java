package State;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

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
	
	private boolean BFSSolving;
	
	private DFSMaker dFSMaker;
	
	private BFSSolver bFSSolver;
	
	private Entrance entrance;
	
	private Exit exit;
	
	private ArrayList<Wall> walls=new ArrayList<Wall>();
	
	private ArrayList<Path> paths=new ArrayList<Path>();
	
	public RunState(Display display) {
		super(display);
		
		createObject();
	}
	
	private void createObject() {
		entrance=new Entrance(display,0,0);
		exit=new Exit(display,0,0);
	}
	
	public void tick() {
		DFSMakerTick();
		
		BFSSolverTick();
		
		entrance.tick();
		
		exit.tick();
		
		for(int i=0;i<walls.size();i++) {
			walls.get(i).tick();
		}
	}
	
	private void DFSMakerTick() {
		if(DFSMaking==true) {
			dFSMaker=new DFSMaker(display);
			dFSMaker.start();
			DFSMaking=false;
		}
		
		if(dFSMaker!=null && dFSMaker.getDone()) {
			boolean[][] map=dFSMaker.getMap();
			
			for(int x=0;x<map.length;x++) {
				for(int y=0;y<map.length;y++) {
					if(map[x][y]==false) {
						walls.add(new Wall(display,x*display.getResolution(),y*display.getResolution()));
					}
				}
			}
			dFSMaker.stop();
			dFSMaker=null;
		}
	}
	
	private void BFSSolverTick() {
		if(BFSSolving==true) {
			bFSSolver=new BFSSolver(display);
			bFSSolver.start();
			BFSSolving=false;
		}
		
		if(bFSSolver!=null && bFSSolver.getDone() && bFSSolver.getSolution()!=null) {
			int[] solution=bFSSolver.getSolution();
			
			int x=0; int y=0;
			
			for(int i=0;i<solution.length;i++) {
				int step=solution[i];
				
				if(step==1) {
					y--;
					paths.add(new Path(display,x*display.getResolution(),y*display.getResolution()));
				}else if(step==2) {
					y++;
					paths.add(new Path(display,x*display.getResolution(),y*display.getResolution()));
				}else if(step==3) {
					x--;
					paths.add(new Path(display,x*display.getResolution(),y*display.getResolution()));
				}else if(step==4) {
					x++;
					paths.add(new Path(display,x*display.getResolution(),y*display.getResolution()));
				}
			}
			bFSSolver.stop();
			bFSSolver=null;
		}
	}
	
	
	public void render(Graphics2D g) {
		
		entrance.render(g);
		
		exit.render(g);
		
		for(int i=0;i<walls.size();i++) {
			walls.get(i).render(g);
		}
		
		for(int i=0;i<paths.size();i++) {
			paths.get(i).render(g);
		}
		
		for(int x=0;x<display.getWidth()/display.getResolution();x++) {
			for(int y=0;y<display.getHeight()/display.getResolution();y++) {
				g.setColor(Color.LIGHT_GRAY);
				g.drawRect(x*display.getResolution(),y*display.getResolution(),display.getResolution(),display.getResolution());
			}
		}
	}
	
	public void reset() {
		walls.removeAll(walls);
		
		paths.removeAll(paths);
		
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
	
	public DFSMaker getDFSMaker() {
		return dFSMaker;
	}
	
	public BFSSolver getBFSSolver() {
		return bFSSolver;
	}
	
	public Entrance getEntrance() {
		return entrance;
	}
	
	public Exit getExit() {
		return exit;
	}
	
	public ArrayList<Wall> getWalls() {
		return walls;
	}
}
