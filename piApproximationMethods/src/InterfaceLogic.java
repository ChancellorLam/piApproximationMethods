package piApproximationMethods;
import java.util.*;

public class InterfaceLogic {
	private static Scanner input = new Scanner(System.in);
	
	static long obtainInputFromUser(String question, int minimum) {
		long userInput = minimum;
		
		while (userInput < minimum + 1) {
			boolean errorMsg = false;
			
			System.out.println(question);
			try {
				userInput = input.nextLong();
			}
			catch (InputMismatchException e) {
				errorMsg = true;
				System.out.println("Invalid input. You must enter a positive integer that is at least " + minimum + ".");
				System.out.println();
				input.nextLine();
			}
			
			if (userInput < minimum && errorMsg == false) {
				System.out.println("Invalid input. You must enter a positive integer that is at least " + minimum + ".");
				System.out.println();
			}
		}
		
		return userInput;
	}
	
	static long askForCustomSubintervals() {
		long numSubintervals = 0;
		boolean numSubintervalsNotValid = true;
		
		while (numSubintervalsNotValid) {
			System.out.print("Would you like to choose how many subintervals? ");
			System.out.println("If not, a default amount (6000) will be chosen.");
			System.out.println("[1] Yes:");
			System.out.println("[2] No:");
			
			String choice = input.nextLine();
			if (choice.equals("1")) {
				while (numSubintervals == 0) {
					numSubintervals = obtainInputFromUser("How many subintervals?", 0);
					numSubintervalsNotValid = false;
				}
			}
			else if (choice.equals("2")) {
				numSubintervals = 6000;
				numSubintervalsNotValid = false;
			}
			else {
				System.out.println("Invalid input. Please answer [1] Yes or [2] No.");
				System.out.println();
			}
		}
		return numSubintervals;
	}
	
	
	static long obtainEvenNumSubintervals() {
		long numSubintervals = 0;
		boolean numSubintervalsNotEven = true;
		
		while (numSubintervalsNotEven) {
			long numSubintervalsAttempt = askForCustomSubintervals();
			System.out.println(numSubintervalsAttempt);
			if (numSubintervalsAttempt % 2 != 0) { // if the number of subintervals is not even, ask again for a valid number
				System.out.println("Invalid amount. You need to input an even number that is at least 2.");
				System.out.println();
				input.nextLine();
			}
			else {
				numSubintervals = numSubintervalsAttempt;
				numSubintervalsNotEven = false;
			}
		}
		return numSubintervals;
	}
	
	static void approximatePi() {
		boolean methodNotChosen = true;
		
		while (methodNotChosen) {
			
			System.out.print("What method would you like to use in order to approximate pi? ");
			System.out.println("Choose by inputting a number from 0-4:");
			System.out.println("[0] Archimedes Method:");
			System.out.println("[1] Madhava-Leibniz Infinite Series:");
			System.out.println("[2] Newton's Method:");
			System.out.println("[3] Monte-Carlo Method:");
			System.out.println("[4] Ramanujan-Sato Series:");
			
			String chosenMethod = input.nextLine();
			if (chosenMethod.equals("0")) { 
				System.out.println("Not implemented yet, sorry!");
			}
			else if (chosenMethod.equals("1")) {
				PiApproximationMethods.madhavaLeibniz();
				methodNotChosen = false;
			}
			else if (chosenMethod.equals("2")) {
				PiApproximationMethods.newtonsMethod();
				methodNotChosen = false;
			}
			else if (chosenMethod.equals("3")) {
				PiApproximationMethods.monteCarlo();
				methodNotChosen = false;
			}
			else if (chosenMethod.equals("4")) {
				System.out.println("Not implemented yet, sorry!");
			}
			else if (chosenMethod.isEmpty()) {
				System.out.println("Invalid input. Please input a number from 0-4.");
				System.out.println();
			}
			else {
				System.out.println("Invalid input. Please input a number from 0-4.");
				System.out.println();
			}
		}
	}
}