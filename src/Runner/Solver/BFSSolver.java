package Runner.Solver;

import java.util.ArrayList;

import Entity.Entity;
import Entity.Entrance;
import Entity.Exit;
import Entity.Path;
import Entity.Wall;
import Main.Display;
import Runner.Runner;

public class BFSSolver extends Runner {
	
	private Thread thread;
	
	private int targetX;
	
	private int targetY;
	
	private ArrayList<int[]> locations=new ArrayList<int[]>();
	
	private ArrayList<int[]> steps=new ArrayList<int[]>();
	
	private boolean[][] map;
	
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
		map=new boolean[display.getWidth()/display.getResolution()][display.getHeight()/display.getResolution()];
		
		Entity[][] entities=display.getRunState().getEntities();
		
		Exit exit=display.getRunState().getExit();
		
		Entrance entrance=display.getRunState().getEntrance();
		
		for(int x=0;x<entities.length;x++) {
			for(int y=0;y<entities.length;y++) {
				if(entities[x][y] instanceof Wall) {
					map[x][y]=true;
				}
			}
		}
		
		targetX=exit.getX()/display.getResolution(); targetY=exit.getY()/display.getResolution();
		
		locations.add(new int[] {entrance.getX()/display.getResolution(),entrance.getY()/display.getResolution()});
		
		while(!isSolved()) {
			
			ArrayList<int[]> newLocations=new ArrayList<int[]>();
			ArrayList<int[]> newSteps=new ArrayList<int[]>();
			
			for(int i=0;i<locations.size();i++) {
				int[] location=locations.get(i);
				boolean[] directions=getDirections(location[0],location[1]);
				
				if(directions[0]) {
					
					int[] localLocation=new int[] {location[0],location[1]-1};
					
					if(!contains(localLocation,newLocations)) {
					
						newLocations.add(localLocation);
						
						if(steps.size()>0) {
							int[] step=new int[steps.get(i).length+1];
							
							for(int x=0;x<steps.get(i).length;x++) {
								step[x]=steps.get(i)[x];
							}
							
							step[step.length-1]=1;
							
							newSteps.add(step);
						}else {
							newSteps.add(new int[] {1});
						}
					
					}
				}
				
				if(directions[1]) {
					int[] localLocation=new int[] {location[0],location[1]+1};
					
					if(!contains(localLocation,newLocations)) {
					
						newLocations.add(localLocation);
						
						if(steps.size()>0) {
							int[] step=new int[steps.get(i).length+1];
							
							for(int x=0;x<steps.get(i).length;x++) {
								step[x]=steps.get(i)[x];
							}
							
							step[step.length-1]=2;
							
							newSteps.add(step);
						}else {
							newSteps.add(new int[] {2});
						}
					
					}
				}
				
				if(directions[2]) {
					int[] localLocation=new int[] {location[0]-1,location[1]};
					
					if(!contains(localLocation,newLocations)) {
					
						newLocations.add(localLocation);
						
						if(steps.size()>0) {
							int[] step=new int[steps.get(i).length+1];
							
							for(int x=0;x<steps.get(i).length;x++) {
								step[x]=steps.get(i)[x];
							}
							
							step[step.length-1]=3;
							
							newSteps.add(step);
						}else {
							newSteps.add(new int[] {3});
						}
					
					}	
				}
				
				if(directions[3]) {
					int[] localLocation=new int[] {location[0]+1,location[1]};
					
					if(!contains(localLocation,newLocations)) {
					
						newLocations.add(localLocation);
						
						if(steps.size()>0) {
							int[] step=new int[steps.get(i).length+1];
							
							for(int x=0;x<steps.get(i).length;x++) {
								step[x]=steps.get(i)[x];
							}
							
							step[step.length-1]=4;
							
							newSteps.add(step);
						}else {
							newSteps.add(new int[] {4});
						}
					}	
				}
			}
			
			locations=newLocations;
			steps=newSteps;
		}
		
		stop();
	}
	
	private boolean contains(int[] array,ArrayList<int[]> list) {
		for(int i=0;i<list.size();i++) {
			int[] location=list.get(i);
			
			if(location[0]==array[0] && location[1]==array[1]) {
				return true;
			}
		}
		
		return false;
	}
	
	private boolean isSolved() {
		for(int i=0;i<locations.size();i++) {
			int[] location=locations.get(i);
			
			if(location[0]==targetX && location[1]==targetY) {
				
				Entity[][] entities=display.getRunState().getEntities();
				
				int[] step=steps.get(i);
				
				int x=display.getRunState().getEntrance().getX()/display.getResolution();
				
				int y=display.getRunState().getEntrance().getY()/display.getResolution();
				
				for(int w=0;w<step.length-1;w++) {
					int localStep=step[w];
					
					if(localStep==1) {
						y--;
						entities[x][y]=new Path(display,x*display.getResolution(),y*display.getResolution());
					}else if(localStep==2) {
						y++;
						entities[x][y]=new Path(display,x*display.getResolution(),y*display.getResolution());
					}else if(localStep==3) {
						x--;
						entities[x][y]=new Path(display,x*display.getResolution(),y*display.getResolution());
					}else if(localStep==4) {
						x++;
						entities[x][y]=new Path(display,x*display.getResolution(),y*display.getResolution());
					}
				}
				
				return true;
			}
		}
		return false;
	}
	
	private boolean[] getDirections(int x,int y) {
		boolean up=true;
		boolean down=true;
		boolean left=true;
		boolean right=true;
		
		if(y-1<0) {
			up=false;
		}
		
		if(y+1>map.length-1) {
			down=false;
		}
		
		if(x-1<0) {
			left=false;
		}
		
		if(x+1>map.length-1) {
			right=false;
		}
		
		try {
			if(map[x][y-1]) {
				up=false;
			}
		}catch(Exception e) {}
		
		try {
			if(map[x][y+1]) {
				down=false;
			}
		}catch(Exception e) {}
		
		try {
			if(map[x-1][y]) {
				left=false;
			}
		}catch(Exception e) {}
		
		try {
			if(map[x+1][y]) {
				right=false;
			}
		}catch(Exception e) {}
		
		return new boolean[] {up,down,left,right};
	}
}
