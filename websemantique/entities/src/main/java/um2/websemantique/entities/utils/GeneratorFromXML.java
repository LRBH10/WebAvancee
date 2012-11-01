package um2.websemantique.entities.utils;

import java.io.IOException;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import um2.websemantique.entities.base.AuthorGoodRead;

public class GeneratorFromXML {

	/**
	 * build a document from url
	 * 
	 * @param url
	 * @return {@link Document}
	 */
	public static Document getDocumentFromUrl(String url) {
		SAXBuilder builder = new SAXBuilder();

		Document document = null;
		try {
			document = builder.build(url);
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return document;
	}

	/**
	 * getting id of Author in GoodRead Web Site
	 * 
	 * @param xml_url
	 * @return the id of author in GoodReads web site
	 */
	public static String getIdGoodReadAuthor(String xml_url) {
		String res = "";

		Document document = getDocumentFromUrl(xml_url);
		Element rootNode = document.getRootElement();

		System.out.println(xml_url);
		try {

			Element id = rootNode.getChild("search").getChild("results")
					.getChild("work").getChild("best_book").getChild("author")
					.getChild("id");
			res = id.getValue();
		} catch (NullPointerException e) {
			res = "0";
		}

		return res;
	}

	/**
	 * getting {@link AuthorGoodRead} in GoodRead Web Site
	 * 
	 * @param xml_url
	 * @return {@link AuthorGoodRead}
	 */
	public static AuthorGoodRead getGoodReadAuthor(String xml_url) {
		AuthorGoodRead res = new AuthorGoodRead();

		Document document = getDocumentFromUrl(xml_url);
		Element author = document.getRootElement().getChild("author");

		res.setAbout(getSCFE(author, "about"));
		res.setBornAt(getSCFE(author, "born_at"));
		res.setLink(getSCFE(author, "link"));

		res.setDiedAt(getSCFE(author, "died_at"));
		res.setFansCount(getSCFE(author, "fans_count"));
		res.setGender(getSCFE(author, "gender"));
		res.setHometown(getSCFE(author, "hometown"));
		res.setId(getSCFE(author, "id"));
		res.setImageUrl(getSCFE(author, "image_url"));
		res.setName(getSCFE(author, "name"));
		res.setWorksCount(getSCFE(author, "works_count"));

		return res;
	}

	/**
	 * get Value of Child in the ELEMENT getStringChildFromElement :) -- ABBREV
	 * 
	 * @param element
	 *            {@link Element}
	 * @param child
	 *            key to get value in this element
	 * @return {@link String}
	 */
	public static String getSCFE(Element element, String child) {
		String res = null;
		String val = element.getChild(child).getValue();
		if (!val.equals("")) {
			res = val;
		}
		return res;
	}
}
