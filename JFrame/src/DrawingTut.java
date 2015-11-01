import java.awt.Color;
import java.awt.Graphics;
import javax.swing.*;


public class DrawingTut extends JPanel {

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		this.setBackground(Color.WHITE);
		
		g.setColor(Color.BLUE);
		g.fill3DRect(100, 50, 600, 400, true);
	}
	
	public static void main(String[] args) {
		JFrame jf = new JFrame("Title");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
		jf.setSize(1000, 500);
		DrawingTut dt = new DrawingTut();
		dt.setBackground(Color.RED);
		jf.add(dt);
	}

}
