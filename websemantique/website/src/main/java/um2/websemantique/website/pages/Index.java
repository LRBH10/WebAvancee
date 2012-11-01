package um2.websemantique.website.pages;

import org.apache.regexp.recompile;
import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.beaneditor.Validate;
import org.apache.tapestry5.corelib.components.Zone;

import um2.websemantique.entities.base.Book;
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
	
	
	Book getBookAff(){
		Book ss = new Book();
		return ss;
	}
	
	
	@Property
	@Persist
	private int clickCount;

	@InjectComponent
	private Zone counterZone;

	@OnEvent(value = EventConstants.REFRESH)

	Object doFromClicker()
	{
	clickCount++;

	return counterZone.getBody();
	}

}
