package um2.websemantique.webservice.resources;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/tests/")
@Produces("application/json")
public class OperationTestResources {

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
