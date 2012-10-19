<?php

/*
 * includes RAP FILES 
 */

define("RDFAPI_INCLUDE_DIR", __DIR__."/lib/rdf_api/api/");                               
include(RDFAPI_INCLUDE_DIR . "RdfAPI.php");
include(RDFAPI_INCLUDE_DIR . "resModel\\ResModelP.php");
include(RDFAPI_INCLUDE_DIR . "ontModel\\OntModelP.php");
include( RDFAPI_INCLUDE_DIR . "vocabulary\\RDF_RES.php");
 
/**
 * defines 
 */
define('BOOK_NS', 'http://www.googleapi.com/book/');


/**
 * include our Files
 */
include_once 'classes/Book.php';
include_once 'classes/Author.php';
include_once 'classes/OntologyModel.php';

/**
 *  Includes Apis Files
 */
include_once 'classes/apis/GoodReadApiCaller.php';
include_once 'classes/apis/GoogleBookApiCaller.php';
include_once 'classes/apis/FacebookApiCaller.php';
?>
