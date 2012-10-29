import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Création de l'ontologie
		OntModel ontologie = ModelFactory
				.createOntologyModel(OntModelSpec.OWL_DL_MEM_RULE_INF);
		String namespace = "http://www.ontologie.fr/monOntologie#";
		ontologie.createOntology(namespace);

		// Création de la classe Voiture
		OntClass voiture = ontologie.createClass(namespace + "Voiture");

		ontologie.write(System.out,"RDF/XML-ABBREV");
	}

}
