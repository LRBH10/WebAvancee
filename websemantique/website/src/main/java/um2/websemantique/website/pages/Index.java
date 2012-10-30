package um2.websemantique.website.pages;

import java.util.Date;
import org.apache.tapestry5.annotations.*;
import org.apache.tapestry5.ioc.annotations.*;
import org.apache.tapestry5.corelib.components.*;
import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.alerts.AlertManager;

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

	
	void onValidateFromSearchForm() {
		containts = search;
		//return containts;
	}

}
