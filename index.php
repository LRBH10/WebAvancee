<?php
define("RDFAPI_INCLUDE_DIR", "C:/Program Files (x86)/EasyPHP-12.1/www/WebAvancee/lib/rdf_api/api/"); 
//include(RDFAPI_INCLUDE_DIR . "RdfAPI.php");
//define("RDFAPI_INCLUDE_DIR", "C:/wamps/www/WebAvancee/lib/rdf_api/api/");
include(RDFAPI_INCLUDE_DIR . "RdfAPI.php");
include(RDFAPI_INCLUDE_DIR . "resModel\\ResModelP.php");
include(RDFAPI_INCLUDE_DIR . "ontModel\\OntModelP.php");
include( RDFAPI_INCLUDE_DIR . "vocabulary\\RDF_RES.php");

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


*/
$ontModel = ModelFactory::getOntModel(MEMMODEL,RDFS_VOCABULARY);

$documment = $ontModel->createOntClass("documment");


$book = $ontModel->createOntClass("bookNS"."book");

$book->addSuperClass($documment);

$comment= new ResLiteral("this is my comment");

$book->addComment($comment);

$resLiteral = new ResLiteral("blazoo");

$book->addLabelProperty($resLiteral);

$book1 = $book->createInstance("http://../book1");

$property = $ontModel->createOntProperty("1stppt");
$lit =  $ontModel->createLiteral("xxx");
$lit->setDatatype("http://www.w3.org/TR/xmlschema-2/#rf-enumeration");

$book1->addProperty($property, $lit);


$ontModel->saveAs("test.rdf","rdf");

$x = new OntologyModel();

$googleapi = new GoogleBookApiCaller();
$books = $googleapi->callAuthor("yasmina khadra",'fr',true);

foreach ($books as $book){
    $book->Generate_Book_RDF($x->getBaseRDF(), $x->getAuthorClass(), $x->getBookClass());
}

?>