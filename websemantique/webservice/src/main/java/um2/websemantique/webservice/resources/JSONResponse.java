package um2.websemantique.webservice.resources;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import um2.websemantique.ontoligie.utils.ResponseQuery;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class JSONResponse {

	@GET
	@Path("excuteSPRQL/{query}")
	public String excuteSPRQL(@PathParam("query") String query) {
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		String res =  gson.toJson(query);
		return res + "\n";
	}

	
	@GET
	@Path("excuteQuery/q={query}&type={type}")
	public String excuteQuery(@PathParam("query") String query,@PathParam("type") String type) {
		
		ResponseQuery result = new ResponseQuery();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		String res =  gson.toJson(result) + type;
		return res + "\n";
	}

	
	@GET
	@Path("testGet")
	public String testGET() {
		String res = "sqdqsd ";
		return res + "\n";
	}

	@POST
	@Path("testPost")
	public String testPost(@FormParam("name") String name) {
		System.out.println("Beta");
		return "nameSMD : " + name;
	}

}
