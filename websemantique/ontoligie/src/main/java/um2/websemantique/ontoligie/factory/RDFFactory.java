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
import um2.websemantique.ontoligie.utils.VocabularyAutheur;
import um2.websemantique.ontoligie.utils.VocabularyBook;

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
            addPropertyToAuthorInstance(VocabularyAutheur.googreadIdAutheur, instance, author.getGoodRead().getId());
            addPropertyToAuthorInstance(VocabularyAutheur.goodreadLink, instance, author.getGoodRead().getLink());
            addPropertyToAuthorInstance(VocabularyAutheur.googreadName, instance, author.getGoodRead().getName());
            addPropertyToAuthorInstance(VocabularyAutheur.goodreadFansCount, instance, author.getGoodRead().getFansCount());
            addPropertyToAuthorInstance(VocabularyAutheur.goodreadImageUri, instance, author.getGoodRead().getImageUrl());
            addPropertyToAuthorInstance(VocabularyAutheur.goodreadAbout, instance, author.getGoodRead().getAbout());
            addPropertyToAuthorInstance(VocabularyAutheur.goodreadWorksCount, instance, author.getGoodRead().getWorksCount());
            addPropertyToAuthorInstance(VocabularyAutheur.goodreadSex, instance, author.getGoodRead().getSex());
            addPropertyToAuthorInstance(VocabularyAutheur.goodreadHomeTown, instance, author.getGoodRead().getHometown());
            addPropertyToAuthorInstance(VocabularyAutheur.goodreadBornAt, instance, author.getGoodRead().getBornAt());
            addPropertyToAuthorInstance(VocabularyAutheur.goodreadDiedAt, instance, author.getGoodRead().getDiedAt());
        }
        if (!author.isAuthorFacebookNull()) {
            addPropertyToAuthorInstance(VocabularyAutheur.facebookIdAutheur, instance, author.getFacebook().getId());
            addPropertyToAuthorInstance(VocabularyAutheur.facebookLikes, instance, new String("" + author.getFacebook().getLikes()));
            addPropertyToAuthorInstance(VocabularyAutheur.facebookLink, instance, author.getFacebook().getLink());
            addPropertyToAuthorInstance(VocabularyAutheur.facebookTalkingAboutCount, instance, new String("" + author.getFacebook().getTalkingAboutCount()));
            addPropertyToAuthorInstance(VocabularyAutheur.facebookName, instance, author.getFacebook().getName());
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

        addPropertyToBookInstance(VocabularyBook.idBook, instance, book.getId());
        addPropertyToBookInstance(VocabularyBook.title, instance, book.getTitle());
        addPropertyToBookInstance(VocabularyBook.description, instance, book.getDescription());
        addPropertyToBookInstance(VocabularyBook.publisher, instance, book.getPublisher());
        addPropertyToBookInstance(VocabularyBook.publishedDate, instance, book.getPublishedDate());
        addPropertyToBookInstance(VocabularyBook.pageCount, instance, book.getPageCount());
        addPropertyToBookInstance(VocabularyBook.image, instance, book.getThumbnail());
        addPropertyToBookInstance(VocabularyBook.language, instance, book.getLanguage());
        addPropertyToBookInstance(VocabularyBook.previewLink, instance,book.getPreviewLink());
        addPropertyToBookInstance(VocabularyBook.infoLink, instance, book.getInfoLink());
        addPropertyToBookInstance(VocabularyBook.jsonLink, instance, book.getSelfLink());
        addPropertyToBookInstance(VocabularyBook.averageRaiting, instance, book.getAverageRating());
        addPropertyToBookInstance(VocabularyBook.raitingCount, instance, book.getRatingsCount());
        addPropertyToBookInstance(VocabularyBook.country, instance, book.getCountry());
        addPropertyToBookInstance(VocabularyBook.saleability, instance, book.getSaleability());
        addPropertyToBookInstance(VocabularyBook.isEbook, instance, book.isEbook());
        addPropertyToBookInstance(VocabularyBook.price, instance, book.getPrice());
        addPropertyToBookInstance(VocabularyBook.priceSymbol, instance, book.getPriceSymbol());
        addPropertyToBookInstance(VocabularyBook.buyLink, instance, book.getBuyLink());
        addPropertyToBookInstance(VocabularyBook.viewability, instance, book.getViewability());
        addPropertyToBookInstance(VocabularyBook.publicDomain, instance, book.getPublicDomain());
        addPropertyToBookInstance(VocabularyBook.epubLink, instance, book.getEpubLink());
        addPropertyToBookInstance(VocabularyBook.pdfLink, instance, book.getPdfLink());
        addPropertyToBookInstance(VocabularyBook.webReaderLink, instance, book.getWebReaderLink());
        addPropertyToBookInstance(VocabularyBook.textSnippet, instance, book.getTextSnippet());
        addPropertyToBookInstance(VocabularyBook.currencyCode, instance, book.getCurrencyCode());

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
                addPropertyToBookInstance(VocabularyBook.isbn10, instance,
                        item.getContaints());
            }
            if (item.getType().equals(IdentifierBook.ISBN13)) {
                addPropertyToBookInstance(VocabularyBook.isbn13, instance,
                        item.getContaints());
            }
            if (item.getType().equals(IdentifierBook.OTHER)) {
                addPropertyToBookInstance(VocabularyBook.identifier, instance,
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
            addPropertyToBookInstance(VocabularyBook.category, instance, item);
        }
    }

    private static void addLinkAuthorToBook(ArrayList<String> listsLinkAuthor,
            Individual instance) {

        for (String item : listsLinkAuthor) {
            addPropertyToBookInstance(VocabularyBook.authorLink, instance, item);
        }
    }

    private static void addAuthorToBook(ArrayList<String> authors, Individual instance) {
        for (String item : authors) {
            addPropertyToBookInstance(VocabularyBook.author, instance, item);
        }
    }
}
