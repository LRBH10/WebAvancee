package um2.websemantique.ontoligie.sdb;

import com.hp.hpl.jena.sdb.StoreDesc;
import com.hp.hpl.jena.sdb.sql.JDBC;
import com.hp.hpl.jena.sdb.sql.SDBConnection;
import com.hp.hpl.jena.sdb.store.DatabaseType;
import com.hp.hpl.jena.sdb.store.LayoutType;


public class SDBUtil {

	public static SDBConnection getSDBConnection() {

		JDBC.loadDriverMySQL();
		String jdbcURL = "jdbc:mysql://localhost:3306/rdf_base";
		SDBConnection conn = new SDBConnection(jdbcURL, "root", "rabah123");
		return conn;

	}

	public static StoreDesc getStoreDesc() {
		StoreDesc storeDesc = new StoreDesc(LayoutType.LayoutTripleNodesHash,
				DatabaseType.MySQL);
		return storeDesc;
	}

}
