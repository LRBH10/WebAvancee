package um2.websemantique.entities.apicallers;

import um2.websemantique.entities.base.AuthorGoodRead;
import um2.websemantique.entities.utils.GeneratorFromXML;

public class GoodReadApiCaller extends ApiCaller {
	private String key = "IJ9e4JY4Gnl1JU5ADSYYg";
	private String urlSearch = "http://www.goodreads.com/search.xml";
	private String urlAuthor = "http://www.goodreads.com/author/show?id=";

	/**
	 * getting author xml Description from Good Read
	 * 
	 * @param author
	 * @return
	 */
	public AuthorGoodRead findGoodReadAuthor(String author) {
		author = ApiCaller.urlEncode(author);
		String url = urlSearch + "?key=" + key + "&q=" + author
				+ "&search[field]=author";
		String id = GeneratorFromXML.getIdGoodReadAuthor(url);

		url = urlAuthor + id + "&key=" + key;
		AuthorGoodRead result = GeneratorFromXML.getGoodReadAuthor(url);
		return result;
	}
}
