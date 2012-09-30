<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of GoogleBookApiCaller
 *
 * @author bibouh
 */

include_once 'Auteur.php';
class GoogleBookApiCaller {

    //URL of google book api
    var $key = "AIzaSyBaZwSa7tSjJnWmiCGYnEY087u-P-aGFGE";
    var $url = "https://www.googleapis.com/books/v1/volumes?maxResults=40&prettyPrint=false&printType=books&orderBy=relevance&q=";

    //put your code here
    function callAuthor($author, $lang = 'fr' ) {
        
        $author = urlencode($author);
        $url_ = $this->url . "inauthor:" . $author . "&langRestrict=" . $lang ;

        //DEBUG
        var_dump($url_);
        echo "<br/>";
        
//Curl Requete
        $c = curl_init();
        curl_setopt($c, CURLOPT_URL, $url_);
        curl_setopt($c, CURLOPT_RETURNTRANSFER, true);
        curl_setopt($c, CURLOPT_HEADER, false);
        curl_setopt($c, CURLOPT_FOLLOWLOCATION, true);
        curl_setopt($c, CURLOPT_SSL_VERIFYPEER, false);
        $resultat = curl_exec($c);
        
        $json_result = json_encode($resultat);

        //DEBUG
        print $json_result; // */
        
        
        return $json_result;
    }

    function generateAuthor($json_object){
        
    }
}

?>
