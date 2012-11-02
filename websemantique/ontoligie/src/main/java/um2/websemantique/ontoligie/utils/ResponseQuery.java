package um2.websemantique.ontoligie.utils;

import java.util.ArrayList;

import um2.websemantique.entities.base.Author;
import um2.websemantique.entities.base.Book;
import um2.websemantique.entities.utils.SearchType;

public class ResponseQuery {

	boolean ok;
	String key;
	SearchType type;

	ArrayList<Book> books = new ArrayList<Book>();
	ArrayList<Author> authors = new ArrayList<Author>();

	public ResponseQuery() {
	}

	public ResponseQuery(String key, SearchType type) {
		this.key = key;
		this.type = type;
	}

	public boolean isOk() {
		return ok;
	}

	public void setType(SearchType type) {
		this.type = type;
	}

	public ArrayList<Author> getAuthors() {
		return authors;
	}

	public ArrayList<Book> getBooks() {
		return books;
	}

	public String getKey() {
		return key;
	}

	public SearchType getType() {
		return type;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public void setOk(boolean ok) {
		this.ok = ok;
	}

	/**
	 * to add a book to {@link ResponseQuery}
	 * 
	 * @param book
	 *            {@link Book}
	 */
	public void addBook(Book book) {
		books.add(book);
	}

	/**
	 * to add an author to {@link ResponseQuery}
	 * 
	 * @param author
	 *            {@link Author} to add
	 */
	public void addAuthor(Author author) {
		authors.add(author);
	}

}
