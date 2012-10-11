<?php
//define("RDFAPI_INCLUDE_DIR", "C:/Program Files (x86)/EasyPHP-12.1/www/WebAvancee/lib/rdf_api/api/"); 
//include(RDFAPI_INCLUDE_DIR . "RdfAPI.php");

include_once 'classes/GoogleBookApiCaller.php';
include_once 'classes/OntologyModel.php';
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


$ont = new OntologyModel();
$base = $ont->getBaseRDF();
foreach ($books as $book){
    $book->Generate_Book_RDF($base);
}


echo '</br>';


?>
