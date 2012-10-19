<?php

include 'includes.php';

$baseOnt = new OntologyModel("test");
$baseOnt->loadBaseRDF();

$goo = new GoogleBookApiCaller();
$list_of_books = $goo->callAuthor("yasmina khadra");

$list_of_books[1]->generate_book_rdf($baseOnt);


$baseOnt->closeBaseRDF();
?>






<?php

/* $goo = new GoogleBookApiCaller();
  $list_of_books = $goo->callAuthor("yasmina khadra");

  foreach ($list_of_books as $book) {
  $book->generate_book_rdf($test);
  }

  print_r($list_of_books);
  // */


/* * ***************** RABAH TEST *********************** */
/* $reader = new GoodReadApiCaller();
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