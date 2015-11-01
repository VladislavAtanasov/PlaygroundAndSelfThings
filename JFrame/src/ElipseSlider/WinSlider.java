package ElipseSlider;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class WinSlider extends JFrame {

	private JSlider slider;
	private Elipse elipse;
	
	public WinSlider(){
		super("Machine");
		
		slider = new JSlider(SwingConstants.HORIZONTAL, 0, 300, 10);
		slider.setMajorTickSpacing(10);
		slider.setPaintTicks(true);
		
		elipse = new Elipse();
		elipse.setBackground(Color.ORANGE);
		
		slider.addChangeListener(
				new ChangeListener() {
					
					@Override
					public void stateChanged(ChangeEvent e) {
						elipse.setD(slider.getValue());
					}
				}
		);
		
		add(elipse, BorderLayout.CENTER);
		add(slider, BorderLayout.SOUTH);
	}
	
	public static void main(String[] args) {
		WinSlider ws = new WinSlider();
		ws.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ws.setVisible(true);
		ws.setSize(400, 400);
	}

}
