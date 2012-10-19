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
class GoodReadApiCaller extends ApiCaller {

    //put your code here
    var $key = "IJ9e4JY4Gnl1JU5ADSYYg";
    var $url_search = "http://www.goodreads.com/search.xml";
    var $url_author = "http://www.goodreads.com/author/show?id=";

    public function searchAuthor($author) {
        /**
         * get author id
         */
        $author_ = urlencode($author);
        $url_ = $this->url_search . "?key=" . $this->key . "&q=" . $author_ . "&search[field]=author";
        $result = $this->callApi($url_);

        $id = $this->parserXMLidAuthor($result);

        /**
         * get author details
         */
        $url_ = $this->url_author . $id . "&key=".  $this->key;
        $result = $this->callApi($url_);
        
        
        return $result;
        
    }

    private function parserXMLidAuthor($xml_str) {
        $result = "";
        $dom = new DOMDocument();
        $dom->loadXML($xml_str);
        $nodes = $dom->getElementsByTagName("id");
        foreach ($nodes as $node) {
            if ($node->getNodePath() == "/GoodreadsResponse/search/results/work[1]/best_book/author/id")
                $result = $node->nodeValue;
        }
        return $result;
    }

}

?>
