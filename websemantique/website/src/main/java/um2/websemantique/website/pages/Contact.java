package um2.websemantique.website.pages;

import org.apache.tapestry5.annotations.Property;

import um2.websemantique.website.base.ApiCaller;

public class Contact extends ApiCaller {
	@Property
	String test;

	public String getApiCall() {
		return cUrl("https://www.googleapis.com/books/v1/volumes?q=inauthor:yasmina+khadra&maxResults=1");

	}

	
}
