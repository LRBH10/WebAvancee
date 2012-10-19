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

    /**
     * @var string name of base connection 
     */
    private $basename;

    /**
     * @var OntModel  base of our system
     */
    private $base;

    /**
     * @var OntClass 
     */
    private $author_class;

    /**
     * @var OntClass 
     */
    private $book_class;

    /**
     * Constructor of Ontology Model
     * @param string $name_of_base name of base
     */
    public function __construct($name_of_base) {
        $this->basename = $name_of_base;
    }

    /**
     * Creating base for the first Time
     */
    public function createOntologyModel() {
        $this->base = ModelFactory::getOntModel(MEMMODEL, RDFS_VOCABULARY);
        $this->base->addNamespace("book", BOOK_NS);

        $this->createBookClass();
        $this->base->saveAs("base/" . $this->basename . ".rdf", "rdf");
    }

    /**
     * get the base model
     * @return OntModel
     */
    public function loadBaseRDF() {
        $this->base = ModelFactory::getOntModel(MEMMODEL, RDFS_VOCABULARY);
        $this->base->load("base/" . $this->basename . ".rdf");
        $this->createBookClass();
        return $this->base;
    }

    /**
     * Close base
     */
    public function closeBaseRDF() {
        $this->base->saveAs("base/" . $this->basename . ".rdf", "rdf");
        //$this->base->close();
    }

    /**
     * @return OntClass
     */
    public function getAuthorInstance() {
        return $this->author_class->createInstance();
    }

    /**
     * @return OntClass
     */
    public function getBookInstance($uri) {
        return $this->book_class->createInstance($uri);
    }

    /**
     * @return OntClass
     */
    public function getAuthorOntClass() {
        return $this->author_class;
    }

    /**
     * @return OntClass
     */
    public function getBookOntClass() {
        return $this->book_class;
    }

    private function createBookClass() {
        $this->book_class = $this->base->createOntClass(BOOK_NS);

        $book_label = new ResLiteral("Book");
        $book_comment = new ResLiteral("Structured type of documment describing a Book");
        $this->book_class->addComment($book_comment);
        $this->book_class->addLabelProperty($book_label);


        $this->createTriplet(BOOK_NS, $this->book_class, "kind", "kind");
        $this->createTriplet(BOOK_NS, $this->book_class, "id", "id");
        $this->createTriplet(BOOK_NS, $this->book_class, "etag", "etag");
        $this->createTriplet(BOOK_NS, $this->book_class, "title", "title");
        $this->createTriplet(BOOK_NS, $this->book_class, "publicher", "publicher");
        $this->createTriplet(BOOK_NS, $this->book_class, "publiching_date", "publiching_date");
        $this->createTriplet(BOOK_NS, $this->book_class, "description", "description");
        $this->createTriplet(BOOK_NS, $this->book_class, "isbn_10", "isbn_10");
        $this->createTriplet(BOOK_NS, $this->book_class, "isbn_13", "isbn_13");
        $this->createTriplet(BOOK_NS, $this->book_class, "page_count", "page_count");
        $this->createTriplet(BOOK_NS, $this->book_class, "print_type", "print_type");
        $this->createTriplet(BOOK_NS, $this->book_class, "average_rating", "average_rating");
    }

    /**
     * 
     * @param string $NS   Name Space
     * @param string $class 
     * @param string $literal_name
     * @param string $property_name
     */
    private function createTriplet($NS, $class, $literal_name, $property_name) {
        $property_ = $this->base->createOntProperty($NS . $property_name);
        $literal_ = new ResLiteral($literal_name);
        $class->addPropertyWithoutDuplicate($property_, $literal_);
    }

    /**
     * 
     * @return OntModel
     */
    public function getBase() {
        return $this->base;
    }

}

/* $property_average_rating = $this->base->createOntProperty(BOOK_NS . 'average_rating');
  $average_rating_literal = new ResLiteral("average_rating");
  $this->book_class->addPropertyWithoutDuplicate($property_average_rating, $average_rating_literal);

  $property_id = $this->base->createOntProperty(BOOK_NS . 'id');
  $id_literal = new ResLiteral("id");
  $this->book_class->addPropertyWithoutDuplicate($property_id, $id_literal);

  $property_etag = $this->base->createOntProperty(BOOK_NS . 'etag');
  $etag_literal = new ResLiteral("etag");
  $this->book_class->addPropertyWithoutDuplicate($property_etag, $etag_literal);

  $property_title = $this->base->createOntProperty(BOOK_NS . 'title');
  $title_literal = new ResLiteral("title");
  $this->book_class->addPropertyWithoutDuplicate($property_title, $title_literal);

  $property_publicher = $this->base->createOntProperty(BOOK_NS . 'publicher');
  $publisher_literal = new ResLiteral("publisher");
  $this->book_class->addPropertyWithoutDuplicate($property_publicher, $publisher_literal);

  $property_publiching_date = $this->base->createOntProperty(BOOK_NS . 'publiching_date');
  $publiching_date_literal = new ResLiteral("publiching_date");
  $this->book_class->addPropertyWithoutDuplicate($property_publiching_date, $publiching_date_literal);

  $property_description = $this->base->createOntProperty(BOOK_NS . 'description');
  $description_literal = new ResLiteral("description");
  $this->book_class->addPropertyWithoutDuplicate($property_description, $description_literal);

  $property_isbn_10 = $this->base->createOntProperty(BOOK_NS . 'isbn_10');
  $isbn_10_literal = new ResLiteral("isbn_10");
  $this->book_class->addPropertyWithoutDuplicate($property_isbn_10, $isbn_10_literal);

  $property_isbn_13 = $this->base->createOntProperty(BOOK_NS . 'isbn_13');
  $isbn_13_literal = new ResLiteral("isbn_13");
  $this->book_class->addPropertyWithoutDuplicate($property_isbn_13, $isbn_13_literal);

  $property_page_count = $this->base->createOntProperty(BOOK_NS . 'page_count');
  $page_count_literal = new ResLiteral("page_count");
  $this->book_class->addPropertyWithoutDuplicate($property_page_count, $page_count_literal);

  $property_print_type = $this->base->createOntProperty(BOOK_NS . 'print_type');
  $print_type_literal = new ResLiteral("print_type");
  $this->book_class->addPropertyWithoutDuplicate($property_print_type, $print_type_literal);// */
?>