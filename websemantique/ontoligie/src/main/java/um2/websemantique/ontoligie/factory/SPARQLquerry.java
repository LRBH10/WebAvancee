/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package um2.websemantique.ontoligie.factory;

import um2.websemantique.entities.utils.SearchType;
import um2.websemantique.ontoligie.utils.Response;

/**
 *
 * @author GoceDelcev
 */
public class SPARQLquerry {
    
    public Response responseSparqlQuerry(String recherche , SearchType typeResearch){
        return null;
    }
    
    /**
     * Method that generate the SPARQL query
     * @param content The content of the searched value
     * @param typeResearch The type if the searched value
     * @return The generated SPARQL query SPARQL 
     */
    private String createSparqlQuerry(String content , SearchType typeResearch){
        String query = "";
    
        switch (typeResearch) {
		case AUTHOR:
			query = "inauthor:";
			break;
		case ISBN:
			query = "isbn:";
			break;
		case TITLE:
			query = "intitle:";
			break;
		case SUBJECT:
			query = "subject:";
			break;

		default: // FOR ANY
			query = "";
			break;
        }
        
        return query;
    }
}
