package Handler;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Entity.Wall;
import Main.Display;

public class ComponentActionHandler implements ActionListener {
	
	private Display display;
	
	private Scanner scanner;
	
	public ComponentActionHandler(Display display) {
		this.display=display;
	}
	
	public void actionPerformed(ActionEvent actionEvent) {
		JTextField textField=display.getWindow().getTextField();
		
		if(textField.getText().equals("startMaking")) {
			display.setStartMaking(true);
			textField.setText("");
		}else if(textField.getText().equals("reset")) {
			display.getRunState().reset();
			textField.setText("");
		}else if(textField.getText().contains("setResolution")) {
			try {
				display.setResolution(Integer.valueOf(textField.getText().substring(14)));
				display.getRunState().reset();
				textField.setText("");
			}catch(Exception exception) {
				JOptionPane.showMessageDialog(display.getWindow().getFrame(),"Incorrect Format");
			}
		}else if(textField.getText().equals("exportTextMap")) {
			String path=JOptionPane.showInputDialog(display.getWindow().getFrame(),"Path");
			exportTextMap(path);
			textField.setText("");
		}else if(textField.getText().equals("exportImageMap")) {
			String path=JOptionPane.showInputDialog(display.getWindow().getFrame(),"Path");
			exportImageMap(path);
			textField.setText("");
		}else if(textField.getText().equals("importMap")) {
			String path=JOptionPane.showInputDialog(display.getWindow().getFrame(),"Path");
			loadMap(path);
			textField.setText("");
		}else if(textField.getText().equals("startSolving")) {
			display.setStartSolving(true);
			textField.setText("");
		}else if(textField.getText().contains("setTPT")) {
			try {
				display.setTPT(Integer.valueOf(textField.getText().substring(7)));
				textField.setText("");
			}catch(Exception exception) {
				JOptionPane.showMessageDialog(display.getWindow().getFrame(),"Incorrect Format");
			}
		}else if(textField.getText().equals("start")) {
			display.setStart(true);
			textField.setText("");
		}
	}
	
	private void exportTextMap(String path) {
		try {
			FileWriter writer=new FileWriter(new File(path));
			writer.write("Resolution:"+display.getResolution()+"\n");
			writer.write(display.getRunState().getMaker().getX()+","+display.getRunState().getMaker().getY()+"\n");
			writer.write(display.getRunState().getExit().getX()+","+display.getRunState().getExit().getY()+"\n");
			for(int i=0;i<display.getRunState().getWalls().size();i++) {
				int x=display.getRunState().getWalls().get(i).getX();
				int y=display.getRunState().getWalls().get(i).getY();
				writer.write(x+","+y+"\n");	
			}
			
			writer.close();
		}catch(Exception exception) {
			JOptionPane.showMessageDialog(display.getWindow().getFrame(),"Cannot export map");
		}
	}
	
	private void exportImageMap(String path) {
		BufferedImage image=new BufferedImage(display.getWidth(),display.getHeight(),BufferedImage.TYPE_INT_RGB);
		
		Graphics2D g=image.createGraphics();
		
		g.setColor(Color.WHITE);
		g.fillRect(0,0,display.getWidth(),display.getHeight());
		
		for(int x=0;x<display.getWidth()/display.getResolution();x++) {
			for(int y=0;y<display.getHeight()/display.getResolution();y++) {
				g.setColor(Color.LIGHT_GRAY);
				g.drawRect(x*display.getResolution(),y*display.getResolution(),display.getResolution(),display.getResolution());
			}
		}
		
		for(int i=0;i<display.getRunState().getWalls().size();i++) {
			Wall wall=display.getRunState().getWalls().get(i);
			g.setColor(Color.GRAY);
			g.fillRect(wall.getX(),wall.getY(),display.getResolution(),display.getResolution());
		}
		
		g.setColor(Color.RED);
		g.fillRect(display.getRunState().getMaker().getX(),display.getRunState().getMaker().getY(),display.getResolution(),display.getResolution());
		
		g.setColor(Color.BLUE);
		g.fillRect(display.getRunState().getExit().getX(),display.getRunState().getExit().getY(),display.getResolution(),display.getResolution());
		
		g.dispose();
		
		File file=new File(path);
		try {
			ImageIO.write(image,"png",file);
		} catch (Exception exception) {
			JOptionPane.showMessageDialog(display.getWindow().getFrame(),"Cannot export map");
		}
	}
	
	private void loadMap(String path) {
		int index=0;
		
		try {
			scanner=new Scanner(new File(path));
		
		
		while(scanner.hasNext()) {
			
			String thing=scanner.next();
			
			int x=0;
			
			int y=0;
			
			try {
				x=Integer.valueOf(thing.substring(0,thing.indexOf(",")));
				
				y=Integer.valueOf(thing.substring(thing.indexOf(",")+1));
			}catch(Exception exception) {}
			
			if(index==0) {
				int resolution=0;
				try {
					resolution=Integer.valueOf(thing.substring(thing.indexOf(":")+1));
				}catch(Exception exception) {
					JOptionPane.showMessageDialog(display.getWindow().getFrame(),"This map cannot be loaded");
				}
				display.setResolution(resolution);
				
				index++;
			}else if(index==1) {
				display.getRunState().getMaker().setX(x);
				display.getRunState().getMaker().setY(y);
				
				index++;
			}else if(index==2) {
				display.getRunState().getExit().setX(x);
				display.getRunState().getExit().setY(y);
				
				index++;
			}else {
				display.getRunState().addWall(new Wall(display,x,y));
			}
		}
		}catch(Exception exception) {
			System.out.println("Cannot find file");
		}
	}
}
