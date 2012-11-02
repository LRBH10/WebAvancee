package um2.websemantique.webservice.resources;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class JSONResponse {

	@GET
	@Path("excuteSPRQL/{query}")
	public String excuteSPRQL(@PathParam("query") String query) {
		String res =  query;
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
