import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.*;

public class Gui extends JFrame{

	private JList list;
	
	private static String[] colors = {"Черно", "Бяло", "Синьо", "Червено", "Зелено"};
	private static Color[] javacolors = {Color.BLACK, Color.WHITE, Color.BLUE, Color.RED, Color.GREEN};
	
	public Gui(){
		super("Смени цвета");
		setLayout(new FlowLayout());
		
		list = new JList(colors);
		list.setVisibleRowCount(5);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		add(new JScrollPane(list));
		
		list.addListSelectionListener(
				new ListSelectionListener() {
					
					@Override
					public void valueChanged(ListSelectionEvent e) {
						getContentPane().setBackground(javacolors[list.getSelectedIndex()]);
					}
				}
				);
		
		
		
	}
	
	public static void main(String[] args) {
		Gui fr = new Gui();
		fr.setVisible(true);
		fr.setSize(800, 400);
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
