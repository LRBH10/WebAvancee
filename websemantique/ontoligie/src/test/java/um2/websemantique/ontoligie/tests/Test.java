package um2.websemantique.ontoligie.tests;

import java.sql.SQLException;

import um2.websemantique.entities.utils.SearchType;
import um2.websemantique.ontoligie.factory.GetterRDFAuthorBook;
import um2.websemantique.ontoligie.sdb.SDBUtil;

public class Test {

	/**
	 * @param args
	 * @throws InterruptedException
	 * @throws SQLException
	 */
	public static void main(String [] args) throws InterruptedException,
			SQLException {
		SDBUtil.openConnection ();

		GetterRDFAuthorBook g = new GetterRDFAuthorBook ();
		g.find ("loubia", SearchType.AUTHOR);//*/
	}

}
