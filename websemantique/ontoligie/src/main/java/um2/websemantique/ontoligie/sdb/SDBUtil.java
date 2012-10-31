package um2.websemantique.ontoligie.sdb;

import com.hp.hpl.jena.db.DBConnection;
import com.hp.hpl.jena.db.IDBConnection;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.ModelMaker;
import com.hp.hpl.jena.sdb.sql.JDBC;
import com.hp.hpl.jena.sdb.sql.SDBConnection;

public class SDBUtil {

	public static SDBConnection getSDBConnection() {

		JDBC.loadDriverMySQL();
		String jdbcURL = "jdbc:mysql://localhost:3306/rdf_base";
		SDBConnection conn = new SDBConnection(jdbcURL, "root", "rabah123");
		return conn;
	}

	public static IDBConnection getIDBConnection() {

		JDBC.loadDriverMySQL();
		String jdbcURL = "jdbc:mysql://localhost:3306/rdf_base";

		IDBConnection conn = new DBConnection(jdbcURL, "root", "rabah123",
				"MySQL");

		return conn;
	}

	public static OntModel createOrGetModel(String modelname) {
		IDBConnection conn = SDBUtil.getIDBConnection();

		ModelMaker maker = ModelFactory.createModelRDBMaker(conn);
		Model tmp = null;
		if (conn.containsModel(modelname)) {
			System.out.println("Opening existing model :" + modelname);
			tmp = maker.openModel(modelname, true);
		} else {
			System.out.println("Creating new model :" + modelname);
			tmp = maker.createModel(modelname, true);
		}
		OntModel mdb = ModelFactory.createOntologyModel(
				OntModelSpec.OWL_DL_MEM, tmp);

		return mdb;
	}
}
