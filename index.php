<?php

include_once 'classes/GoogleBookApiCaller.php';
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
echo ' <h1> testCurl</h1> ';

$googleapi = new GoogleBookApiCaller();

$googleapi->callAuthor("test");

?>
