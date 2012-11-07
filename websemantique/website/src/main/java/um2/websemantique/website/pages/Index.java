package um2.websemantique.website.pages;

import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.InjectPage;
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
	@Persist
	@Validate("required,minlength=2")
	private String		search;

	@Property
	@Persist
	@Validate("required,minlength=2")
	private String		searchInterne;

	@Property
	@Persist
	@Validate("required,minlength=2")
	private String		searchWeb;

	@Property
	@Persist
	@Validate("required")
	SearchType			type;

	@Property
	@Persist
	@Validate("required")
	SearchType			typeInterne;

	@Property
	@Persist
	@Validate("required")
	SearchType			typeWeb;

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

	@InjectPage
	Index				index;

	@Property
	int					encours;

	public void setup(int enc, Index last) {
		encours = enc;
		search = last.search;
		searchInterne = last.searchInterne;
		searchWeb = last.searchWeb;
		type = last.type;
		typeInterne = last.typeInterne;
		typeWeb = last.typeWeb;

	}

	@OnEvent(value = EventConstants.REFRESH)
	Object doFromClicker() {
		if ( query != null ) {
			progress = query.getProgress ();
		}
		return progressZone.getBody ();
	}

	/**
	 * when the page is selected
	 */
	public void onActivate() {
		if ( query == null ) {
			query = new GetterRDFAuthorBook ();
		}
		progress = 0;
	}

	/**
	 * Search Interne and in Web
	 * 
	 * @return {@link Zone}
	 */
	Object onValidateFromSearchForm() {
		SDBUtil.openConnection ();
		System.out.println (search + " " + type + "\n\n\n\n\n\n");
		response = query.find (search, type);
		index.setup (0, this);
		return index;
	}

	/**
	 * Search Internal
	 * 
	 * @return {@link Zone}
	 */
	Object onValidateFromSearchFormInterne() {

		SDBUtil.openConnection ();
		response = query.findSPRQL (searchInterne, typeInterne);
		index.setup (1, this);
		return index;
	}

	/**
	 * Search in Web
	 * 
	 * @return {@link Zone}
	 * @throws {@link InterruptedException} if probleme in waiting
	 */
	Object onValidateFromSearchFormWeb() throws InterruptedException {

		SDBUtil.openConnection ();
		response = query.find (searchWeb, typeWeb);
		while (progress != 0) {
			Thread.sleep (100);
		}
		index.setup (2, this);
		return index;
	}

	@Property
	Book	book;

	@Property
	Author	author;

}
