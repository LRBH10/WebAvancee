package um2.websemantique.entities.apicallers;

import um2.websemantique.entities.utils.GeneratorFromXML;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*GoogleBookApiCaller g = new GoogleBookApiCaller();
		ArrayList<Book> bks = JSONFactory.createBooks(g
				.findBookofAuthor("yasmina khadra"));

		for (int i = 0; i < bks.size(); i++) {
			System.out.println(bks.get(i));
		} //*/
		
		/*FacebookAuthorApiCaller ff = new FacebookAuthorApiCaller();
		System.out.println(ff.findAuthorFacebook("yasmina khadra"));//*/
		
		
		GoodReadApiCaller gr = new GoodReadApiCaller();
		gr.findGoodReadAuthor("yasmina khadra");
	}

}
