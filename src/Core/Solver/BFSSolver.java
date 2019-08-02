package Core.Solver;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import Core.Runner;
import Entity.Exit;
import Entity.Wall;
import Main.Display;

public class BFSSolver extends Runner implements Runnable {
	
	private Thread thread;
	
	private int startX;
	
	private int startY;
	
	private ArrayList<ArrayList<Integer>> locations=new ArrayList<ArrayList<Integer>>();
	
	private ArrayList<ArrayList<Integer>> steps=new ArrayList<ArrayList<Integer>>();
	
	private ArrayList<Integer> shortestPath=new ArrayList<Integer>();
	
	private boolean done=false;
	
	private boolean showed=false;
	
	private double time;
	
	public BFSSolver(Display display) {
		super(display);
		
		thread=new Thread(this);
	}
	
	public synchronized void start() {
		thread.start();
	}
	
	@SuppressWarnings("deprecation")
	public synchronized void stop() {
		thread.stop();
	}
	
	public void run() {
		
		startX=display.getRunState().getMaker().getX();
		
		startY=display.getRunState().getMaker().getY();
		
		long last=System.nanoTime();
		
		long now;
		
		System.out.println("----------");
		System.out.print("Solving->");
		
		ArrayList<Integer> location=new ArrayList<Integer>();
		
		location.add(display.getRunState().getMaker().getX());
		
		location.add(display.getRunState().getMaker().getY());
		
		locations.add(location);
		
		while(!isFound(locations)) {
			now=System.nanoTime();
			
			time+=(now-last);
			
			last=now;
			
			locations=getLocations(locations,display.getRunState().getWalls());
			
			for(int i=0;i<locations.size();i++) {
				while(!isOnly(locations.get(i),locations)) {
					locations.remove(i);
					steps.remove(i);
				}
			}	
		}
		
		System.out.println("Done");
		System.out.println("Time: "+time/(1000000000)+" seconds");
		System.out.println("Steps: "+shortestPath.size()+" steps");
		System.out.println("----------");
	}
	
	private boolean isFound(ArrayList<ArrayList<Integer>> locations) {
		boolean found=false;
		
		Exit exit=display.getRunState().getExit();
		
		for(int i=0;i<locations.size();i++) {
			int x=locations.get(i).get(0);
			
			int y=locations.get(i).get(1);
			
			if(x==exit.getX() && y==exit.getY()) {
				found=true;
				if(steps.size()!=0) {
					shortestPath=steps.get(i);
				}else {
					JOptionPane.showMessageDialog(display.getWindow().getFrame(),"Position Collide");
				}
				done=true;
				break;
			}
		}
		
		return found;
	}
	
	private ArrayList<ArrayList<Integer>> getLocations(ArrayList<ArrayList<Integer>> locations,ArrayList<Wall> walls) {
		ArrayList<ArrayList<Integer>> newLocations=new ArrayList<ArrayList<Integer>>();
		
		ArrayList<ArrayList<Integer>> newSteps=new ArrayList<ArrayList<Integer>>();
		
		int size=display.getResolution();
		
		for(int i=0;i<locations.size();i++) {
			int x=locations.get(i).get(0);
			
			int y=locations.get(i).get(1);
			
			ArrayList<Integer> thing=new ArrayList<Integer>();
			
			try {
				thing=steps.get(i);
			}catch(Exception exception) {}
			
			boolean up=true;
			
			boolean down=true;
			
			boolean left=true;
			
			boolean right=true;
			
			if(y<=0) {
				up=false;
			}
			
			if(y+size>=display.getHeight()) {
				down=false;
			}
			
			if(x<=0) {
				left=false;
			}
			
			if(x+size>=display.getWidth()) {
				right=false;
			}
			
			for(int w=0;w<walls.size();w++) {
				Wall wall=walls.get(w);
				
				if(up!=false) {
					if(wall.getX()==x && wall.getY()+size==y) {
						up=false;
					}
				}
				
				if(down!=false) {
					if(wall.getX()==x && wall.getY()-size==y) {
						down=false;
					}
				}
				
				if(left!=false) {
					if(wall.getY()==y && wall.getX()+size==x) {
						left=false;
					}
				}
				
				if(right!=false) {
					if(wall.getY()==y && wall.getX()-size==x) {
						right=false;
					}
				}
			}
			
			if(up) {
				ArrayList<Integer> location=new ArrayList<Integer>();
				
				location.add(x);
				location.add(y-display.getResolution());
				
				newLocations.add(location);
				
				ArrayList<Integer> step=new ArrayList<Integer>();
				
				for(int t=0;t<thing.size();t++) {
					step.add(thing.get(t));
				}
				
				step.add(1);
				
				newSteps.add(step);
			}
			
			if(down) {
				ArrayList<Integer> location=new ArrayList<Integer>();
				
				location.add(x);
				location.add(y+display.getResolution());
				
				newLocations.add(location);
				
				ArrayList<Integer> step=new ArrayList<Integer>();
				
				for(int t=0;t<thing.size();t++) {
					step.add(thing.get(t));
				}
				
				step.add(2);
				
				newSteps.add(step);
			}
			
			if(left) {
				ArrayList<Integer> location=new ArrayList<Integer>();
				
				location.add(x-display.getResolution());
				location.add(y);
				
				newLocations.add(location);
				
				ArrayList<Integer> step=new ArrayList<Integer>();
				
				for(int t=0;t<thing.size();t++) {
					step.add(thing.get(t));
				}
				
				step.add(3);
				
				newSteps.add(step);
			}
			
			if(right) {
				ArrayList<Integer> location=new ArrayList<Integer>();
				
				location.add(x+display.getResolution());
				location.add(y);
				
				newLocations.add(location);
				
				ArrayList<Integer> step=new ArrayList<Integer>();
				
				for(int t=0;t<thing.size();t++) {
					step.add(thing.get(t));
				}
				
				step.add(4);
				
				newSteps.add(step);
			}
		}
		
		this.steps=newSteps;
		
		return newLocations;
	}
	
	private boolean isOnly(ArrayList<Integer> localList,ArrayList<ArrayList<Integer>> globalList) {
		int targetX=localList.get(0);
		
		int targetY=localList.get(1);
		
		int times=0;
		
		for(int i=0;i<globalList.size();i++) {
			int x=globalList.get(i).get(0);
			
			int y=globalList.get(i).get(1);
			
			if(x==targetX && y==targetY) {
				times++;
			}
		}
		
		if(times==1) {
			return true;
		}else {
			return false;
		}
		
	}
	
	public int getStartX() {
		return startX;
	}
	
	public int getStartY() {
		return startY;
	}
	
	public ArrayList<Integer> getShortestPath(){
		return shortestPath;
	}
	
	public boolean getDone() {
		return done;
	}
	
	public void setShowed(boolean showed) {
		this.showed=showed;
	}
	
	public boolean getShowed() {
		return showed;
	}
}
