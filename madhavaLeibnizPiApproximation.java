import java.util.Scanner;

public class madhavaLeibnizPiApproximation {
	
	public static void main(String[] args) {
		long counter = 0;
		double currentValue = 0;
		long denominator = 1;
		double newValue;
		
		System.out.println("How many iterations would you like to perform?");
		Scanner input = new Scanner(System.in);
		long numOfIterations = input.nextLong();
		for (long i = 0; i < numOfIterations; i++) {
			if (counter % 2 == 0) {
				newValue = currentValue + 4.0/denominator;
				System.out.println("Iteration " + (counter + 1) + ": " + currentValue + " + " + "4/" + denominator + " = " + newValue);
				currentValue = newValue;
				counter++;
				denominator = denominator + 2;
			} else if (counter % 2 == 1) {
				newValue = currentValue - 4.0/denominator;
				System.out.println("Iteration " + (counter + 1)  + ": " + currentValue + " - " + "4/" + denominator + " = " + newValue);
				currentValue = newValue;
				counter++;
				denominator = denominator + 2;
			}
		}
	}
}