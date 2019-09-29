package Runner.Maker;

import java.util.Stack;

import Entity.Entity;
import Entity.Entrance;
import Entity.Exit;
import Entity.Wall;
import Main.Display;
import Runner.Runner;

public class DFSMaker extends Runner {
	
	private Thread thread;
	
	private Stack<Integer> stacks=new Stack<Integer>();
	
	private boolean[][] map;
	
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
		map=new boolean[display.getWidth()/display.getResolution()][display.getHeight()/display.getResolution()];
		
		Entity[][] entities=display.getRunState().getEntities();
		
		Entrance entrance=display.getRunState().getEntrance();
		
		Exit exit=display.getRunState().getExit();
		
		map[exit.getX()/display.getResolution()][exit.getY()/display.getResolution()]=true;
		
		int x=entrance.getX()/display.getResolution(); int y=entrance.getY()/display.getResolution();
		
		map[x][y]=true;
	
		do {
			boolean[] directions=getDirections(x,y);
			
			if(directions[0]==false && directions[1]==false && directions[2]==false && directions[3]==false) {
				
				int step=stacks.peek();
				
				if(step==0) {
					y++;
				}else if(step==1) {
					y--;
				}else if(step==2) {
					x++;
				}else if(step==3) {
					x--;
				}
				
				stacks.pop();
			}else {
				while(true) {
					int index=(int)(Math.random()*directions.length);
					
					if(index==0 && directions[0]) {
						y--;
						stacks.add(0);
						break;
					}else if(index==1 && directions[1]) {
						y++;
						stacks.add(1);
						break;
					}else if(index==2 && directions[2]) {
						x--;
						stacks.add(2);
						break;
					}else if(index==3 && directions[3]) {
						x++;
						stacks.add(3);
						break;
					}
				}
			}
			
			map[x][y]=true;
			
			
		}while(!stacks.isEmpty());
		
		x=exit.getX()/display.getResolution(); y=exit.getY()/display.getResolution();
		
		fix(x,y);
		
		for(x=0;x<entities.length;x++) {
			for(y=0;y<entities.length;y++) {
				if(map[x][y]==false) {
					entities[x][y]=new Wall(display,x*display.getResolution(),y*display.getResolution());
				}
			}
		}
		stop();
	}
	
	private void fix(int x,int y) {
		
		boolean up=true;
		
		boolean down=true;
		
		boolean left=true;
		
		boolean right=true;
		
		if(y==0) {
			up=false;
		}
		
		if(y==map.length-1) {
			down=false;
		}
		
		if(x==0) {
			left=false;
		}
		
		if(x==map.length-1) {
			right=false;
		}
		
		if(up==true && map[x][y-1]==true) {
			return;
		}
		
		if(down==true && map[x][y+1]==true) {
			return;
		}
		
		if(left==true && map[x-1][y]==true) {
			return;
		}
		
		if(right==true && map[x+1][y]==true) {
			return;
		}
		
		while(true) {
			int direction=(int)(Math.random()*4);
		
			if(direction==0 && up) {
				while(true) {
					y--;
					
					if(map[x][y]==true) {
						return;
					}else {
						map[x][y]=true;
					}
				}
			}else if(direction==1 && down) {
				while(true) {
					y++;
					
					if(map[x][y]==true) {
						return;
					}else {
						map[x][y]=true;
					}
				}
			}else if(direction==2 && left) {
				while(true) {
					x--;
					
					if(map[x][y]==true) {
						return;
					}else {
						map[x][y]=true;
					}
				}
			}else if(direction==3 && right) {
				while(true) {
					x++;
					
					if(map[x][y]==true) {
						return;
					}else {
						map[x][y]=true;
					}
				}
			}
		}
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
			if(map[x][y-2]) {
				up=false;
			}
		}catch(Exception e) {}
		
		try {
			if(map[x-1][y-1]) {
				up=false;
			}
		}catch(Exception e) {}
		
		try {
			if(map[x+1][y-1]) {
				up=false;
			}
		}catch(Exception e) {}
		
		try {
			if(map[x][y+1]) {
				down=false;
			}
		}catch(Exception e) {}
		
		try {
			if(map[x][y+2]) {
				down=false;
			}
		}catch(Exception e) {}
		
		try {
			if(map[x-1][y+1]) {
				down=false;
			}
		}catch(Exception e) {}
		
		try {
			if(map[x+1][y+1]) {
				down=false;
			}
		}catch(Exception e) {}
		
		try {
			if(map[x-1][y]) {
				left=false;
			}
		}catch(Exception e) {}
		
		try {
			if(map[x-2][y]) {
				left=false;
			}
		}catch(Exception e) {}
		
		try {
			if(map[x-1][y-1]) {
				left=false;
			}
		}catch(Exception e) {}
		
		try {
			if(map[x-1][y+1]) {
				left=false;
			}
		}catch(Exception e) {}
		
		try {
			if(map[x+1][y]) {
				right=false;
			}
		}catch(Exception e) {}
		
		try {
			if(map[x+2][y]) {
				right=false;
			}
		}catch(Exception e) {}
		
		try {
			if(map[x+1][y-1]) {
				right=false;
			}
		}catch(Exception e) {}
		
		try {
			if(map[x+1][y+1]) {
				right=false;
			}
		}catch(Exception e) {}
		
		return new boolean[] {up,down,left,right};
	}
}
