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
    
    
    public static void generateRDFAuthorInstance(Author author) {

        OntClass authorClass = RDFOntology.getInstanceRDFOntology().getAuthorClass();
        Individual instance = authorClass.createIndividual(author.getLinkAbout());

        if (!author.isAuthorGoodReadNull()) {
            addPropertyToAuthorInstance("id", instance, author.getGoodRead().getId());
            addPropertyToAuthorInstance("link", instance, author.getGoodRead().getLink());
            addPropertyToAuthorInstance("name", instance, author.getGoodRead().getName());
            addPropertyToAuthorInstance("fans_count", instance, author.getGoodRead().getFansCount());
            addPropertyToAuthorInstance("image_uri", instance, author.getGoodRead().getImageUrl());
            addPropertyToAuthorInstance("about", instance, author.getGoodRead().getAbout());
            addPropertyToAuthorInstance("works_count", instance, author.getGoodRead().getWorksCount());
            addPropertyToAuthorInstance("gender", instance, author.getGoodRead().getGender());
            addPropertyToAuthorInstance("home_town", instance, author.getGoodRead().getHometown());
            addPropertyToAuthorInstance("born_at", instance, author.getGoodRead().getBornAt());
            addPropertyToAuthorInstance("died_at", instance, author.getGoodRead().getDiedAt());
        }
        if (!author.isAuthorFacebookNull()) {
            addPropertyToAuthorInstance("id_facebook", instance, author.getFacebook().getId());
            addPropertyToAuthorInstance("likes_facebook", instance, new String("" + author.getFacebook().getLikes()));
            addPropertyToAuthorInstance("link_facebook", instance, author.getFacebook().getLink());
            addPropertyToAuthorInstance("talking_about_count_facebook", instance, new String("" + author.getFacebook().getTalkingAboutCount()));
            addPropertyToAuthorInstance("name_facebook", instance, author.getFacebook().getName());
        }
    }
    
    private static void addPropertyToAuthorInstance(String propertyName, Individual instance, String literalValue) {
        if (literalValue != null) {
            OntClass authorClass = RDFOntology.getInstanceRDFOntology().getAuthorClass();
            Iterator<OntProperty> i = authorClass.listDeclaredProperties();
            while (i.hasNext()) {
                OntProperty property = i.next();
                if (property.getLocalName().equals(propertyName)) {
                    instance.addProperty(property, literalValue);
                }
            }
        }
    }
    
    /**
     * This is the method that generate the rdf individuals into the base RDF
     * from a book instance
     *
     * @param book This is the result of google-book API store into an instance
     * of Book
     */
    public static void generateRDFBookInstance(Book book) {
        OntClass bookClass = RDFOntology.getInstanceRDFOntology().getBookClass();
        Individual instance = bookClass.createIndividual(book.getCanonicalVolumeLink());
        System.out.println(book.getSelfLink());

        addPropertyToBookInstance("id", instance, book.getId());
        addPropertyToBookInstance("title", instance, book.getTitle());
        addPropertyToBookInstance("description", instance, book.getDescription());
        addPropertyToBookInstance("publisher", instance, book.getPublisher());
        addPropertyToBookInstance("published_date", instance, book.getPublishedDate());
        addPropertyToBookInstance("page_count", instance, book.getPageCount());
        addPropertyToBookInstance("image", instance, book.getThumbnail());
        addPropertyToBookInstance("language", instance, book.getLanguage());
        addPropertyToBookInstance("preview_link", instance,book.getPreviewLink());
        addPropertyToBookInstance("info_link", instance, book.getInfoLink());
        addPropertyToBookInstance("link_json", instance, book.getSelfLink());
        addPropertyToBookInstance("average_raiting", instance, book.getAverageRating());
        addPropertyToBookInstance("raiting_count", instance, book.getRatingsCount());
        addPropertyToBookInstance("country", instance, book.getCountry());
        addPropertyToBookInstance("saleability", instance, book.getSaleability());
        addPropertyToBookInstance("is_ebook", instance, book.isEbook());
        addPropertyToBookInstance("price", instance, book.getPrice());
        addPropertyToBookInstance("price_symbol", instance, book.getPriceSymbol());
        addPropertyToBookInstance("buy_link", instance, book.getBuyLink());
        addPropertyToBookInstance("viewability", instance, book.getViewability());
        addPropertyToBookInstance("public_domain", instance, book.getPublicDomain());
        addPropertyToBookInstance("epub_link", instance, book.getEpubLink());
        addPropertyToBookInstance("pdf_link", instance, book.getPdfLink());
        addPropertyToBookInstance("web_reader_link", instance, book.getWebReaderLink());
        addPropertyToBookInstance("text_snippet", instance, book.getTextSnippet());
        addPropertyToBookInstance("currency_code", instance, book.getCurrencyCode());

        addIdentifierToBook(book.getIndustryIdentifiers(), instance);
        addCategoryToBook(book.getCategories(), instance);
        addLinkAuthorToBook(book.getAuthorslink(), instance);
        addAuthorToBook(book.getAuthors(), instance);
    }
    
    /**
     * The method that actually add the properties into the individual, by
     * getting the right propriety from the OWL Class Book and adding a literal
     * value to that propriety
     *
     * @param propertyName The name of the OWL Book Propriety
     * @param instance The individual want to add into the RDF base
     * @param literalValue The Value added into the Propriety
     */
    private static void addPropertyToBookInstance(String propertyName,
            Individual instance, String literalValue) {
        OntClass bookClass = RDFOntology.getInstanceRDFOntology().getBookClass();
        Iterator<OntProperty> i = bookClass.listDeclaredProperties();
        if (literalValue != null) {
            while (i.hasNext()) {
                OntProperty property = i.next();
                if (property.getLocalName().equals(propertyName)) {
                    instance.addProperty(property, literalValue);
                }
            }
        }
    }

    /**
     * The method generating the Industrial Identifiers properties into the
     * individual thats stored into the RDF base
     *
     * @param identifierList List of identifiers ( isbn 10, isnb 13 of other
     * industrial identifiers
     * @param instance The individual we want to store into the RDF base
     */
    private static void addIdentifierToBook(ArrayList<IdentifierBook> identifierList,
            Individual instance) {
        for (IdentifierBook item : identifierList) {
            if (item.getType().equals(IdentifierBook.ISBN10)) {
                addPropertyToBookInstance("isbn_10", instance,
                        item.getContaints());
            }
            if (item.getType().equals(IdentifierBook.ISBN13)) {
                addPropertyToBookInstance("isbn_13", instance,
                        item.getContaints());
            }
            if (item.getType().equals(IdentifierBook.OTHER)) {
                addPropertyToBookInstance("identifier", instance,
                        item.getContaints());
            }
        }
    }

    /**
     * This method generates the category properties into the book individual
     *
     * @param listCategory The list of categories
     * @param instance The individual we want to store into the RDF base
     */
    private static void addCategoryToBook(ArrayList<String> listCategory,
            Individual instance) {

        for (String item : listCategory) {
            addPropertyToBookInstance("category", instance, item);
        }
    }

    private static void addLinkAuthorToBook(ArrayList<String> listsLinkAuthor,
            Individual instance) {

        for (String item : listsLinkAuthor) {
            addPropertyToBookInstance("link_author", instance, item);
        }
    }

    private static void addAuthorToBook(ArrayList<String> authors, Individual instance) {
        for (String item : authors) {
            addPropertyToBookInstance("author", instance, item);
        }
    }
}
