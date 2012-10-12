<?php
include_once 'classes/Book.php';
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of OntologyModel
 *
 * @author Rabah
 */
class OntologyModel {

    private $base;

    //put your code here

    public function create() {
        $base = ModelFactory::getOntModel(MEMMODEL,RDFS_VOCABULARY); 
        //We define the ontology here
        //$book = $base->createOntClass( "bookNS:" . "Book" );
        //$book->setIsDefinedBy(new Resource("http://purl.org/ontology/bibo/Book"));
        
        //$base->saveAs("base.rdf", "rdf");
        //$base->close();
    }

    public function getBaseRDF() { 
        $this->base->load("base.rdf");
        return $this->base;
    }
    
    public function test(){
        
    }

    public function closeBaseRDF() {
        $this->base->close();
    }

}

?>
