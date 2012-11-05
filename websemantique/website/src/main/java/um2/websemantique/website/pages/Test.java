package um2.websemantique.website.pages;

import java.util.ArrayList;
import java.util.List;

import org.apache.tapestry5.annotations.Property;
import org.got5.tapestry5.jquery.utils.JQueryAccordionData;

@SuppressWarnings("deprecation")
public class Test {

	@Property
	String	test	= "pan2";

	public String getRaw() {
		return "<t:block id=\"rabah\"><h3>Element 3</h3><hr/>content from block 3	</t:block>";

	}

	public List<JQueryAccordionData> getData() {
		List<JQueryAccordionData> list = new ArrayList<JQueryAccordionData> ();
		list.add (new JQueryAccordionData ("Element1", "rabah"));
		list.add (new JQueryAccordionData ("Element2", "block2"));
		list.add (new JQueryAccordionData ("Element3", "block3"));
		list.add (new JQueryAccordionData ("Element4", "block4"));
		return list;
	}

}
