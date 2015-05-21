/**
 * 
 */
package org.app.ds.boolmfilter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import org.app.ds.applications.menumanager.Menu;
import org.app.ds.applications.menumanager.MenuCommand;
import org.app.ds.applications.menumanager.MenuManager;

/**
 * @author anandm
 * 
 */
public class Application {

	/**
	 * @param args
	 */

	public static void main(String[] args) throws FileNotFoundException,
			IOException {

		final Dictionary dictionary = new EnglishDictionary();

		final Scanner scanner = new Scanner(System.in);

		Menu lookUpWordMenu = new Menu(1, "Look Up Word", new MenuCommand() {

			@Override
			public boolean execute() {

				System.out.println("Enter Word");
				String word = scanner.nextLine();
				if (dictionary.lookup(word)) {
					System.out.println(word + " exists in english dictionary");
				}

				return true;
			}
		});

		MenuManager menuManager = new MenuManager();

		menuManager.addMenu(lookUpWordMenu);

		menuManager.start();

	}

}
