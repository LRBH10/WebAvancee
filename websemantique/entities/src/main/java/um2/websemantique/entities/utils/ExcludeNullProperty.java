package um2.websemantique.entities.utils;

import java.util.ArrayList;
import java.util.List;


public class ExcludeNullProperty {

	public static String isExcluded(String attribute, String name) {
		String ret = "";
		if ( attribute == null ) {
			ret = "," + name;
		}
		return ret;
	}
	public static String isReordered(String attribute, String name) {
		String ret = "";
		if ( attribute != null ) {
			ret = "," + name;
		}
		return ret;
	}
	
	public static Pair excludedReorder (String attribute, String name) {
		Pair ret = new Pair ();
		if ( attribute == null ) {
			ret.setExcludes ("," + name)  ;
		}
		else{
			ret.setReorder ("," + name);
		}
		return ret;
	}
}
