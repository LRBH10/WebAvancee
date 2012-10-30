package um2.websemantique.entities.base;

public class AuthorGoodRead {
	private String id;
	private String name;
	private String link;
	private String fansCount;
	private String imageUrl;
	private String about;
	private String worksCount;
	private String gender;
	private String hometown;
	private String bornAt;
	private String diedAt;

	public String getAbout() {
		return about;
	}

	public String getBornAt() {
		return bornAt;
	}

	public String getDiedAt() {
		return diedAt;
	}

	public String getFansCount() {
		return fansCount;
	}

	public String getGender() {
		return gender;
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

	public void setGender(String gender) {
		this.gender = gender;
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

	public void setWorksCount(String worksCount) {
		this.worksCount = worksCount;
	}
}
