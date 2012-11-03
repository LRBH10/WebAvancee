package um2.websemantique.entities.utils;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import um2.websemantique.entities.base.AuthorFacebook;
import um2.websemantique.entities.base.Book;

/**
 * generating the following object from json
 * 
 * Google book {@link Book}<br/>
 * Facebook Author {@link AuthorFacebook}
 * 
 * @author rabah
 * 
 */

public class GeneratorFromJSON {

	/**
	 * Create {@link AuthorFacebook} from {@link JSONObject}
	 * 
	 * @param json_object
	 * @return {@link AuthorFacebook}
	 */
	public static AuthorFacebook createAuthorFacebook(JSONObject json_object) {

		AuthorFacebook author = new AuthorFacebook ();

		author.setId (GeneratorFromJSON.getStrFromJSON (json_object, "id"));
		author.setLink (GeneratorFromJSON.getStrFromJSON (json_object, "link"));
		author.setName (GeneratorFromJSON.getStrFromJSON (json_object, "name"));

		author.setLikes (GeneratorFromJSON.getStrFromJSON (json_object, "likes"));
		author.setTalkingAboutCount (GeneratorFromJSON.getStrFromJSON (json_object, "talking_about_count"));

		return author;
	}

	/**
	 * Create {@link AuthorFacebook} from {@link String}
	 * 
	 * @param json_text
	 * @return {@link AuthorFacebook}
	 */
	public static AuthorFacebook createAuthorFacebook(String json_text) {
		if ( json_text.equals ("0") ) {
			return null;
		} else {
			return GeneratorFromJSON.createAuthorFacebook (GeneratorFromJSON.createJSONObject (json_text));
		}
	}

	/**
	 * Creating Book instance from json object
	 * 
	 * @param json_object
	 *            {@link JSONObject}
	 * @return instance of {@link Book} if json_object is not null <br/>
	 *         <strong>null </strong> else
	 */
	public static Book createBook(JSONObject json_object) {
		Book book = new Book ();

		JSONObject volumeinfo = null;
		JSONObject saleInfo = null;
		JSONObject accessInfo = null;

		volumeinfo = json_object.optJSONObject ("volumeInfo");
		saleInfo = json_object.optJSONObject ("saleInfo");
		accessInfo = json_object.optJSONObject ("accessInfo");

		/**
		 * for all
		 */
		book.setSelfLink (GeneratorFromJSON.getStrFromJSON (json_object, "selfLink"));
		book.setId (GeneratorFromJSON.getStrFromJSON (json_object, "id"));

		/**
		 * Volume info
		 */
		book.setInfoLink (GeneratorFromJSON.getStrFromJSON (volumeinfo, "infoLink"));
		book.setPublisher (GeneratorFromJSON.getStrFromJSON (volumeinfo, "publisher"));
		book.setCanonicalVolumeLink (GeneratorFromJSON.getStrFromJSON (volumeinfo, "canonicalVolumeLink"));
		book.setTitle (GeneratorFromJSON.getStrFromJSON (volumeinfo, "title"));

		book.setPreviewLink (GeneratorFromJSON.getStrFromJSON (volumeinfo, "previewLink"));
		book.setDescription (GeneratorFromJSON.getStrFromJSON (volumeinfo, "description"));
		book.setLanguage (GeneratorFromJSON.getStrFromJSON (volumeinfo, "language"));
		book.setPublishedDate (GeneratorFromJSON.getStrFromJSON (volumeinfo, "publishedDate"));

		book.setRatingsCount (GeneratorFromJSON.getStrFromJSON (volumeinfo, "ratingsCount"));
		book.setPageCount (GeneratorFromJSON.getStrFromJSON (volumeinfo, "pageCount"));
		book.setAverageRating (GeneratorFromJSON.getStrFromJSON (volumeinfo, "averageRating"));

		if ( volumeinfo.has ("authors") ) {
			JSONArray ar = volumeinfo.optJSONArray ("authors");
			for ( int i = 0 ; i < ar.length () ; i++ ) {
				book.getAuthors ().add (ar.optString (i));
			}
		}

		if ( volumeinfo.has ("imageLinks") ) {
			JSONObject images = volumeinfo.optJSONObject ("imageLinks");
			book.setThumbnail (GeneratorFromJSON.getStrFromJSON (images, "thumbnail"));

		}

		if ( volumeinfo.has ("categories") ) {
			JSONArray ar = volumeinfo.optJSONArray ("categories");
			for ( int i = 0 ; i < ar.length () ; i++ ) {
				book.getCategories ().add (ar.optString (i));
			}
		}

		if ( volumeinfo.has ("industryIdentifiers") ) {
			JSONArray ar = volumeinfo.optJSONArray ("industryIdentifiers");
			for ( int i = 0 ; i < ar.length () ; i++ ) {
				String type = GeneratorFromJSON.getStrFromJSON (ar.optJSONObject (i), "type");
				String identifier = GeneratorFromJSON.getStrFromJSON (ar.optJSONObject (i), "identifier");
				book.getIndustryIdentifiers ().add (new IdentifierBook (type,
						identifier));
			}
		}

		/**
		 * Sale info
		 */
		book.setSaleability (GeneratorFromJSON.getStrFromJSON (saleInfo, "saleability"));

		if ( volumeinfo.has ("listPrice") ) {
			JSONObject lp = saleInfo.optJSONObject ("listPrice");

			book.setPrice (GeneratorFromJSON.getStrFromJSON (lp, "amount"));
			book.setCurrencyCode (GeneratorFromJSON.getStrFromJSON (lp, "currencyCode"));
		}

		book.setBuyLink (GeneratorFromJSON.getStrFromJSON (saleInfo, "buyLink"));
		book.setEbook (GeneratorFromJSON.getStrFromJSON (saleInfo, "isEbook"));
		book.setCountry (GeneratorFromJSON.getStrFromJSON (saleInfo, "country"));

		/**
		 * acces info
		 */
		book.setWebReaderLink (GeneratorFromJSON.getStrFromJSON (accessInfo, "webReaderLink"));
		book.setViewability (GeneratorFromJSON.getStrFromJSON (accessInfo, "viewability"));

		if ( accessInfo.has ("pdf") ) {
			book.setPdfLink (GeneratorFromJSON.getStrFromJSON (accessInfo.optJSONObject ("pdf"), "acsTokenLink"));
		}
		if ( accessInfo.has ("epub") ) {
			book.setPdfLink (GeneratorFromJSON.getStrFromJSON (accessInfo.optJSONObject ("epub"), "acsTokenLink"));
		}

		return book;
	}

	/**
	 * generate books from result of call api
	 * 
	 * @param json_text
	 * @return
	 */
	public static ArrayList<Book> createBooks(String json_text) {
		JSONArray array = GeneratorFromJSON.createJSONObject (json_text).optJSONArray ("items");
		ArrayList<Book> books = new ArrayList<Book> ();

		for ( int i = 0 ; i < array.length () ; i++ ) {
			books.add (GeneratorFromJSON.createBook (array.optJSONObject (i)));
		}
		return books;
	}

	// ** FACEBOOOOOOOOOOOK AUTHOR

	/**
	 * Creating Book instance from json text
	 * 
	 * @param json_text
	 *            {@link String} contains JSON TEXT
	 * @return instance of {@link JSONObject} if json_text is valid <br/>
	 *         <strong>null </strong> else
	 */
	public static JSONObject createJSONObject(String json_text) {
		JSONObject object = null;
		try {
			object = new JSONObject (json_text);
		} catch ( JSONException e ) {
			e.printStackTrace ();
			return null;
		}

		return object;
	}

	/**
	 * getting id of first response
	 * 
	 * @param json_text
	 *            {@link String}
	 * @return {@link String}
	 */

	public static String getIdAuthorFacebook(String json_text) {
		JSONArray obj = GeneratorFromJSON.createJSONObject (json_text).optJSONArray ("data");
		String result = "0";
		if ( obj.length () > 0 ) {
			result = obj.optJSONObject (0).optString ("id");
		}
		return result;

	}

	/**
	 * gettin a String with key from JSONObject
	 * 
	 * @param obj
	 *            {@link JSONObject} for the search
	 * @param key
	 *            {@link String} to search
	 * @return {@link String} <br/>
	 *         if the key exist return contents of the key<br/>
	 *         <strong>null</strong> else
	 */
	public static String getStrFromJSON(JSONObject obj, String key) {
		if ( obj.has (key) ) {
			return obj.optString (key);
		}
		return null;
	}

}
