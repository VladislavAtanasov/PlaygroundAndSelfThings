import javax.swing.JOptionPane;

public class JOptionExamples {

	public static void main(String[] args) {
		String first = JOptionPane.showInputDialog("������ �����");
		String second = JOptionPane.showInputDialog("������ ����� �����");
		
		int sum = Integer.parseInt(first) + Integer.parseInt(second);
		JOptionPane.showMessageDialog(null, "������ � " + sum , "���������", JOptionPane.PLAIN_MESSAGE);
	}

}
