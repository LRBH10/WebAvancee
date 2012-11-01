package um2.websemantique.ontoligie.factory;

import um2.websemantique.entities.utils.GetterBookAuthor;
import um2.websemantique.entities.utils.SearchType;
import um2.websemantique.ontoligie.utils.Response;

public class GetterRDFAuthorBook extends Thread {
	private String key;
	private SearchType typeS;
	private GetterBookAuthor getweb = new GetterBookAuthor();

	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public SearchType getTypeS() {
		return typeS;
	}
	public void setTypeS(SearchType typeS) {
		this.typeS = typeS;
	}
	public GetterBookAuthor getGetweb() {
		return getweb;
	}
	
	/**
	 * find a query with type given from database or from web
	 * 
	 * @param query
	 *            that the client want to get
	 * @param type
	 *            {@link SearchType}
	 * @return result of query
	 */
	public String find(String query, SearchType type) {
		key = query;
		typeS = type;

		Response res = findSPRQL(query, type);

		String resultat = "";
		if (res.isOK()) {
			resultat = res.getContaints();
		} else {
			this.start();
		}

		return resultat;
	}

	/**
	 * find a query in data base
	 * 
	 * @param query
	 *            that the client want to get
	 * @param type
	 *            {@link SearchType}
	 * @return {@link Response}
	 */
	private Response findSPRQL(String query, SearchType type) {
		Response ret = new Response();
		ret.setContaints("OK");
		ret.setOK(false);
		return ret;
	}

	/**
	 * get the information into data base
	 */
	@Override
	public void run() {
		/**
		 * To Do
		 */
		
	}
}
