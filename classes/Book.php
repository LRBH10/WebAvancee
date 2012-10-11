<?php

//define("RDFAPI_INCLUDE_DIR", "C:/Program Files (x86)/EasyPHP-12.1/www/WebAvancee/lib/rdf_api/api/"); 
define("RDFAPI_INCLUDE_DIR", "C:/wamps/www/WebAvancee/lib/rdf_api/api/");

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

    var $kind;
    var $id;
    var $etag;
    var $selfLink;
    var $title;
    var $description;
    var $publisher;
    var $publishedDate;
    var $pageCount;
    var $authors; //[]
    var $industryIdentifiers; //[]
    var $printType;
    var $contentVersion;
    var $smallThumbnail;
    var $thumbnail;
    var $language;
    var $previewLink;
    var $infoLink;
    var $canonicalVolumeLink;
    var $categories; //[]
    var $averageRating;
    var $ratingsCount;
    var $country;
    var $saleability;
    var $isEbook;
    var $price;
    var $priceSymbol;
    var $buyLink;
    var $viewability;
    var $embeddable;
    var $publicDomain;
    var $epubAvailable;
    var $epubLink;
    var $pdfAvailable;
    var $pdfLink;
    var $webReaderLink;
    var $textSnippet;

    public function __construct() {
        $this->authors = array();
        $this->industryIdentifiers = array();
        $this->categories = array();
    }

    function Generate_Book_RDF($base) {

        $subject = new Resource($this->selfLink);

        $class_vars = get_class_vars(get_class($this));

        foreach ($class_vars as $name => $value) {
            if ($name != "authors" || $name != "categories" || $name != "industryIdentifiers ") {
                echo $name . " : " . $this->$name . "<br/>";
                $base->addWithoutDuplicates(new Statement($subject, new Resource("http://www.googleapi.com/book/".$name), new Literal($this->$name)));
            }
            else if($name == "authors"){
                
            }
            else if($name == "categories"){
                
            }
            else if($name == "industryIdentifiers"){
                
            }
        }

        /* $subject = new Resource("https://www.googleapis.com/books/v1/volumes/" . $this->id);

          $attributes = get_object_vars($this);
          $base->addWithoutDuplicates(new Statement($subject, new Resource("http://www.googleapi.com/book/id"), new Literal($this->id)));
          $base->addWithoutDuplicates(new Statement($subject, new Resource("http://www.googleapi.com/book/titre"), new Literal($this->titre)));
          $base->addWithoutDuplicates(new Statement($subject, new Resource("http://www.googleapi.com/book/publisher"), new Literal($this->publisher)));
          $base->addWithoutDuplicates(new Statement($subject, new Resource("http://www.googleapi.com/book/number-of-pages"), new Literal($this->nb_pages)));
          $base->addWithoutDuplicates(new Statement($subject, new Resource("http://www.googleapi.com/book/isbn-10"), new Literal($this->isbn_10)));
          $base->addWithoutDuplicates(new Statement($subject, new Resource("http://www.googleapi.com/book/isbn-13"), new Literal($this->isbn_13)));
         */
    }

    public static function parseFromJson($object) {
        $book = new Book();
        $volumeinfo = $object['volumeInfo'];
        $saleInfo = $object['saleInfo'];
        $accessInfo = $object['accessInfo'];

        if (array_key_exists('authors', $volumeinfo)) {
            $book->authors = $volumeinfo['authors'];
        }



        if (array_key_exists('averageRating', $volumeinfo)) {
            $book->averageRating = $volumeinfo['averageRating'];
        }

        if (array_key_exists('buyLink', $saleInfo)) {
            $book->buyLink = $saleInfo['buyLink'];
        }

        if (array_key_exists('categories', $volumeinfo)) {
            $book->categories = $volumeinfo['categories'];
        }

        if (array_key_exists('cononicalVolumeLink', $volumeinfo)) {
            $book->cononicalVolumeLink = $volumeinfo['cononicalVolumeLink'];
        }

        if (array_key_exists('contentVersion', $volumeinfo)) {
            $book->contentVersion = $volumeinfo['contentVersion'];
        }

        if (array_key_exists('country', $saleInfo)) {
            $book->country = $saleInfo['country'];
        }

        if (array_key_exists('description', $volumeinfo)) {
            $book->description = $volumeinfo['description'];
        }

        if (array_key_exists('embeddable', $accessInfo)) {
            $book->embeddable = $accessInfo['embeddable'];
        }

        if (array_key_exists('epub', $accessInfo)) {
            if (array_key_exists('isAvailable', $accessInfo['epub'])) {
                $book->epubAvailable = $accessInfo['epub']['isAvailable'];
            }
            if (array_key_exists('acsTokenLink', $accessInfo['epub'])) {
                $book->epubLink = $accessInfo['epub']['acsTokenLink'];
            }
        }

        $book->etag = $object['etag'];

        $book->id = $object['id'];

        if (array_key_exists('industryIdentifiers', $volumeinfo)) {
            $book->industryIdentifiers = $volumeinfo['industryIdentifiers'];
        }

        if (array_key_exists('infoLink', $volumeinfo)) {
            $book->infoLink = $volumeinfo['infoLink'];
        }

        if (array_key_exists('isEbook', $saleInfo)) {
            $book->isEbook = $saleInfo['isEbook'];
        }
        $book->kind = $object['kind'];

        if (array_key_exists('language', $volumeinfo)) {
            $book->language = $volumeinfo['language'];
        }


        if (array_key_exists('pdf', $accessInfo)) {
            if (array_key_exists('isAvailable', $accessInfo['pdf'])) {
                $book->epubAvailable = $accessInfo['pdf']['isAvailable'];
            }
            if (array_key_exists('acsTokenLink', $accessInfo['pdf'])) {
                $book->epubLink = $accessInfo['pdf']['acsTokenLink'];
            }
        }

        if (array_key_exists('previewLink', $volumeinfo)) {
            $book->previewLink = $volumeinfo['previewLink'];
        }


        if (array_key_exists('listPrice', $saleInfo)) {
            if (array_key_exists('amount', $saleInfo['listPrice'])) {
                $book->price = $saleInfo['listPrice']['amount'];
            }
            if (array_key_exists('currencyCode', $saleInfo['listPrice'])) {
                $book->priceSymbol = $saleInfo['listPrice']['currencyCode'];
            }
        }

        if (array_key_exists('printType', $volumeinfo)) {
            $book->printType = $volumeinfo['printType'];
        }

        if (array_key_exists('publicDomain', $accessInfo)) {
            $book->publicDomain = $accessInfo['publicDomain'];
        }

        if (array_key_exists('publishedDate', $volumeinfo)) {
            $book->publishedDate = $volumeinfo['publishedDate'];
        }

        if (array_key_exists('publisher', $volumeinfo)) {
            $book->publisher = $volumeinfo['publisher'];
        }
        if (array_key_exists('ratingsCount', $volumeinfo)) {
            $book->ratingsCount = $volumeinfo['ratingsCount'];
        }


        if (array_key_exists('saleability', $saleInfo)) {
            $book->saleability = $saleInfo['saleability'];
        }

        $book->selfLink = $object['selfLink'];


        if (array_key_exists('imageLinks', $volumeinfo)) {
            if (array_key_exists('smallThumbnail', $volumeinfo['imageLinks'])) {
                $book->smallThumbnail = $volumeinfo['imageLinks']['smallThumbnail'];
            }
            if (array_key_exists('thumbnail', $volumeinfo['imageLinks'])) {
                $book->thumbnail = $volumeinfo['imageLinks']['thumbnail'];
            }
        }

        if (array_key_exists('searchInfo', $object)) {
            if (array_key_exists('textSnippet', $object['searchInfo'])) {
                $book->textSnippet = $object['searchInfo']['textSnippet'];
            }
        }


        if (array_key_exists('title', $volumeinfo)) {
            $book->title = $volumeinfo['title'];
        }

        if (array_key_exists('viewability', $accessInfo)) {
            $book->viewability = $accessInfo['viewability'];
        }
        if (array_key_exists('webReaderLink', $accessInfo)) {
            $book->webReaderLink = $accessInfo['webReaderLink'];
        }

        return $book;
    }

    //put your code here
}

?>
