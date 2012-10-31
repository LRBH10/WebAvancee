import um2.websemantique.ontoligie.sdb.SDBUtil;

import com.hp.hpl.jena.db.DBConnection;
import com.hp.hpl.jena.db.IDBConnection;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.ontology.Ontology;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.ModelMaker;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.XSD;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {

			SDBUtil.openConnection();
			
			
			OntModel mdb = SDBUtil.createOrGetModel("books");
			String ns = "http://localhost:8080/about/book#";
			Ontology ont = mdb.createOntology(ns+"book");
			mdb.setNsPrefix("Book", ns);

			OntClass c = mdb.createClass(ns+"simple");
			OntProperty p =mdb.createOntProperty(ns+"has");
			p.setDomain(c);
			p.setRange(XSD.Name);
			
			
			Individual s1  = c.createIndividual(ns+"s1");
			s1.addProperty(p, "alpha");
			
			Individual s2  = c.createIndividual(ns+"s2");
			Individual s3  = c.createIndividual(ns+"s3");
			
			System.out.println("Contenu " + mdb.size());
			mdb.write(System.out, "RDF/XML-ABBREV");
			// Close the database connection
			SDBUtil.closeConnection();

		} catch (Exception e) {
			System.out.println("Exception");
			System.out.println(e);
		}
	}
}
