package um2.websemantique.entities.base;

import java.util.ArrayList;

import com.sun.istack.internal.NotNull;

import um2.websemantique.entities.utils.IdentifierBook;

public class Book {
	private String id;
	private String selfLink;
	private String title;
	private String description;
	private String publisher;
	private String publishedDate;
	private String pageCount;
	private ArrayList<String> authors = new ArrayList<String>(); // []
	private ArrayList<String> authorslink = new ArrayList<String>(); // []
	private ArrayList<IdentifierBook> industryIdentifiers = new ArrayList<IdentifierBook>(); // []
	private String thumbnail;
	private String language;
	private String previewLink;
	private String infoLink;
	private String canonicalVolumeLink;
	private ArrayList<String> categories = new ArrayList<String>(); // []
	private String averageRating;
	private String ratingsCount;
	private String country;
	private String saleability;
	private String Ebook;
	private String price;
	private String priceSymbol;
	private String buyLink;
	private String viewability;
	private String publicDomain;
	private String epubLink;
	private String pdfLink;
	private String webReaderLink;
	private String textSnippet;
	private String currencyCode;

	public ArrayList<String> getAuthorslink() {
		return authorslink;
	}

	public void setAuthorslink(ArrayList<String> authorslink) {
		this.authorslink = authorslink;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the pageCount
	 */
	public String getPageCount() {
		return pageCount;
	}

	/**
	 * @param pageCount
	 *            the pageCount to set
	 */
	public void setPageCount(String pageCount) {
		this.pageCount = pageCount;
	}

	/**
	 * @return the selfLink
	 */
	public String getSelfLink() {
		return selfLink;
	}

	/**
	 * @param selfLink
	 *            the selfLink to set
	 */
	public void setSelfLink(String selfLink) {
		this.selfLink = selfLink;
	}

	public ArrayList<String> getAuthors() {
		return authors;
	}

	public void setAuthors(ArrayList<String> authors) {
		this.authors = authors;
	}

	public String getAverageRating() {
		return averageRating;
	}

	public void setAverageRating(String averageRating) {
		this.averageRating = averageRating;
	}

	public String getBuyLink() {
		return buyLink;
	}

	public void setBuyLink(String buyLink) {
		this.buyLink = buyLink;
	}

	public String getCanonicalVolumeLink() {
		return canonicalVolumeLink;
	}

	public void setCanonicalVolumeLink(String canonicalVolumeLink) {
		this.canonicalVolumeLink = canonicalVolumeLink;
	}

	public ArrayList<String> getCategories() {
		return categories;
	}

	public void setCategories(ArrayList<String> categories) {
		this.categories = categories;
	}

	public String getCountry() {
		return country;
	}

	public String getDescription() {
		return description;
	}

	public String getEpubLink() {
		return epubLink;
	}

	public ArrayList<IdentifierBook> getIndustryIdentifiers() {
		return industryIdentifiers;
	}

	public String getInfoLink() {
		return infoLink;
	}

	public String isEbook() {
		return Ebook;
	}

	public String getLanguage() {
		return language;
	}

	public String getPdfLink() {
		return pdfLink;
	}

	public String getPreviewLink() {
		return previewLink;
	}

	public String getPrice() {
		return price;
	}

	public String getPriceSymbol() {
		return priceSymbol;
	}

	public String getPublicDomain() {
		return publicDomain;
	}

	public String getPublishedDate() {
		return publishedDate;
	}

	public String getPublisher() {
		return publisher;
	}

	public String getRatingsCount() {
		return ratingsCount;
	}

	public String getSaleability() {
		return saleability;
	}

	public String getTextSnippet() {
		return textSnippet;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public String getViewability() {
		return viewability;
	}

	public String getWebReaderLink() {
		return webReaderLink;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setEpubLink(String epubLink) {
		this.epubLink = epubLink;
	}

	public void setIndustryIdentifiers(
			ArrayList<IdentifierBook> industryIdentifiers) {
		this.industryIdentifiers = industryIdentifiers;
	}

	public void setInfoLink(String infoLink) {
		this.infoLink = infoLink;
	}

	public void setEbook(String ebook) {
		Ebook = ebook;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public void setPdfLink(String pdfLink) {
		this.pdfLink = pdfLink;
	}

	public void setPreviewLink(String previewLink) {
		this.previewLink = previewLink;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public void setPriceSymbol(String priceSymbol) {
		this.priceSymbol = priceSymbol;
	}

	public void setPublicDomain(String publicDomain) {
		this.publicDomain = publicDomain;
	}

	public void setPublishedDate(String publishedDate) {
		this.publishedDate = publishedDate;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public void setRatingsCount(String ratingsCount) {
		this.ratingsCount = ratingsCount;
	}

	public void setSaleability(String saleability) {
		this.saleability = saleability;
	}

	public void setTextSnippet(String textSnippet) {
		this.textSnippet = textSnippet;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public void setViewability(String viewability) {
		this.viewability = viewability;
	}

	public void setWebReaderLink(String webReaderLink) {
		this.webReaderLink = webReaderLink;
	}

	@Override
	public String toString() {
		return super.toString();
	}
	
	
	public String getExcludes(){
		String res ="";
		if(this.authors.size() == 0){
			res = "authors,";
		}
		if(this.authorslink.size() == 0){
			res = "authorslink,";
		}
	
		if(this.categories.size() == 0){
			res = "categories,";
		}
	
		res += ExcludeNullProperty.isExcluded(this.averageRating,"averageRating");
		res += ExcludeNullProperty.isExcluded(this.buyLink,"buyLink");
		res += ExcludeNullProperty.isExcluded(this.canonicalVolumeLink,"canonicalVolumeLink");
		res += ExcludeNullProperty.isExcluded(this.country,"country");
		res += ExcludeNullProperty.isExcluded(this.currencyCode,"currencyCode");
		res += ExcludeNullProperty.isExcluded(this.description,"description");
		res += ExcludeNullProperty.isExcluded(this.Ebook,"Ebook");
		res += ExcludeNullProperty.isExcluded(this.epubLink,"epubLink");
		res += ExcludeNullProperty.isExcluded(this.id,"id");
		res += ExcludeNullProperty.isExcluded(this.infoLink,"infoLink");
		res += ExcludeNullProperty.isExcluded(this.language,"language");
		res += ExcludeNullProperty.isExcluded(this.pageCount,"pageCount");
		res += ExcludeNullProperty.isExcluded(this.pdfLink,"pdfLink");
		res += ExcludeNullProperty.isExcluded(this.previewLink,"previewLink");
		res += ExcludeNullProperty.isExcluded(this.price,"price");
		res += ExcludeNullProperty.isExcluded(this.priceSymbol,"priceSymbol");
		res += ExcludeNullProperty.isExcluded(this.publicDomain,"publicDomain");
		res += ExcludeNullProperty.isExcluded(this.publishedDate,"publishedDate");
		res += ExcludeNullProperty.isExcluded(this.publisher,"publisher");
		res += ExcludeNullProperty.isExcluded(this.ratingsCount,"ratingsCount");
		res += ExcludeNullProperty.isExcluded(this.saleability,"saleability");
		res += ExcludeNullProperty.isExcluded(this.selfLink,"selfLink");
		res += ExcludeNullProperty.isExcluded(this.textSnippet,"textSnippet");
		res += ExcludeNullProperty.isExcluded(this.thumbnail,"thumbnail");
		res += ExcludeNullProperty.isExcluded(this.title,"title");
		res += ExcludeNullProperty.isExcluded(this.viewability,"viewability");
		res += ExcludeNullProperty.isExcluded(this.webReaderLink,"webReaderLink");
		
		return res;
	}

	
	
	
}
