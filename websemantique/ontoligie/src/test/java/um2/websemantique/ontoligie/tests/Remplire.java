package um2.websemantique.ontoligie.tests;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import um2.websemantique.entities.utils.SearchType;
import um2.websemantique.ontoligie.factory.GetterRDFAuthorBook;
import um2.websemantique.ontoligie.sdb.SDBUtil;


public class Remplire {

	/**
	 * @param args
	 */
	public static void main(String [] args) {
		String chaine="";
		String fichier ="french.txt";
		
		SDBUtil.openConnection ();
		GetterRDFAuthorBook get = new GetterRDFAuthorBook ();
		
		try{
			InputStream ips=new FileInputStream(fichier); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			String ligne;
			while ((ligne=br.readLine())!=null){
				System.out.println(ligne);
				chaine+=ligne+"\n";
				get.findFromWeb (ligne, SearchType.AUTHOR);
			}
			br.close(); 
		}		
		catch (Exception e){
			e.printStackTrace ();
		}

	}

}
