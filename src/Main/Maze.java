package Main;

public class Maze {
	private final String title="Maze";
	
	private final int width=800;
	
	private final int height=800;
	
	public Window window;
	
	private Display display;
	
	public static void main(String[]args) {
		new Maze();
	}
	
	private Maze() {
		window=new Window(title,width,height);
		
		display=new Display(window);
		display.start();
	}
}
