package um2.websemantique.entities.utils;

public class IdentifierBook {

	public static final String	ISBN10	= "ISBN_10";
	public static final String	ISBN13	= "ISBN_13";
	public static final String	OTHER	= "OTHER";

	private String				type;
	private String				containts;

	public IdentifierBook() {
	}

	public IdentifierBook(String type, String containts) {
		this.type = type;
		this.containts = containts;
	}

	public String getContaints() {
		return containts;
	}

	public String getType() {
		return type;
	}

	public void setContaints(String containts) {
		this.containts = containts;
	}

	public void setType(String type) {
		this.type = type;
	}

}
