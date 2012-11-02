package um2.websemantique.ontoligie.sdb;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import um2.websemantique.entities.utils.SearchType;

import um2.websemantique.ontoligie.factory.RDFOntology;
import um2.websemantique.ontoligie.factory.SPARQLquerry;

public class Main {

	/**
	 * @param args
	 * @throws SQLException
	 * @throws UnsupportedEncodingException 
	 */
	public static void main(String[] args) throws SQLException, UnsupportedEncodingException {

		
		 SDBUtil.openConnection();
		 SPARQLquerry.responseSparqlQuerry("Isabelle Eberhardt", SearchType.AUTHOR);
	}
}
