package um2.websemantique.entities.apicallers;

import java.net.URL;

import um2.websemantique.entities.utils.SearchType;

public class GoogleBookApiCaller extends ApiCaller {

	private int		maxResults	= 5;
	private int		startIndex	= 0;

	private String	key			= "AIzaSyBaZwSa7tSjJnWmiCGYnEY087u-P-aGFGE";
	private String	url			= "https://www.googleapis.com/books/v1/volumes?printType=books&prettyPrint=false&orderBy=relevance&q="; //
	private String	type		= SearchType.getValueFromType (SearchType.ANY);

	public String findBooks(String query) {
		query = ApiCaller.urlEncode (query);
		URL url_object = ApiCaller.getUrlFromString (url + type + query
				+ "&maxResults=" + maxResults + "&startIndex=" + startIndex);
		String result = ApiCaller.cUrl (url_object);
		return result;
	}

	public String getKey() {
		return key;
	}

	public int getMaxResults() {
		return maxResults;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public String getType() {
		return type;
	}

	public String getUrl() {
		return url;
	}

	public void setMaxResults(int maxResults) {
		if ( maxResults > 40 || maxResults < 0 ) {
			maxResults = 40;
		}

		this.maxResults = maxResults;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public void setType(String type) {
		this.type = type;
	}
}
