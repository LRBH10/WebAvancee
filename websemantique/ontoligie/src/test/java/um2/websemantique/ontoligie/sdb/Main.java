package um2.websemantique.ontoligie.sdb;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import um2.websemantique.entities.utils.SearchType;

import um2.websemantique.ontoligie.factory.RDFOntology;
import um2.websemantique.ontoligie.factory.SPARQLQuery;

public class Main {

	/**
	 * @param args
	 * @throws SQLException
	 * @throws UnsupportedEncodingException 
	 */
	public static void main(String[] args) throws SQLException, UnsupportedEncodingException {
		 SDBUtil.openConnection();
                 //GetterRDFAuthorBook g = new GetterRDFAuthorBook();
                 //g.find("Isabelle Eberhardt", SearchType.ANY);
                     RDFOntology.getInstanceRDFOntology().databaseToString();
		 //SPARQLQuery.responseSparqlQuerry("Isabelle Eberhardt", SearchType.AUTHOR);
                 /*
                 String test = "Blazo Nastov";
                 String  [] x = test.split(" ");
                 for(String r : x){
                     System.out.println(r);
                 }
                 */
                 //SPARQLQuery.responseSparqlQuerry("Eberhardt Isabelle", SearchType.AUTHOR);
	}
}
