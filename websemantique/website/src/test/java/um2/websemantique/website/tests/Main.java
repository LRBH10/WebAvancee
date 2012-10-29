package um2.websemantique.website.tests;

import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.ModelFactory;

public class Main {

	public static void main(String[] args) {
		//Création de l'ontologie
		OntModel ontologie = ModelFactory.createOntologyModel ();
		String namespace = "http://www.ontologie.fr/monOntologie#";
		ontologie.createOntology (namespace);

		
		//Création de la classe Voiture
		OntClass voiture = ontologie.createClass (namespace + "Voiture");
		ontologie.write(System.out);

	}
}
