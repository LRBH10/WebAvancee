package um2.websemantique.entities.apicallers;

import java.net.URL;

public class GoogleBookApiCaller extends ApiCaller {

	private String key = "AIzaSyBaZwSa7tSjJnWmiCGYnEY087u-P-aGFGE";
	private String url = "https://www.googleapis.com/books/v1/volumes?maxResults=5&printType=books&orderBy=relevance&q=";// &prettyPrint=false

	public String getKey() {
		return key;
	}

	public String getUrl() {
		return url;
	}

	public String findBookofAuthor(String author) {
		author = ApiCaller.urlEncode(author);
		URL url_object = ApiCaller.getUrlFromString(url + "inauthor:" + author);
		String result = ApiCaller.cUrl(url_object);
		return result;
	}
}
