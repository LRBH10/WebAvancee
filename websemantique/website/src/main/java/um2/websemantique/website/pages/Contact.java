package um2.websemantique.website.pages;

import org.apache.tapestry5.annotations.Property;

import um2.websemantique.entities.apicallers.GoogleBookApiCaller;

public class Contact {
	@Property
	String test;
	
	

	public String getApiCall() {
		GoogleBookApiCaller gg =new GoogleBookApiCaller();
		return gg.findBooks("yasmina khadra");//*/
	}
	
	
}
