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

    var $id;
    var $name;
    var $link;
    var $fans_count;
    var $image_url;
    var $about;
    var $works_count;
    var $gender;
    var $hometown;
    var $born_at;
    var $died_at;

    public static function parsefromXML($source) {
        $goodauthor = new GoodReadAuthor();

        $dom = new DOMDocument();
        $dom->loadXML($source);

        $goodauthor->id = $dom->getElementsByTagName('id')->item(0)->nodeValue;
        $goodauthor->name = $dom->getElementsByTagName('name')->item(0)->nodeValue;
        $goodauthor->link = $dom->getElementsByTagName('link')->item(0)->nodeValue;
        $goodauthor->fans_count = $dom->getElementsByTagName('fans_count')->item(0)->nodeValue;
        $goodauthor->image_url = $dom->getElementsByTagName('image_url')->item(0)->nodeValue;
        $goodauthor->about = $dom->getElementsByTagName('about')->item(0)->nodeValue;
        $goodauthor->works_count = $dom->getElementsByTagName('works_count')->item(0)->nodeValue;
        $goodauthor->gender = $dom->getElementsByTagName('gender')->item(0)->nodeValue;
        $goodauthor->hometown = $dom->getElementsByTagName('hometown')->item(0)->nodeValue;
        $goodauthor->born_at = $dom->getElementsByTagName('born_at')->item(0)->nodeValue;
        $goodauthor->died_at = $dom->getElementsByTagName('died_at')->item(0)->nodeValue;

        return $goodauthor;
    }

}

/**
 * 
 */
class Author {

    var $read;
    var $face;

    public static function searchAuthor($name_author) {
        $reader = new GoodReadApiCaller();
        $result = $reader->searchAuthor($name_author);

        $face = new FacebookApiCaller();
        $json_o = $face->searchAuthor($name_author);
    
        return Author::getAuthor($json_o, $result);
    }

    public static function getAuthor($json_array, $xml_source) {
        $author = new Author();

        $author->face = FacebookAuthor::getFacebookDetailsFromJson($json_array);
        $author->read = GoodReadAuthor::parsefromXML($xml_source);

        return $author;
    }

    public function generateAuthor($json_array, $xml_source) {
        $this->face = FacebookAuthor::getFacebookDetailsFromJson($json_array);
        $this->read = GoodReadAuthor::parsefromXML($xml_source);
    }

    public function getFacebookDetailFromJson($json_array) {
        $this->face = FacebookAuthor::getFacebookDetailsFromJson($json_array);
    }

    public function getGoodReadDetailFromXml($xml_source) {
        $this->read = GoodReadAuthor::parsefromXML($xml_source);
    }

}

?>
