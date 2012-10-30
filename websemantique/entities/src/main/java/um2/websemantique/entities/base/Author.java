package um2.websemantique.entities.base;

import um2.websemantique.entities.apicallers.FacebookAuthorApiCaller;
import um2.websemantique.entities.apicallers.GoodReadApiCaller;
import um2.websemantique.entities.utils.GeneratorFromJSON;

public class Author {
	private AuthorGoodRead goodRead;
	private AuthorFacebook facebook;

	public AuthorGoodRead getGoodRead() {
		return goodRead;
	}

	public void setGoodRead(AuthorGoodRead goodRead) {
		this.goodRead = goodRead;
	}

	public AuthorFacebook getFacebook() {
		return facebook;
	}

	public void setFacebook(AuthorFacebook facebook) {
		this.facebook = facebook;
	}

	/**
	 * Constuct from two class Author
	 * 
	 * @param gauthor
	 *            {@link AuthorGoodRead}
	 * @param fauthor
	 *            {@link AuthorFacebook}
	 */
	Author(AuthorGoodRead gauthor, AuthorFacebook fauthor) {
		goodRead = gauthor;
		facebook = fauthor;
	}

	/**
	 * Construct from author name
	 * 
	 * @param author
	 *            {@link String}
	 */
	public Author(String author) {
		GoodReadApiCaller gd = new GoodReadApiCaller();
		goodRead = gd.findGoodReadAuthor(author);
		
		FacebookAuthorApiCaller fa = new FacebookAuthorApiCaller();
		facebook = GeneratorFromJSON.createAuthorFacebook(fa.findAuthorFacebook(author));
	}

}
