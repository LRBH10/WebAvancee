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

    //put your code here
    function callAuthor($author) {
       $URL = 'www.google.fr';
       
        $ch = curl_init();
        curl_setopt($ch, CURLOPT_URL, $URL);
        curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1);
        $resultat = curl_exec($ch);

        var_dump($resultat);
        
    }

}

?>
