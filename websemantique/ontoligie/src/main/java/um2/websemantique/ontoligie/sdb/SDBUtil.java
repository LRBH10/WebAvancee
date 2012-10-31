package um2.websemantique.ontoligie.sdb;

import com.hp.hpl.jena.db.DBConnection;
import com.hp.hpl.jena.db.IDBConnection;
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
}
