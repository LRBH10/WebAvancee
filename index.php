<?php
include 'classes/includes.php';

/*$reader = new GoodReadApiCaller();
$reader->searchAuthor("yasmina khadra");

$goo = new GoogleBookApiCaller();
$goo ->callAuthor("yasmina khadra");//*/

$face = new FacebookApiCaller();
$face->searchAuthor("yasmina khadra");
?>
 
 