/**
 * 
 */
package org.app.ds.others.bookrecommender;

import java.io.Serializable;

/**
 * @author anandm
 * 
 */
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;
	private String location;
	private String age;

	/**
	 * @param id
	 * @param location
	 * @param age
	 */
	public User(String id, String location, String age) {
		super();
		this.id = id;
		this.location = location;
		this.age = age;
	}

	public String getId() {
		return id;
	}

	public String getLocation() {
		return location;
	}

	public String getAge() {
		return age;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		User other = (User) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", location=" + location + ", age=" + age
				+ "]";
	}

}
