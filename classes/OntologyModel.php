<?php

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
        $base = new MemModel();
        $base->saveAs("base.rdf", "rdf");
        $base->close();
    }

    public function getBaseRDF() {
        $this->base = ModelFactory::getDefaultModel();
        $this->base->load("base.rdf");
        $this->base->addNamespace("book", "http://www.googleapi.com/book/");// */
        return $this->base;
    }

    public function closeBaseRDF() {
        $this->base->close();
    }

}

?>
