package um2.websemantique.entities.base;

public class AuthorGoodRead {

	private String	id;
	private String	name;
	private String	link;
	private String	fansCount;
	private String	imageUrl;
	private String	about;
	private String	worksCount;
	private String	sex;
	private String	hometown;
	private String	bornAt;
	private String	diedAt;

	public String getAbout() {
		return about;
	}

	public String getBornAt() {
		return bornAt;
	}

	public String getDiedAt() {
		return diedAt;
	}

	public String getExcludes() {
		String res = "";

		res += ExcludeNullProperty.isExcluded (this.about, "about");
		res += ExcludeNullProperty.isExcluded (this.bornAt, "bornAt");
		res += ExcludeNullProperty.isExcluded (this.diedAt, "diedAt");
		res += ExcludeNullProperty.isExcluded (this.fansCount, "fansCount");
		res += ExcludeNullProperty.isExcluded (this.hometown, "hometown");
		res += ExcludeNullProperty.isExcluded (this.id, "id");
		res += ExcludeNullProperty.isExcluded (this.imageUrl, "imageUrl");
		res += ExcludeNullProperty.isExcluded (this.link, "link");
		res += ExcludeNullProperty.isExcluded (this.name, "name");
		res += ExcludeNullProperty.isExcluded (this.sex, "sex");
		res += ExcludeNullProperty.isExcluded (this.worksCount, "worksCount");

		return res;

	}

	public String getFansCount() {
		return fansCount;
	}

	public String getHometown() {
		return hometown;
	}

	public String getId() {
		return id;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public String getLink() {
		return link;
	}

	public String getName() {
		return name;
	}

	public String getSex() {
		return sex;
	}

	public String getWorksCount() {
		return worksCount;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public void setBornAt(String bornAt) {
		this.bornAt = bornAt;
	}

	public void setDiedAt(String diedAt) {
		this.diedAt = diedAt;
	}

	public void setFansCount(String fansCount) {
		this.fansCount = fansCount;
	}

	public void setHometown(String hometown) {
		this.hometown = hometown;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSex(String gender) {
		this.sex = gender;
	}

	public void setWorksCount(String worksCount) {
		this.worksCount = worksCount;
	}

}
