package InstantMessenger;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Client extends JFrame{

	private JTextArea area;
	private JTextField  userField;
	private ObjectInputStream input;
	private ObjectOutputStream output;
	private Socket connection;
	private ServerSocket server;
	private String serverIp;
	
	public Client(String name) {
		super("Client");
		
		serverIp = name;
		
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
					waitForConnection();
					setupSockets();
					whileChatting();
				}catch(EOFException eofException){
					showMessage("\n Client terminated the connection");
				}catch(IOException ioException){
					ioException.printStackTrace();
				}finally{
					closeStuff();
				}
	}
	
	private void waitForConnection() throws IOException{
		showMessage("Attempting connection... \n");
		connection = new Socket(InetAddress.getByName(serverIp), 6789);
		showMessage("Connection Established! Connected to: " + connection.getInetAddress().getHostName());	
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
		} while (!message.equals("CLIENT - END"));
		
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
			output.writeObject("CLIENT - " + mes);
			output.flush();
			showMessage("\nCLIENT - " + mes);
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
		Client user = new Client("127.0.0.1");
		user.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		user.setSize(800, 300);
		user.setVisible(true);
		user.start();
	}

}
