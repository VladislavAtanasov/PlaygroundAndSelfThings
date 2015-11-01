package JListsMovingObjects;

import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.*;

public class Gui extends JFrame {
	
	private JList left;
	private JList right;
	private JButton move;
	private static String[] foods = {"Bacon", "Eggs", "Ham", "Cheese", "Carrots", "Potatoes"};
	
	public Gui(){
		super("Choose food/s");
		setLayout(new FlowLayout());
		
		left = new JList(foods);
		left.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		left.setVisibleRowCount(3);
		add(new JScrollPane(left));
		
		move = new JButton("Move it right");
		move.addActionListener(
				new ActionListener() {
					
					@SuppressWarnings("deprecation")
					@Override
					public void actionPerformed(ActionEvent e) {
						right.setListData(left.getSelectedValues());
					}
				}
		);
		add(move);
		
		right = new JList();
		right.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		right.setVisibleRowCount(3);
		add(new JScrollPane(right));
		
	}
	
	public static void main(String[] args) {
		Gui fr = new Gui();
		fr.setVisible(true);
		fr.setSize(800, 300);
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
