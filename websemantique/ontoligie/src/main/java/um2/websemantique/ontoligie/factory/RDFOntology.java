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
import um2.websemantique.ontoligie.utils.VocabularyAutheur;
import um2.websemantique.ontoligie.utils.VocabularyBook;

/**
 * A Singleton class that generate our ontology i.e. all classes with their
 * properties
 * 
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

	public synchronized static RDFOntology getInstanceRDFOntology() {
		if (singleton == null) {
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

		addAuthorProperty(VocabularyAutheur.facebookIdAutheur);
		addAuthorProperty(VocabularyAutheur.facebookLikes);
		addAuthorProperty(VocabularyAutheur.facebookLink);
		addAuthorProperty(VocabularyAutheur.facebookName);
		addAuthorProperty(VocabularyAutheur.facebookTalkingAboutCount);
                
		addAuthorProperty(VocabularyAutheur.goodreadAbout);
		addAuthorProperty(VocabularyAutheur.goodreadBornAt);
		addAuthorProperty(VocabularyAutheur.goodreadDiedAt);
		addAuthorProperty(VocabularyAutheur.goodreadFansCount);
		addAuthorProperty(VocabularyAutheur.goodreadHomeTown);
		addAuthorProperty(VocabularyAutheur.goodreadImageUri);
		addAuthorProperty(VocabularyAutheur.goodreadLink);
		addAuthorProperty(VocabularyAutheur.goodreadSex);
		addAuthorProperty(VocabularyAutheur.goodreadWorksCount);
		addAuthorProperty(VocabularyAutheur.googreadIdAutheur);
		addAuthorProperty(VocabularyAutheur.googreadName);

	}

	private OntProperty addAuthorProperty(String propertyName) {
		OntProperty property = this.base.createOntProperty(this.authorClass
				.getNameSpace() + propertyName);
		property.setDomain(this.authorClass);
                property.setRange(XSD.xstring);
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

		addBookProperty(VocabularyBook.author);
		addBookProperty(VocabularyBook.averageRaiting);
		addBookProperty(VocabularyBook.buyLink);
		addBookProperty(VocabularyBook.category);
		addBookProperty(VocabularyBook.country);
		addBookProperty(VocabularyBook.currencyCode);
		addBookProperty(VocabularyBook.description);
		addBookProperty(VocabularyBook.epubLink);
		addBookProperty(VocabularyBook.idBook);
		addBookProperty(VocabularyBook.identifier);
		addBookProperty(VocabularyBook.image);
		addBookProperty(VocabularyBook.infoLink);
		addBookProperty(VocabularyBook.isEbook);
		addBookProperty(VocabularyBook.isbn10);
		addBookProperty(VocabularyBook.isbn13);
		addBookProperty(VocabularyBook.jsonLink);
		addBookProperty(VocabularyBook.language);
		addBookProperty(VocabularyBook.pageCount);
		addBookProperty(VocabularyBook.pdfLink);
		addBookProperty(VocabularyBook.previewLink);
		addBookProperty(VocabularyBook.price);
		addBookProperty(VocabularyBook.priceSymbol);
		addBookProperty(VocabularyBook.publicDomain);
		addBookProperty(VocabularyBook.publishedDate);
		addBookProperty(VocabularyBook.publisher);
		addBookProperty(VocabularyBook.raitingCount);
		addBookProperty(VocabularyBook.saleability);
		addBookProperty(VocabularyBook.selfLink);
		addBookProperty(VocabularyBook.textSnippet);
		addBookProperty(VocabularyBook.title);
		addBookProperty(VocabularyBook.viewability);
		addBookProperty(VocabularyBook.webReaderLink);
                
                addBookAutheurLinkProperty(VocabularyBook.authorLink);

	}
        
        private OntProperty addBookAutheurLinkProperty(String propertyName){
                OntProperty property = this.base.createOntProperty(this.bookClass
				.getNameSpace() + propertyName);
                property.setDomain(this.bookClass);
                property.setRange(this.authorClass);
                return property;
        }
	private OntProperty addBookProperty(String propertyName) {
		OntProperty property = this.base.createOntProperty(this.bookClass
				.getNameSpace() + propertyName);
		property.setDomain(this.bookClass);
                property.setRange(XSD.xstring);
		return property;
	}

	public OntModel getModel() {
		return this.base;
	}

	public OntClass getBookClass() {
		return bookClass;
	}

	public OntClass getAuthorClass() {
		return authorClass;
	}

	public void databaseToString() {
		this.base.write(System.out, "RDF/XML-ABBREV");
	}
}
