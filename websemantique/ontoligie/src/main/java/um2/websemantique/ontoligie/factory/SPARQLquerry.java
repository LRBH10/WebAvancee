/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package um2.websemantique.ontoligie.factory;

import com.hp.hpl.jena.graph.query.Query;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import java.util.ArrayList;
import org.openjena.atlas.io.IndentedWriter;
import um2.websemantique.entities.base.Author;
import um2.websemantique.entities.base.AuthorFacebook;
import um2.websemantique.entities.base.AuthorGoodRead;
import um2.websemantique.entities.base.Book;
import um2.websemantique.entities.utils.SearchType;
import um2.websemantique.ontoligie.sdb.SDBUtil;
import um2.websemantique.ontoligie.utils.Response;

/**
 *
 * @author GoceDelcev
 */
public class SPARQLquerry {
    
    public static final String NL = System.getProperty("line.separator") ;
    
    public static Response responseSparqlQuerry(String recherche , SearchType typeResearch){
        
        if(typeResearch.equals(SearchType.AUTHOR)){
            createAuthorFromSPARQL(executeSPARQLQuery( createSparqlQuerry(recherche, typeResearch)));
        }
        if(typeResearch.equals(SearchType.TITLE)){
            createBookFromSPARQL(executeSPARQLQuery( createSparqlQuerry(recherche, typeResearch)));
        }
        //executeSPARQLQuery( createSparqlQuerry(recherche, typeResearch) ) ;
        
        return null;
    }
    
    /**
     * Method that generates instance of book from a resource 
     * @param list  the result from executeSPARQLquery
     * @return the new generated book
     */
    public static Book createBookFromSPARQL( ArrayList<Resource> list ){
        Resource res = list.get(0);
        //Book result = new Book();
        
        StmtIterator i = res.listProperties();
        while(i.hasNext()){
            System.out.println(i.next().getLiteral().getString());
        }
        
        return null;
    }
    
    /**
     * Method that generates instance of author from a resource 
     * @param list  the result from executeSPARQLquery
     * @return the new generated author
     */
    public static Author createAuthorFromSPARQL( ArrayList<Resource> list ){
        
        Resource res = list.get(0);
        AuthorGoodRead grAuthor = new AuthorGoodRead();
        AuthorFacebook fbAuthor = new AuthorFacebook();
        
        StmtIterator i = res.listProperties();
        while(i.hasNext()){
            Statement x = i.next();
            String literalValue = x.getLiteral().getString();
            String propertyValue = x.getPredicate().getLocalName();
            //System.out.println( propertyValue + " --- " + literalValue);
            
            if(propertyValue.equals("id")){
                grAuthor.setId(literalValue);
            }
            if(propertyValue.equals("name")){
                grAuthor.setName(literalValue);
            }
            if(propertyValue.equals("link")){
                grAuthor.setLink(literalValue);
            }
            if(propertyValue.equals("fans_count")){
                grAuthor.setFansCount(literalValue);
            }
            if(propertyValue.equals("image_uri")){
                grAuthor.setImageUrl(literalValue);
            }
            if(propertyValue.equals("about")){
                grAuthor.setAbout(literalValue);
            }
            if(propertyValue.equals("works_count")){
                grAuthor.setWorksCount(literalValue);
            }
            if(propertyValue.equals("gender")){
                grAuthor.setGender(literalValue);
            }
            if(propertyValue.equals("home_town")){
                grAuthor.setHometown(literalValue);
            }
            if(propertyValue.equals("born_at")){
                grAuthor.setBornAt(literalValue);
            }
            if(propertyValue.equals("died_at")){
                grAuthor.setDiedAt(literalValue);
            }
            if(propertyValue.equals("name_facebook")){
                fbAuthor.setName(literalValue);
            }
            /*
            if(propertyValue.equals("likes_facebook")){
                fbAuthor.setLikes(literalValue);
            }
            * */
            if(propertyValue.equals("link_facebook")){
                fbAuthor.setLink(literalValue);
            }
            if(propertyValue.equals("id_facebook")){
                fbAuthor.setId(literalValue);
            }
            /*
            if(propertyValue.equals("talking_about_count_facebook")){
                fbAuthor.setTalkingAboutCount(literalValue);
            }
            */           
        }
        
        return new Author( grAuthor , fbAuthor);
    }   
    
    /**
     * Method that execute a SPARQL query passed by parameter 
     * @param queryString this is the SPARQL query that will be executed
     * @return The generated list of Resources
     */
    public static ArrayList<Resource> executeSPARQLQuery(String queryString){
        
        ArrayList<Resource> result = new ArrayList<Resource>();
        com.hp.hpl.jena.query.Query query = QueryFactory.create(queryString) ;
        query.serialize(new IndentedWriter(System.out,true)) ;
	System.out.println();
        QueryExecution qexec = QueryExecutionFactory.create(query, RDFOntology.getInstanceRDFOntology().getModel() );
	try {
            ResultSet rs = qexec.execSelect() ;
            for ( ; rs.hasNext() ; )
            {
                QuerySolution rb = rs.nextSolution() ;
		//Resource z = (Resource) rb.getResource("individu");
                result.add( (Resource) rb.getResource("individu") );
		//System.out.println("Individual : "+z.getNameSpace());
            }
        }
	finally
        {
            qexec.close() ;
	}
        return result;
    }
    
    /**
     * Method that generate the SPARQL query 
     * @param content The content of the searched value
     * @param typeResearch The type if the searched value
     * @return The generated SPARQL query SPARQL 
     */
    private static String createSparqlQuerry(String content , SearchType typeResearch){
        
        String prefixBook = "PREFIX book: <"+RDFOntology.getInstanceRDFOntology().getBookClass().getNameSpace()+">" ;
        String prefixAutheur = "PREFIX author: <"+RDFOntology.getInstanceRDFOntology().getAuthorClass().getNameSpace()+">" ;
        String queryString = "";        
        switch (typeResearch) {
		case AUTHOR:
			queryString =  prefixAutheur + NL +
		            "SELECT ?individu  WHERE {?individu author:name \"" + content +"\" }" ;;
			break;
		case ISBN:
			queryString =  prefixBook + NL +
		            "SELECT ?individu  WHERE {?individu book:isbn_10 \"" + content +"\" }" ;;
			break;
		case TITLE:
			queryString = prefixBook + NL +
		            "SELECT ?individu  WHERE {?individu book:title \"" + content +"\" }" ;                        
			break;
		case SUBJECT:
			queryString = "subject:";
			break;

		default: // FOR ANY
			queryString = "";
			break;
        }
        
        return queryString;
    }
}
