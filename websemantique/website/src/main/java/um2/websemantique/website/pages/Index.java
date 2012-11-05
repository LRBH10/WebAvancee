package um2.websemantique.website.pages;

import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.beaneditor.Validate;
import org.apache.tapestry5.corelib.components.Zone;

import um2.websemantique.entities.base.Author;
import um2.websemantique.entities.base.Book;
import um2.websemantique.entities.utils.SearchType;
import um2.websemantique.ontoligie.factory.GetterRDFAuthorBook;
import um2.websemantique.ontoligie.sdb.SDBUtil;
import um2.websemantique.ontoligie.utils.ResponseQuery;

/**
 * Start page of application website.
 */
public class Index {

	@Property
	@Persist(PersistenceConstants.FLASH)
	private String		search;

	@Property
	@Persist
	@Validate("required")
	SearchType			type;

	@Property
	@Persist
	GetterRDFAuthorBook	query;

	@Property
	@Persist
	ResponseQuery		response;

	@Property
	@Persist
	private int			progress;

	@InjectComponent
	private Zone		progressZone;

	@OnEvent(value = EventConstants.REFRESH)
	Object doFromClicker() {
		if ( query != null ) {
			progress = query.getProgress ();
		}

		return progressZone.getBody ();
	}

	
	public void onActivate() {
		if ( query == null ) {
			query = new GetterRDFAuthorBook ();
		}
		progress = 0;
	}

	void onValidateFromSearchForm() {
		SDBUtil.openConnection ();
		System.out.println (search + " " + type + "\n\n\n\n\n\n");
		response = query.find (search, type);
	}

	

	@Property
	Book			book;

	@Property
	Author			author;

}
