package um2.websemantique.entities.base;

import um2.websemantique.entities.apicallers.ApiCaller;
import um2.websemantique.entities.apicallers.FacebookAuthorApiCaller;
import um2.websemantique.entities.apicallers.GoodReadApiCaller;
import um2.websemantique.entities.utils.GeneratorFromJSON;

public class Author {

	private AuthorGoodRead	goodRead;
	private AuthorFacebook	facebook;
	private String			key;

	/**
	 * Constuct from two class Author
	 * 
	 * @param gauthor
	 *            {@link AuthorGoodRead}
	 * @param fauthor
	 *            {@link AuthorFacebook}
	 */
	public Author(AuthorGoodRead gauthor, AuthorFacebook fauthor) {
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
		key = author;

		GoodReadApiCaller gd = new GoodReadApiCaller ();
		goodRead = gd.findGoodReadAuthor (author);

		FacebookAuthorApiCaller fa = new FacebookAuthorApiCaller ();
		facebook = GeneratorFromJSON.createAuthorFacebook (fa.findAuthorFacebook (author));
	}

	public String getExcludes() {
		String res = "";
		res += this.facebook.getExcludes () + this.goodRead.getExcludes ();
		return res;

	}

	public AuthorFacebook getFacebook() {
		return facebook;
	}

	public AuthorGoodRead getGoodRead() {
		return goodRead;
	}

	/**
	 * key of Search
	 * 
	 * @return
	 */
	public String getKey() {
		return key;
	}

	/**
	 * Get Link identifier for this instance of {@link Author}
	 * 
	 * @return link
	 */

	public String getLinkAbout() {
		String res = "";
		if ( goodRead != null ) {
			res = goodRead.getLink ();
		} else if ( facebook != null ) {
			res = facebook.getLink ();
		} else {
			res = "http://www.google.com?q="
					+ ApiCaller.urlEncode (key).toString ();
		}

		return res;
	}

	/**
	 * @return
	 */
	public boolean isAuthorFacebookNull() {
		boolean x = false;
		if ( this.facebook == null ) {
			x = true;
		}
		return x;
	}

	public boolean isAuthorGoodReadNull() {
		boolean x = false;
		if ( this.goodRead == null ) {
			x = true;
		}
		return x;
	}

	/**
	 * Verify if the two Object facebook and goodRead are'nt null
	 * 
	 * @return {@link Boolean}
	 */
	public boolean isNull() {
		boolean ret = false;
		if ( goodRead == null && facebook == null ) {
			ret = true;
		}
		return ret;
	}

	public void setFacebook(AuthorFacebook facebook) {
		this.facebook = facebook;
	}

	public void setGoodRead(AuthorGoodRead goodRead) {
		this.goodRead = goodRead;
	}

}
