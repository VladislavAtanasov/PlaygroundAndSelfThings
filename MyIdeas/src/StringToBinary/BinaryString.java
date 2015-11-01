package StringToBinary;

import java.util.Scanner;

public class BinaryString {

	public static String toBinary(String expr){
		int charNum;
		String binary = "";
		for (int i = 0; i < expr.length(); i++) {
			charNum = (int) expr.charAt(i);
			while (charNum > 0) {
				binary = (charNum % 2 == 0 ? "0" : "1") + binary;
				charNum /= 2;
			}
			binary += " ";
		}
		return binary;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		System.out.println(BinaryString.toBinary(str));
		sc.close();

	}

}
