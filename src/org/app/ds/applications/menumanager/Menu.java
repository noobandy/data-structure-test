/**
 * 
 */
package org.app.ds.applications.menumanager;

/**
 * @author anandm
 * 
 */
public class Menu implements Comparable<Menu> {

	private int sequence;
	private String name;

	private MenuCommand command;

	/**
	 * @param sequence
	 *            non repeating value in range (1 - {@link Integer#MAX_VALUE})
	 *            including boundary values
	 * @param name
	 *            name for display
	 * @param command
	 *            {@link MenuCommand} to execute (invoke execute method) when
	 *            this menu is selected
	 */
	public Menu(int sequence, String name, MenuCommand command) {
		super();
		this.sequence = sequence;
		this.name = name;
		this.command = command;
	}

	public int getSequence() {
		return sequence;
	}

	public String getName() {
		return name;
	}

	public MenuCommand getCommand() {
		return command;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Menu other = (Menu) obj;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}

	@Override
	public int compareTo(Menu o) {

		return sequence - o.sequence;
	}

}
