package um2.websemantique.ontoligie.sdb;

import java.sql.SQLException;
import um2.websemantique.entities.utils.GetterBookAuthor;
import um2.websemantique.entities.utils.SearchType;

import um2.websemantique.ontoligie.factory.RDFOntology;

public class Main {
	public static void main(String [] args) throws SQLException {
		SDBUtil.openConnection ();
                GetterBookAuthor get = new GetterBookAuthor();
                get.find("Yasmina Khadra", SearchType.AUTHOR);
	}
}
