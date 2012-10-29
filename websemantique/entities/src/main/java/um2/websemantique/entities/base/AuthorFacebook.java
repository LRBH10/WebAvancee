package um2.websemantique.entities.base;

public class AuthorFacebook {

	private String name;
	private int likes;
	private String id;
	private int talkingAboutCount;
	private String link;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public int getLikes() {
		return likes;
	}

	public String getLink() {
		return link;
	}

	public int getTalkingAboutCount() {
		return talkingAboutCount;
	}

	public void setTalkingAboutCount(int talkingAboutCount) {
		this.talkingAboutCount = talkingAboutCount;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public void setLink(String link) {
		this.link = link;
	}

}
