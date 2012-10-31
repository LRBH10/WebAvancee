package um2.websemantique.entities.utils;

public enum SearchType {
	AUTHOR, ANY, ISBN, TITLE, SUBJECT;

	public static String getValueFromType(SearchType type) {
		String res = "";
		switch (type) {
		case AUTHOR:
			res = "inauthor:";
			break;
		case ISBN:
			res = "isbn:";
			break;
		case TITLE:
			res = "intitle:";
			break;
		case SUBJECT:
			res = "subject:";
			break;

		default: // FOR ANY
			res = "";
			break;
		}

		return res;
	}
}
