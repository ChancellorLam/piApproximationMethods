package piApproximationMethods;
import java.util.Scanner;

public class ProgramManager {
	static Scanner input = new Scanner(System.in);

	void approximatePi() {
		String prompt = "What method would you like to use in order to approximate pi?";
		String[] options = {"Archimedes' Method", "Madhava-Leibniz Infinite Series", "Newton's Method",
				"Monte-Carlo Method", "Ramanujan-Sato Series", "Chudnovsky Algorithm", "Exit"};
		String methodUsed = "";
		boolean continueRunning = true;
		MenuGenerator mainMenu = new MenuGenerator();
		char[] piApproximation = new char[32];
		double timeElapsedInSeconds = 0;

		while (continueRunning) {
			int choice = mainMenu.selectionMenu(prompt, options);
			PiApproximator piApproximator = new PiApproximator();
			if (choice == 0) {
				PiApproxData data = piApproximator.archimedesMethod();
				piApproximation = data.piApproximation().clone();
				timeElapsedInSeconds = data.timeElapsedInSec();
				methodUsed = options[choice];
			}
			else if (choice == 1) {
				PiApproxData data = piApproximator.madhavaLeibniz();
				piApproximation = data.piApproximation().clone();
				timeElapsedInSeconds = data.timeElapsedInSec();
				methodUsed = options[choice];
			}
			else if (choice == 2) {
				PiApproxData data = piApproximator.newtonsMethod();
				piApproximation = data.piApproximation().clone();
				timeElapsedInSeconds = data.timeElapsedInSec();
				methodUsed = options[choice];
			}
			else if (choice == 3) {
				PiApproxData data = piApproximator.monteCarlo();
				piApproximation = data.piApproximation().clone();
				timeElapsedInSeconds = data.timeElapsedInSec();
				methodUsed = options[choice];
			}
			else if (choice == 4) {
				PiApproxData data = piApproximator.ramanujanSato();
				piApproximation = data.piApproximation().clone();
				timeElapsedInSeconds = data.timeElapsedInSec();
				methodUsed = options[choice];
			}
			else if (choice == 5) {
				PiApproxData data = piApproximator.chudnovskyAlgorithm();
				piApproximation = data.piApproximation().clone();
				timeElapsedInSeconds = data.timeElapsedInSec();
				methodUsed = "the ".concat(options[choice]);
			}
			else if (choice == 6) { // Exit
				continueRunning = false;
			}
			else {
				System.out.println("Invalid input. Please choose from the 5 available options.");
				System.out.println();
			}
			if (continueRunning) {
				char[] actualPi = "3.1415926535897932384626433832795".toCharArray();
				int numOfCorrectPiDigits = 0;
				while (numOfCorrectPiDigits < piApproximation.length &&
						piApproximation[numOfCorrectPiDigits] == actualPi[numOfCorrectPiDigits]) {
					numOfCorrectPiDigits++;
				}
				System.out.println();
				System.out.println("Approximated value of pi: " + String.valueOf(piApproximation).trim());
				System.out.println("      Actual value of pi: " + String.valueOf(actualPi));
				System.out.printf("Using %s, we have approximated the number π to %d digits in %f seconds.\n",
						methodUsed, numOfCorrectPiDigits - 1, timeElapsedInSeconds);
				System.out.printf("That is %f digits per second!\n", (numOfCorrectPiDigits - 1) / timeElapsedInSeconds);
				System.out.println("Press Enter to Continue.");
				input.nextLine();
			}
		}
	}
}
