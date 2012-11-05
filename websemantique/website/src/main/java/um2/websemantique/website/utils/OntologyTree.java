package um2.websemantique.website.utils;

import java.util.List;
import java.util.UUID;

import org.apache.tapestry5.ValueEncoder;
import org.apache.tapestry5.beaneditor.NonVisual;
import org.apache.tapestry5.ioc.internal.util.CollectionFactory;
import org.apache.tapestry5.tree.DefaultTreeModel;
import org.apache.tapestry5.tree.TreeModel;
import org.apache.tapestry5.tree.TreeModelAdapter;

import um2.websemantique.ontoligie.factory.RDFOntology;

import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;

public class OntologyTree {

	@NonVisual
	private long					id;
	private String					name;
	private OntClass				book;
	private OntClass				author;
	public final String				uuid		= UUID.randomUUID ().toString ();

	public final List<OntologyTree>	children	= CollectionFactory.newList ();

	public OntologyTree(String nom) {
		name = nom;
	}

	public OntologyTree() {
		this (null);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String firstName) {
		this.name = firstName;
	}

	public static final OntologyTree	ROOT	= new OntologyTree ("<root>");

	public OntologyTree addChildrenNamed(String... names) {

		for ( String name : names ) {
			children.add (new OntologyTree (name));
		}
		return this;
	}

	public OntologyTree addChild(OntologyTree BookTree) {
		children.add (BookTree);
		return this;
	}

	public OntologyTree seek(String uuid) {
		if ( this.uuid.equals (uuid) ) return this;
		for ( OntologyTree child : children ) {
			OntologyTree match = child.seek (uuid);
			if ( match != null ) return match;
		}
		return null;
	}

	static {
	
		ROOT.author = RDFOntology.getInstanceRDFOntology ().getAuthorClass ();
		ROOT.book = RDFOntology.getInstanceRDFOntology ().getBookClass ();
		create ();
	}

	private static void create() {
		ExtendedIterator<OntProperty> lst= ROOT.author.listDeclaredProperties ();
		
		while(lst.hasNext ()){
			OntProperty tmp = lst.next ();
			
		}
	}
	public static TreeModel<OntologyTree> createTreeModel() {

		ValueEncoder encoder = new ValueEncoder<OntologyTree> () {

			public String toClient(OntologyTree arg0) {
				return arg0.uuid;
			}

			public OntologyTree toValue(String arg0) {
				return OntologyTree.ROOT.seek (arg0);
			}
		};

		TreeModelAdapter<OntologyTree> adapter = new TreeModelAdapter<OntologyTree> () {

			public List<OntologyTree> getChildren(OntologyTree arg0) {
				return arg0.children;
			}

			public boolean hasChildren(OntologyTree arg0) {
				return !arg0.children.isEmpty ();
			}

			public boolean isLeaf(OntologyTree arg0) {
				return arg0.children.isEmpty ();
			}

			@Override
			public String getLabel(OntologyTree arg0) {
				// TODO Auto-generated method stub
				return null;
			}

		};

		return new DefaultTreeModel<OntologyTree> (encoder, adapter,
				OntologyTree.ROOT.children);

	}
}
