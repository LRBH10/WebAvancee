<?php
define("RDFAPI_INCLUDE_DIR", "C:/Program Files (x86)/EasyPHP-12.1/www/WebAvancee/lib/rdf_api/api/"); 
//define("RDFAPI_INCLUDE_DIR", "C:/wamp/www/WebAvancee/lib/rdf_api/api/"); 

include(RDFAPI_INCLUDE_DIR . "RdfAPI.php");
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of Book
 *
 * @author bibouh & blazo
 */
class Book {

    var $id;
    var $titre;
    var $publisher;
    var $date_pub;
    var $nb_pages;
    var $isbn_10;
    var $isbn_13;

    public function __construct($id, $titre, $publisher, $date_pub, $nb_pages, $isbn_10, $isbn_13) {
        $this->id = $id;
        $this->titre = $titre;
        $this->publisher = $publisher;
        $this->date_pub = $date_pub;
        $this->nb_pages = $nb_pages;
        $this->isbn_10 = $isbn_10;
        $this->isbn_13 = $isbn_13;
    }

    function Generate_Book_RDF() {
        $base = ModelFactory::getDefaultModel();
        $base->load("base.rdf");
        $base->addNamespace("book", "http://www.googleapi.com/book/");
        
        
        $subject = new Resource ("https://www.googleapis.com/books/v1/volumes/" . $this->id); 
        
        $base->addWithoutDuplicates(new Statement($subject, new Resource("http://www.googleapi.com/book/id"), new Literal($this->id)));
        $base->addWithoutDuplicates(new Statement($subject, new Resource("http://www.googleapi.com/book/titre"), new Literal($this->titre)));
        $base->addWithoutDuplicates(new Statement($subject, new Resource("http://www.googleapi.com/book/publisher"), new Literal($this->publisher)));
        $base->addWithoutDuplicates(new Statement($subject, new Resource("http://www.googleapi.com/book/number-of-pages"), new Literal($this->nb_pages)));
        $base->addWithoutDuplicates(new Statement($subject, new Resource("http://www.googleapi.com/book/isbn-10"), new Literal($this->isbn_10)));
        $base->addWithoutDuplicates(new Statement($subject, new Resource("http://www.googleapi.com/book/isbn-13"), new Literal($this->isbn_13)));
        
        $base->saveAs("base.rdf", "rdf");
        $base->close();
}

    public static function parseFromJson($object) {
        $id = $object['id'];
        $titre = $object['volumeInfo']['title'];
        if (array_key_exists('publisher', $object['volumeInfo'])) {
            $publisher = $object['volumeInfo']['publisher'];
        } else {
            $publisher = 'does not exist';
        }


        if (array_key_exists('publishedDate', $object['volumeInfo'])) {
            $date_pub = $object['volumeInfo']['publishedDate'];
        } else {
            $date_pub = 'does not exist';
        }


        if (count($object['volumeInfo']['industryIdentifiers']) == 2) {
            $isbn_10 = $object['volumeInfo']['industryIdentifiers'][0]['identifier'];
            $isbn_13 = $object['volumeInfo']['industryIdentifiers'][1]['identifier'];
        } else {
            $isbn_10 = 'does not exist';
            $isbn_13 = 'does not exist';
        }

        $new_book = new Book($id, $titre, $publisher, $date_pub, 100, $isbn_10, $isbn_13);
        return $new_book;
    }

    public static function debug($book) {
        echo $book->id . "<br/>" . $book->titre . "<br/>" . $book->date_pub . "<br/>" . $book->publisher . "<br/>" . $book->isbn_10 . "<br/>" . $book->isbn_13 ;
    }

    //put your code here
}

?>
