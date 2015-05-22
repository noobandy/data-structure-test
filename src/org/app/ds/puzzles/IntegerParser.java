/**
 * 
 */
package org.app.ds.puzzles;

import java.util.Scanner;

import org.app.ds.applications.menumanager.Menu;
import org.app.ds.applications.menumanager.MenuCommand;
import org.app.ds.applications.menumanager.MenuManager;

/**
 * @author anandm
 * 
 */
public class IntegerParser {

	private boolean isNumericCharacter(Character character) {
		return 48 <= character && 57 >= character;
	}
	
	

	public int parse(String integerString) {
		int number = 0;
		int multiplier = 1;
		for (int i = integerString.length() - 1; i >= 0; i--) {
			Character character = integerString.charAt(i);
			if (!isNumericCharacter(character)) {
				throw new IllegalArgumentException(
						"integerString contains non numeric characters");
			}

			number = number + ((character - 48) * multiplier);
			multiplier = multiplier * 10;
		}
		return number;
	}

	public static void main(String[] args) {
		final IntegerParser integerParser = new IntegerParser();

		MenuManager menuManager = new MenuManager();
		menuManager.addMenu(new Menu(1, "Parse Int", new MenuCommand() {

			@Override
			public boolean execute() {
				Scanner scanner = new Scanner(System.in);
				String integerString = scanner.nextLine();
				System.out.println(integerParser.parse(integerString));
				return true;
			}
		}));

		menuManager.start();
	}
}
