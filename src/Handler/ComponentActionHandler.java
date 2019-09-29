package Handler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import Main.Display;

public class ComponentActionHandler implements ActionListener {
	
	private Display display;
	
	public ComponentActionHandler(Display display) {
		this.display=display;
	}
	
	public void actionPerformed(ActionEvent actionEvent) {
		String text=display.getWindow().getTextField().getText();
		
		if(text.equals("make")) {
			display.getRunState().reset();
			display.getRunState().setDFSMaking(true);
			display.getWindow().getTextField().setText("");
		}else if(text.equals("solve")) {
			display.getRunState().setBFSSolving(true);
			display.getWindow().getTextField().setText("");
		}else if(text.equals("reset")) {
			display.getRunState().reset();
			display.getWindow().getTextField().setText("");
		}else if(text.contains("setResolution")) {
			display.setResolution(Integer.valueOf(text.substring(14)));
			display.getRunState().reset();
			display.getWindow().getTextField().setText("");
		}else {
			JOptionPane.showMessageDialog(display.getWindow().getFrame(),"Unknown Command");
		}
	}
}
