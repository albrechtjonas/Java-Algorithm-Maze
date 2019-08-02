package State;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.LinkedList;

import Core.Maker.DFSMaker;
import Core.Solver.AStarSolver;
import Core.Solver.BFSSolver;
import Entity.Exit;
import Entity.Maker;
import Entity.Path;
import Entity.Wall;
import Main.Display;

public class RunState extends State {
	
	private LinkedList<Path> paths=new LinkedList<Path>();
	
	private ArrayList<Wall> walls=new ArrayList<Wall>();
	
	private Maker maker;
	
	private Exit exit;
	
	private DFSMaker dFSMaker;
	
	private BFSSolver bFSSolver;
	
	private AStarSolver aStarSolver;
	
	public RunState(Display display) {
		super(display);
		
		createObject();
	}
	
	private void createObject() {
		maker=new Maker(display,0,0);
		
		exit=new Exit(display,0,0);
	}
	
	public void tick() {
		for(int i=0;i<paths.size();i++) {
			try {
				paths.get(i).tick();
			}catch(Exception exception) {}
		}
		
		for(int i=0;i<walls.size();i++) {
			try {
				walls.get(i).tick();
			}catch(Exception exception) {}
		}
		
		maker.tick();
		
		exit.tick();
		
		if(display.getStartMaking()==true) {
			dFSMaker=new DFSMaker(display);
			dFSMaker.start();
			display.setStartMaking(false);
		}
		
		if(display.getStartSolving()==true) {
			bFSSolver=new BFSSolver(display);
			bFSSolver.start();
			display.setStartSolving(false);
		}
		
		if(display.getStart()==true) {
			aStarSolver=new AStarSolver(display);
			aStarSolver.start();
			display.setStart(false);
		}
	}
	
	public void render(Graphics2D g) {
		for(int i=0;i<paths.size();i++) {
			try {
				paths.get(i).render(g);
			}catch(Exception exception) {}
		}
		
		for(int i=0;i<walls.size();i++) {
			try {
				walls.get(i).render(g);
			}catch(Exception exception) {}
		}
		
		for(int x=0;x<display.getWidth()/display.getResolution();x++) {
			for(int y=0;y<display.getHeight()/display.getResolution();y++) {
				g.setColor(Color.LIGHT_GRAY);
				g.drawRect(x*display.getResolution(),y*display.getResolution(),display.getResolution(),display.getResolution());
			}
		}
		
		maker.render(g);
		
		exit.render(g);
		
	}
	
	public void reset() {
		if(dFSMaker!=null) {
			dFSMaker.stop();
			dFSMaker=null;
		}
		
		if(bFSSolver!=null) {
			bFSSolver.stop();
			bFSSolver=null;
		}
		
		if(aStarSolver!=null) {
			aStarSolver.stop();
			aStarSolver=null;
		}
		
		if(walls.size()!=0) {
			try {
				walls.removeAll(walls);
			}catch(Exception exception) {}
		}
		
		if(paths.size()!=0) {
			try {
				paths.removeAll(paths);
			}catch(Exception exception) {}
		}
		
		maker.setX(0);
		maker.setY(0);
		maker.setIndex(0);
		
		exit.setX(0);
		exit.setY(0);
	}
	
	public LinkedList<Path> getPaths() {
		return paths;
	}
	
	public void addPath(Path path) {
		paths.add(path);
	}
	
	public void removePath(Path path) {
		paths.remove(path);
	}
	
	public void removeAllPaths() {
		paths.removeAll(paths);
	}
	
	public ArrayList<Wall> getWalls() {
		return walls;
	}
	
	public void addWall(Wall wall) {
		walls.add(wall);
	}
	
	public void removeWall(Wall wall) {
		walls.remove(wall);
	}
	
	public Maker getMaker() {
		return maker;
	}
	
	public Exit getExit() {
		return exit;
	}
	
	public DFSMaker getDFSMaker() {
		return dFSMaker;
	}
	
	public BFSSolver getBFSSolver() {
		return bFSSolver;
	}
}
