package um2.websemantique.webservice.resources;

import java.util.ArrayList;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import um2.websemantique.entities.utils.SearchType;
import um2.websemantique.ontoligie.factory.SPARQLQuery;
import um2.websemantique.ontoligie.sdb.SDBUtil;
import um2.websemantique.ontoligie.utils.ResponseQuery;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hp.hpl.jena.query.QueryParseException;
import com.hp.hpl.jena.rdf.model.Resource;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class JSONResponse {

	/**
	 * Excute a search query with Web Service
	 * 
	 * @param query
	 * @param type
	 *            {@link SearchType}
	 * @return associeted json
	 */
	@GET
	@Path("get/excuteQuery/q={query}&t={type}")
	public String excuteQuery(@PathParam("query") String query, @PathParam("type") String type) {
		SDBUtil.openConnection ();
		ResponseQuery result = SPARQLQuery.responseSPARQLQuerry (query, SearchType.fromString (type));
		Gson gson = new GsonBuilder ().setPrettyPrinting ().create ();

		String res = gson.toJson (result) + type;
		return res + "\n";
	}

	/**
	 * POST Excute a search query with Web Service
	 * 
	 * @param query
	 * @param type
	 *            {@link SearchType}
	 * @return associeted json
	 */
	@POST
	@Path("post/excuteQuery/")
	public String excuteQueryPost(@FormParam("query") String query, @PathParam("type") String type) {

		SDBUtil.openConnection ();
		ResponseQuery result = SPARQLQuery.responseSPARQLQuerry (query, SearchType.fromString (type));
		Gson gson = new GsonBuilder ().setPrettyPrinting ().create ();

		String res = gson.toJson (result);
		return res + "\n";
	}

	/**
	 * Execute a query SPARQL from host client
	 * 
	 * @param query
	 *            sparql string
	 * @return json associated
	 */
	@GET
	@Path("get/excuteSPARQL/q={query}")
	public String excuteSPRQL(@PathParam("query") String query) {
		String ret = "";
		ResponseQuery res;
		Gson gson = new GsonBuilder ().setPrettyPrinting ().create ();

		try {
			res = SPARQLQuery.responseSPARQLQueryFromService (query);
			ret = gson.toJson (res);

		} catch ( QueryParseException e ) {
			ret = "{ queryparseexception :" + gson.toJson (e) + "\n}";

		} catch ( Exception e ) {

			ret = "{ exception :" + gson.toJson (e) + "\n}";
		}

		return ret + "\n";
	}

	/**
	 * POST Execute a query SPARQL from host client
	 * 
	 * @param query
	 *            sparql string
	 * @return json associated
	 */
	@POST
	@Path("post/excuteSPARQL/")
	public String excuteSPRQLPost(@FormParam("query") String query) {

	    System.out.println(query);
	    String ret = ""+query;
	    ResponseQuery res;
		Gson gson = new GsonBuilder ().setPrettyPrinting ().create ();
		if(query!=null){
		try {
			res = SPARQLQuery.responseSPARQLQueryFromService (query);
			
			
			//ret = gson.toJson (res);

		} catch ( QueryParseException e ) {
		    //e.printStackTrace ();
		    	System.out.println (e);
		    //ret = "{ queryparseexception :" + gson.toJson (e) + "\n}";

		} catch ( Exception e ) {
		    System.out.println (e);
			//e.printStackTrace ();
			//ret = "{ exception :" + gson.toJson (e) + "\n}";
			}//*/
		}
		else{
		    ret = "Requete Vide";
		}
		return ret + "\n";
	}

	/**
	 * test POST
	 * 
	 * @param name
	 * @return
	 */
	@POST
	@Path("testPost")
	public String testPost(@FormParam("name") String name) {
		System.out.println ("Beta");
		return "nameSMD : " + name;
	}

}
