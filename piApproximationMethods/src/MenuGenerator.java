package piApproximationMethods;

public class MenuGenerator {

	int selectionMenu(String prompt, String[] options) {
		boolean selectionNotMade = true;
		int validSelection = -1;
		
		while (selectionNotMade) {
			InputTaker inputTaker = new InputTaker();

			// print out menu options
			System.out.println(prompt);
			for (int i = 0; i < options.length; i++) {
				System.out.println("[" + i + "] " + options[i]);
			}

			int selection = inputTaker.takeMenuSelection();
			if (selection >= 0 && selection < options.length) {
				validSelection = selection;
				selectionNotMade = false;
			}
			else {
				System.out.println("Invalid input. You must make a choice between 0 and " + (options.length - 1) + ".");
				System.out.println();
			}
		}
		return validSelection;
	}

	public boolean askYesOrNo(String question) {
		boolean selectionNotMade = true;
		boolean boolValue = false;

		while (selectionNotMade) {
			InputTaker inputTaker = new InputTaker();

			System.out.printf("%s \n[0] Yes \n[1] No\n", question);
			int selection = inputTaker.takeMenuSelection();
			if (selection == 0) {
				boolValue = true;
				selectionNotMade = false;
			}
			else if (selection == 1) {
				selectionNotMade = false;
            }
			else {
				System.out.println("Invalid input. You must make a choice between 0 and 1.");
				System.out.println();
			}
		}
		return boolValue;
	}
}
