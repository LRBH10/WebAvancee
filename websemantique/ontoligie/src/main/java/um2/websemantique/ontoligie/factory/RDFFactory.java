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
import java.util.Iterator;
import um2.websemantique.entities.base.Book;

/**
 *  The class RDFFactory will generate the ontology of the rdf base.
 * It generate two classes : Book and Author, and their porprieties
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

	public RDFFactory( String nameOfBase ) {
            
		this.baseName = nameOfBase;
		this.loadOntologyModel();
                this.initFactoryClass();
	}
        /**
         * This methode is used to initialise the bookClass and the authorClass
         * attributes from the rdf base  if they exists. If not it calls
         * methods that will create the 2 classes and make the init after
         */
        private void initFactoryClass(){
            ExtendedIterator<OntClass> s = base.listNamedClasses();
            boolean existBookClass = false;
            boolean existeAuthorClass = false;
		
            while (s.hasNext()) {
            	OntClass x = s.next();
            	if(x.getLocalName().equals("Author")){
			existeAuthorClass = true;
			this.authorClass = x;
		}
		if(x.getLocalName().equals("Book")){
			existBookClass = true;
			this.bookClass = x;
		}
            }	
            if(!existBookClass)
                this.createBookClass();
            if(!existeAuthorClass)
                this.createAuhorClass();
        }

	public OntModel getBase() {
		return this.base;
	}
        
        /**
         * This method will load the base if it exist, if not, it will create the base.
         * This method is called only once, in the RDFFactory constructor
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
                        this.base.setNsPrefix( "Book", this.bookNS );
                        this.base.setNsPrefix( "Author", this.authorNS );
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
         * This method creates  the Author Class with it's proprietys  
         */
	private void createAuhorClass() {
		this.authorClass = this.base.createClass(authorNS + "Author");
		this.authorClass.addLabel("The  Author Class", "en");
		this.authorClass.addComment("The class describint en author", "en");
	}
        
        /**
         * This method creates  the Author Class with it's proprietys  
         */
	private void createBookClass() {

		this.bookClass = this.base.createClass(bookNS + "Book");

		this.bookClass.addComment("This is the class we use to create all book individuals","en");
		this.bookClass.addLabel("The Book Class", "en");

		addBookProperty( "id"  ).setDomain(XSD.ID);
                addBookProperty( "self_link" ).setDomain(XSD.xstring);
                addBookProperty( "title" ).setDomain(XSD.xstring);
                addBookProperty( "description").setDomain(XSD.xstring);
		addBookProperty( "publisher" ).setDomain(XSD.Name);
		addBookProperty( "published_date" ).setDomain(XSD.date);
		addBookProperty( "page_count" ).setDomain(XSD.xint);
		addBookProperty( "autheur" ).setDomain(this.authorClass);
		addBookProperty( "isbn_10" ).setDomain(XSD.xstring);
		addBookProperty( "isbn_13" ).setDomain(XSD.xstring);
		addBookProperty( "identifier" ).setDomain(XSD.xstring);
		addBookProperty( "image" ).setDomain(XSD.xstring);
		addBookProperty( "language" ).setDomain(XSD.language);
		addBookProperty( "preview_link" ).setDomain(XSD.xstring);
		addBookProperty( "info_link" ).setDomain(XSD.xstring);
		addBookProperty( "canonical_volume_link" ).setDomain(XSD.xstring);
		addBookProperty( "category" ).setDomain(XSD.xstring);
		addBookProperty( "average_raiting" ).setDomain(XSD.xfloat);
		addBookProperty( "raiting_count" ).setDomain(XSD.xint);
		addBookProperty( "country" ).setDomain(XSD.xstring);
		addBookProperty( "saleability" ).setDomain(XSD.xstring);
		addBookProperty( "is_ebook" ).setDomain(XSD.xstring);
		addBookProperty( "price" ).setDomain(XSD.xfloat);
		addBookProperty( "price_symbol" ).setDomain(XSD.xstring);
		addBookProperty( "buy_link" ).setDomain(XSD.xstring);
		addBookProperty( "viewability" ).setDomain(XSD.xstring);
		addBookProperty( "public_domain" ).setDomain(XSD.xstring);
		addBookProperty( "epub_link" ).setDomain(XSD.xstring);
		addBookProperty( "pdf_link" ).setDomain(XSD.xstring);
		addBookProperty( "web_reader_link" ).setDomain(XSD.xstring);
		addBookProperty( "text_snippet" ).setDomain(XSD.xstring);
		addBookProperty( "currency_code" ).setDomain(XSD.xstring);
	}
        
        private OntProperty addBookProperty(String propertyName){
            OntProperty property = this.base.createOntProperty(this.bookClass.getNameSpace() + propertyName );
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
        
        
        /*
        public void generateRDFBookInstance(Book book){
            Individual instance = bookClass.createIndividual(book.getSelfLink());
            Iterator<OntProperty> i = model.getBookClass().listDeclaredProperties();
		
		while(i.hasNext()){
			OntProperty property = i.next();
			String propertyName = property.getLocalName();
			
			System.out.println(propertyName);
			
			if(propertyName.equals("id")){
				instance.addProperty(property, this.id);
			}		
			
			if(propertyName.equals("title")){
				instance.addProperty(property, this.title);
			}
			
			if(propertyName.equals("description")){
				instance.addProperty(property, this.description);
			}		
			
			if(propertyName.equals("publisher")){
				instance.addProperty(property, this.publisher);
			}		
			
			if(propertyName.equals("published_date")){
				instance.addProperty(property, this.publishedDate);
			}
			
			if(propertyName.equals("page_count")){
				
				instance.addProperty(property, this.pageCount  );
			}
			
			if(propertyName.equals("image")){
				instance.addProperty(property, this.thumbnail);
			}		
			
			if(propertyName.equals("language")){
				instance.addProperty(property, this.language);
			}
			
			if(propertyName.equals("preview_link")){
				instance.addProperty(property, this.previewLink);
			}		
			
			if(propertyName.equals("info_link")){
				instance.addProperty(property, this.infoLink);
			}		
			
			if(propertyName.equals("canonical_volume_link")){
				instance.addProperty(property, this.canonicalVolumeLink);
			}
			
			if(propertyName.equals("average_raiting")){
				instance.addProperty(property, this.averageRating);
			}
			
			if(propertyName.equals("raiting_count")){
				
				instance.addProperty(property, this.ratingsCount);
			}
			
			if(propertyName.equals("country")){
				instance.addProperty(property, this.country);
			}		
			
			if(propertyName.equals("saleability")){
				instance.addProperty(property, this.saleability);
			}
			
			if(propertyName.equals("is_ebook")){
				instance.addProperty(property, this.Ebook);
			}		
			
			if(propertyName.equals("price")){
				instance.addProperty(property, this.price);
			}		
			
			if(propertyName.equals("price_symbol")){
				instance.addProperty(property, this.priceSymbol);
			}
			
			if(propertyName.equals("buy_link")){
				instance.addProperty(property, new String ( ""+this.buyLink));
			}
			
			if(propertyName.equals("viewability")){
				instance.addProperty(property, this.viewability);
			}		
			
			if(propertyName.equals("public_domain")){
				instance.addProperty(property, this.publicDomain);
			}
			
			if(propertyName.equals("epub_link")){
				instance.addProperty(property, this.epubLink);
			}		
			
			if(propertyName.equals("pdf_link")){
				instance.addProperty(property, this.pdfLink);
			}		
			
			if(propertyName.equals("web_reader_link")){
				instance.addProperty(property, this.webReaderLink);
			}
			
			if(propertyName.equals("text_snippet")){
				instance.addProperty(property, this.textSnippet);
			}
			
			if(propertyName.equals("currency_code")){
				instance.addProperty(property, this.currencyCode);
			}
			
			for (String category : this.categories) {
				if(category.equals("ISBN_10")){
					if(propertyName.equals("isbn_10")){
						instance.addProperty(property, category);
					}
				}
				else{
					if(category.equals("ISBN_13")){
						if(propertyName.equals("isbn_13")){
							instance.addProperty(property, category);
						}
					}
					else{
						if(propertyName.equals("identifier")){
							instance.addProperty(property, category);
						}
					}
				}
			}
		}
        }*/

}
