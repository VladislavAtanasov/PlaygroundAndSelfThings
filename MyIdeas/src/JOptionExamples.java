import javax.swing.JOptionPane;

public class JOptionExamples {

	public static void main(String[] args) {
		String first = JOptionPane.showInputDialog("Въведи число");
		String second = JOptionPane.showInputDialog("Въведи второ число");
		
		int sum = Integer.parseInt(first) + Integer.parseInt(second);
		JOptionPane.showMessageDialog(null, "Сумата е " + sum , "Програмка", JOptionPane.PLAIN_MESSAGE);
	}

}
