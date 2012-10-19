<?php
include(RDFAPI_INCLUDE_DIR . "/RdfAPI.php");

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of OntologyModel
 *
 * @author Rabah
 */
class OntologyModel {

    private $base;
    private $author_class;
    private $book_class;
    //put your code here

    public function OntologyModel() {
        $this->base = ModelFactory::getOntModel(MEMMODEL,RDFS_VOCABULARY);
        //Creating the class  AUTHOR
        /*
        define('AUTHOR_NS', 'http://www.googleapi.com/author/');
        
        $this->author_class = $this->base->createOntClass(AUTHOR_NS);
        $author_label = new ResLiteral("author");
        $author_comment = new ResLiteral("Personne qui ecrit des livres");
        $this->author_class->setComment($author_comment);
        $this->author_class->setLabelProperty($author_label);
        
        $property = $this->base->createOntProperty(AUTHOR_NS.'name');
        $this->author_class->addProperty($property, $author_label);
        */
        //Creating the class BOOK
        define('BOOK_NS', 'http://www.googleapi.com/book/');
        $this->book_class = $this->base->createOntClass(BOOK_NS);
        $book_label = new ResLiteral("Book");
        $book_comment = new ResLiteral("Structured type of documment describing a story");
        $this->book_class->addComment($book_comment);
        $this->book_class->addLabelProperty($book_label);
        
        $property_kind = $this->base->createOntProperty(BOOK_NS.'kind');
        $kind_literal = new ResLiteral("kind");
        $this->book_class->addProperty($property_kind, $kind_literal);
        
        $property_id = $this->base->createOntProperty(BOOK_NS.'id');
        $id_literal = new ResLiteral("id");
        $this->book_class->addProperty($property_id, $id_literal);
        
        $property_etag = $this->base->createOntProperty(BOOK_NS.'etag');
        $etag_literal = new ResLiteral("etag");
        $this->book_class->addProperty($property_etag, $etag_literal);
        
        $property_title = $this->base->createOntProperty(BOOK_NS.'title');
        $title_literal = new ResLiteral("title");
        $this->book_class->addProperty($property_title, $title_literal);
        
        $property_publicher = $this->base->createOntProperty(BOOK_NS.'publicher');
        $publisher_literal = new ResLiteral("publisher");
        $this->book_class->addProperty($property_publicher, $publisher_literal);
        
        $property_publiching_date = $this->base->createOntProperty(BOOK_NS.'publiching_date');
        $publiching_date_literal = new ResLiteral("publiching_date");
        $this->book_class->addProperty($property_publiching_date, $publiching_date_literal);
        
        $property_description = $this->base->createOntProperty(BOOK_NS.'description');
        $description_literal = new ResLiteral("description");
        $this->book_class->addProperty($property_description, $description_literal);
        
        $property_isbn_10 = $this->base->createOntProperty(BOOK_NS.'isbn_10');
        $isbn_10_literal = new ResLiteral("isbn_10");
        $this->book_class->addProperty($property_isbn_10, $isbn_10_literal);
        
        $property_isbn_13 = $this->base->createOntProperty(BOOK_NS.'isbn_13');
        $isbn_13_literal = new ResLiteral("isbn_13");
        $this->book_class->addProperty($property_isbn_13, $isbn_13_literal);
        
        $property_page_count = $this->base->createOntProperty(BOOK_NS.'page_count');
        $page_count_literal = new ResLiteral("page_count");
        $this->book_class->addProperty($property_page_count, $page_count_literal);
        
        $property_print_type = $this->base->createOntProperty(BOOK_NS.'print_type');
        $print_type_literal = new ResLiteral("print_type");
        $this->book_class->addProperty($property_print_type, $print_type_literal);
        
        $property_average_rating = $this->base->createOntProperty(BOOK_NS.'average_rating');
        $average_rating_literal = new ResLiteral("average_rating");
        $this->book_class->addProperty($property_average_rating, $average_rating_literal);
        
        $this->base->saveAs("base/base.rdf", "rdf");
        
    }

    public function getBaseRDF() {
        $this->base =  ModelFactory::getOntModel(MEMMODEL,RDFS_VOCABULARY);
        $this->base->load("base/base.rdf");
        return $this->base;
    }

    public function closeBaseRDF() {
        $this->base->saveAs("base/base.rdf","rdf");
        //$this->base->close();
    }
    
    public function getAuthorClass(){
        return $this->author_class;
    }
    
    public function getBookClass(){
        return $this->book_class;
    }
}
?>