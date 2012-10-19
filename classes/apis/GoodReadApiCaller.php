<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
include_once 'ApiCaller.php';

/**
 * Description of GoodReadApiCaller
 *
 * @author Rabah
 */
class GoodReadApiCaller extends ApiCaller{

    //put your code here
    var $key = "IJ9e4JY4Gnl1JU5ADSYYg";
    var $url_search = "http://www.goodreads.com/search.xml";
    var $url_author = "http://www.goodreads.com/author/show/";

    public function searchAuthor($author) {
        $author_ = urlencode($author);
        $url_ = $this->url_search . "?key=" . $this->key . "&q=" . $author_ . "&search[field]=author";
        $result = $this->callApi($url_);
        //var_dump($result);
        //echo "<br/>";
        
    }

}

?>
