package MouseEvents;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;


public class Mouse extends JFrame {

	private JPanel mouse;
	private JLabel display;
	
	public Mouse(){
		super("Mouse Events");
		
		mouse = new JPanel();
		mouse.setBackground(Color.WHITE);
		add(mouse, BorderLayout.CENTER);
		
		display = new JLabel("Default");
		display.setBackground(Color.WHITE);
		display.setHorizontalAlignment(JLabel.CENTER);
		add(display, BorderLayout.SOUTH);
		
		Handler handler = new Handler();
		mouse.addMouseListener(handler);
		mouse.addMouseMotionListener(handler);
	}
	
	private class Handler implements MouseListener, MouseMotionListener{

		@Override
		public void mouseDragged(MouseEvent arg0) {
			display.setText("You are dragging the mouse");
			display.setFont(new Font("Serif", Font.CENTER_BASELINE, 18));
		}

		@Override
		public void mouseMoved(MouseEvent arg0) {
			display.setText("You are moving the mouse");
			display.setFont(new Font("Serif", Font.CENTER_BASELINE, 18));
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			display.setText("You entered the screen");
			display.setFont(new Font("Serif", Font.CENTER_BASELINE, 18));
			mouse.setBackground(Color.BLUE);
		}

		@Override
		public void mouseExited(MouseEvent e) {
			display.setText("You exited the screen");
			display.setFont(new Font("Serif", Font.CENTER_BASELINE, 18));
			mouse.setBackground(Color.WHITE);
		}

		@Override
		public void mousePressed(MouseEvent e) {
			display.setText("You pressed the mouse");
			display.setFont(new Font("Serif", Font.CENTER_BASELINE, 18));
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			display.setText("You released the mouse");
			display.setFont(new Font("Serif", Font.CENTER_BASELINE, 18));
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			display.setText(String.format("You clicked the mouse at (%d, %d)", e.getX(), e.getY()));
			display.setFont(new Font("Serif", Font.CENTER_BASELINE, 18));
		}
	}
	
	public static void main(String[] args) {
		Mouse mice = new Mouse();
		mice.setVisible(true);
		mice.setSize(1000, 400);
		mice.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
