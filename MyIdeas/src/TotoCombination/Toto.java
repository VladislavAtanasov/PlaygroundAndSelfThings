package TotoCombination;

import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class Toto {
	
	public static void randomCombination(){
		Random number = new Random();
		int digit, count = 0;
		Set<Integer> digits = new TreeSet<Integer>();
		while (count < 7){
			digit = 1 + number.nextInt(48);
			boolean added = digits.add(digit);
			if (added) {
				System.out.print(digit + " ");
				++count;
			}
		}
	}

	public static void main(String[] args) {
		Toto.randomCombination();
	}

}
