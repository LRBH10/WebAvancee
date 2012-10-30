package um2.websemantique.entities.utils;

import java.util.ArrayList;

import um2.websemantique.entities.apicallers.FacebookAuthorApiCaller;
import um2.websemantique.entities.apicallers.GoogleBookApiCaller;
import um2.websemantique.entities.base.Author;
import um2.websemantique.entities.base.Book;

public class GetterBookAuthor {
	ArrayList<Author> authors = new ArrayList<Author>();
	ArrayList<Book> books = new ArrayList<Book>();

	/**
	 * find all book for an author given
	 * 
	 * @param query
	 *            name of author
	 * @param type
	 *            {@link GoogleBookApiCaller}
	 * @param maxResults
	 *            max book calculed
	 */

	public void find(String query, String type, int maxResults) {
		GoogleBookApiCaller g = new GoogleBookApiCaller();
		g.setMaxResults(maxResults);
		g.setType(type);

		books = GeneratorFromJSON.createBooks(g.findBooks(query));
		Author origin = null;

		for (Book book : books) {
			if (origin == null || origin.isNull()) {
				origin = new Author(book.getAuthors().get(0));
				authors.add(origin);
			}

			if (book.getAuthors().size() < 1) {
				book.getAuthorslink().add(origin.getLinkAbout());

			} else {
				for (String name : book.getAuthors()) {
					if (!origin.isNull()
							&& !name.toLowerCase().equals(
									origin.getGoodRead().getName()
											.toLowerCase())) {
						Author inter = new Author(name);
						book.getAuthorslink().add(inter.getLinkAbout());
						authors.add(inter);
					} else {
						book.getAuthorslink().add(origin.getLinkAbout());
					}
				}
			}
		}
	}

	public ArrayList<Author> getAuthors() {
		return authors;
	}

	public ArrayList<Book> getBooks() {
		return books;
	}
}
