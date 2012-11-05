package um2.websemantique.ontoligie.sdb;

import java.sql.SQLException;
import java.util.ArrayList;
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
                
                //GetterBookAuthor get = new GetterBookAuthor();
                //get.find("Yasmina Khadra", SearchType.AUTHOR);
                
                //RDFOntology.getInstanceRDFOntology().databaseToString();
                
                ResponseQuery res = SPARQLQuery.responseSPARQLQuerry("8497899938", SearchType.ANY);
                ArrayList<Author> listAuthors = res.getAuthors();
                ArrayList<Book> listBooks = res.getBooks();
                
                for(Author author : listAuthors){
                    System.out.println(author.getGoodRead().getName());
                }
                
                for(Book book : listBooks){
                    System.out.println(book.getTitle());
                }
                
                
                /*
                String querry = "SELECT ?subsidiaryName ?description WHERE { {"
                        + " SERVICE <http://dbpedia.org/sparql> { <http://dbpedia.org/resource/IBM> <http://dbpedia.org/ontology/Company/subsidiary> ?ibmSub ."
                        + " ?ibmSub <http://dbpedia.org/property/abstract> ?description ."
                        + " ?ibmSub rdfs:label ?subsidiaryName ."
                        + " FILTER (lang(?description) = \"en\")"
                        + " FILTER (lang(?subsidiaryName) = \"en\") }}}";
                SPARQLQuery.executeSPARQLQuery(querry);
                */
	}
}
