package piApproximationMethods;
import java.util.Random;
import java.util.Scanner;
import java.lang.Math;

public class PiApproximationMethods {
	private static Scanner input = new Scanner(System.in);
	
	static void madhavaLeibniz() {
		long counter = 0;
		double currentValue = 0;
		long denominator = 1;
		double newValue;
		
		long numOfIterations = InterfaceLogic.obtainInputFromUser("How many iterations would you like to perform?", 0);
		for (long i = 0; i < numOfIterations; i++) { // 4/1 - 4/3 + 4/5 - 4/7 + 4/9...± 4/n
			if (counter % 2 == 0) {	// + terms
				newValue = currentValue + 4.0/denominator;
				System.out.print("Iteration " + (counter + 1) + ": " + currentValue + " + " + "4/" + denominator + " = ");
				System.out.println(newValue);
				currentValue = newValue;
				counter++;
				denominator = denominator + 2;
			} 
			else if (counter % 2 == 1) { // - terms
				newValue = currentValue - 4.0/denominator;
				System.out.print("Iteration " + (counter + 1)  + ": " + currentValue + " - " + "4/" + denominator + " = ");
				System.out.println(newValue);
				currentValue = newValue;
				counter++;
				denominator = denominator + 2;
			}
		}
		
	}
	
	static void monteCarlo() {
		Random random = new Random();
		long pointsInsideCircle = 0;
		
		long numOfPoints = InterfaceLogic.obtainInputFromUser("How many points would you like to plot?", 0);
		
		for (long i = 0; i < numOfPoints; i++) {
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
		
		System.out.print("After plotting " + numOfPoints + " points in a square of side length 0.5, " + pointsInsideCircle);
		System.out.println(" points were inside of a quarter circle with a radius of 1.");
		
		System.out.print("By dividing the ratio of points inside the circle by the total amount of points in the square");
		System.out.println(" we can approximate pi/4, which we can then use to find pi:"); 
		
		System.out.println(pointsInsideCircle + "/" + numOfPoints + " * 4 = " + pointsInsideCircle/(double)numOfPoints * 4);
		
	}
	
	static void newtonsMethod() {
		double areaUnderCurve = 0;
		String methodUsed = "";
		
		while (areaUnderCurve == 0) { // determine how user wants to find area under curve to use Newton's method to find pi
			System.out.print("How would you like to numerically calculate the area under the curve? ");
			System.out.println("The most accurate for this scenario is [1] Simpson's rule:");
			System.out.println("Choose by inputting a number from 0-2:");
			System.out.println("[1] Simpson's Rule:");    // calculates area under a curve using parabolas
			System.out.println("[2] Midpoint Rule:");     // calculates area under a curve using rectangles
			System.out.println("[3] Trapezoidal Rule:");  // calculates area under a curve using trapezoids
			
			String chosenMethod = input.nextLine();
			if (chosenMethod.equals("1") || chosenMethod.isEmpty()) { // user decides to use Simpson's rule, also default scenario
				long numSubintervals = InterfaceLogic.obtainEvenNumSubintervals();
				areaUnderCurve = Quadrature.simpsonsRule(numSubintervals);
				methodUsed = "Simpson's Rule";
			}
			
			else if (chosenMethod.equals("2")) { // user decides to use Midpoint Rule
				long numSubintervals = InterfaceLogic.askForCustomSubintervals();
				areaUnderCurve = Quadrature.midpointRule(numSubintervals);
				methodUsed = "Midpoint Rule";
			}
			else if (chosenMethod.equals("3")) { // if user decides to use trapezoidal rule
				long numSubintervals = InterfaceLogic.askForCustomSubintervals();
				areaUnderCurve = Quadrature.trapezoidalRule(numSubintervals);
				methodUsed = "Trapezoidal Rule";
			}
			else {
				System.out.println("Invalid input. Please input a number from 0-2.");
				System.out.println();
			}
		}
		double piApproximation = 3 * Math.sqrt(3) / 4 + 24 * areaUnderCurve; // use area under curve in Newton's method
		System.out.print("The area under the curve from 0 to 0.25 found through " + methodUsed + " can be used to ");
		System.out.println("approximate pi to the value of: " + piApproximation);
	}
	
	static long factorial(int maxFactor) {  // long data type will only support up to 20
		long product = 1;
		
		for (long i = 2; i <= maxFactor; i++) {
			product = i * product;
		}
		return product;
	}
	
	static void ramanujanSato() {
		long numOfIterations = -1;
		while (numOfIterations < 0 || numOfIterations > 20) {
			long numOfIterationsAttempt = InterfaceLogic.obtainInputFromUser("How many iterations would you like to perform? (16 max)", 0);
			if (numOfIterationsAttempt > 16) {
				System.out.println("Invalid input. You need to enter a positive integer that is less than 17.");
			}
			else {
				numOfIterations = numOfIterationsAttempt;
			}
		}
		double constant = 2 * Math.sqrt(2) / Math.pow(99, 2);
		double factorialPart = 0;
		double secondPart = 0;
		double sum = 0;
		for (int i = 0; i <= numOfIterations; i++ ) {
			factorialPart = factorial(4 * i) / Math.pow(factorial(i), 4);
			secondPart = (26390 * i + 1103) / Math.pow(396, 4 * i);
			sum = sum + (secondPart * factorialPart);
			System.out.println("Iteration " + i + ": " + 1 / (sum * constant));
		}
	}
}