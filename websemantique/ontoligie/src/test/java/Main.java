import um2.websemantique.ontoligie.sdb.SDBUtil;

import com.hp.hpl.jena.db.DBConnection;
import com.hp.hpl.jena.db.IDBConnection;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.ModelMaker;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {

			IDBConnection conn = SDBUtil.getIDBConnection();

			ModelMaker maker = ModelFactory.createModelRDBMaker(conn);
			Model tmp = null;
			if (conn.containsModel("Maladie")) {
				System.out.println("Opening existing model");
				tmp = maker.openModel("Maladie", true);
			} else {
				System.out.println("Creating new model");
				tmp = maker.createModel("Maladie", true);
			}
			// Start a database transaction. Without one, each statement will be
			// auto-committed
			// as it is added, which slows down the model import significantly.
			// tmp.begin();
			OntModelSpec spec = new OntModelSpec(OntModelSpec.OWL_DL_MEM);
			OntModel mdb = ModelFactory.createOntologyModel(spec, tmp);
			String ns = "http://t.t.fr/";
			mdb.setNsPrefix("Maladie", ns);
			Resource m = mdb.createClass(ns + "Grippe");
			Resource v = mdb.createClass(ns + "FLUAV");
			Property p = mdb.createObjectProperty(ns + "AprOrigine");

			mdb.add(m, p, v);

			// Commit the database transaction
			// tmp.commit();

			System.out.println("Contenu " + tmp.size());
			mdb.write(System.out, "RDF/XML-ABBREV");
			// Close the database connection
			conn.close();
		} catch (Exception e) {
			System.out.println("Exception");
			System.out.println(e);
		}
	}
}
