package um2.websemantique.ontoligie.utils;

import java.util.ArrayList;

import um2.websemantique.entities.base.Author;
import um2.websemantique.entities.base.Book;
import um2.websemantique.entities.utils.SearchType;

public class Response {
	boolean returned;
	String key;
	SearchType type;
	
	String containts;
	ArrayList<Book> books = new ArrayList<Book>();
	ArrayList<Author> authors = new ArrayList<Author>();

	public void setContaints(String containts) {
		this.containts = containts;
	}

	public String getContaints() {
		return containts;
	}

	public boolean isOK() {
		return returned;
	}

	public void setOK(boolean returned) {
		this.returned = returned;
	}

}
