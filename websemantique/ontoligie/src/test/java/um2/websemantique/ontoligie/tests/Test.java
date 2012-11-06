package um2.websemantique.ontoligie.tests;

import com.hp.hpl.jena.rdf.model.Resource;
import java.sql.SQLException;

import um2.websemantique.entities.utils.SearchType;
import um2.websemantique.ontoligie.factory.GetterRDFAuthorBook;
import um2.websemantique.ontoligie.factory.RDFOntology;
import um2.websemantique.ontoligie.interconnection.DbpediaConnection;
import um2.websemantique.ontoligie.sdb.SDBUtil;

public class Test {

	/**
	 * @param args
	 * @throws InterruptedException
	 * @throws SQLException
	 */
	public static void main(String [] args) throws InterruptedException,
			SQLException {
		//SDBUtil.openConnection ();
		//RDFOntology.getInstanceRDFOntology ().databaseToString ();
		/*GetterRDFAuthorBook g = new GetterRDFAuthorBook ();
		g.find ("alo", SearchType.TITLE);//*/
            
            Resource r = DbpediaConnection.executeSPARQLToDbpedia("123");
            if(r != null)
                System.out.println(r.getURI());
	}

}
