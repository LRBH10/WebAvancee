<?php

/*
 * 19 / 10 / 2012
 * @author Rabah
 */

/**
 * @FacebookAuthor  contains details come from Facebook Graph 
 * @link https://developers.facebook.com/docs/reference/api/ 
 */
class FacebookAuthor {

    var $name;
    var $likes;
    var $id;
    var $talking_about_count;
    var $link;

    public static function getFacebookDetailsFromJson($json_object) {
        $authorFace = new FacebookAuthor();
        if (array_key_exists('name', $json_object)) {
            $authorFace->name = $json_object['name'];
        }
        if (array_key_exists('likes', $json_object)) {
            $authorFace->likes = $json_object['likes'];
        }
        if (array_key_exists('id', $json_object)) {
            $authorFace->id = $json_object['id'];
        }
        if (array_key_exists('talking_about_count', $json_object)) {
            $authorFace->talking_about_count = $json_object['talking_about_count'];
        }
        if (array_key_exists('link', $json_object)) {
            $authorFace->link = $json_object['link'];
        }
        return $authorFace;
    }

}

class GoodReadAuthor {
    
}

/**
 * Description of Author
 *
 * 
 */
class Author {

    var $read;
    var $face;

    public function getFacebookDetailFromJson($json_o){
        $this->face = new FacebookAuthor();
        $this->face = FacebookAuthor::getFacebookDetailsFromJson($json_o);
    }
}

?>
