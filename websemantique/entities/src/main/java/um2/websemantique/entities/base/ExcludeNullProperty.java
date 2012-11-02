package um2.websemantique.entities.base;

public class ExcludeNullProperty {
	
	public static String isExcluded(String attribute, String name) {
		String ret = "";
		if (attribute == null) {
			ret = "," + name;
		}
		return ret;
	}
}
