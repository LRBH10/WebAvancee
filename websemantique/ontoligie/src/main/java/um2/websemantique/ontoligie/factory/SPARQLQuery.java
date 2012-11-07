/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package um2.websemantique.ontoligie.factory;

import java.util.ArrayList;
import java.util.List;

import org.openjena.atlas.io.IndentedWriter;

import um2.websemantique.entities.base.Author;
import um2.websemantique.entities.base.AuthorFacebook;
import um2.websemantique.entities.base.AuthorGoodRead;
import um2.websemantique.entities.base.Book;
import um2.websemantique.entities.utils.IdentifierBook;
import um2.websemantique.entities.utils.SearchType;
import um2.websemantique.ontoligie.utils.ResponseQuery;
import um2.websemantique.ontoligie.utils.VocabularyAutheur;
import um2.websemantique.ontoligie.utils.VocabularyBook;

import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;

/**
 *
 * @author GoceDelcev
 */
public class SPARQLQuery {

    public static final String NL = System.getProperty("line.separator");

    public static String endpointExec(String query) {
        return executeEndpointSPARQLQuery(query);
    }

    /**
     * Method that execute a SPARQL query into the endpoint ( out base rdf ) and
     * return the result of the research
     *
     * @param query the query
     * @return the result of the research
     */
    public static ResponseQuery responseSPARQLQueryFromService(String query) {
        ArrayList<Resource> resultExec = new ArrayList<Resource>();
        resultExec.addAll(executeSPARQLQuery(query));
        return dispatchClasses(resultExec);
    }

    /**
     * Method that search a word into the base rdf and return the result of the
     * research
     *
     * @param recherche searched word
     * @param typeResearch type of research ( by author , by title ....)
     * @return the result of the research
     */
    public static ResponseQuery responseSPARQLQuerry(String recherche, SearchType typeResearch) {

        return dispatch(recherche, typeResearch);
    }

    /**
     * The main dispatch method that make connection between<br/> - the passed
     * word, his type and the query generated from it<br/> - the generated query
     * and the result of its execution
     *
     * @param recherche the word
     * @param type the type of the research
     * @{link SearchType}
     * @return
     */
    private static ResponseQuery dispatch(String recherche, SearchType type) {
        ArrayList<String> querys = createSPARQLQuerys(recherche, type);
        ArrayList<Resource> resultExec = new ArrayList<Resource>();
        for (String query : querys) {
            resultExec.addAll(executeSPARQLQuery(query));
        }
        return dispatchClasses(resultExec);
    }

    private static ResponseQuery dispatchClasses(ArrayList<Resource> listRes) {
        ResponseQuery result = new ResponseQuery();
        result.setAuthors(filterAuthors(createAuthorFromSPARQL(listRes)));
        result.setBooks(filterBooks(createBookFromSPARQL(listRes)));

        if (result.getBooks().size() + result.getAuthors().size() <= 0) {
            result.setOk(false);
        } else {
            result.setOk(true);
        }
        return result;
    }

    private static ArrayList<Author> filterAuthors(ArrayList<Author> list) {
        ArrayList<Author> result = new ArrayList<Author>();
        boolean test;
        for (int i = 0; i < list.size(); i++) {
            test = true;
            if (list.get(i).getGoodRead().getName() != null) {
                for (int j = i + 1; j < list.size(); j++) {
                    if (list.get(i).getGoodRead().getName().equals(list.get(j).getGoodRead().getName())) {
                        test = false;
                    }
                }
            } else {
                test = false;
            }
            if (test) {
                result.add(list.get(i));
            }
        }
        return result;
    }

    private static ArrayList<Book> filterBooks(ArrayList<Book> list) {
        ArrayList<Book> result = new ArrayList<Book>();
        boolean test;
        for (int i = 0; i < list.size(); i++) {
            test = true;
            if (list.get(i).getTitle() != null) {
                for (int j = i + 1; j < list.size(); j++) {
                    if (list.get(i).getTitle().equals(list.get(j).getTitle())) {
                        test = false;
                    }
                }
            } else {
                test = false;
            }
            if (test) {
                result.add(list.get(i));
            }
        }
        return result;
    }

    /**
     * Method that generates instance of book from a resource
     *
     * @param list the result from executeSPARQLquery
     * @return the new generated book
     */
    public static ArrayList<Book> createBookFromSPARQL(ArrayList<Resource> list) {
        ArrayList<Book> result = new ArrayList<Book>();

        for (Resource res : list) {
            result.add(createBook(res));
        }
        return result;
    }

    private static Book createBook(Resource res) {
        Book book = new Book();
        ArrayList<String> authors = new ArrayList<String>();
        ArrayList<String> authorLinks = new ArrayList<String>();
        ArrayList<String> categorys = new ArrayList<String>();
        ArrayList<IdentifierBook> identifiers = new ArrayList<IdentifierBook>();

        List<Statement> list = res.listProperties().toList();
        for (int i = 0; i < list.size() - 1; i++) {

            String literalValue = list.get(i).getLiteral().getString();
            String propertyValue = list.get(i).getPredicate().getLocalName();

            if (propertyValue.equals(VocabularyBook.SameAs)) {
                book.setSameAs(propertyValue);
            }
            if (propertyValue.equals(VocabularyBook.author)) {
                authors.add(propertyValue);
            }
            if (propertyValue.equals(VocabularyBook.authorLink)) {
                authorLinks.add(propertyValue);
            }
            if (propertyValue.equals(VocabularyBook.averageRaiting)) {
                book.setAverageRating(literalValue);
            }
            if (propertyValue.equals(VocabularyBook.buyLink)) {
                book.setBuyLink(literalValue);
            }
            if (propertyValue.equals(VocabularyBook.category)) {
                categorys.add(literalValue);
            }
            if (propertyValue.equals(VocabularyBook.country)) {
                book.setCountry(literalValue);
            }
            if (propertyValue.equals(VocabularyBook.currencyCode)) {
                book.setCurrencyCode(literalValue);
            }
            if (propertyValue.equals(VocabularyBook.description)) {
                book.setDescription(literalValue);
            }
            if (propertyValue.equals(VocabularyBook.epubLink)) {
                book.setEpubLink(literalValue);
            }
            if (propertyValue.equals(VocabularyBook.idBook)) {
                book.setId(literalValue);
            }
            if (propertyValue.equals(VocabularyBook.identifier)) {
                identifiers.add(new IdentifierBook(VocabularyBook.identifier,
                        literalValue));
            }
            if (propertyValue.equals(VocabularyBook.isbn10)) {
                identifiers.add(new IdentifierBook(VocabularyBook.isbn10,
                        literalValue));
            }
            if (propertyValue.equals(VocabularyBook.isbn13)) {
                identifiers.add(new IdentifierBook(VocabularyBook.isbn13,
                        literalValue));
            }
            if (propertyValue.equals(VocabularyBook.image)) {
                book.setThumbnail(literalValue);
            }
            if (propertyValue.equals(VocabularyBook.infoLink)) {
                book.setInfoLink(literalValue);
            }
            if (propertyValue.equals(VocabularyBook.isEbook)) {
                book.setEbook(literalValue);
            }
            if (propertyValue.equals(VocabularyBook.jsonLink)) {
                book.setSelfLink(literalValue);
            }
            if (propertyValue.equals(VocabularyBook.language)) {
                book.setLanguage(literalValue);
            }
            if (propertyValue.equals(VocabularyBook.pageCount)) {
                book.setPageCount(literalValue);
            }
            if (propertyValue.equals(VocabularyBook.pdfLink)) {
                book.setPdfLink(literalValue);
            }
            if (propertyValue.equals(VocabularyBook.previewLink)) {
                book.setPreviewLink(literalValue);
            }
            if (propertyValue.equals(VocabularyBook.price)) {
                book.setPreviewLink(literalValue);
            }
            if (propertyValue.equals(VocabularyBook.priceSymbol)) {
                book.setPriceSymbol(literalValue);
            }
            if (propertyValue.equals(VocabularyBook.publicDomain)) {
                book.setPublicDomain(literalValue);
            }
            if (propertyValue.equals(VocabularyBook.publishedDate)) {
                book.setPublishedDate(literalValue);
            }
            if (propertyValue.equals(VocabularyBook.publisher)) {
                book.setPublisher(literalValue);
            }
            if (propertyValue.equals(VocabularyBook.raitingCount)) {
                book.setRatingsCount(literalValue);
            }
            if (propertyValue.equals(VocabularyBook.saleability)) {
                book.setSaleability(literalValue);
            }
            if (propertyValue.equals(VocabularyBook.selfLink)) {
                book.setSelfLink(literalValue);
            }
            if (propertyValue.equals(VocabularyBook.textSnippet)) {
                book.setTextSnippet(literalValue);
            }
            if (propertyValue.equals(VocabularyBook.title)) {
                book.setTitle(literalValue);
            }
            if (propertyValue.equals(VocabularyBook.viewability)) {
                book.setViewability(literalValue);
            }
            if (propertyValue.equals(VocabularyBook.webReaderLink)) {
                book.setWebReaderLink(literalValue);
            }
        }
        book.setAuthors(authors);
        book.setAuthorslink(authorLinks);
        book.setCategories(categorys);
        book.setIndustryIdentifiers(identifiers);

        return book;
    }

    /**
     * Method that generates list of author instances from resources
     *
     * @param list the result from executeSPARQLquery
     * @return the new generated author
     */
    public static ArrayList<Author> createAuthorFromSPARQL(ArrayList<Resource> list) {
        ArrayList<Author> result = new ArrayList<Author>();

        for (Resource res : list) {
            result.add(createAuthor(res));
        }

        return result;
    }

    /**
     * Method that generates AuthorGoodRead instance from Resource
     *
     * @param res the resource
     * @return the new generated instance
     */
    private static AuthorGoodRead createGoodreadAuthor(Resource res) {

        AuthorGoodRead grAuthor = new AuthorGoodRead();
        List<Statement> list = res.listProperties().toList();

        for (int i = 0; i < list.size() - 1; i++) {

            String literalValue = list.get(i).getLiteral().getString();
            String propertyValue = list.get(i).getPredicate().getLocalName();

            if (propertyValue.equals(VocabularyAutheur.googreadIdAutheur)) {
                grAuthor.setId(literalValue);
            }
            if (propertyValue.equals(VocabularyAutheur.googreadName)) {
                grAuthor.setName(literalValue);
            }
            if (propertyValue.equals(VocabularyAutheur.goodreadLink)) {
                grAuthor.setLink(literalValue);
            }
            if (propertyValue.equals(VocabularyAutheur.goodreadFansCount)) {
                grAuthor.setFansCount(literalValue);
            }
            if (propertyValue.equals(VocabularyAutheur.goodreadImageUri)) {
                grAuthor.setImageUrl(literalValue);
            }
            if (propertyValue.equals(VocabularyAutheur.goodreadAbout)) {
                grAuthor.setAbout(literalValue);
            }
            if (propertyValue.equals(VocabularyAutheur.goodreadWorksCount)) {
                grAuthor.setWorksCount(literalValue);
            }
            if (propertyValue.equals(VocabularyAutheur.goodreadSex)) {
                grAuthor.setSex(literalValue);
            }
            if (propertyValue.equals(VocabularyAutheur.goodreadHomeTown)) {
                grAuthor.setHometown(literalValue);
            }
            if (propertyValue.equals(VocabularyAutheur.goodreadBornAt)) {
                grAuthor.setBornAt(literalValue);
            }
            if (propertyValue.equals(VocabularyAutheur.goodreadDiedAt)) {
                grAuthor.setDiedAt(literalValue);
            }
        }
        return grAuthor;
    }

    /**
     * To create author with key and same as
     *
     * @param res
     * @return
     */
    private static Author createAuthor(Resource res) {

        Author author = new Author(createGoodreadAuthor(res),
                createFacebookAuthor(res));
        List<Statement> list = res.listProperties().toList();

        for (int i = 0; i < list.size() - 1; i++) {

            String literalValue = list.get(i).getLiteral().getString();
            String propertyValue = list.get(i).getPredicate().getLocalName();

            if (propertyValue.equals(VocabularyAutheur.SameAs)) {
                author.setSameAs(literalValue);
            }
            if (propertyValue.equals(VocabularyAutheur.key)) {
                author.setKey(literalValue);
            }
        }
        return author;
    }

    /**
     * Method that generates AuthorFacebook instance from Resource
     *
     * @param res the resource
     * @return the new generated instance
     */
    private static AuthorFacebook createFacebookAuthor(Resource res) {
        AuthorFacebook fbAuthor = new AuthorFacebook();

        List<Statement> list = res.listProperties().toList();
        for (int i = 0; i < list.size() - 1; i++) {
            String literalValue = list.get(i).getLiteral().getString();
            String propertyValue = list.get(i).getPredicate().getLocalName();

            if (propertyValue.equals(VocabularyAutheur.facebookName)) {
                fbAuthor.setName(literalValue);
            }
            if (propertyValue.equals(VocabularyAutheur.facebookLikes)) {
                fbAuthor.setLikes(literalValue);
            }
            if (propertyValue.equals(VocabularyAutheur.facebookLink)) {
                fbAuthor.setLink(literalValue);
            }
            if (propertyValue.equals(VocabularyAutheur.facebookIdAutheur)) {
                fbAuthor.setId(literalValue);
            }

            if (propertyValue.equals(VocabularyAutheur.facebookTalkingAboutCount)) {
                fbAuthor.setTalkingAboutCount(literalValue);
            }
        }
        return fbAuthor;
    }

    /**
     * Method that execute a SPARQL query passed by parameter
     *
     * @param queryString this is the SPARQL query that will be executed
     * @return The generated list of Resources
     */
    public static ArrayList<Resource> executeSPARQLQuery(String queryString) {

        ArrayList<Resource> result = new ArrayList<Resource>();
        com.hp.hpl.jena.query.Query query = QueryFactory.create(queryString);
        query.serialize(new IndentedWriter(System.out, true));
        System.out.println();
        QueryExecution qexec = QueryExecutionFactory.create(query, RDFOntology.getInstanceRDFOntology().getModel());
        try {
            ResultSet rs = qexec.execSelect();
            for (; rs.hasNext();) {
                QuerySolution rb = rs.nextSolution();
                Resource r = (Resource) rb.getResource("individu");
                if (r != null) {
                    result.add(r);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            qexec.close();
        }
        return result;
    }

    public static String executeEndpointSPARQLQuery(String queryString) {

        String result = "";
        com.hp.hpl.jena.query.Query query = QueryFactory.create(queryString);
        List<String> queryVars = query.getResultVars();
        
        query.serialize(new IndentedWriter(System.out, true));
        
        System.out.println();
        QueryExecution qexec = QueryExecutionFactory.create(query, RDFOntology.getInstanceRDFOntology().getModel());
        try {
            ResultSet rs = qexec.execSelect();
            for (; rs.hasNext();) {
                QuerySolution solution = rs.nextSolution();
                
                for(String var : queryVars){
                    result += solution.get(var).toString() + "\t";
                }
                
                result += "\n";
                
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        } finally {
            qexec.close();
        }
        return result;
    }
    
    /**
     * Method that generate the SPARQL query
     *
     * @param content The content of the searched value
     * @param typeResearch The type if the searched value
     * @return The generated SPARQL query SPARQL
     */
    public static String createSPARQLQuerry(String content, SearchType typeResearch) {

        String prefixBook = "PREFIX book: <"
                + RDFOntology.getInstanceRDFOntology().getBookClass().getNameSpace()
                + ">";
        String prefixAutheur = "PREFIX author: <"
                + RDFOntology.getInstanceRDFOntology().getAuthorClass().getNameSpace()
                + ">";
        String queryString = "";
        switch (typeResearch) {
            case AUTHOR:
                queryString = prefixAutheur + NL
                        + "SELECT DISTINCT ?individu  " + "WHERE {"
                        + "{?individu author:" + VocabularyAutheur.googreadName
                        + " ?name . " + "FILTER regex( ?name,\"" + content
                        + "\" , \"i\" ) }" + NL + "UNION" + NL
                        + "{?individu author:" + VocabularyAutheur.facebookName
                        + " ?name . " + "FILTER regex( ?name,\"" + content
                        + "\" , \"i\" ) }}";

                ;
                break;
            case ISBN:
                queryString = prefixBook + NL + "SELECT DISTINCT ?individu  "
                        + "WHERE {" + "{?individu book:"
                        + VocabularyBook.isbn10 + " ?isbn ."
                        + "FILTER regex( ?isbn,\"" + content + "\" , \"i\" ) }"
                        + "UNION" + "{?individu book:" + VocabularyBook.isbn13
                        + " ?isbn ." + "FILTER regex( ?isbn,\"" + content
                        + "\" , \"i\" ) }}";
                break;
            case TITLE:
                queryString = prefixBook + NL + "SELECT DISTINCT ?individu  "
                        + "WHERE " + "{?individu book:" + VocabularyBook.title
                        + " ?title . " + "FILTER regex( ?title,\"" + content
                        + "\" , \"i\" ) }";

                break;

            default: // FOR ANY
                queryString = prefixAutheur + NL + prefixBook + NL
                        + "SELECT DISTINCT ?individu  " + "WHERE {"
                        + "{?individu author:" + VocabularyAutheur.googreadName
                        + " ?name . " + "FILTER regex( ?name,\"" + content
                        + "\" , \"i\" ) }" + NL + "UNION" + NL
                        + "{?individu author:" + VocabularyAutheur.facebookName
                        + " ?name . " + "FILTER regex( ?name,\"" + content
                        + "\" , \"i\" ) }" + NL + "UNION" + NL
                        + "{?individu book:" + VocabularyBook.title
                        + " ?title . " + "FILTER regex( ?title,\"" + content
                        + "\" , \"i\" ) }" + NL + "UNION" + NL
                        + "{?individu book:" + VocabularyBook.isbn10
                        + " ?isbn ." + "FILTER regex( ?isbn,\"" + content
                        + "\" , \"i\" ) }" + NL + "UNION" + NL
                        + "{?individu book:" + VocabularyBook.isbn13
                        + " ?isbn ." + "FILTER regex( ?isbn,\"" + content
                        + "\" , \"i\" ) }}";
                break;
        }

        return queryString;
    }

    /**
     * Method that generates a list of SPARQL querys
     *
     * @param recherche
     * @param type
     * @return
     */
    public static ArrayList<String> createSPARQLQuerys(String recherche, SearchType type) {
        ArrayList<String> result = new ArrayList<String>();
        String[] words = recherche.split(" ");
        for (String word : words) {
            result.add(createSPARQLQuerry(word, type));
        }
        return result;
    }
}
