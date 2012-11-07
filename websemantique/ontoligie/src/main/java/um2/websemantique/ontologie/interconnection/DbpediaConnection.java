/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 // */
package um2.websemantique.ontologie.interconnection;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.rdf.model.Resource;
import java.util.ArrayList;
import org.openjena.atlas.io.IndentedWriter;

/**
 *
 * @author GoceDelcev
 */
public class DbpediaConnection {
    
    /**
     * Multiple words, complete phrase,in start of phrase.
     * Select distinct values of subjects and objects, where subjects have labels 
     * that start with the exact searched keywords. 
     * The subject is not from: http://dbpedia.org/resource/Category 
     * http://dbpedia.org/resource/List 
     * http://sw.opencyc.org/ which are in English language.
     * 
     * @param word The searched word
     * @return dbpedia statement assocciated to this word
     */
    public static Resource executeSPARQLToDbpedia(String word){      
            Query query = QueryFactory.create(createQueryForDbpedia(word));
            query.serialize(new IndentedWriter(System.out, true));
            
            QueryExecution qexec = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", query);
            try {
                ArrayList<Resource> res = new ArrayList<Resource>();
                ResultSet results = qexec.execSelect();                
                //ResultSetFormatter.out(System.out, results, query);                
                for (; results.hasNext();) {
                    QuerySolution rb = results.nextSolution();                    
                    return rb.getResource("s");
                }                
            }
            catch(Exception e){
                System.out.println("Impossible to find Resource for "+ word + " !");
                return null;
            }
            finally {
               qexec.close();
            }
            return null;
    }
    
    /**
     * Takes word and generate the associated query 
     * @param word the word
     * @return sparql query
     */
    public static String createQueryForDbpedia(String word){
        String result = "SELECT DISTINCT ?s ?o"
                        + " WHERE {"
                + "?s  <http://www.w3.org/2000/01/rdf-schema#label> ?o . "
                + "?o <bif:contains> \'";
        String aux = "FILTER (";
        String[] words = word.split(" ");
        for (int i = 0; i < words.length - 1; i++) {
            result += words[i] + " and ";
            aux += "(regex(str(?o), \'^"+ words[i] +"\', 'i')) || ";
        }
        result+= words[words.length - 1] + "\' .";
        result += aux + "(regex(str(?o), \'^"+ words[words.length - 1] +"\', 'i')) ).";
        result += "FILTER (!regex(str(?s), '^http://dbpedia.org/resource/Category:'))."
                + "FILTER (!regex(str(?s), '^http://dbpedia.org/resource/List'))."
                + "FILTER (!regex(str(?s), '^http://sw.opencyc.org/'))."
                + "FILTER (lang(?o) = 'en').}"
                + "Limit 1";       
        
        return result;
    }
}
