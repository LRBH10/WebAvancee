package um2.websemantique.website.pages.about;

import com.hp.hpl.jena.ontology.OntClass;

import um2.websemantique.ontoligie.factory.RDFOntology;
import um2.websemantique.website.base.Details;


public class Author extends Details{

	@Override
	protected OntClass init() {
		return RDFOntology.getInstanceRDFOntology ().getAuthorClass ();
	}

	
}
