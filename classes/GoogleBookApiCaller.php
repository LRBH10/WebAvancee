<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
include_once 'ApiCaller.php';
include_once 'Book.php';

/**
 * Description of GoogleBookApiCaller
 *
 * @author bibouh
 */
class GoogleBookApiCaller extends ApiCaller {

    //URL of google book api
    var $key = "AIzaSyBaZwSa7tSjJnWmiCGYnEY087u-P-aGFGE";
    var $url = "https://www.googleapis.com/books/v1/volumes?maxResults=5&prettyPrint=false&printType=books&orderBy=relevance&q=";

    //put your code here
    function callAuthor($author) {
        $author_ = urlencode($author);
        $url_ = $this->url . "inauthor:" . $author_;//. "&langRestrict=" . $lang;
        //DEBUG
        var_dump($url_);
        echo "<br/>";

//Curl Requete
        $resultat = $this->callApi($url_, true);
        $json_result = json_decode($resultat, true);

        return $this->generateBooksOfAuthor($json_result);
    }

    function generateBooksOfAuthor($json_object) {
        $list_of_books = array();
        if ($json_object != null) {
            foreach ($json_object['items'] as $book) {
                //DEBUG
                echo "<br/>****************<br/><br/>";
                $list_of_books[] = Book::parseFromJson($book);
            }
        } else {
            echo "<br/> Fuck requete";
        }

        return $list_of_books;
    }

}

?>
