package um2.websemantique.ontoligie.factory;

import com.hp.hpl.jena.rdf.model.Resource;

import um2.websemantique.entities.base.Author;
import um2.websemantique.entities.base.Book;
import um2.websemantique.entities.utils.GetterBookAuthor;
import um2.websemantique.entities.utils.SearchType;
import um2.websemantique.ontoligie.utils.ResponseQuery;
import um2.websemantique.ontologie.interconnection.DbpediaConnection;

public class GetterRDFAuthorBook implements Runnable {

	private String				key;
	private SearchType			typeS;
	private GetterBookAuthor	getweb		= new GetterBookAuthor ();
	private int					progress	= 0;
	private int					taille		= 0;

	/**
	 * find a query with type given from database or from web
	 * 
	 * @param query
	 *            that the client want to get
	 * @param type
	 *            {@link SearchType}
	 * @return result of query
	 */
	public ResponseQuery find(String query, SearchType type) {
		key = query;
		typeS = type;

		ResponseQuery res = findSPRQL (query, type);

		if ( !res.isOk () ) {
			Thread x = new Thread (this);
			x.start ();
		}
		return res;
	}
	
	public void findWeb(String query, SearchType type) {
		key = query;
		typeS = type;
		
		Thread x = new Thread (this);
		x.start ();
		
	}

	/**
	 * find from Web and pill the DATABASE
	 * 
	 * @param query
	 * @param type
	 */
	public synchronized void findFromWeb(String query, SearchType type) {
		progress = 25;
		getweb.find (query, type);
		progress = 50;

		int taille_ = getweb.getAuthors ().size () + getweb.getBooks ().size ();

		setTaille (taille_);
		int i = 0;

		for ( Author author : getweb.getAuthors () ) {
			progress = 50 + i * 50 / getTaille ();
			Resource str = DbpediaConnection.executeSPARQLToDbpedia (author.getKey ());
			if ( str != null ) author.setSameAs (str.getURI ());

			RDFFactory.generateRDFAuthorInstance (author);
			i++;
		}

		for ( Book book : getweb.getBooks () ) {
			progress = 50 + i * 50 / getTaille ();
			Resource str = DbpediaConnection.executeSPARQLToDbpedia (book.getTitle ());
			if ( str != null ) book.setSameAs (str.getURI ());
			RDFFactory.generateRDFBookInstance (book);

			i++;
		}
		progress = 0;
	}

	
	
	/**
	 * find a query in data base
	 * 
	 * @param query
	 *            that the client want to get
	 * @param type
	 *            {@link SearchType}
	 * @return {@link ResponseQuery}
	 */
	public ResponseQuery findSPRQL(String query, SearchType type) {
		ResponseQuery ret = SPARQLQuery.responseSPARQLQuerry (query, type);
		return ret;
	}

	public GetterBookAuthor getGetweb() {
		return getweb;
	}

	public String getKey() {
		return key;
	}

	public int getProgress() {
		return progress;
	}

	public synchronized int getTaille() {
		return taille;
	}

	public SearchType getTypeS() {
		return typeS;
	}

	/**
	 * get the information into data base
	 */
	@Override
	public void run() {
		findFromWeb (key, typeS);
	}

	public void setKey(String key) {
		this.key = key;
	}

	private synchronized void setTaille(int taille) {
		this.taille += taille;
	}

	public void setTypeS(SearchType typeS) {
		this.typeS = typeS;
	}
}
