<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
include_once 'ApiCaller.php';

/**
 * Description of FacebookApiCaller
 * Class that call Graph Facebook to get a public data
 * @author Rabah
 */
class FacebookApiCaller extends ApiCaller {

    /**
     * 
     * @var Oauth of facebook  
     */
    var $key = "AAACEdEose0cBAHNUPuG1JMXhAXFDZATXYDv3ZB5uhVK7dvsgkBG4JJwL91MfL8kVSf0SWb1eDrVZCtxAWvXffJzxHIrSEvXJcou0bZCE4Dbo647b2MPq";

    /**
     *
     * @var uri to get the best page of an author 
     */
    var $url = "https://graph.facebook.com/search?limit=1&type=page&q=";

    /**
     *
     * @var uri to get the details of the best page
     */
    var $url_detail = "https://graph.facebook.com/";

    /**
     * to get a detail for authors
     * @param string $author  name of author
     * @return Author 
     */
    public function searchAuthor($author) {
        $author_ = urlencode($author);

        /*
         * get the most page with author name
         */
        $url_ = $this->url . $author_;
        $result1 = $this->callApi($url_, true);
        $results1 = json_decode($result1, TRUE);

        /**
         * get the ID of the most page author (the first)
         */
        $id = $results1['data'][0]['id'];

        /**
         * get the details
         */
        $url_ = $this->url_detail . $id;
        $result = $this->callApi($url_, true);
        $results = json_decode($result, TRUE);
        return $results;
    }

}

?>
