package um2.websemantique.website.base;

import java.util.ArrayList;
import java.util.List;

import org.apache.tapestry5.annotations.Log;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;

import um2.websemantique.ontoligie.sdb.SDBUtil;

import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;

public abstract class Details {

	@Property
	OntClass			details;

	@Property
	@Persist
	List<OntProperty>	Properties;

	@Property
	OntProperty			p;

	public String getLabel() {
		return p.getLabel ("en");
	}

	public String getComment() {
		return p.getComment ("fr");
	}

	public String getCommentClass() {
		return details.getComment ("en");
	}

	public String getLabelClass() {
		return details.getLabel ("en");
	}

	protected abstract OntClass init();

	@Log
	public void onActivate() {
		if ( Properties == null || details == null ) {
			Properties = new ArrayList<OntProperty> ();

			SDBUtil.openConnection ();

			details = init ();

			ExtendedIterator<OntProperty> lst = details.listDeclaredProperties ();
			while (lst.hasNext ()) {
				OntProperty p = lst.next ();
				Properties.add (p);
				System.out.println (p.getLocalName () + "-" + p.getLabel ("en")
						+ "-" + p.getComment ("fr"));
			}
		}
		System.out.println (Properties.size ());
	}

	
}
