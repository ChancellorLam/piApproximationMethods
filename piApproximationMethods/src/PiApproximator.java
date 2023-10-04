package piApproximationMethods;
import java.util.Random;
import java.lang.Math;

public class PiApproximator {
	
	void madhavaLeibniz() {
		long counter = 0;
		double currentValue = 0;
		long denominator = 1;
		double newValue;
		InputTaker inputTaker = new InputTaker();
		String question = "How many iterations would you like to perform?";

		long numOfIterations = inputTaker.takeLongInputWithLowerBound(0, question);
		for (long i = 0; i < numOfIterations; i++) { // 4/1 - 4/3 + 4/5 - 4/7 + 4/9...± 4/n
			if (counter % 2 == 0) {	// + terms
				newValue = currentValue + 4.0 / denominator;
				System.out.println("Iteration " + (counter + 1) + ": " + currentValue + " + " + "4/" + denominator +
						" = " +newValue);
				currentValue = newValue;
				counter++;
				denominator = denominator + 2;
			} 
			else if (counter % 2 == 1) { // - terms
				newValue = currentValue - 4.0 / denominator;
				System.out.println("Iteration " + (counter + 1)  + ": " + currentValue + " - " + "4/" + denominator +
						" = " + newValue);
				currentValue = newValue;
				counter++;
				denominator = denominator + 2;
			}
		}
		
	}
	
	void monteCarlo() {
		Random random = new Random();
		long pointsInsideCircle = 0;
		InputTaker inputTaker = new InputTaker();
		String question = "How many points would you like to plot?";

		long numOfPoints = inputTaker.takeLongInputWithLowerBound(0, question);
		
		for (long i = 0; i < numOfPoints; i++) {
			// Creates random x and y coordinates from 0 to 1 which creates a point in a square
			double x = random.nextDouble();
			double y = random.nextDouble();
			
			// given x and y coordinates, Pythagorean theorem can be used to determine diagonal distance to the origin
			double distance = Math.sqrt(x * x + y * y);
			System.out.println("Point " + (i + 1) + ": (" + x + ", " + y + ")");
			
			// if the diagonal distance is <= 1 then it falls within a quarter-circle with a radius of 1
			if (distance <= 1) {
				pointsInsideCircle++;
			}
		}
		
		System.out.println("After plotting " + numOfPoints + " points in a square of side length 0.5, "
				+ pointsInsideCircle + " points were inside of a quarter circle with a radius of 1.");
		System.out.println("By dividing the ratio of points inside the circle by the total amount of points in the" +
				" square we can approximate pi/4, which we can then use to find pi:");
		System.out.println(pointsInsideCircle + "/" + numOfPoints + " * 4 = "
				+ pointsInsideCircle/(double)numOfPoints * 4);

	}
	
	void newtonsMethod() {
		double areaUnderCurve = 0;
		MenuGenerator menu = new MenuGenerator();
		SubintervalQuery query = new SubintervalQuery();
		IntegralTaker integral = new IntegralTaker();
		String question = "How would you like to approximate the area under the curve?" +
				" The most accurate for this scenario is [1] Simpson's rule:";
		String[] options = {"Simpson's Rule", "Midpoint Rule", "Trapezoidal Rule"};
		String methodUsed = "";

		int approximationChoice = menu.selectionMenu(question, options);
		if (approximationChoice == 0) {  // Simpson's Rule
			int customSubinterval = query.askIfUserWantsToChooseSubintervals();
			long numSubintervals = query.setOnlyEvenSubintervals(customSubinterval);
			areaUnderCurve = integral.simpsonsRule(numSubintervals);
			methodUsed = options[0];
		}
		else if (approximationChoice == 1) {  // Midpoint Rule
			int customSubinterval = query.askIfUserWantsToChooseSubintervals();
			long numSubintervals = query.setSubintervals(customSubinterval);
			areaUnderCurve = integral.midpointRule(numSubintervals);
			methodUsed = options[1];
		}
		else if (approximationChoice == 2) {  // Trapezoidal Rule
			int customSubinterval = query.askIfUserWantsToChooseSubintervals();
			long numSubintervals = query.setSubintervals(customSubinterval);
			areaUnderCurve = integral.trapezoidalRule(numSubintervals);
			methodUsed = options[2];
		}

		double piApproximation = 3 * Math.sqrt(3) / 4 + 24 * areaUnderCurve;  // use area under curve in Newton's method
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
	
	void ramanujanSato() {
		InputTaker inputTaker = new InputTaker();
		String question = "How many iterations would you like to perform? (16 max)";
		long numOfIterations = inputTaker.takeIntInputWithLowerAndUpperBound(0, 16, question);

		double constant = 2 * Math.sqrt(2) / Math.pow(99, 2);
		double factorialPart;
		double secondPart;
		double sum = 0;
		for (int i = 0; i <= numOfIterations; i++ ) {
			factorialPart = factorial(4 * i) / Math.pow(factorial(i), 4);
			secondPart = (26390 * i + 1103) / Math.pow(396, 4 * i);
			sum = sum + (secondPart * factorialPart);
			System.out.println("Iteration " + i + ": " + 1 / (sum * constant));
		}
	}
}