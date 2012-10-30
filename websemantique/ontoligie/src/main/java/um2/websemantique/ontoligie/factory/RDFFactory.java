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
import java.util.ArrayList;
import java.util.Iterator;
import um2.websemantique.entities.base.Book;
import um2.websemantique.entities.utils.IdentifierBook;

/**
 * The class RDFFactory will generate the ontology of the rdf base. It generate
 * two classes : Book and Author, and their porprieties
 *
 * @author GoceDelcev
 */
public class RDFFactory {

    private String baseName;
    private OntModel base;
    private OntClass authorClass;
    private String authorNS = "http://fuck.JENA/author#";
    private OntClass bookClass;
    private String bookNS = "http://fuck.JENA/book#";

    public RDFFactory(String nameOfBase) {

        this.baseName = nameOfBase;
        this.loadOntologyModel();
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
        if (!existBookClass) {
            this.createBookClass();
        }
        if (!existeAuthorClass) {
            this.createAuhorClass();
        }
    }

    public OntModel getBase() {
        return this.base;
    }

    /**
     * This method will load the base if it exist, if not, it will create the
     * base. This method is called only once, in the RDFFactory constructor
     *
     * @return en Ontology Model, loaded from the RDF base
     */
    private OntModel loadOntologyModel() {
        this.base = ModelFactory.createOntologyModel();
        File f = new File(baseName + ".rdf");
        if (!f.exists()) {
            try {
                f.createNewFile();
                System.out.println("The Ontology Base : " + baseName
                        + " has been created");
                this.base.setNsPrefix("Book", this.bookNS);
                this.base.setNsPrefix("Author", this.authorNS);
                FileWriter fstream = new FileWriter(baseName + ".rdf");
                this.base.write(fstream, "RDF/XML-ABBREV");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("The Ontology Base : " + baseName
                    + " already exists");
            InputStream in = FileManager.get().open(baseName + ".rdf");
            this.base.read(in, null);
        }
        return this.base;
    }

    /**
     * This method creates the Author Class with it's properties
     */
    private void createAuhorClass() {
        this.authorClass = this.base.createClass(authorNS + "Author");
        this.authorClass.addLabel("The  Author Class", "en");
        this.authorClass.addComment("The class describint en author", "en");
    }

    /**
     * This method creates the Author Class with it's properties
     */
    private void createBookClass() {

        this.bookClass = this.base.createClass(bookNS + "Book");

        this.bookClass.addComment("This is the class we use to create all book individuals", "en");
        this.bookClass.addLabel("The Book Class", "en");

        addBookProperty("id").setDomain(XSD.ID);
        addBookProperty("self_link").setDomain(XSD.xstring);
        addBookProperty("title").setDomain(XSD.xstring);
        addBookProperty("description").setDomain(XSD.xstring);
        addBookProperty("publisher").setDomain(XSD.Name);
        addBookProperty("published_date").setDomain(XSD.date);
        addBookProperty("page_count").setDomain(XSD.xint);
        addBookProperty("autheur").setDomain(this.authorClass);
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
        OntProperty property = this.base.createOntProperty(this.bookClass.getNameSpace() + propertyName);
        property.setDomain(this.bookClass);
        return property;
    }

    public OntClass getBookClass() {
        return this.bookClass;
    }

    public OntClass getAuthorClass() {
        return authorClass;
    }

    public Individual createBookInstance(String uri) {
        return bookClass.createIndividual(uri);
    }
    
    /**
     * The method that actually add the properties into the individual, by getting the
     * right propriety from the OWL Class Book and adding a literal value to that propriety
     * 
     * @param propertyName The name of the OWL Book Propriety 
     * @param instance The individual want to add into the RDF base
     * @param literalValue The Value added into the Propriety
     */
    public void addPropertyToBookInstance(String propertyName , Individual instance, String literalValue) {
        Iterator<OntProperty> i = this.bookClass.listDeclaredProperties();
        while (i.hasNext()) {
            OntProperty property = i.next();
            if (property.getLocalName().equals(propertyName)) {
                instance.addProperty(property, literalValue);
            }
        }
    }
    
    /**
     * The method generating the Industrial Identifiers properties into the individual
     * thats stored into the  RDF base
     * 
     * @param identifierList List of identifiers ( isbn 10, isnb 13 of other industrial identifiers
     * @param instance The individual we want to store into the RDF base
     */
    public void addIdentifierToBook(ArrayList<IdentifierBook> identifierList, Individual instance){
        for (IdentifierBook item : identifierList){
            if(item.getType().equals(IdentifierBook.ISBN10))
                addPropertyToBookInstance("isbn_10", instance, item.getContaints());
            if(item.getType().equals(IdentifierBook.ISBN13))
                addPropertyToBookInstance("isbn_13", instance, item.getContaints());
            if(item.getType().equals(IdentifierBook.OTHER))
                addPropertyToBookInstance("identifier", instance, item.getContaints());
        }
    }
    
    /**
     * This method generates the category properties into the book individual
     *
     * @param listCategory The list of categories
     * @param instance The individual we want to store into the RDF base
     */
    public void addCategoryToBook(ArrayList<String> listCategory , Individual instance){
        
        for(String item : listCategory){
            addPropertyToBookInstance("category" , instance, item);
        }
    }
    
    /**
     * This is the method that generate the rdf individuals into the base RDF
     * from a book instance 
     * 
     * @param book This is the result of google-book API store into an instance
     * of Book
     */
    public void generateRDFBookInstance(Book book) {
        
        Individual instance = this.bookClass.createIndividual(book.getSelfLink());
        
        addPropertyToBookInstance( "id" , instance , book.getId());
        addPropertyToBookInstance( "title" , instance , book.getTitle());
        addPropertyToBookInstance( "description" , instance , book.getDescription());
        addPropertyToBookInstance( "publisher" , instance , book.getPublisher());
        addPropertyToBookInstance( "published_date" , instance , book.getPublishedDate());
        addPropertyToBookInstance( "page_count" , instance , book.getPageCount());
        addPropertyToBookInstance( "image" , instance , book.getThumbnail());
        addPropertyToBookInstance( "language" , instance , book.getLanguage());
        addPropertyToBookInstance( "preview_link" , instance , book.getPreviewLink());
        addPropertyToBookInstance( "info_link" , instance , book.getInfoLink());
        addPropertyToBookInstance( "canonical_volume_link" , instance , book.getCanonicalVolumeLink());
        addPropertyToBookInstance( "average_raiting" , instance , book.getAverageRating());
        addPropertyToBookInstance( "raiting_count" , instance , book.getRatingsCount());
        addPropertyToBookInstance( "country" , instance , book.getCountry());
        addPropertyToBookInstance( "saleability" , instance , book.getSaleability());
        addPropertyToBookInstance( "is_ebook" , instance , book.isEbook());
        addPropertyToBookInstance( "price" , instance , book.getPrice());
        addPropertyToBookInstance( "price_symbol" , instance , book.getPriceSymbol());
        addPropertyToBookInstance( "buy_link" , instance , book.getBuyLink());
        addPropertyToBookInstance( "viewability" , instance , book.getViewability());
        addPropertyToBookInstance( "public_domain" , instance , book.getPublicDomain());
        addPropertyToBookInstance( "epub_link" , instance , book.getEpubLink());
        addPropertyToBookInstance( "pdf_link" , instance , book.getPdfLink());
        addPropertyToBookInstance( "web_reader_link" , instance , book.getWebReaderLink());
        addPropertyToBookInstance( "text_snippet" , instance , book.getTextSnippet());
        addPropertyToBookInstance( "currency_code" , instance , book.getCurrencyCode());    
        
        addIdentifierToBook( book.getIndustryIdentifiers() , instance );
        addCategoryToBook( book.getCategories() , instance );
        
    }
}
