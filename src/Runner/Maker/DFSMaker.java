package Runner.Maker;

import java.util.Stack;

import Entity.Entrance;
import Main.Display;
import Runner.Runner;

public class DFSMaker extends Runner {
	
	private Thread thread;
	
	private int startX;
	
	private int startY;
	
	private Stack<Integer> stacks=new Stack<Integer>();
	
	private boolean[][] map;
	
	private boolean done;
	
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
		
		Entrance entrance=display.getRunState().getEntrance();
		
		int x=entrance.getX()/display.getResolution(); int y=entrance.getY()/display.getResolution();
		
		startX=x; startY=y;
		
		map[x][y]=true;
	
		do {
			boolean[] directions=getDirections(x,y);
			
			if(directions[0]==false && directions[1]==false && directions[2]==false && directions[3]==false) {
				
				int step=stacks.get(stacks.size()-1);
				
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
			
			
		}while(x!=startX || y!=startY);
		
		done=true;
		
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
	
	public boolean[][] getMap(){
		return map;
	}
	
	public boolean getDone() {
		return done;
	}
}
