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


$x = new OntologyModel();

$googleapi = new GoogleBookApiCaller();
$books = $googleapi->callAuthor("yasmina khadra",'fr',true);

foreach ($books as $book){
    $book->Generate_Book_RDF($x->getBaseRDF());
}

/*
echo 'finish</br>';

$model = ModelFactory::getOntModel(MEMMODEL,RDFS_VOCABULARY);

$fullNameLiteral = $model->createLiteral("Blazo Nastov");
$johnSmith = $model->createResource("http://../blazonastov");
define('VCARD_NS', 'http://www.w3.org/2001/vcard-rdf/3.0#');
$vcard_FN= $model->createProperty(VCARD_NS.'FN');

$johnSmith1 = $model->createResource("http://../blazonastov1");

$johnSmith->addProperty($vcard_FN, $fullNameLiteral);
$johnSmith->addProperty($vcard_FN, $johnSmith1);

$fullNameLiteral1 = $model->createLiteral("Blazo");
$fullNameLiteral2 = $model->createLiteral("Nastov");

$johnSmith1->addProperty($vcard_FN, $fullNameLiteral1);
$johnSmith1->addProperty($vcard_FN, $fullNameLiteral2);
$model->saveAs("test.rdf", "rdf");

*/
?>