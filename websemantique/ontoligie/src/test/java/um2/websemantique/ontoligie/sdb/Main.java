package um2.websemantique.ontoligie.sdb;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.SQLException;

public class Main {

	/**
	 * @param args
	 * @throws SQLException
	 * @throws UnsupportedEncodingException 
	 */
	public static void main(String[] args) throws SQLException, UnsupportedEncodingException {

		/*
		 * SDBUtil.openConnection().cleanDB();
		 * 
		 * GetterRDFAuthorBook rr = new GetterRDFAuthorBook();
		 * rr.find("yasmina", SearchType.ANY); while(rr.getProgress() <100){
		 * System.out.println("Progress : ..."+rr.getProgress() + "%"); try {
		 * Thread.sleep(100); } catch (InterruptedException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); } }
		 * RDFOntology.getInstanceRDFOntology().databaseToString();//
		 */

		String str = "http://books.google.fr/books/about/De_L_utopie_Totalitaire_Aux_Oeuvres_de_Y.html?hl=&amp;id=8u_B5_W4U4gC";
		System.out.println(str + "\n" + URLDecoder.decode(str, "UTF-8"));
	}
}
