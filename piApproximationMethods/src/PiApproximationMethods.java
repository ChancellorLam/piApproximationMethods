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
		
		long numOfIterations = InterfaceLogic.obtainInputFromUser("How many iterations would you like to perform?");
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
		int pointsInsideCircle = 0;
		
		long numOfPoints = InterfaceLogic.obtainInputFromUser("How many points would you like to plot?");
		
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
		
		System.out.print("After plotting " + numOfPoints + " points in a square of side length 0.5, " + pointsInsideCircle);
		System.out.println(" points were inside of a quarter circle with a radius of 1.");
		
		System.out.print("By dividing the ratio of points inside the circle by the total amount of points in the square");
		System.out.println(" we can approximate pi/4, which we can then use to find pi:"); 
		
		System.out.println(pointsInsideCircle + "/" + numOfPoints + " * 4 = " + pointsInsideCircle/(double)numOfPoints * 4);
		
	}
	
	static void newtonsMethod() {
		String userInput;
		boolean startIntegrating = false;
		
		while (startIntegrating == false) { // determine how user wants to find area under curve to use Newton's method to find pi
			System.out.print("How would you like to numerically calculate the area under the curve? ");
			System.out.println("The most accurate for this scenario is [0] Simpson's rule:");
			System.out.println("Choose by inputting a number from 0-2:");
			
			// calculates area under a curve using parabolas
			System.out.println("[0] Simpson's Rule (Most Accurate for Curves):");
			// calculates area under a curve using rectangles
			System.out.println("[1] Midpoint Rule (Most Accurate for Step functions):");
			//calculates area under a curve using trapezoids
			System.out.println("[2] Trapezoidal Rule (Most Accurate for Linear functions):");
			String chosenMethod = input.nextLine();
			
			if (chosenMethod.equals("0") || chosenMethod.isEmpty()) { // user decides to use Simpson's rule, also default scenario
				int numSubintervals = 0;
				
				System.out.print("Would you like to choose how many subintervals? (Y/N) ");
				System.out.println("If not, a default amount will be chosen.");
				userInput = input.nextLine();
				
				if (userInput.toLowerCase().equals("y") || userInput.toLowerCase().equals("yes")) { // user wants to choose number of subintervals
					System.out.println("How many subintervals? (This amount must be even and greater than 0)");
					while (startIntegrating == false) {
						numSubintervals = input.nextInt();
						
						if (numSubintervals % 2 != 0) { // if the number of subintervals is not even, ask again for a valid number
							System.out.println("Invalid amount. You need to input an even number that is greater than zero.");
						}
						else if (numSubintervals <= 0) { // 0 subintervals is invalid, ask again for a valid number
							System.out.println("Invalid amount. You need to input an even number that is greater than zero.");
						}
						else { // valid number of subintervals, calculate area under curve using Simpson's rule
							startIntegrating = true;
						}
					}
				}
				else { // default scenario; if user doesn't choose how many subintervals
					numSubintervals = 6000;
					startIntegrating = true;
				}
				
				double areaUnderCurve = Quadrature.simpsonsRule(numSubintervals);
				double piApproximation = 3 * Math.sqrt(3) / 4 + 24 * areaUnderCurve; // use area under curve in Newton's method
				System.out.print("The area under the curve found through Simpson's rule can be used in Newton's Method to ");
				System.out.println("approximate pi to the value of: " + piApproximation);
			}
			
			else if (chosenMethod.equals("1")) { // if user decides to use Midpoint Rule
				int numSubintervals = 0;
				
				System.out.print("Would you like to choose how many subintervals? (Y/N) ");
				System.out.println("If not, a default amount will be chosen.");
				userInput = input.nextLine();
				
				if (userInput.toLowerCase().equals("y") || userInput.toLowerCase().equals("yes")) { // user wants to choose
					System.out.println("How many subintervals? (This amount must be even and greater than 0)");
					while (startIntegrating == false) {
						
						numSubintervals = input.nextInt();
						if (numSubintervals <= 0) { // 0 subintervals is invalid, ask again for a valid number
							System.out.println("Invalid amount. You need to input an even number that is greater than zero.");
						}
						else { // valid number of subintervals, calculate area under curve using Simpson's rule
							startIntegrating = true;
						}
					}
				}
				else { // default scenario; if user doesn't choose how many subintervals
					numSubintervals = 6000;
					startIntegrating = true;
				}
				
				double areaUnderCurve = Quadrature.midpointRule(numSubintervals);
				double piApproximation = 3 * Math.sqrt(3) / 4 + 24 * areaUnderCurve; // use area under curve in Newton's method
				System.out.print("The area under the curve found through Midpoint rule can be used in Newton's Method to ");
				System.out.println("approximate pi to the value of: " + piApproximation);
			}
			else if (chosenMethod.equals("2")) { // if user decides to use trapezoidal rule
				int numSubintervals = 0;
				
				System.out.print("Would you like to choose how many subintervals? (Y/N) ");
				System.out.println("If not, a default amount will be chosen.");
				userInput = input.nextLine();
				
				if (userInput.toLowerCase().equals("y") || userInput.toLowerCase().equals("yes")) { // user wants to choose
					System.out.println("How many subintervals? (This amount must be even and greater than 0)");
					while (startIntegrating == false) {
						
						numSubintervals = input.nextInt();
						if (numSubintervals <= 0) { // 0 subintervals is invalid, ask again for a valid number
							System.out.println("Invalid amount. You need to input an even number that is greater than zero.");
						}
						else { // valid number of subintervals, calculate area under curve using Simpson's rule
							startIntegrating = true;
						}
					}
				}
				else { // default scenario; if user doesn't choose how many subintervals
					numSubintervals = 6000;
					startIntegrating = true;
				}
				
				double areaUnderCurve = Quadrature.trapezoidalRule(numSubintervals);
				double piApproximation = 3 * Math.sqrt(3) / 4 + 24 * areaUnderCurve; // use area under curve in Newton's method
				System.out.print("The area under the curve found through Trapezoidal rule can be used in Newton's Method to ");
				System.out.println("approximate pi to the value of: " + piApproximation);
			}
			else {
				System.out.println("Invalid input. Please input a number from 0-2.");
			}
		}
		
	}
	
}