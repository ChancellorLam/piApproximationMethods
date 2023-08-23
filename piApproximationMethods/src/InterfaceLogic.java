package piApproximationMethods;
import java.util.Scanner;
import java.util.*;

public class InterfaceLogic {
	private static Scanner input = new Scanner(System.in);
	
	static long obtainInputFromUser(String question) {
		long userInput = 0;
		
		while (userInput < 1) {
			boolean errorMsg = false;
			
			System.out.println(question);
			try {
				userInput = input.nextLong();
			}
			catch (InputMismatchException e) {
				errorMsg = true;
				System.out.println("Invalid input. You must enter a positive integer that is at least 1.");
				System.out.println();
				input.nextLine();
			}
			
			if (userInput < 1 && errorMsg == false) {
				System.out.println("Invalid input. You must enter a positive integer that is at least 1.");
				System.out.println();
			}
		}
		
		return userInput;
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