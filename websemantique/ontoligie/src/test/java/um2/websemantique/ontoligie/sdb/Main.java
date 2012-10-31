package um2.websemantique.ontoligie.sdb;

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
import um2.websemantique.entities.base.Author;
import um2.websemantique.entities.utils.GetterBookAuthor;
import um2.websemantique.entities.utils.SearchType;
import um2.websemantique.ontoligie.factory.RDFFactory;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
                    SDBUtil.openConnection();
                    RDFFactory factory = new RDFFactory();
                    
                     System.out.println("AFTER factory construcs");
                    GetterBookAuthor get = new GetterBookAuthor();
                    get.find("Yasmina Khadra",SearchType.ANY,10);
                    System.out.println("before for!!!");
                    for (Author author : get.getAuthors()) {
                        if(author != null )
                            System.out.println(author.getGoodRead().getName());
			//factory.generateRDFAuthorInstance(author);
                    }
                    System.out.println("Not DONE !!!");
                    factory.getBase().write(System.out, "RDF/XML-ABBREV");
                    System.out.println("DONE!!!");
                    SDBUtil.closeConnection();
                    /*
			SDBUtil.openConnection().cleanDB();		
			
			OntModel mdb = SDBUtil.createOrGetModel("baseName");
			String ns = "http://localhost:8080/about/book#";

			OntClass c = mdb.createClass(ns+"simple");
			OntProperty p =mdb.createOntProperty(ns+"has");
			p.setDomain(c);
			p.setRange(XSD.Name);
			
			
			Individual s1  = c.createIndividual(ns+"s1");
			s1.addProperty(p, "alpha3");
                        s1.addProperty(p, "alpha4");
			
			Individual s2  = c.createIndividual(ns+"s2");
			Individual s3  = c.createIndividual(ns+"s4");
			
			System.out.println("Contenu " + mdb.size());
			mdb.write(System.out, "RDF/XML-ABBREV");
			// Close the database connection
			SDBUtil.closeConnection();
                        */
		} catch (Exception e) {
			System.out.println("Exception");
			System.out.println(e);
		}
	}
}
