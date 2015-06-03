/**
 * 
 */
package org.app.ds.others.bookrecommender;

/**
 * @author anandm
 * 
 */
public class BookRating {

	private String userId;
	private String isbn;
	private double rating;

	/**
	 * @param userId
	 * @param isbn
	 * @param rating
	 */
	public BookRating(String userId, String isbn, double rating) {
		super();
		this.userId = userId;
		this.isbn = isbn;
		this.rating = rating;
	}

	public String getUserId() {
		return userId;
	}

	public String getIsbn() {
		return isbn;
	}

	public double getRating() {
		return rating;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((isbn == null) ? 0 : isbn.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
		BookRating other = (BookRating) obj;
		if (isbn == null) {
			if (other.isbn != null) {
				return false;
			}
		} else if (!isbn.equals(other.isbn)) {
			return false;
		}
		if (userId == null) {
			if (other.userId != null) {
				return false;
			}
		} else if (!userId.equals(other.userId)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "BookRating [userId=" + userId + ", isbn=" + isbn + ", rating="
				+ rating + "]";
	}

}
