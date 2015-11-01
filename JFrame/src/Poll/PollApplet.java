package Poll;

import java.awt.*;
import java.net.*;
import javax.swing.*;
import javax.swing.event.*;
import java.applet.*;
import java.util.*;

public class PollApplet extends JApplet {

	private HashMap<String, URL> info;
	private JList list;
	private ArrayList<String> names;
	
	public void init(){
		info = new HashMap<String, URL>();
		names = new ArrayList<String>();
		
		grabHTML();
		add(new JLabel("Choose a site"));
		
		list = new JList(names.toArray());
		list.addListSelectionListener(
				new ListSelectionListener() {
					
					@Override
					public void valueChanged(ListSelectionEvent e) {
						Object obj = list.getSelectedValue();
						URL url = info.get(obj);
						AppletContext browser = getAppletContext();
						browser.showDocument(url);
					}
				}
		);
		
		add(new JScrollPane(list), BorderLayout.CENTER);
	}
	
	public void grabHTML(){
		String title;
		String address;
		int count = 0;
		
		title = getParameter("title" + count);
		while(title != null){
			address = getParameter("address" + count);
			URL addUrl;
			try {
				addUrl = new URL(address);
				info.put(title, addUrl);
				names.add(title);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			++count;
			title = getParameter("title" + count);
		}
		
	}
	
	public static void main(String[] args) {

	}

}
