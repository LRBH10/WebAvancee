package um2.websemantique.entities.apicallers;

import um2.websemantique.entities.base.Author;
import um2.websemantique.entities.utils.GetterBookAuthor;



public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GetterBookAuthor get = new GetterBookAuthor();
		get.findwithAuthor("blazo");
		
		for (Author author : get.getAuthors()) {
			System.out.println(author.getLinkAbout());
		}
		
	}

}
