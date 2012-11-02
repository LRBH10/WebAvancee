package um2.websemantique.webservice.resources;

import java.util.ArrayList;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hp.hpl.jena.query.QueryParseException;
import com.hp.hpl.jena.rdf.model.Resource;

import um2.websemantique.entities.utils.SearchType;
import um2.websemantique.ontoligie.factory.SPARQLQuery;
import um2.websemantique.ontoligie.utils.ResponseQuery;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class JSONResponse {

	
	/**
	 * Execute a query SPARQL from host client
	 * @param query  sparql string
	 * @return json associated
	 */
	@GET
	@Path("get/excuteSPARQL/sparql={query}")
	public String excuteSPRQL(@PathParam("query") String query) {
		String res = "";
		ArrayList<Resource> rs = null;
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		try {
			rs = SPARQLQuery.executeSPARQLQuery(query);

			res = "{\nsize:\"" + rs.size() + "\"items:";
			res = gson.toJson(rs, Resource.class);
			res = "\n}";

		} catch (QueryParseException e) {
			res = "{ queryparseexception :" + gson.toJson(e) + "\n}";

		} catch (Exception e) {

			res = "{ exception :" + gson.toJson(e) + "\n}";
			System.out.println(e);
		}

		return res + "\n";
	}

	/**
	 * Excute a search query with Web Service
	 * @param query 
	 * @param type {@link SearchType}
	 * @return associeted json
	 */
	@GET
	@Path("get/excuteQuery/q={query}&type={type}")
	public String excuteQuery(@PathParam("query") String query,
			@PathParam("type") String type) {

		ResponseQuery result = new ResponseQuery(query,SearchType.fromString(type));
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		String res = gson.toJson(result) + type;
		return res + "\n";
	}
	
	
	/**
	 * POST Execute a query SPARQL from host client
	 * @param query  sparql string
	 * @return json associated
	 */
	@POST
	@Path("get/excuteSPARQL/")
	public String excuteSPRQLPost(@FormParam("query") String query) {
		String res = "";
		ArrayList<Resource> rs = null;
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		try {
			rs = SPARQLQuery.executeSPARQLQuery(query);

			res = "{\nsize:\"" + rs.size() + "\"items:";
			res = gson.toJson(rs, Resource.class);
			res = "\n}";

		} catch (QueryParseException e) {
			res = "{ queryparseexception :" + gson.toJson(e) + "\n}";

		} catch (Exception e) {

			res = "{ exception :" + gson.toJson(e) + "\n}";
			System.out.println(e);
		}

		return res + "\n";
	}

	/**
	 * POST Excute a search query with Web Service
	 * @param query 
	 * @param type {@link SearchType}
	 * @return associeted json
	 */
	@POST
	@Path("post/excuteQuery/")
	public String excuteQueryPost(@FormParam("query") String query,
			@PathParam("type") String type) {

		ResponseQuery result = new ResponseQuery(query,SearchType.fromString(type));
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		String res = gson.toJson(result) + type;
		return res + "\n";
	}


	/**
	 * test POST
	 * @param name
	 * @return
	 */
	@POST
	@Path("testPost")
	public String testPost(@FormParam("name") String name) {
		System.out.println("Beta");
		return "nameSMD : " + name;
	}

}
