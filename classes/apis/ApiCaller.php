<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of ApiCaller
 *
 * @author Rabah
 */
class ApiCaller {

    protected function callApi($url, $isHTTPS = false) {
        var_dump($url);
        echo "<br/>";
        
        
        $c = curl_init();
        curl_setopt($c, CURLOPT_URL, $url);
        curl_setopt($c, CURLOPT_RETURNTRANSFER, true);
        //curl_setopt($c, CURLOPT_HEADER, false);
        //curl_setopt($c, CURLOPT_FOLLOWLOCATION, true);
        if($isHTTPS){
            curl_setopt($c, CURLOPT_SSL_VERIFYPEER, false);
        }
        $result = curl_exec($c);

        return $result;
    }

}

?>
