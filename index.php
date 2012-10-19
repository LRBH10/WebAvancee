<?php

include 'includes.php';

$test = new OntologyModel("test");

//$test->createOntologyModel();


$test->loadBaseRDF();

$instance = $test->getBookInstance("sddsfsd");

$pp = $test->getBase()->createOntProperty("propriété");
$lit = $test->getBase()->createLiteral("literal");

$instance->addProperty($pp, $lit);


$test->closeBaseRDF();//*/
?>






<?php

    /******************* RABAH TEST ************************/
/*$reader = new GoodReadApiCaller();
$result = $reader->searchAuthor("yasmina khadra");



var_dump(GoodReadAuthor::parsefromXML($result));
echo "<br/>";

  $goo = new GoogleBookApiCaller();
 $goo ->callAuthor("yasmina khadra");

$face = new FacebookApiCaller();
$json_o = $face->searchAuthor("yasmina khadra");

//var_dump(FacebookAuthor::getFacebookDetailsFromJson($json_o));
var_dump(Author::getAuthor($json_o, $result));
 
 */
?>