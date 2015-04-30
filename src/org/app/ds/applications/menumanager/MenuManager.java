/**
 * 
 */
package org.app.ds.applications.menumanager;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * @author anandm
 * 
 */
public class MenuManager {

	private Map<Integer, Menu> menus;

	/**
	 * 
	 */
	public MenuManager() {
		this(new MenuCommand() {

			@Override
			public boolean execute() {
				System.exit(0);
				return true;
			}
		});
	}

	/**
	 * 
	 * @param exitMenuCommand
	 *            {@link MenuCommand} to execute when user selects exit menu.
	 */
	public MenuManager(MenuCommand exitMenuCommand) {
		super();
		menus = new TreeMap<Integer, Menu>();

		Menu exitMenu = new Menu(0, "Exit", exitMenuCommand);

		addMenu(exitMenu);
	}

	public void addMenu(Menu menu) {
		menus.put(menu.getSequence(), menu);
	}

	public void removeMenu(Menu menu) {
		menus.remove(menu.getSequence());
	}

	public void start() {

		String choice = "0";

		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.println("---Menu---");

			for (Menu menu : menus.values()) {
				System.out.println(menu.getSequence() + " : " + menu.getName());
			}

			choice = scanner.nextLine();

			int sequence = -1;

			try {
				sequence = Integer.valueOf(choice);

			} catch (NumberFormatException e) {
				// ignore
			}

			Menu target = menus.get(sequence);

			if (target == null) {
				System.out.println("no such option found.");
			} else {
				target.getCommand().execute();
			}
		}
	}
}
