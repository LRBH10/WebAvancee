package um2.websemantique.website.components;

import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.Block;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;

public class LayoutWS {
	   @Property
	   @Parameter(defaultPrefix = BindingConstants.LITERAL)
	   private Block sidebar;
}
