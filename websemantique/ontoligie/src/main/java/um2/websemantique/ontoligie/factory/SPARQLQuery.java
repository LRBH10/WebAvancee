/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package um2.websemantique.ontoligie.factory;

import java.util.ArrayList;

import org.openjena.atlas.io.IndentedWriter;

import um2.websemantique.entities.base.Author;
import um2.websemantique.entities.base.AuthorFacebook;
import um2.websemantique.entities.base.AuthorGoodRead;
import um2.websemantique.entities.base.Book;
import um2.websemantique.entities.utils.SearchType;
import um2.websemantique.ontoligie.utils.ResponseQuery;

import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import um2.websemantique.entities.apicallers.GoodReadApiCaller;
import um2.websemantique.ontoligie.utils.VocabularyAutheur;
import um2.websemantique.ontoligie.utils.VocabularyBook;

/**
 *
 * @author GoceDelcev
 */
public class SPARQLQuery {

    public static final String NL = System.getProperty("line.separator");

    public static ResponseQuery responseSPARQLQuerry(String recherche,
            SearchType typeResearch) {
        
         
        

        return null;
    }
    
    public static ArrayList<String> createSPARQLQuerys(String recherche , SearchType type){
        ArrayList<String> result = new ArrayList<String>(); 
        String [] words = recherche.split(" ");
        for(String word : words){
            result.add( createSPARQLQuerry(word, type) );
        }
        return result;
    }

    /**
     * Method that generates instance of book from a resource
     *
     * @param list the result from executeSPARQLquery
     * @return the new generated book
     */
    public static Book createBookFromSPARQL(ArrayList<Resource> list) {
        Resource res = list.get(0);
        // Book result = new Book();

        StmtIterator i = res.listProperties();
        while (i.hasNext()) {
            System.out.println(i.next().getLiteral().getString());
        }

        return null;
    }

    /**
     * Method that generates list of author instances from resources
     *
     * @param list the result from executeSPARQLquery
     * @return the new generated author
     */
    public static ArrayList<Author> createAuthorFromSPARQL(ArrayList<Resource> list) {
        ArrayList<Author> result = new ArrayList<Author>();
        
        for(Resource res : list){
            result.add(new Author(createGoodreadAuthor( res ), createFacebookAuthor( res )));            
        }
        return result;
    }
    
    /**
     * Method that generates AuthorGoodRead instance from Resource
     * 
     * @param res the resource
     * @return the new generated instance
     */
    private static AuthorGoodRead createGoodreadAuthor(Resource res){
        
        AuthorGoodRead grAuthor = new AuthorGoodRead();
        StmtIterator i = res.listProperties();
        while (i.hasNext()) {
            Statement x = i.next();
            String literalValue = x.getLiteral().getString();
            String propertyValue = x.getPredicate().getLocalName();
            System.out.println(propertyValue + " --- " + literalValue);
            
            if (propertyValue.equals(VocabularyAutheur.googreadIdAutheur)) {
                grAuthor.setId(literalValue);
            }
            if (propertyValue.equals(VocabularyAutheur.googreadName)) {
                grAuthor.setName(literalValue);
            }
            if (propertyValue.equals(VocabularyAutheur.goodreadLink)) {
                grAuthor.setLink(literalValue);
            }
            if (propertyValue.equals(VocabularyAutheur.goodreadFansCount)) {
                grAuthor.setFansCount(literalValue);
            }
            if (propertyValue.equals(VocabularyAutheur.goodreadImageUri)) {
                grAuthor.setImageUrl(literalValue);
            }
            if (propertyValue.equals(VocabularyAutheur.goodreadAbout)) {
                grAuthor.setAbout(literalValue);
            }
            if (propertyValue.equals(VocabularyAutheur.goodreadWorksCount)) {
                grAuthor.setWorksCount(literalValue);
            }
            if (propertyValue.equals(VocabularyAutheur.goodreadSex)) {
                grAuthor.setSex(literalValue);
            }
            if (propertyValue.equals(VocabularyAutheur.goodreadHomeTown)) {
                grAuthor.setHometown(literalValue);
            }
            if (propertyValue.equals(VocabularyAutheur.goodreadBornAt)) {
                grAuthor.setBornAt(literalValue);
            }
            if (propertyValue.equals(VocabularyAutheur.goodreadDiedAt)) {
                grAuthor.setDiedAt(literalValue);
            }
        }
        return grAuthor;
    }
    
    /**
     * Method that generates AuthorFacebook instance from Resource
     * @param res the resource
     * @return the new generated instance
     */
    private static AuthorFacebook createFacebookAuthor(Resource res){
        AuthorFacebook fbAuthor = new AuthorFacebook();
        
        StmtIterator i = res.listProperties();
        while (i.hasNext()) {
            Statement x = i.next();
            String literalValue = x.getLiteral().getString();
            String propertyValue = x.getPredicate().getLocalName();
            System.out.println(propertyValue + " --- " + literalValue);
            
            if (propertyValue.equals(VocabularyAutheur.facebookName)) {
                fbAuthor.setName(literalValue);
            }
            if (propertyValue.equals(VocabularyAutheur.facebookLikes)) {
                fbAuthor.setLikes(literalValue);
            }
            if (propertyValue.equals(VocabularyAutheur.facebookLink)) {
                fbAuthor.setLink(literalValue);
            }
            if (propertyValue.equals(VocabularyAutheur.facebookIdAutheur)) {
                fbAuthor.setId(literalValue);
            }

            if (propertyValue.equals(VocabularyAutheur.facebookTalkingAboutCount)) {
                fbAuthor.setTalkingAboutCount(literalValue);
            }
        }
        return fbAuthor;
    }

    /**
     * Method that execute a SPARQL query passed by parameter
     *
     * @param queryString this is the SPARQL query that will be executed
     * @return The generated list of Resources
     */
    public static ArrayList<Resource> executeSPARQLQuery(String queryString) {

        ArrayList<Resource> result = new ArrayList<Resource>();
        com.hp.hpl.jena.query.Query query = QueryFactory.create(queryString);
        query.serialize(new IndentedWriter(System.out, true));
        System.out.println();
        QueryExecution qexec = QueryExecutionFactory.create(query, RDFOntology
                .getInstanceRDFOntology().getModel());
        try {
            ResultSet rs = qexec.execSelect();
            for (; rs.hasNext();) {
                QuerySolution rb = rs.nextSolution();
                // Resource z = (Resource) rb.getResource("individu");
                result.add((Resource) rb.getResource("individu"));
                // System.out.println("Individual : "+z.getNameSpace());
            }
        } finally {
            qexec.close();
        }
        return result;
    }

    /**
     * Method that generate the SPARQL query
     *
     * @param content The content of the searched value
     * @param typeResearch The type if the searched value
     * @return The generated SPARQL query SPARQL
     */
    private static String createSPARQLQuerry(String content,
            SearchType typeResearch) {

        String prefixBook = "PREFIX book: <"
                + RDFOntology.getInstanceRDFOntology().getBookClass()
                .getNameSpace() + ">";
        String prefixAutheur = "PREFIX author: <"
                + RDFOntology.getInstanceRDFOntology().getAuthorClass()
                .getNameSpace() + ">";
        String queryString = "";
        switch (typeResearch) {
            case AUTHOR:
                queryString = prefixAutheur + NL
                        + "SELECT ?individu  "
                        + "WHERE {"
                            + "{?individu author:" + VocabularyAutheur.googreadName + " ?name . "
                            + "FILTER regex( ?name,\"" + content + "\" , \"i\" ) }"
                                + "UNION"
                            + "{?individu author:" + VocabularyAutheur.facebookName + " ?name . "
                            + "FILTER regex( ?name,\"" + content + "\" , \"i\" ) }";
                ;
                break;
            case ISBN:
                queryString = prefixBook + NL
                        + "SELECT ?individu  "
                        + "WHERE {"
                            + "{?individu book:"+VocabularyBook.isbn10+" ?isbn ."
                            + "FILTER regex( ?isbn \""+ content + "\" , \"i\" ) }"
                                + "UNION"
                            +"{?individu book:"+VocabularyBook.isbn13+" ?isbn ."
                            + "FILTER regex( ?isbn \""+ content + "\" , \"i\" ) }";
                break;
            case TITLE:
                queryString = prefixBook + NL
                        + "SELECT ?individu  WHERE {?individu book:"+VocabularyBook.title+" ?title"
                        + "FILTER regex( ?title \""+ content + "\" , \"i\" ) }";
                break;
                
            default: // FOR ANY
                queryString = "";
                break;
        }

        return queryString;
    }
}
