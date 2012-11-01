/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package um2.websemantique.ontoligie.factory;

import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;
import com.hp.hpl.jena.vocabulary.XSD;
import um2.websemantique.ontoligie.sdb.SDBUtil;

/**
 *  A Singleton class that generate our ontology i.e. all classes with their
 * properties
 * @author GoceDelcev
 */
public class RDFOntology {
        private static RDFOntology singleton = null;
        private String baseName = "base_rdf";
	private OntModel base;
	private OntClass authorClass;
	private String authorNS = "http://fuck.JENA/author#";
	private OntClass bookClass;
	private String bookNS = "http://fuck.JENA/book#";
        
        public static RDFOntology getInstanceRDFOntology(){
            if(singleton ==null){
                singleton = new RDFOntology();
            }
            return singleton;
        }

	private RDFOntology() {
		this.base = SDBUtil.createOrGetModel(this.baseName);
		this.initFactoryClass();
	}
        
        /**
	 * This method is used to initialise the bookClass and the authorClass
	 * attributes from the rdf base if they exists. If not it calls methods that
	 * will create the 2 classes and make the init after
	 */
	private void initFactoryClass() {
		ExtendedIterator<OntClass> s = base.listNamedClasses();
		boolean existBookClass = false;
		boolean existeAuthorClass = false;

		while (s.hasNext()) {
			OntClass x = s.next();
			if (x.getLocalName().equals("Author")) {
				existeAuthorClass = true;
				this.authorClass = x;
			}
			if (x.getLocalName().equals("Book")) {
				existBookClass = true;
				this.bookClass = x;
			}
		}
		if (!existeAuthorClass) {
			System.out.println("creating author class");
			this.base.setNsPrefix("Author", this.authorNS);
			this.createAuhorClass();
		}
		if (!existBookClass) {
			System.out.println("creating book class");
			this.base.setNsPrefix("Book", this.bookNS);
			this.createBookClass();
		}
	}

        
        /**
	 * This method creates the Author Class with it's properties
	 */
	private void createAuhorClass() {

		this.authorClass = this.base.createClass(authorNS + "Author");
		this.authorClass.addLabel("The  Author Class", "en");
		this.authorClass.addComment("The class describint en author", "en");

		addAuthorProperty("link").setRange(XSD.xstring);
		addAuthorProperty("id").setRange(XSD.ID);
		addAuthorProperty("name").setRange(XSD.Name);
		addAuthorProperty("fans_count").setRange(XSD.xint);
		addAuthorProperty("image_uri").setRange(XSD.xstring);
		addAuthorProperty("about").setRange(XSD.xstring);
		addAuthorProperty("works_count").setRange(XSD.ID);
		addAuthorProperty("gender").setRange(XSD.xstring);
		addAuthorProperty("home_town").setRange(XSD.xstring);
		addAuthorProperty("born_at").setRange(XSD.xstring);
		addAuthorProperty("died_at").setRange(XSD.xstring);
		addAuthorProperty("id_facebook").setRange(XSD.ID);
		addAuthorProperty("name_facebook").setRange(XSD.xstring);
		addAuthorProperty("likes_facebook").setRange(XSD.xint);
		addAuthorProperty("talking_about_count_facebook").setRange(XSD.xint);
		addAuthorProperty("link_facebook").setRange(XSD.xstring);

	}

	private OntProperty addAuthorProperty(String propertyName) {
		OntProperty property = this.base.createOntProperty(this.authorClass
				.getNameSpace() + propertyName);
		property.setRange(this.authorClass);
		return property;
	}
        
        /**
	 * This method creates the Author Class with it's properties
	 */
	private void createBookClass() {

		this.bookClass = this.base.createClass(bookNS + "Book");

		this.bookClass
				.addComment(
						"This is the class we use to create all book individuals",
						"en");
		this.bookClass.addLabel("The Book Class", "en");

		addBookProperty("id").setRange(XSD.ID);
		addBookProperty("self_link").setRange(XSD.xstring);
		addBookProperty("title").setRange(XSD.xstring);
		addBookProperty("description").setRange(XSD.xstring);
		addBookProperty("publisher").setRange(XSD.Name);
		addBookProperty("published_date").setRange(XSD.date);
		addBookProperty("page_count").setRange(XSD.xint);
		addBookProperty("author").setRange(XSD.Name);
		addBookProperty("link_author").setRange(this.authorClass);
		addBookProperty("isbn_10").setRange(XSD.xstring);
		addBookProperty("isbn_13").setRange(XSD.xstring);
		addBookProperty("identifier").setRange(XSD.xstring);
		addBookProperty("image").setRange(XSD.xstring);
		addBookProperty("language").setRange(XSD.language);
		addBookProperty("preview_link").setRange(XSD.xstring);
		addBookProperty("info_link").setRange(XSD.xstring);
		addBookProperty("canonical_volume_link").setRange(XSD.xstring);
		addBookProperty("category").setRange(XSD.xstring);
		addBookProperty("average_raiting").setRange(XSD.xfloat);
		addBookProperty("raiting_count").setRange(XSD.xint);
		addBookProperty("country").setRange(XSD.xstring);
		addBookProperty("saleability").setRange(XSD.xstring);
		addBookProperty("is_ebook").setRange(XSD.xstring);
		addBookProperty("price").setRange(XSD.xfloat);
		addBookProperty("price_symbol").setRange(XSD.xstring);
		addBookProperty("buy_link").setRange(XSD.xstring);
		addBookProperty("viewability").setRange(XSD.xstring);
		addBookProperty("public_domain").setRange(XSD.xstring);
		addBookProperty("epub_link").setRange(XSD.xstring);
		addBookProperty("pdf_link").setRange(XSD.xstring);
		addBookProperty("web_reader_link").setRange(XSD.xstring);
		addBookProperty("text_snippet").setRange(XSD.xstring);
		addBookProperty("currency_code").setRange(XSD.xstring);

	}

	private OntProperty addBookProperty(String propertyName) {
		OntProperty property = this.base.createOntProperty(this.bookClass
				.getNameSpace() + propertyName);
		property.setRange(this.bookClass);
		return property;
	}
        
        
        public OntModel getModel() {
		return this.base;
	}
        
        public OntClass getBookClass(){
            return bookClass;
        }
        
        public OntClass getAuthorClass(){
            return authorClass;
        }
        
        public void databaseToString(){
            this.base.write(System.out, "RDF/XML-ABBREV");
        }
}
