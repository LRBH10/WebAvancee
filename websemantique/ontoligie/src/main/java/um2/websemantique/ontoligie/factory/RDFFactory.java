/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package um2.websemantique.ontoligie.factory;

import java.util.ArrayList;
import java.util.Iterator;

import um2.websemantique.entities.base.Author;
import um2.websemantique.entities.base.Book;
import um2.websemantique.entities.utils.IdentifierBook;
import um2.websemantique.ontoligie.utils.VocabularyAutheur;
import um2.websemantique.ontoligie.utils.VocabularyBook;
import um2.websemantique.ontologie.interconnection.DbpediaConnection;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.rdf.model.Resource;

/**
 * The class RDFFactory will generate the ontology of the rdf base. It generate
 * two classes : Book and Author, and their porprieties
 * 
 * @author GoceDelcev
 */
public class RDFFactory {

	private static void addAuthorToBook(ArrayList<String> authors, Individual instance) {
		for ( String item : authors ) {
			RDFFactory.addPropertyToBookInstance (VocabularyBook.author, instance, item);
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
	private static void addCategoryToBook(ArrayList<String> listCategory, Individual instance) {

		for ( String item : listCategory ) {
			RDFFactory.addPropertyToBookInstance (VocabularyBook.category, instance, item);
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
	private static void addIdentifierToBook(ArrayList<IdentifierBook> identifierList, Individual instance) {
		for ( IdentifierBook item : identifierList ) {
			if ( item.getType ().equals (IdentifierBook.ISBN10) ) {
				RDFFactory.addPropertyToBookInstance (VocabularyBook.isbn10, instance, item.getContaints ());
			}
			if ( item.getType ().equals (IdentifierBook.ISBN13) ) {
				RDFFactory.addPropertyToBookInstance (VocabularyBook.isbn13, instance, item.getContaints ());
			}
			if ( item.getType ().equals (IdentifierBook.OTHER) ) {
				RDFFactory.addPropertyToBookInstance (VocabularyBook.identifier, instance, item.getContaints ());
			}
		}
	}

	private static void addLinkAuthorToBook(ArrayList<String> listsLinkAuthor, Individual instance) {

		for ( String item : listsLinkAuthor ) {
			RDFFactory.addPropertyToBookInstance (VocabularyBook.authorLink, instance, item);
		}
	}

	private static void addPropertyToAuthorInstance(String propertyName, Individual instance, String literalValue) {
		if ( literalValue != null ) {
			OntClass authorClass = RDFOntology.getInstanceRDFOntology ().getAuthorClass ();
			Iterator<OntProperty> i = authorClass.listDeclaredProperties ();
			while (i.hasNext ()) {
				OntProperty property = i.next ();
				if ( property.getLocalName ().equals (propertyName) ) {
					instance.addProperty (property, literalValue);
				}
			}
		}
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
	private static void addPropertyToBookInstance(String propertyName, Individual instance, String literalValue) {
		OntClass bookClass = RDFOntology.getInstanceRDFOntology ().getBookClass ();
		Iterator<OntProperty> i = bookClass.listDeclaredProperties ();
		if ( literalValue != null ) {
			while (i.hasNext ()) {
				OntProperty property = i.next ();
				if ( property.getLocalName ().equals (propertyName) ) {
					instance.addProperty (property, literalValue);
				}
			}
		}
	}
        
        /**
	 * This is the method that generate the rdf individuals into the base RDF
	 * from a author instance and interlink the instance with resource from
         * dbpedia if one exist
	 * 
	 * @param author  This is the result of facebook and goodread API stored into 
         * an instance of Book
	 */
	public static void generateRDFAuthorInstance(Author author) {

		OntClass authorClass = RDFOntology.getInstanceRDFOntology ().getAuthorClass ();
		Individual instance = authorClass.createIndividual (author.getLinkAbout ());
         
		RDFFactory.addPropertyToAuthorInstance (VocabularyAutheur.SameAs, instance, author.getSameAs ());
		RDFFactory.addPropertyToAuthorInstance (VocabularyAutheur.key, instance, author.getKey ());
		
		
		if ( !author.isAuthorGoodReadNull () ) {
			RDFFactory.addPropertyToAuthorInstance (VocabularyAutheur.googreadIdAutheur, instance, author.getGoodRead ().getId ());
			RDFFactory.addPropertyToAuthorInstance (VocabularyAutheur.goodreadLink, instance, author.getGoodRead ().getLink ());
			RDFFactory.addPropertyToAuthorInstance (VocabularyAutheur.googreadName, instance, author.getGoodRead ().getName ());
			RDFFactory.addPropertyToAuthorInstance (VocabularyAutheur.goodreadFansCount, instance, author.getGoodRead ().getFansCount ());
			RDFFactory.addPropertyToAuthorInstance (VocabularyAutheur.goodreadImageUri, instance, author.getGoodRead ().getImageUrl ());
			RDFFactory.addPropertyToAuthorInstance (VocabularyAutheur.goodreadAbout, instance, author.getGoodRead ().getAbout ());
			RDFFactory.addPropertyToAuthorInstance (VocabularyAutheur.goodreadWorksCount, instance, author.getGoodRead ().getWorksCount ());
			RDFFactory.addPropertyToAuthorInstance (VocabularyAutheur.goodreadSex, instance, author.getGoodRead ().getSex ());
			RDFFactory.addPropertyToAuthorInstance (VocabularyAutheur.goodreadHomeTown, instance, author.getGoodRead ().getHometown ());
			RDFFactory.addPropertyToAuthorInstance (VocabularyAutheur.goodreadBornAt, instance, author.getGoodRead ().getBornAt ());
			RDFFactory.addPropertyToAuthorInstance (VocabularyAutheur.goodreadDiedAt, instance, author.getGoodRead ().getDiedAt ());
		}
		if ( !author.isAuthorFacebookNull () ) {
			RDFFactory.addPropertyToAuthorInstance (VocabularyAutheur.facebookIdAutheur, instance, author.getFacebook ().getId ());
			RDFFactory.addPropertyToAuthorInstance (VocabularyAutheur.facebookLikes, instance, new String (
					"" + author.getFacebook ().getLikes ()));
			RDFFactory.addPropertyToAuthorInstance (VocabularyAutheur.facebookLink, instance, author.getFacebook ().getLink ());
			RDFFactory.addPropertyToAuthorInstance (VocabularyAutheur.facebookTalkingAboutCount, instance, new String (
					"" + author.getFacebook ().getTalkingAboutCount ()));
			RDFFactory.addPropertyToAuthorInstance (VocabularyAutheur.facebookName, instance, author.getFacebook ().getPageName ());
                        
                        
		}
	}

	/**
	 * This is the method that generate the rdf individuals into the base RDF
	 * from a book instance and interlink the instance with resource from
         * dbpedia if one exist
	 * 
	 * @param book
	 *            This is the result of google-book API store into an instance
	 *            of Book
	 */
	public static void generateRDFBookInstance(Book book) {
		OntClass bookClass = RDFOntology.getInstanceRDFOntology ().getBookClass ();
		Individual instance = bookClass.createIndividual (book.getCanonicalVolumeLink ());
		
		RDFFactory.addPropertyToBookInstance (VocabularyBook.SameAs, instance, book.getSameAs ());
		
		RDFFactory.addPropertyToBookInstance (VocabularyBook.idBook, instance, book.getId ());
		RDFFactory.addPropertyToBookInstance (VocabularyBook.title, instance, book.getTitle ());
		RDFFactory.addPropertyToBookInstance (VocabularyBook.description, instance, book.getDescription ());
		RDFFactory.addPropertyToBookInstance (VocabularyBook.publisher, instance, book.getPublisher ());
		RDFFactory.addPropertyToBookInstance (VocabularyBook.publishedDate, instance, book.getPublishedDate ());
		RDFFactory.addPropertyToBookInstance (VocabularyBook.pageCount, instance, book.getPageCount ());
		RDFFactory.addPropertyToBookInstance (VocabularyBook.image, instance, book.getThumbnail ());
		RDFFactory.addPropertyToBookInstance (VocabularyBook.language, instance, book.getLanguage ());
		RDFFactory.addPropertyToBookInstance (VocabularyBook.previewLink, instance, book.getPreviewLink ());
		RDFFactory.addPropertyToBookInstance (VocabularyBook.infoLink, instance, book.getInfoLink ());
		RDFFactory.addPropertyToBookInstance (VocabularyBook.jsonLink, instance, book.getSelfLink ());
		RDFFactory.addPropertyToBookInstance (VocabularyBook.averageRaiting, instance, book.getAverageRating ());
		RDFFactory.addPropertyToBookInstance (VocabularyBook.raitingCount, instance, book.getRatingsCount ());
		RDFFactory.addPropertyToBookInstance (VocabularyBook.country, instance, book.getCountry ());
		RDFFactory.addPropertyToBookInstance (VocabularyBook.saleability, instance, book.getSaleability ());
		RDFFactory.addPropertyToBookInstance (VocabularyBook.isEbook, instance, book.isEbook ());
		RDFFactory.addPropertyToBookInstance (VocabularyBook.price, instance, book.getPrice ());
		RDFFactory.addPropertyToBookInstance (VocabularyBook.priceSymbol, instance, book.getPriceSymbol ());
		RDFFactory.addPropertyToBookInstance (VocabularyBook.buyLink, instance, book.getBuyLink ());
		RDFFactory.addPropertyToBookInstance (VocabularyBook.viewability, instance, book.getViewability ());
		RDFFactory.addPropertyToBookInstance (VocabularyBook.publicDomain, instance, book.getPublicDomain ());
		RDFFactory.addPropertyToBookInstance (VocabularyBook.epubLink, instance, book.getEpubLink ());
		RDFFactory.addPropertyToBookInstance (VocabularyBook.pdfLink, instance, book.getPdfLink ());
		RDFFactory.addPropertyToBookInstance (VocabularyBook.webReaderLink, instance, book.getWebReaderLink ());
		RDFFactory.addPropertyToBookInstance (VocabularyBook.textSnippet, instance, book.getTextSnippet ());
		RDFFactory.addPropertyToBookInstance (VocabularyBook.currencyCode, instance, book.getCurrencyCode ());

		RDFFactory.addIdentifierToBook (book.getIndustryIdentifiers (), instance);
		RDFFactory.addCategoryToBook (book.getCategories (), instance);
		RDFFactory.addLinkAuthorToBook (book.getAuthorslink (), instance);
		RDFFactory.addAuthorToBook (book.getAuthors (), instance);
	}
}
