package Main;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JTextField;

public class Window {
	private String title;
	
	private int width;
	
	private int height;
	
	private JFrame frame;
	
	private Canvas canvas;
	
	private final int textFieldHeight=50;
	
	private JTextField textField;
	
	public Window(String title,int width,int height) {
		this.title=title;
		this.width=width;
		this.height=height;
		
		createWindow();
	}
	
	private void createWindow() {
		frame=new JFrame();
		frame.setTitle(title);
		
		canvas=new Canvas();
		canvas.setPreferredSize(new Dimension(width,height));
		canvas.setMaximumSize(new Dimension(width,height));
		canvas.setMinimumSize(new Dimension(width,height));
		frame.add(canvas,BorderLayout.CENTER);
		
		textField=new JTextField();
		textField.setSize(width,textFieldHeight);
		frame.add(textField,BorderLayout.SOUTH);
		
		frame.pack();
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public int getWidth() {
		return canvas.getWidth();
	}
	
	public int getHeight() {
		return canvas.getHeight();
	}
	
	public JFrame getFrame() {
		return frame;
	}
	
	public Canvas getCanvas() {
		return canvas;
	}
	
	public JTextField getTextField() {
		return textField;
	}
}
