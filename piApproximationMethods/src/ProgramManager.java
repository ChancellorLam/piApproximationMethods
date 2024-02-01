package piApproximationMethods;

public class ProgramManager {

	void approximatePi() {
		String prompt = "What method would you like to use in order to approximate pi?";
		String[] options = {"Archimedes' Method", "Madhava-Leibniz Infinite Series", "Newton's Method",
				"Monte-Carlo Method", "Ramanujan-Sato Series"};
		MenuGenerator mainMenu = new MenuGenerator();

		int choice = mainMenu.selectionMenu(prompt, options);
		PiApproximator piApproximator = new PiApproximator();
		if (choice == 0) {
			piApproximator.archimedesMethod();
		}
		else if (choice == 1) {
			piApproximator.madhavaLeibniz();
		}
		else if (choice == 2) {
			piApproximator.newtonsMethod();
		}
		else if (choice == 3) {
			piApproximator.monteCarlo();
		}
		else if (choice == 4) {
			piApproximator.ramanujanSato();
		}
		else {
			System.out.println("Invalid input. Please choose from the 5 available options.");
			System.out.println();
		}
	}
}