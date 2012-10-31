package um2.websemantique.ontoligie.sdb;

import java.sql.SQLException;

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

	private static IDBConnection conn = null;

	/**
	 * for openning Connection
	 */
	public static IDBConnection openConnection() {

		JDBC.loadDriverMySQL();
		String jdbcURL = "jdbc:mysql://localhost:3306/rdf_base";

		conn = new DBConnection(jdbcURL, "root", "rabah123", "MySQL");

		return conn;
	}

	/**
	 * closing connection
	 */
	public static void closeConnection() {
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 *  create a new {@link OntModel} or getting an existing {@link OntModel}
	 * @param modelname the name to save the Model
	 * @return {@link OntModel}
	 */
	public static OntModel createOrGetModel(String modelname) {

		ModelMaker maker = ModelFactory.createModelRDBMaker(conn);
		Model tmp = null;
		if (conn.containsModel(modelname)) {
			//System.out.println("Opening existing model :" + modelname);
			tmp = maker.openModel(modelname, true);
		} else {
			//System.out.println("Creating new model :" + modelname);
			tmp = maker.createModel(modelname, true);
		}
		OntModel mdb = ModelFactory.createOntologyModel(
				OntModelSpec.OWL_MEM, tmp);

		return mdb;
	}
	
	
}
