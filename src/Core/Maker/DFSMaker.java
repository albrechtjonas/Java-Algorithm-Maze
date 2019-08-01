package Core.Maker;

import java.util.LinkedList;

import Core.Runner;
import Entity.Maker;
import Entity.Path;
import Entity.Wall;
import Main.Display;

public class DFSMaker extends Runner implements Runnable {
	
	private Thread thread;
	
	private boolean done=false;
	
	private LinkedList<Integer> steps=new LinkedList<Integer>();
	
	private int size=0;
	
	private double time=0;
	
	public DFSMaker(Display display) {
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
		
		size=display.getResolution();
		
		long last=System.nanoTime();
		
		long now;
		
		System.out.println("----------");
		System.out.print("Making->");
		
		Maker maker=display.getRunState().getMaker();
		
		display.getRunState().addPath(new Path(display,maker.getX(),maker.getY()));
		
		while(!done) {
			
			now=System.nanoTime();
			
			time+=(now-last);
			
			last=now;
			
			LinkedList<Integer> directions=getDirections(maker);
			
			if(directions.size()>0) {
			
			int number=directions.get((int)(Math.random()*directions.size()));
			
			if(number==1) {
				maker.setY(maker.getY()-size);
				display.getRunState().addPath(new Path(display,maker.getX(),maker.getY()));
				steps.add(1);
			}
			
			if(number==2) {
				maker.setY(maker.getY()+size);
				display.getRunState().addPath(new Path(display,maker.getX(),maker.getY()));
				steps.add(2);
			}
			
			if(number==3) {
				maker.setX(maker.getX()-size);
				display.getRunState().addPath(new Path(display,maker.getX(),maker.getY()));
				steps.add(3);
			}
			
			if(number==4) {
				maker.setX(maker.getX()+size);
				display.getRunState().addPath(new Path(display,maker.getX(),maker.getY()));
				steps.add(4);
			}
			
			}else {
				if(steps.size()!=0) {
					if(isFree(maker.getX()-size,maker.getY())) {
						display.getRunState().addWall(new Wall(display,maker.getX()-size,maker.getY()));
					}
					
					if(isFree(maker.getX()+size,maker.getY())) {
						display.getRunState().addWall(new Wall(display,maker.getX()+size,maker.getY()));
					}
					
					if(isFree(maker.getX(),maker.getY()-size)) {
						display.getRunState().addWall(new Wall(display,maker.getX(),maker.getY()-size));
					}
					
					if(isFree(maker.getX(),maker.getY()+size)) {
						display.getRunState().addWall(new Wall(display,maker.getX(),maker.getY()+size));
					}
					
					int lastStep=steps.get(steps.size()-1);
				
					if(lastStep==1) {
						maker.setY(maker.getY()+size);
						steps.remove(steps.size()-1);
					}
				
					if(lastStep==2) {
						maker.setY(maker.getY()-size);
						steps.remove(steps.size()-1);
					}
				
					if(lastStep==3) {
						maker.setX(maker.getX()+size);
						steps.remove(steps.size()-1);
					}
				
					if(lastStep==4) {
						maker.setX(maker.getX()-size);
						steps.remove(steps.size()-1);
					}
				}else {
					done=true;
				}
			}
			
		}
		
		System.out.println("Done");
		System.out.println("Time: "+time/(1000000000)+" seconds");
		System.out.println("----------");
		
		for(int i=0;i<display.getRunState().getPaths().size();i++) {
			Path path=display.getRunState().getPaths().get(i);
			
			if(path.getX()>=display.getWidth()-size*2) {
				if(path.getY()>=display.getHeight()-size*2) {
					display.getRunState().getExit().setX(path.getX());
					display.getRunState().getExit().setY(path.getY());
				}
			}
		}
		
	}
	
	private LinkedList<Integer> getDirections(Maker maker) {
		LinkedList<Integer> list=new LinkedList<Integer>();
		
		boolean up=true;
		
		boolean down=true;
		
		boolean left=true;
		
		boolean right=true;
		
		if(maker.getY()<=0) {
			up=false;
		}
		
		if(maker.getY()+size>=display.getHeight()) {
			down=false;
		}
		
		if(maker.getX()<=0) {
			left=false;
		}
		
		if(maker.getX()+size>=display.getWidth()) {
			right=false;
		}
		
		for(int i=0;i<display.getRunState().getPaths().size();i++) {
			Path path=display.getRunState().getPaths().get(i);
			
			boolean upOne=path.getX()==maker.getX() && maker.getY()-size==path.getY();
			
			boolean upTwo=path.getX()==maker.getX() && maker.getY()-2*size==path.getY();
			
			boolean upThree=maker.getY()-size==path.getY() && maker.getX()-size==path.getX();
			
			boolean upFour=maker.getY()-size==path.getY() && maker.getX()+size==path.getX();
			
			if(upOne || upTwo || upThree || upFour) {
				up=false;
			}
			
			boolean downOne=path.getX()==maker.getX() && maker.getY()+size==path.getY();
			
			boolean downTwo=path.getX()==maker.getX() && maker.getY()+2*size==path.getY();
			
			boolean downThree=maker.getY()+size==path.getY() && maker.getX()-size==path.getX();
			
			boolean downFour=maker.getY()+size==path.getY() && maker.getX()+size==path.getX();
			
			if(downOne || downTwo || downThree || downFour) {
				down=false;
			}
			
			boolean leftOne=path.getY()==maker.getY() && maker.getX()-size==path.getX();
			
			boolean leftTwo=path.getY()==maker.getY() && maker.getX()-2*size==path.getX();
			
			boolean leftThree=path.getX()==maker.getX()-size && maker.getY()-size==path.getY();
			
			boolean leftFour=path.getX()==maker.getX()-size && maker.getY()+size==path.getY();
			
			if(leftOne || leftTwo || leftThree || leftFour) {
				left=false;
			}
			
			boolean rightOne=path.getY()==maker.getY() && maker.getX()+size==path.getX();
			
			boolean rightTwo=path.getY()==maker.getY() && maker.getX()+2*size==path.getX();
			
			boolean rightThree=path.getX()==maker.getX()+size && maker.getY()-size==path.getY();
			
			boolean rightFour=path.getX()==maker.getX()+size && maker.getY()+size==path.getY();
			
			if(rightOne || rightTwo || rightThree || rightFour) {
				right=false;
			}
		}
		
		if(up) {
			list.add(1);
		}
		
		if(down) {
			list.add(2);
		}
		
		if(left) {
			list.add(3);
		}
		
		if(right) {
			list.add(4);
		}

		return list;
	}
	
	private boolean isFree(int x,int y) {
		boolean free=true;
		
		for(int i=0;i<display.getRunState().getPaths().size();i++) {
			Path path=display.getRunState().getPaths().get(i);
			
			if(path.getX()==x && path.getY()==y) {
				free=false;
				break;
			}
		}
		
		return free;
	}
	
	public boolean getDone() {
		return done;
	}
}
