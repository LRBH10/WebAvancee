package um2.websemantique.entities.base;

public class AuthorFacebook {

	private String	name;
	private String	likes;
	private String	id;
	private String	talkingAboutCount;
	private String	link;

	public String getExcludes() {
		String res = "";

		res += ExcludeNullProperty.isExcluded (this.id, "id");
		res += ExcludeNullProperty.isExcluded (this.likes, "likes");
		res += ExcludeNullProperty.isExcluded (this.link, "link");
		res += ExcludeNullProperty.isExcluded (this.name, "name");
		res += ExcludeNullProperty.isExcluded (this.talkingAboutCount, "talkingAboutCount");

		return res;

	}

	public String getId() {
		return id;
	}

	public String getLikes() {
		return likes;
	}

	public String getLink() {
		return link;
	}

	public String getName() {
		return name;
	}

	public String getTalkingAboutCount() {
		return talkingAboutCount;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setLikes(String likes) {
		this.likes = likes;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setTalkingAboutCount(String talkingAboutCount) {
		this.talkingAboutCount = talkingAboutCount;
	}
}
