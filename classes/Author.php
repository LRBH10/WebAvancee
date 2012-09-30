<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of Auteur
 *
 * @author GoceDelcev
 */

include_once  'Book.php';

class Author {
    var $name;
    var $liste_livres;
    
    public function __construct( $name ) {
        $this->name = $name;
        $this->liste_livres = array();
    }
    
    function Add_Book($book){        
        $this->liste_livres[] = $book ;
    }
    
    function Generate_RDF(){
        $f = fopen("test.rdf", "w");
        fwrite($f, '<rdf:RDF xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#" xmlns:dcterms="http://purl.org/dc/terms/"><rdf:Description rdf:about="http://.../autor/'.$this->name.'"><a:books rdf:parseType="Collection">');
        foreach ($this->liste_livres as $book){
            $book->Generate_Book_RDF($f);
        }
        fwrite($f,'</a:books></rdf:Description></rdf:RDF>');
        fclose($f);
    }
    

    //put your code here
}

?>
