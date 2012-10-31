package um2.websemantique.website.pages;

import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.beaneditor.Validate;

import um2.websemantique.entities.utils.SearchType;


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
		containts = search + type + SearchType.getValueFromType(type);
		
	}

}
