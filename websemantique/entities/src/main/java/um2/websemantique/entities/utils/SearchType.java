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

	public static int getMaxValueOf(SearchType type) {
		int res = 0;
		switch (type) {
		case AUTHOR:
			res = 20;
			break;

		case ANY:
			res = 5;
			break;

		default:
			res = 10;
			break;
		}

		return res;
	}

	public static SearchType fromString(String type) {
		SearchType t;
		try{
			t = SearchType.valueOf(type);
		}catch(Exception e){
			t = SearchType.ANY;
		}
		return t;
		
	}
}
