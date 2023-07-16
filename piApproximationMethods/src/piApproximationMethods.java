package piApproximationMethods;
import java.util.Random;
import java.util.Scanner;

public class piApproximationMethods {
	
	static void madhavaLeibniz() {
		
		Scanner input = new Scanner(System.in);
		long counter = 0;
		double currentValue = 0;
		long denominator = 1;
		double newValue;
		
		System.out.println("How many iterations would you like to perform?");
		long numOfIterations = input.nextLong();
		for (long i = 0; i < numOfIterations; i++) { // 4/1 - 4/3 + 4/5 - 4/7 + 4/9...± 4/n
			if (counter % 2 == 0) {	// +
				newValue = currentValue + 4.0/denominator;
				System.out.println("Iteration " + (counter + 1) + ": " + currentValue + " + " + "4/" + denominator + " = " + newValue);
				currentValue = newValue;
				counter++;
				denominator = denominator + 2;
			} 
			else if (counter % 2 == 1) { // -
				newValue = currentValue - 4.0/denominator;
				System.out.println("Iteration " + (counter + 1)  + ": " + currentValue + " - " + "4/" + denominator + " = " + newValue);
				currentValue = newValue;
				counter++;
				denominator = denominator + 2;
			}
		}
	}
	
	static void monteCarlo() {
		
		Scanner input = new Scanner(System.in);
		Random random = new Random();
		int pointsInsideCircle = 0;
		
		System.out.println("How many points would you like to plot?");
		int numOfPoints = input.nextInt();
		
		for (int i = 0; i < numOfPoints; i++) {
			// Creates random x and y coordinates from 0 to 1 which creates a point in a square
			double x = random.nextDouble();
			double y = random.nextDouble();
			
			// given x and y coordinates, Pythagora's theorem can be used to determine diagonal distance to the origin
			double distance = Math.sqrt(x * x + y * y);
			System.out.println("Point " + (i + 1) + ": (" + x + ", " + y + ")");
			
			// if the diagonal distance is <= 1 then it falls within a quarter-circle with a radius of 1
			if (distance <= 1) {
				pointsInsideCircle++;
			}
		}
		
		System.out.println("After plotting " + numOfPoints + " points in a square of side length 0.5, " + pointsInsideCircle
				+ " points were inside of a quarter circle with a radius of 1.");
		System.out.println("By dividing the ratio of points inside the circle by the total amount of points in the square"
				+ " we can approximate pi/4, which we can then use to find pi:"); 
		System.out.println(pointsInsideCircle + "/" + numOfPoints + " * 4 = " + pointsInsideCircle/(double)numOfPoints * 4);
	}
	
	
}