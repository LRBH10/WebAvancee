/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package um2.websemantique.ontoligie.factory;

import um2.websemantique.ontoligie.sdb.SDBUtil;
import um2.websemantique.ontoligie.utils.VocabularyAutheur;
import um2.websemantique.ontoligie.utils.VocabularyBook;

import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.sparql.vocabulary.FOAF;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;
import com.hp.hpl.jena.vocabulary.XSD;

/**
 * A Singleton class that generate our ontology i.e. all classes with their
 * properties
 * 
 * @author GoceDelcev
 */
public class RDFOntology {

	private static RDFOntology	singleton	= null;

	public synchronized static RDFOntology getInstanceRDFOntology() {
		if ( RDFOntology.singleton == null ) {
			RDFOntology.singleton = new RDFOntology ();
		}
		return RDFOntology.singleton;
	}

	private String		baseName	= "base_rdf";
	private OntModel	base;
	private OntClass	authorClass;
	private String		authorNS	= "http://localhost:8080/website/about/Author#";
	private OntClass	bookClass;

	private String		bookNS		= "http://localhost:8080/website/about/Book#";

	private RDFOntology() {
		this.base = SDBUtil.createOrGetModel (this.baseName);
		this.initFactoryClass ();
	}

	private OntProperty addAuthorProperty(String propertyName, String comment, String label, Resource type) {
		OntProperty property = this.base.createOntProperty (this.authorClass.getNameSpace ()
				+ propertyName);
		property.setDomain (this.authorClass);
		property.setRange (type);
		property.addComment (comment, "fr");
		property.setLabel (label, "en");

		return property;
	}

	private OntProperty addBookAutheurLinkProperty(String propertyName) {
		OntProperty property = this.base.createOntProperty (this.bookClass.getNameSpace ()
				+ propertyName);
		property.setDomain (this.bookClass);
		property.setRange (this.authorClass);
		property.addComment ("Lien vers Auteur ", "fr");
		property.addLabel ("Author Link", "en");
		property.addLabel ("Lien d'Auteur", "fr");

		return property;
	}

	private OntProperty addBookProperty(String propertyName, String comment, String label, Resource type) {
		OntProperty property = this.base.createOntProperty (this.bookClass.getNameSpace ()
				+ propertyName);
		property.setDomain (this.bookClass);
		property.setRange (type);
		property.addComment (comment, "fr");
		property.setLabel (label, "en");
		return property;
	}

	/**
	 * This method creates the Author Class with it's properties
	 */
	private void createAuhorClass() {

		this.authorClass = this.base.createClass (authorNS + "Author");
		this.authorClass.addLabel ("The  Author Class", "en");
		this.authorClass.addComment ("The class describint en author", "en");
		this.authorClass.addComment ("La description de la class Auteur", "fr");


		addAuthorProperty (VocabularyAutheur.key, "Le nom de l'auteur extrait de book", "Key", XSD.Name);
		
		addAuthorProperty (VocabularyAutheur.SameAs, "Le lien vers Dbpedia (InterConnection)", "Same As", XSD.xstring);
		addAuthorProperty (VocabularyAutheur.facebookIdAutheur, "ID de l'auteur attribuer par le web service http://graph.facebook.com", "Facebook id auteur", XSD.ID);
		addAuthorProperty (VocabularyAutheur.facebookLikes, "Nombre de Likes de la meilleur page de Facebook", "Facebook Likes", XSD.integer);
		addAuthorProperty (VocabularyAutheur.facebookLink, "Lien vers la page de Facebook", "Facebook Link", XSD.xstring);
		addAuthorProperty (VocabularyAutheur.facebookName, "Nom de la page de Facebook", "Facebook page name", XSD.Name);
		addAuthorProperty (VocabularyAutheur.facebookTalkingAboutCount, "Facebook talking count", "Nombre de personne qui ont commenté ou contribué dans la page Facebook", XSD.integer);

		addAuthorProperty (VocabularyAutheur.goodreadAbout, "Biographie de l'auteur returner par le web service GoodRead", "GoodRead Biogrphie", XSD.xstring);
		addAuthorProperty (VocabularyAutheur.goodreadBornAt, "La date de naissance de l'auteur ", "GoodRead Date Naissance", XSD.date);
		addAuthorProperty (VocabularyAutheur.goodreadDiedAt, "La date de mort de l'auteur", "GoodRead Date Mort", XSD.date);
		addAuthorProperty (VocabularyAutheur.goodreadFansCount, "Nombre des fans sur GoodRead pour un auteur", "GoodRead Fans Count", XSD.integer);
		addAuthorProperty (VocabularyAutheur.goodreadHomeTown, "La ville de naissance de l'auteur", "GoodRead HomeTown", XSD.xstring);
		addAuthorProperty (VocabularyAutheur.goodreadImageUri, "Le lien de l'image de l'auteur", "GoodRead Image", XSD.xstring);
		addAuthorProperty (VocabularyAutheur.goodreadLink, "Le lien vers la page de l'auteur sur GoodRead.com", "GoodRead Link", XSD.xstring);
		addAuthorProperty (VocabularyAutheur.goodreadSex, "Le sexe de l'auteur", "GoodRead Gender", XSD.xstring);
		addAuthorProperty (VocabularyAutheur.goodreadWorksCount, "Le Nombre des traveaux dans GoodRead", "GoodRead Works Count", XSD.integer);
		addAuthorProperty (VocabularyAutheur.googreadIdAutheur, "ID de l'auteur attribuer par le site web goodRead", "GoodRead ID", XSD.ID);
		addAuthorProperty (VocabularyAutheur.googreadName, "Le nom de l'auteur", "GoodRead name", FOAF.name);

	}

	/**
	 * This method creates the Author Class with it's properties
	 */
	private void createBookClass() {

		this.bookClass = this.base.createClass (bookNS + "Book");

		this.bookClass.addComment ("This is the class we use to create all book individuals", "en");
		this.bookClass.addComment ("cette class contient tout les attributes de livre", "fr");

		this.bookClass.addLabel ("The Book Class", "en");

		addAuthorProperty (VocabularyBook.SameAs, "Le lien vers Dbpedia (InterConnection)", "Same As", XSD.xstring);
		addBookProperty (VocabularyBook.author, "Nom de l'auteur de livre", "Nom de l'Auteur", FOAF.Person);
		addBookProperty (VocabularyBook.averageRaiting, "La moyenne des votes sur le livre de Google Book", "Note", XSD.xdouble);
		addBookProperty (VocabularyBook.buyLink, "Le lien pour acheter le livre", "Lien d'Achat", XSD.xstring);
		addBookProperty (VocabularyBook.category, "La catégorie de livre (Romance,Science Fictions ...)", "Catégorie", XSD.xstring);
		addBookProperty (VocabularyBook.country, "Le Symbole de  Langue de livre", "Langue Symbol", XSD.xstring);
		addBookProperty (VocabularyBook.currencyCode, "Le code de la monnaie de prix de Livre", "Currency Code", XSD.xstring);
		addBookProperty (VocabularyBook.description, "La description de livre", "Description", XSD.xstring);
		addBookProperty (VocabularyBook.epubLink, "Le lien vers la publicité de livre", "Pub Link", XSD.xstring);
		addBookProperty (VocabularyBook.idBook, "ID attribuer par google Book", "Id Book", XSD.ID);
		addBookProperty (VocabularyBook.identifier, "Autre identifier de book or ISBN10 et ISBN13", "Identifier", XSD.xstring);
		addBookProperty (VocabularyBook.image, "Le lien vers l'image de livre", "Image Book", XSD.xstring);
		addBookProperty (VocabularyBook.infoLink, "Le lien d'information sur le livre", "Info Link", XSD.xstring);
		addBookProperty (VocabularyBook.isEbook, "La disponiblité en Ebook", "EBook", XSD.xstring);
		addBookProperty (VocabularyBook.isbn10, "identifier ISBN10", "ISBN10", XSD.xstring);
		addBookProperty (VocabularyBook.isbn13, "identifier ISBN13", "ISBN13", XSD.xstring);
		addBookProperty (VocabularyBook.jsonLink, "Le lien vers le lien JSON", "JSON Link", XSD.xstring);
		addBookProperty (VocabularyBook.language, "La langue de Livre", "Langue", XSD.xstring);
		addBookProperty (VocabularyBook.pageCount, "Le Nombre de page", "Page Count", XSD.integer);
		addBookProperty (VocabularyBook.pdfLink, "Le lien vers PDF link de livre", "PDF Link", XSD.integer);
		addBookProperty (VocabularyBook.previewLink, "Le lien vers l'explorateur de  livre dans Google Book", "Preview Link", XSD.xstring);
		addBookProperty (VocabularyBook.price, "Le prix de livre", "Price", XSD.xdouble);
		addBookProperty (VocabularyBook.priceSymbol, "Le Symbole de  Langue de livre", "Langue Symbol", XSD.xstring);
		addBookProperty (VocabularyBook.publicDomain, "Domain public", "Public Domain", XSD.xstring);
		addBookProperty (VocabularyBook.publishedDate, "Date de publication de livre", "Published Date", XSD.xstring);
		addBookProperty (VocabularyBook.publisher, "L'organistion de publication", "Publisher", XSD.xstring);
		addBookProperty (VocabularyBook.raitingCount, "Le nombre de vote sur ce livre dans Google Book", "Rating Count", XSD.integer);
		addBookProperty (VocabularyBook.saleability, "La disponiblité de livre en vente", "Saleability", XSD.xboolean);
		addBookProperty (VocabularyBook.selfLink, "Le lien vers la page de livre dans  Google Book", "Self Link", XSD.xstring);
		addBookProperty (VocabularyBook.textSnippet, "Une bref description de livre", "Text Snippet", XSD.xstring);
		addBookProperty (VocabularyBook.title, "Titre de livre ", "Title", XSD.xstring);
		addBookProperty (VocabularyBook.viewability, "type de visibilité de livre", "Viewability", XSD.xstring);
		addBookProperty (VocabularyBook.webReaderLink, "Le lien de lecture en ligne de livre", "Web Reader", XSD.xstring);

		addBookAutheurLinkProperty (VocabularyBook.authorLink);

	}

	public void databaseToString() {
		this.base.write (System.out, "RDF/XML-ABBREV");
	}

	public OntClass getAuthorClass() {
		return authorClass;
	}

	public OntClass getBookClass() {
		return bookClass;
	}

	public OntModel getModel() {
		return this.base;
	}

	/**
	 * This method is used to initialise the bookClass and the authorClass
	 * attributes from the rdf base if they exists. If not it calls methods that
	 * will create the 2 classes and make the init after
	 */
	private void initFactoryClass() {
		ExtendedIterator<OntClass> s = base.listNamedClasses ();
		boolean existBookClass = false;
		boolean existeAuthorClass = false;

		while (s.hasNext ()) {
			OntClass x = s.next ();
			if ( x.getLocalName ().equals ("Author") ) {
				existeAuthorClass = true;
				this.authorClass = x;
			}
			if ( x.getLocalName ().equals ("Book") ) {
				existBookClass = true;
				this.bookClass = x;
			}
		}
		if ( !existeAuthorClass ) {
			System.out.println ("creating author class");
			this.base.setNsPrefix ("Author", this.authorNS);
			this.createAuhorClass ();
		}
		if ( !existBookClass ) {
			System.out.println ("creating book class");
			this.base.setNsPrefix ("Book", this.bookNS);
			this.createBookClass ();
		}
	}
}
