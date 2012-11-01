package um2.websemantique.ontoligie.tests;

import um2.websemantique.entities.utils.SearchType;
import um2.websemantique.ontoligie.factory.GetterRDFAuthorBook;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GetterRDFAuthorBook g =new GetterRDFAuthorBook();
		g.find("ssss", SearchType.SUBJECT);
	}

}
