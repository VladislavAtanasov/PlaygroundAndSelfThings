package ElipseSlider;

import java.awt.*;
import javax.swing.*;

public class Elipse extends JPanel {
	
	private int d = 20;
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.fillOval(30, 30, d, d);
	}
	
	public void setD(int newD){
		d = (newD >= 0 ? newD : 10);
		repaint();
	}
	
}
