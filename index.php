<?php
//define("RDFAPI_INCLUDE_DIR", "C:/Program Files (x86)/EasyPHP-12.1/www/WebAvancee/lib/rdf_api/api/"); 
//include(RDFAPI_INCLUDE_DIR . "RdfAPI.php");

include_once 'classes/GoogleBookApiCaller.php';
include_once 'classes/Author.php';
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */




echo ' <h1> testCurl</h1> ';


/*$model1 = new MemModel();
$model1->load("base.rdf");

$query = "SELECT ?titre
WHERE
{
  <https://www.googleapis.com/books/v1/volumes/R1g7aLtLMAkC> <http://www.googleapi.com/book/titre> ?titre .
}";

$result = $model1->sparqlQuery($query);

var_dump($result);
//*/

$googleapi = new GoogleBookApiCaller();

$books = $googleapi->callAuthor("yasmina khadra",'fr',true);

foreach ($books as $book){
    $book->Generate_Book_RDF();
}


echo '</br>';
//$auteur1->Generate_RDF();

/*
        $base = new MemModel();
        $base->load("base.rdf");
        
        $subject = new Resource ("https://www.googleapis.com/books/v1/volumes/"); 
        //Creating Property
        $property = new Resource ();
        
        $base->addWithoutDuplicates(new Statement($subject, new Resource("http://www.example.org/myVocabulary/id"), new Literal("id")));
        $base->addWithoutDuplicates(new Statement($subject, new Resource("http://www.example.org/myVocabulary/titre"), new Literal("titre")));
        $base->addWithoutDuplicates(new Statement($subject, new Resource("http://www.example.org/myVocabulary/publisher"), new Literal("publisher")));
        $base->addWithoutDuplicates(new Statement($subject, new Resource("http://www.example.org/myVocabulary/number-of-pages"), new Literal("nb_pages")));
        $base->addWithoutDuplicates(new Statement($subject, new Resource("http://www.example.org/myVocabulary/isbn-10"), new Literal("isbn_10")));
        $base->addWithoutDuplicates(new Statement($subject, new Resource("http://www.example.org/myVocabulary/isbn-13"), new Literal("isbn_13")));
        
        $base->saveAs("base.rdf", "rdf");
        $base->close();
 * 
 */


?>
