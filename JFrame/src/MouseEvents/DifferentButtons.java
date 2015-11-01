package MouseEvents;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

public class DifferentButtons extends JFrame{

	private JLabel display;
	private String message;
		
	public DifferentButtons(){
		super("Mouse Events");
			
		display = new JLabel("Default");
		display.setBackground(Color.WHITE);
		display.setHorizontalAlignment(JLabel.CENTER);
		add(display, BorderLayout.SOUTH);
			
		addMouseListener(new MouseClass());
	}
		
	private class MouseClass extends MouseAdapter{
		@Override
		public void mouseClicked(MouseEvent e){
			message = String.format("You clicked the mouse %d times with ", e.getClickCount());
			
			if (e.isMetaDown()) {
				message += "the right button.";
			}
			else if (e.isAltDown()) {
				message += "the middle button.";
			}
			else{
				message += "the left button.";
			}
			display.setText(message);
		}
	}
	
		public static void main(String[] args) {
			DifferentButtons mice = new DifferentButtons();
			mice.setVisible(true);
			mice.setSize(1000, 400);
			mice.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
}


