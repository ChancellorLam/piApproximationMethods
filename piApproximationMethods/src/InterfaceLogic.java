package piApproximationMethods;
import java.util.Scanner;

public class InterfaceLogic {
	
	static void approximatePi() {
		
		Scanner input = new Scanner(System.in);
		boolean startApproximating = false;
		
		while(startApproximating == false) {
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
				startApproximating = true;
			}
			else if (chosenMethod.equals("2")) {
				PiApproximationMethods.newtonsMethod();
				startApproximating = true;
			}
			else if (chosenMethod.equals("3")) {
				PiApproximationMethods.monteCarlo();
				startApproximating = true;
			}
			else if (chosenMethod.equals("4")) {
				System.out.println("Not implemented yet, sorry!");
			}
			else if (chosenMethod.isEmpty()) {
				System.out.println("Invalid input. Please input a number from 0-4.");
			}
			else {
				System.out.println("Invalid input. Please input a number from 0-4.");
			}
		}
	}
}