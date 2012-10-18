<?php
define('BOOK_NS', 'http://www.googleapi.com/book/');

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
    
    function generate_book_instance(OntModel $base , OntClass $book_class){
        
        $instance = $book_class->createInstance($this->selfLink);
        
        $kind = $base->createLiteral($this->kind);
        $id =  $base->createLiteral($this->id);
        $etag =  $base->createLiteral($this->etag);
        $kind->setDatatype("http://www.w3.org/TR/xmlschema-2/#rf-enumeration");        
        $id->setDatatype("http://www.w3.org/TR/xmlschema-2/#ID"); 
        $etag->setDatatype("http://www.w3.org/TR/xmlschema-2/#string");
        
        
        foreach($book_class->listProperties() as $property){
            
            if($property->getLabelObject() == 'kind'){
                echo $property->getPredicate();
                echo '<br/>'.$property->pred;
                
                
            }
            if($property->getLabelObject() == 'id'){
                //echo $property->getPredicate();
                //$instance->addProperty($property->getPredicate() , $id);
            }
            if($property->getLabelObject() == 'etag'){
                //echo $property->getSubject();
                //$instance->addProperty($property->getPredicate() , $etag);
            }
        }
 
        $instance1 = $book_class->createInstance("testinstance");
        $ppt = $base->createOntProperty("http://../pt1");
        $x = $base->createLiteral("lit");        
        $instance1->addProperty($ppt , $x);
        
        
        
        //echo '<br/><br/>'.$instance->toString().'final<br/><br/>';
    }

    function generate_book_rdf(OntModel $base, OntClass $bookClass) {       
        
        $this->generate_book_instance($base , $bookClass);         
       
/*
        $class_vars = get_class_vars(get_class($this));

        foreach ($class_vars as $name => $value) {
            if ($name != "authors" || $name != "categories" || $name != "industryIdentifiers ") {
                echo $name . " : " . $this->$name . "<br/>";
                //$base->addWithoutDuplicates(new Statement($subject, new Resource("http://www.googleapi.com/book/".$name), new Literal($this->$name)));
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
        
        
        /*
        $title =  $base->createLiteral($this->title);
        $title->setDatatype("http://xmlns.com/foaf/spec/#term_title");
        $title_p =$base->createProperty(BOOK_NS.'title');
        $subject->addProperty($title_p,$title);
        
        $publisher =  $base->createLiteral($this->publisher);
        $publisher_p =$base->createProperty(BOOK_NS.'publisher');
        $subject->addProperty($publisher_p,$publisher);
        
        $publiched_date =  $base->createLiteral($this->publishedDate);
        $publiched_date->setDatatype("http://www.w3.org/TR/xmlschema-2/#date");
        $publiched_date_p =$base->createProperty(BOOK_NS.'publiching_date');
        $subject->addProperty($publiched_date_p,$publiched_date);
        
        $description =  $base->createLiteral($this->description);
        $description->setDatatype("http://www.w3.org/TR/xmlschema-2/#string");
        $description_p =$base->createProperty(BOOK_NS.'description');
        $subject->addProperty($description_p,$description);
        
        foreach ($this->industryIdentifiers as $identifiers){
            //echo $identifiers['type'].'->'.$identifiers['identifier'].'<br/>';
            $identity =  $base->createLiteral($identifiers['identifier']);
            $identity_p =$base->createProperty(BOOK_NS.$identifiers['type']);
            $subject->addProperty($identity_p,$identity);
        }
        
        $page_count =  $base->createLiteral($this->pageCount);
        $page_count->setDatatype("http://www.w3.org/TR/xmlschema-2/#integer");
        $page_count_p =$base->createProperty(BOOK_NS.'page_count');
        $subject->addProperty($page_count_p,$page_count);
        
        $print_typet =  $base->createLiteral($this->printType);
        $print_type_p =$base->createProperty(BOOK_NS.'print_type');
        $subject->addProperty($print_type_p,$print_typet);
        
        foreach ($this->categories as $categorie){
            $category =  $base->createLiteral($categorie);
            $category_p =$base->createProperty(BOOK_NS.'category');
            $subject->addProperty($category_p,$category);
        }
        
        $average_rating =  $base->createLiteral($this->averageRating);
        $average_rating->setDatatype("http://www.w3.org/TR/xmlschema-2/#float");
        $average_rating_p =$base->createProperty(BOOK_NS.'average_rating');
        $subject->addProperty($average_rating_p,$average_rating);
        
        $rating_count =  $base->createLiteral($this->ratingsCount);
        $rating_count->setDatatype("http://www.w3.org/TR/xmlschema-2/#integer");
        $rating_count_p =$base->createProperty(BOOK_NS.'rating_count');
        $subject->addProperty($rating_count_p,$rating_count);
        
        $contentversion =  $base->createLiteral($this->contentVersion);
        $content_version_p =$base->createProperty(BOOK_NS.'content_version');
        $subject->addProperty($content_version_p,$contentversion);
        
        $language =  $base->createLiteral($this->language);
        $language_p =$base->createProperty(BOOK_NS.'language');
        $subject->addProperty($language_p,$language);
       
        $preview_link =  $base->createLiteral($this->previewLink);
        $preview_link->setDatatype("http://www.w3.org/TR/xmlschema-2/#string");
        $preview_link_p =$base->createProperty(BOOK_NS.'preview_link');
        $subject->addProperty($preview_link_p,$preview_link);
        
        
        $info_link =  $base->createLiteral($this->infoLink);
        $info_link_p =$base->createProperty(BOOK_NS.'info_link');
        $subject->addProperty($info_link_p,$info_link);
        
        $country =  $base->createLiteral($this->country);
        $country_p =$base->createProperty(BOOK_NS.'country');
        $subject->addProperty($country_p,$country);
        
        $saleability =  $base->createLiteral($this->saleability);
        $saeleability_p =$base->createProperty(BOOK_NS.'saleability');
        $subject->addProperty($saeleability_p,$saleability);
        
        $is_ebook =  $base->createLiteral($this->isEbook);
        $is_ebook_p =$base->createProperty(BOOK_NS.'is_ebook');
        $subject->addProperty($is_ebook_p,$is_ebook);
        
        $price =  $base->createLiteral($this->price);
        $price_p =$base->createProperty(BOOK_NS.'price');
        $subject->addProperty($price_p,$price);
        
        $buy_link =  $base->createLiteral($this->buyLink);
        $buy_link_p =$base->createProperty(BOOK_NS.'buy_link');
        $subject->addProperty($buy_link_p,$buy_link);
        
        $viewability =  $base->createLiteral($this->viewability);
        $viewability_p =$base->createProperty(BOOK_NS.'viewability');
        $subject->addProperty($viewability_p,$viewability);
        
        $embeddable =  $base->createLiteral($this->embeddable);
        $embeddable_p =$base->createProperty(BOOK_NS.'embeddable');
        $subject->addProperty($embeddable_p,$embeddable);
        
        $public_domain =  $base->createLiteral($this->publicDomain);
        $public_domain_p =$base->createProperty(BOOK_NS.'public_domain');
        $subject->addProperty($public_domain_p,$public_domain);
        
        $text_snippet =  $base->createLiteral($this->textSnippet);
        $text_snippet_p =$base->createProperty(BOOK_NS.'text_snippet');
        $subject->addProperty($text_snippet_p,$text_snippet);
        
        $epub_available =  $base->createLiteral($this->epubAvailable);
        $epub_available_p =$base->createProperty(BOOK_NS.'epub_available');
        $subject->addProperty($epub_available_p,$epub_available);
        
        $epub_link =  $base->createLiteral($this->epubLink);
        $epub_link_p =$base->createProperty(BOOK_NS.'epub_link');
        $subject->addProperty($epub_link_p,$epub_link);
        
        $pdf_available =  $base->createLiteral($this->pdfAvailable);
        $pdf_available_p =$base->createProperty(BOOK_NS.'pdf_available');
        $subject->addProperty($pdf_available_p,$pdf_available);
        
        $pdf_link =  $base->createLiteral($this->pdfLink);
        $pdf_link_p =$base->createProperty(BOOK_NS.'pdf_link');
        $subject->addProperty($pdf_link_p,$pdf_link);
        
        $web_reader_link =  $base->createLiteral($this->webReaderLink);
        $web_reader_link_p =$base->createProperty(BOOK_NS.'web_reader_link');
        $subject->addProperty($web_reader_link_p,$web_reader_link);

        //creating and adding bag into the ressource book 
        define('AUTHOR_NS', 'http://www.googleapi.com/author/');
        $bag_authors = $base->createBag();
        $author_p =$base->createProperty(AUTHOR_NS.'authors');
        $subject->addProperty($author_p,$bag_authors);    
        //Creating the authorts riplets
        foreach($this->authors as $author){
            $instance = $authorClass->createInstance(AUTHOR_NS.$author);
            $lit = $base->createLiteral($author); 
            $ppt = $base->createOntProperty(AUTHOR_NS);
            //$instance->addProperty($ppt,$lit);
            $instance->setPropertyValue($ppt, $lit);
            $bag_authors->add($instance);
            
            //$res = $base->createResource('http://../changethiswithDBPediaLink/authorName/'.$author);
            //$lit = $base->createLiteral($author);              
            //$res->addProperty($author_p,$lit);
            //Addingitto the bag
            //$bag_authors->add($res);
        }   
        
        */
        
        //$base->close();
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
