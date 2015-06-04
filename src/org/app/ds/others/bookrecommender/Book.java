/**
 * 
 */
package org.app.ds.others.bookrecommender;

import java.io.Serializable;

/**
 * @author anandm
 * 
 */
public class Book implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String isbn;
	private String title;
	private String author;
	private String yearOfPublication;
	private String publisher;

	/**
	 * @param isbn
	 * @param title
	 * @param author
	 * @param yearOfPublication
	 * @param publisher
	 */
	public Book(String isbn, String title, String author,
			String yearOfPublication, String publisher) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.yearOfPublication = yearOfPublication;
		this.publisher = publisher;
	}

	public String getIsbn() {
		return isbn;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public String getYearOfPublication() {
		return yearOfPublication;
	}

	public String getPublisher() {
		return publisher;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((isbn == null) ? 0 : isbn.hashCode());
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
		Book other = (Book) obj;
		if (isbn == null) {
			if (other.isbn != null) {
				return false;
			}
		} else if (!isbn.equals(other.isbn)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Book [isbn=" + isbn + ", title=" + title + "]";
	}

}
