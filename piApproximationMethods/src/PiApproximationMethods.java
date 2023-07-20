package piApproximationMethods;
import java.util.Random;
import java.util.Scanner;
import java.lang.Math;

public class PiApproximationMethods {
	
	static void madhavaLeibniz() {
		
		Scanner input = new Scanner(System.in);
		long counter = 0;
		double currentValue = 0;
		long denominator = 1;
		double newValue;
		
		System.out.println("How many iterations would you like to perform?");
		long numOfIterations = input.nextLong();
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
	
	static void newtonsMethod() {
		
		Scanner input = new Scanner(System.in);
		String userInput;
		boolean startIntegrating = false;
		
		while(startIntegrating == false) { // determine how user wants to find area under curve to use Newton's method to find pi
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
			if (chosenMethod.equals("0") || chosenMethod.isEmpty()) { // if user decides to use Simpson's rule
				
				int numSubintervals = 0;
				double subintervalWidth;
				
				System.out.print("Would you like to choose how many subintervals? (Y/N) ");
				System.out.println("If not, a default amount will be chosen.");
				userInput = input.nextLine();
				
				if (userInput.toLowerCase().equals("y") || userInput.toLowerCase().equals("yes")) { // user wants to choose
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
				
				// numerically integrate using Simpson's Rule
				int numEndpoints = numSubintervals + 1;
				double sumOfTerms = 0;
				subintervalWidth = 0.25 / (double) numSubintervals;
				
				 // the 0th and last terms in the calculation of the integral using Simpson's rule are not modified
				 // but for all other terms, odd terms are multiplied by 4 and even terms are multiplied by 2
				 // so an example calculation would be: f(0) + 4f(1) + 2f(2) + 4f(3) + ... f(n)
				 // and then all of this would be multiplied by width of the subintervals / 3
				
				sumOfTerms = sumOfTerms + Math.sqrt(0 - 0 * 0); // this is the 0th term
				System.out.println("f(0): 0");
				
				for (int i = 1; i < numEndpoints - 1; i++) { // modify every term except for 0th and last
					double x = subintervalWidth * i;
					if (i % 2 == 1) { // odd terms
						sumOfTerms = sumOfTerms + 4 * Math.sqrt(x - x * x);
						System.out.println("+ 4f(" + x + "): " + 4 * Math.sqrt(x - x * x));
					}
					else if (i % 2 == 0) { // even terms
						sumOfTerms = sumOfTerms + 2 * Math.sqrt(x - x * x);
						System.out.println("+ 2f(" + x + "): " + 2 * Math.sqrt(x - x * x));
					}
				}
				
				sumOfTerms = sumOfTerms + Math.sqrt(0.25 - 0.25 * 0.25); // add the last term
				System.out.println("+ f(0.25): " + Math.sqrt(0.25 - 0.25 * 0.25));
				
				System.out.println();
				System.out.println("Sum of terms: " + sumOfTerms);
				System.out.println("Number of subintervals: " + numSubintervals);
				System.out.println("Width of subintervals: " + subintervalWidth);
				
				//finish calculating integral (area under curve)
				double areaUnderCurve = sumOfTerms * subintervalWidth / 3; // multiply sum of terms by width of subintervals / 3
				System.out.println("Area under curve: " + sumOfTerms + " * (" + subintervalWidth + "/3)");
				double piApproximation = 3 * Math.sqrt(3) / 4 + 24 * areaUnderCurve; // use area under curve in Newton's method
				System.out.println("Area under curve: " + areaUnderCurve);
				System.out.print("The area under the curve found through Simpson's rule can be used in Newton's Method to ");
				System.out.println("approximate pi to the value of: " + piApproximation);
			}
			
			else if (chosenMethod.equals("1")) { // if user decides to use Midpoint Rule
				
				int numSubintervals = 0;
				double subintervalWidth;
				
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
				
				// numerically integrate using Midpoint Rule
				double sumOfTerms = 0;
				subintervalWidth = 0.25 / (double) numSubintervals;
				double midpointSize = subintervalWidth / 2;
				double x = midpointSize; 
				
				for (int i = 0; i < numSubintervals; i++) { 
					sumOfTerms = sumOfTerms + Math.sqrt(x - x * x);
					System.out.println("f(" + x + "): " + sumOfTerms);
					x = x + subintervalWidth;
				}
				
				System.out.println();
				System.out.println("Sum of terms: " + sumOfTerms);
				System.out.println("Number of subintervals: " + numSubintervals);
				System.out.println("Width of subintervals: " + subintervalWidth);
				
				//finish calculating integral (area under curve)
				double areaUnderCurve = sumOfTerms * subintervalWidth; // multiply sum of terms by width of subintervals
				System.out.println("Area under curve: " + sumOfTerms + " * (" + subintervalWidth + "/3)");
				double piApproximation = 3 * Math.sqrt(3) / 4 + 24 * areaUnderCurve; // use area under curve in Newton's method
				System.out.println("Area under curve: " + areaUnderCurve);
				System.out.print("The area under the curve found through Simpson's rule can be used in Newton's Method to ");
				System.out.println("approximate pi to the value of: " + piApproximation);
			}
			else if (chosenMethod.equals("2")) {
				System.out.println("Not implemented yet, sorry!");
			}
			else {
				System.out.println("Invalid input. Please input a number from 0-2.");
			}
		}
	}
	
}