package um2.websemantique.website.pages;

import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.beaneditor.Validate;

import um2.websemantique.entities.utils.SearchType;
import um2.websemantique.ontoligie.factory.GetterRDFAuthorBook;


/**
 * Start page of application website.
 */
public class Index {

	@Property
	@Persist
	private String search;

	@Property
	@Persist
	private String containts;

	@Property
	@Persist
	@Validate("required")
	SearchType type;

	void onValidateFromSearchForm() {
		
		GetterRDFAuthorBook g = new  GetterRDFAuthorBook();
		g.find(search, type);
		containts = search + type + SearchType.getValueFromType(type);
		
	}

}
