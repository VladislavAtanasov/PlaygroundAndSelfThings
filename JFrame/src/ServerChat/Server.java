package ServerChat;

import java.awt.*;
import java.awt.event.*;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.*;
import javax.swing.event.*;


public class Server extends JFrame{

	private JTextArea area;
	private JTextField  userField;
	private ObjectInputStream input;
	private ObjectOutputStream output;
	private Socket connection;
	private ServerSocket server;
	
	public Server(){
		super("Chatty messenger");
		
		area = new JTextArea();
		area.setEditable(false);
		area.setFont(new Font("Serif", Font.BOLD + Font.ITALIC, 20));
		add(new JScrollPane(area), BorderLayout.CENTER);
		
		userField = new JTextField();
		userField.setSize(800, 100);
		userField.setEditable(false);
		userField.addActionListener(
				new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						sendMessage(e.getActionCommand());
						userField.setText("");
					}
				}
		);
		
		add(userField, BorderLayout.NORTH);		
	}
	
	public void start(){
		try {
			server = new ServerSocket(6789, 100);
			
			while(true){
				try {			
					waitForConnection();
					setupSockets();
					whileChatting();
				} catch (EOFException e) {
					showMessage("Connection failed.");
				}finally{
					closeStuff();
				}
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void waitForConnection() throws IOException{
			showMessage("Waiting for someone to connect...");
			connection = server.accept();
			showMessage("\nNow connected to " + connection.getInetAddress().getHostName());		
	}
		
	private void setupSockets() throws IOException{
		output = new ObjectOutputStream(connection.getOutputStream());
		output.flush();
		input = new ObjectInputStream(connection.getInputStream());
		showMessage("\nThe streams are now set up! \n");
	}
	
	private void whileChatting() throws IOException{
		String message = "You are now connected.";
		sendMessage(message);
		
		ableToChat(true);
		
		do {
			try {
				message = (String) input.readObject();
				showMessage("\n" + message);
			} catch (ClassNotFoundException e) {
				showMessage("Cannot read object");
			}
		} while (!message.equals("SERVER - END"));
		
	}
	
	private void closeStuff(){
		showMessage("\nClosing connection...");
		ableToChat(false);
		
		try {
			output.close();
			input.close();
			connection.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void sendMessage(String mes){
		try {
			output.writeObject("SERVER - " + mes);
			output.flush();
			showMessage("\nSERVER - " + mes);
		} catch (IOException e) {
			area.append("\nCannot send this message");
		}
	}
	
	private void showMessage(String mes){
		SwingUtilities.invokeLater(
				new Runnable() {
					
					@Override
					public void run() {
						area.append(mes);
					}
				}
		);
	}
	
	private void ableToChat(boolean tof){
		SwingUtilities.invokeLater(
				new Runnable() {
					
					@Override
					public void run() {
						userField.setEditable(tof);
					}
				}
		);
	}
	
	public static void main(String[] args) {
		Server server = new Server();
		server.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		server.setSize(800, 300);
		server.setVisible(true);
		server.start();
		
	}

}
