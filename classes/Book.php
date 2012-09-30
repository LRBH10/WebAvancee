<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of Book
 *
 * @author bibouh
 */
class Book {
    var $id;
    var $titre;
    var $publicheur;
    var $date_pub;
    var $nb_pages;
    var $isbn_10;
    var $isbn_13;
    
    public function __construct( $id , $titre , $publisheur , $date_pub , $nb_pages , $isbn_10 , $isbn_13 ) {
        $this->id = $id;
        $this->titre = $titre;
        $this->publicheur = $publisheur;
        $this->date_pub = $date_pub;
        $this->nb_pages = $nb_pages;
        $this->isbn_10 = $isbn_10;
        $this->isbn_13 = $isbn_13;
    }
    
    function Generate_Book_RDF($file){
        //Ici on ecrit dans le fichier passe en parametre
        fwrite($file, '<rdf:Description rdf:about="https://www.googleapis.com/books/v1/volumes/'.$this->id.'"><a:titre>'.$this->titre.'</a:titre><a:publisher>'.$this->publicheur.'</a:publisher><a:date_publication>'.$this->date_pub.'</a:date_publication><a:nb_pages>'.$this->nb_pages.'</a:nb_pages><a:isbn_10>'.$this->isbn_10.'</a:isbn_10><a:isbn_13>'.$this->isbn_13.'</a:isbn_13></rdf:Description>');
    }


    //put your code here
}

?>
