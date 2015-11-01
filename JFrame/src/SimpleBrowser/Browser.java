package SimpleBrowser;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;
import javax.swing.event.*;

public class Browser extends JFrame {

	private JTextField tf;
	private JEditorPane area;
	
	public Browser(){
		super("Boom Browser");
		
		tf = new JTextField();
		tf.setText("Enter valid url");
		tf.addActionListener(
				new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						load(e.getActionCommand());
					}
				}
		);
		add(tf, BorderLayout.NORTH);
		area = new JEditorPane();
		area.setEditable(false);
		area.addHyperlinkListener(
				new HyperlinkListener() {
					
					@Override
					public void hyperlinkUpdate(HyperlinkEvent e) {
						if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
							load(e.getURL().toString());
						}
					}
				}
		);
		
		add(new JScrollPane(area), BorderLayout.CENTER);
		setSize(600, 400);
		setVisible(true);
	}
	
	private void load(String user){
		try {
			area.setPage(user);
			tf.setText(user);
		} catch (IOException e) {
			System.out.println("Error");
		}
	}
	
	public static void main(String[] args) {
		Browser browser = new Browser();
		browser.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

}
