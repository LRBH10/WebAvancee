package um2.websemantique.website.pages;

import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Log;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.beaneditor.Validate;
import org.apache.tapestry5.corelib.components.Zone;

import um2.websemantique.entities.base.Book;
import um2.websemantique.entities.utils.SearchType;
import um2.websemantique.ontoligie.factory.GetterRDFAuthorBook;
import um2.websemantique.ontoligie.utils.ResponseQuery;


/**
 * Start page of application website.
 */
public class Index {

	@Property
	@Persist
	private String search;

	@Property
	@Persist
	@Validate("required")
	SearchType type;
	
	@Property
	@Persist
	GetterRDFAuthorBook query ;
	
	@Property
	@Persist
	ResponseQuery response;
	
	@Log
	public void onActivate(){
		if(query == null )
			query = new GetterRDFAuthorBook();
	}

	void onValidateFromSearchForm() {
		//response = query.find(search, type);
		//return this;
	}
	

	@Property 
	@Persist
	Book book;
	
	
	public Book getAlpha(){
		book = new Book();
		book.setTitle("Blazo");
		book.setBuyLink("ssss");
		return book;
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
