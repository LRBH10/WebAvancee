WebAvancee
==========
        Developpers : BLAZO Nastov
                     LAOUADI Rabah


        /base
                base.rdf  // base of books
                test.rdf  // test ontModel BLAZO

        
        /classes
                Author.php   //contain definition of authors getted from Facebook and GoodRead
                Book.php     // book description getted from Google Book
                OntolohyModel.php // definition of our model

            /apis
                    ApiCaller.php  // curl Call
                    FacebookApiCaller //call Facebook
                    GoogleBookApiCaller //call Google Book
                    GoodReadApiCaller //call GoodRead