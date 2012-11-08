package um2.websemantique.ontoligie.sdb;

import com.hp.hpl.jena.query.QueryFactory;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import um2.websemantique.entities.base.Author;
import um2.websemantique.entities.base.Book;
import um2.websemantique.entities.utils.GetterBookAuthor;
import um2.websemantique.entities.utils.SearchType;

import um2.websemantique.ontoligie.factory.RDFOntology;
import um2.websemantique.ontoligie.factory.SPARQLQuery;
import um2.websemantique.ontoligie.utils.ResponseQuery;

public class Main {
	public static void main(String [] args) throws SQLException {
		SDBUtil.openConnection ();
                //RDFOntology.getInstanceRDFOntology().databaseToString();
                System.out.println(SPARQLQuery.executeEndpointSPARQLQuery("select ?p ?s ?o where{ ?s ?o ?p }"));
                //SPARQLQuery.executeEndpointSPARQLQuery("select ?s ?o ?p where{ ?s ?o ?p }");
                //System.out.println(SPARQLQuery.executeEndpointSPARQLQuery(SPARQLQuery.createSPARQLQuerry("Paulo Coelho", SearchType.AUTHOR)));
                
	}
}
