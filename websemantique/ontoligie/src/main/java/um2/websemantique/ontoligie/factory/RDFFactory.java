/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package um2.websemantique.ontoligie.factory;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.util.FileManager;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;
import com.hp.hpl.jena.vocabulary.XSD;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import um2.websemantique.entities.base.Author;
import um2.websemantique.entities.base.Book;
import um2.websemantique.entities.utils.IdentifierBook;
import um2.websemantique.ontoligie.sdb.SDBUtil;

/**
 * The class RDFFactory will generate the ontology of the rdf base. It generate
 * two classes : Book and Author, and their porprieties
 * 
 * @author GoceDelcev
 */
public class RDFFactory {

	private String baseName = "base_rdf";
	private OntModel base;
	private OntClass authorClass;
	private String authorNS = "http://fuck.JENA/author#";
	private OntClass bookClass;
	private String bookNS = "http://fuck.JENA/book#";

	public RDFFactory() {
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

	public OntModel getBase() {
		return this.base;
	}

	/**
	 * This method creates the Author Class with it's properties
	 */
	private void createAuhorClass() {

		this.authorClass = this.base.createClass(authorNS + "Author");
		this.authorClass.addLabel("The  Author Class", "en");
		this.authorClass.addComment("The class describint en author", "en");

		addAuthorProperty("link").setDomain(XSD.xstring);
		addAuthorProperty("id").setDomain(XSD.ID);
		addAuthorProperty("name").setDomain(XSD.Name);
		addAuthorProperty("fans_count").setDomain(XSD.xint);
		addAuthorProperty("image_uri").setDomain(XSD.xstring);
		addAuthorProperty("about").setDomain(XSD.xstring);
		addAuthorProperty("works_count").setDomain(XSD.ID);
		addAuthorProperty("gender").setDomain(XSD.xstring);
		addAuthorProperty("home_town").setDomain(XSD.xstring);
		addAuthorProperty("born_at").setDomain(XSD.xstring);
		addAuthorProperty("died_at").setDomain(XSD.xstring);
		addAuthorProperty("id_facebook").setDomain(XSD.ID);
		addAuthorProperty("name_facebook").setDomain(XSD.xstring);
		addAuthorProperty("likes_facebook").setDomain(XSD.xint);
		addAuthorProperty("talking_about_count_facebook").setDomain(XSD.xint);
		addAuthorProperty("link_facebook").setDomain(XSD.xstring);

	}

	private OntProperty addAuthorProperty(String propertyName) {
		OntProperty property = this.base.createOntProperty(this.authorClass
				.getNameSpace() + propertyName);
		property.setDomain(this.authorClass);
		return property;
	}

	private void addPropertyToAuthorInstance(String propertyName,
			Individual instance, String literalValue) {
		Iterator<OntProperty> i = this.authorClass.listDeclaredProperties();
		while (i.hasNext()) {
			OntProperty property = i.next();
			if (property.getLocalName().equals(propertyName)) {
				instance.addProperty(property, literalValue);
			}
		}
	}

	public void generateRDFAuthorInstance(Author author) {

		Individual instance = this.authorClass.createIndividual(author
				.getLinkAbout());
		if (!author.isAuthorGoodReadNull()) {
			System.out.println("Adding instance properties !!!");
			addPropertyToAuthorInstance("id", instance, author.getGoodRead()
					.getId());
			addPropertyToAuthorInstance("link", instance, author.getGoodRead()
					.getLink());
			addPropertyToAuthorInstance("name", instance, author.getGoodRead()
					.getName());
			addPropertyToAuthorInstance("fans_count", instance, author
					.getGoodRead().getFansCount());
			addPropertyToAuthorInstance("image_uri", instance, author
					.getGoodRead().getImageUrl());
			addPropertyToAuthorInstance("about", instance, author.getGoodRead()
					.getAbout());
			addPropertyToAuthorInstance("works_count", instance, author
					.getGoodRead().getWorksCount());
			addPropertyToAuthorInstance("gender", instance, author
					.getGoodRead().getGender());
			addPropertyToAuthorInstance("home_town", instance, author
					.getGoodRead().getHometown());
			addPropertyToAuthorInstance("born_at", instance, author
					.getGoodRead().getBornAt());
			addPropertyToAuthorInstance("died_at", instance, author
					.getGoodRead().getDiedAt());

		}
		if (!author.isAuthorFacebookNull()) {
			addPropertyToAuthorInstance("id_facebook", instance, author
					.getFacebook().getId());
			addPropertyToAuthorInstance("likes_facebook", instance, new String(
					"" + author.getFacebook().getLikes()));
			addPropertyToAuthorInstance("link_facebook", instance, author
					.getFacebook().getLink());
			addPropertyToAuthorInstance("talking_about_count_facebook",
					instance, new String(""
							+ author.getFacebook().getTalkingAboutCount()));
			addPropertyToAuthorInstance("name_facebook", instance, author
					.getFacebook().getName());
		}

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

		addBookProperty("id").setDomain(XSD.ID);
		addBookProperty("self_link").setDomain(XSD.xstring);
		addBookProperty("title").setDomain(XSD.xstring);
		addBookProperty("description").setDomain(XSD.xstring);
		addBookProperty("publisher").setDomain(XSD.Name);
		addBookProperty("published_date").setDomain(XSD.date);
		addBookProperty("page_count").setDomain(XSD.xint);
		addBookProperty("author").setDomain(XSD.Name);
		addBookProperty("link_author").setDomain(this.authorClass);
		addBookProperty("isbn_10").setDomain(XSD.xstring);
		addBookProperty("isbn_13").setDomain(XSD.xstring);
		addBookProperty("identifier").setDomain(XSD.xstring);
		addBookProperty("image").setDomain(XSD.xstring);
		addBookProperty("language").setDomain(XSD.language);
		addBookProperty("preview_link").setDomain(XSD.xstring);
		addBookProperty("info_link").setDomain(XSD.xstring);
		addBookProperty("canonical_volume_link").setDomain(XSD.xstring);
		addBookProperty("category").setDomain(XSD.xstring);
		addBookProperty("average_raiting").setDomain(XSD.xfloat);
		addBookProperty("raiting_count").setDomain(XSD.xint);
		addBookProperty("country").setDomain(XSD.xstring);
		addBookProperty("saleability").setDomain(XSD.xstring);
		addBookProperty("is_ebook").setDomain(XSD.xstring);
		addBookProperty("price").setDomain(XSD.xfloat);
		addBookProperty("price_symbol").setDomain(XSD.xstring);
		addBookProperty("buy_link").setDomain(XSD.xstring);
		addBookProperty("viewability").setDomain(XSD.xstring);
		addBookProperty("public_domain").setDomain(XSD.xstring);
		addBookProperty("epub_link").setDomain(XSD.xstring);
		addBookProperty("pdf_link").setDomain(XSD.xstring);
		addBookProperty("web_reader_link").setDomain(XSD.xstring);
		addBookProperty("text_snippet").setDomain(XSD.xstring);
		addBookProperty("currency_code").setDomain(XSD.xstring);

	}

	private OntProperty addBookProperty(String propertyName) {
		OntProperty property = this.base.createOntProperty(this.bookClass
				.getNameSpace() + propertyName);
		property.setDomain(this.bookClass);
		return property;
	}

	/**
	 * The method that actually add the properties into the individual, by
	 * getting the right propriety from the OWL Class Book and adding a literal
	 * value to that propriety
	 * 
	 * @param propertyName
	 *            The name of the OWL Book Propriety
	 * @param instance
	 *            The individual want to add into the RDF base
	 * @param literalValue
	 *            The Value added into the Propriety
	 */
	private void addPropertyToBookInstance(String propertyName,
			Individual instance, String literalValue) {
		Iterator<OntProperty> i = this.bookClass.listDeclaredProperties();
		while (i.hasNext()) {
			OntProperty property = i.next();
			if (property.getLocalName().equals(propertyName)) {
				instance.addProperty(property, literalValue);
			}
		}
	}

	/**
	 * The method generating the Industrial Identifiers properties into the
	 * individual thats stored into the RDF base
	 * 
	 * @param identifierList
	 *            List of identifiers ( isbn 10, isnb 13 of other industrial
	 *            identifiers
	 * @param instance
	 *            The individual we want to store into the RDF base
	 */
	private void addIdentifierToBook(ArrayList<IdentifierBook> identifierList,
			Individual instance) {
		for (IdentifierBook item : identifierList) {
			if (item.getType().equals(IdentifierBook.ISBN10))
				addPropertyToBookInstance("isbn_10", instance,
						item.getContaints());
			if (item.getType().equals(IdentifierBook.ISBN13))
				addPropertyToBookInstance("isbn_13", instance,
						item.getContaints());
			if (item.getType().equals(IdentifierBook.OTHER))
				addPropertyToBookInstance("identifier", instance,
						item.getContaints());
		}
	}

	/**
	 * This method generates the category properties into the book individual
	 * 
	 * @param listCategory
	 *            The list of categories
	 * @param instance
	 *            The individual we want to store into the RDF base
	 */
	private void addCategoryToBook(ArrayList<String> listCategory,
			Individual instance) {

		for (String item : listCategory) {
			addPropertyToBookInstance("category", instance, item);
		}
	}

	private void addLinkAuthorToBook(ArrayList<String> listsLinkAuthor,
			Individual instance) {

		for (String item : listsLinkAuthor) {
			addPropertyToBookInstance("link_author", instance, item);
		}
	}

	/**
	 * This is the method that generate the rdf individuals into the base RDF
	 * from a book instance
	 * 
	 * @param book
	 *            This is the result of google-book API store into an instance
	 *            of Book
	 */
	public void generateRDFBookInstance(Book book) {

		Individual instance = this.bookClass.createIndividual(book
				.getSelfLink());

		addPropertyToBookInstance("id", instance, book.getId());
		addPropertyToBookInstance("title", instance, book.getTitle());
		addPropertyToBookInstance("description", instance,
				book.getDescription());
		addPropertyToBookInstance("publisher", instance, book.getPublisher());
		addPropertyToBookInstance("published_date", instance,
				book.getPublishedDate());
		addPropertyToBookInstance("page_count", instance, book.getPageCount());
		addPropertyToBookInstance("image", instance, book.getThumbnail());
		addPropertyToBookInstance("language", instance, book.getLanguage());
		addPropertyToBookInstance("preview_link", instance,
				book.getPreviewLink());
		addPropertyToBookInstance("info_link", instance, book.getInfoLink());
		addPropertyToBookInstance("canonical_volume_link", instance,
				book.getCanonicalVolumeLink());
		addPropertyToBookInstance("average_raiting", instance,
				book.getAverageRating());
		addPropertyToBookInstance("raiting_count", instance,
				book.getRatingsCount());
		addPropertyToBookInstance("country", instance, book.getCountry());
		addPropertyToBookInstance("saleability", instance,
				book.getSaleability());
		addPropertyToBookInstance("is_ebook", instance, book.isEbook());
		addPropertyToBookInstance("price", instance, book.getPrice());
		addPropertyToBookInstance("price_symbol", instance,
				book.getPriceSymbol());
		addPropertyToBookInstance("buy_link", instance, book.getBuyLink());
		addPropertyToBookInstance("viewability", instance,
				book.getViewability());
		addPropertyToBookInstance("public_domain", instance,
				book.getPublicDomain());
		addPropertyToBookInstance("epub_link", instance, book.getEpubLink());
		addPropertyToBookInstance("pdf_link", instance, book.getPdfLink());
		addPropertyToBookInstance("web_reader_link", instance,
				book.getWebReaderLink());
		addPropertyToBookInstance("text_snippet", instance,
				book.getTextSnippet());
		addPropertyToBookInstance("currency_code", instance,
				book.getCurrencyCode());

		addIdentifierToBook(book.getIndustryIdentifiers(), instance);
		addCategoryToBook(book.getCategories(), instance);
		addLinkAuthorToBook(book.getAuthorslink(), instance);
		addAuthorToBook(book.getAuthors(), instance);
	}

	private void addAuthorToBook(ArrayList<String> authors, Individual instance) {
		for (String item : authors) {
			addPropertyToBookInstance("author", instance, item);
		}
	}
}