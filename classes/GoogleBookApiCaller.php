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
class GoogleBookApiCaller {

    //URL of google book api
    var $key = "AIzaSyBaZwSa7tSjJnWmiCGYnEY087u-P-aGFGE";
    var $url = "https://www.googleapis.com/books/v1/volumes?q=";

    //put your code here
    function callAuthor($author) {
        $url_ = "https://www.googleapis.com/books/v1/volumes?q=yasmina&key=AIzaSyBaZwSa7tSjJnWmiCGYnEY087u-P-aGFGE";

        //DEBUG
        var_dump($url_);

//Curl Requete
        $c = curl_init();
        curl_setopt($c, CURLOPT_URL, $url_);
        curl_setopt($c, CURLOPT_RETURNTRANSFER, true);
        curl_setopt($c, CURLOPT_HEADER, false);
        curl_setopt($c, CURLOPT_FOLLOWLOCATION, true);
        $resultat = curl_exec($c);

        //DEBUG
        echo "<br/>";
        var_dump($resultat); // */
    }

}

?>
