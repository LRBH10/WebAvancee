package um2.websemantique.entities.utils;

import java.util.Iterator;

import org.jdom.Element;
import org.jdom.input.SAXBuilder;

public class GeneratorFromXML {
	private static String getIdGoodReadBook(String xml_str) {
		org.jdom.Document document = null;
		Element racine;

		String res = "";
		SAXBuilder sxb = new SAXBuilder();
		try {
			// On crée un nouveau document JDOM avec en argument le fichier XML
			// Le parsing est terminé ;)
			document = sxb.build(xml_str);
		} catch (Exception e) {
		}

		// On initialise un nouvel élément racine avec l'élément racine du
		// document.
		racine = document.getRootElement();

		/*Iterator i = listLivres.iterator();
		while (i.hasNext()) {
			// On recrée l'Element courant à chaque tour de boucle afin de
			// pouvoir utiliser les méthodes propres aux Element
			Element courant = (Element) i.next();

			// On affiche le titre et le genre de l'element courant
			System.out.println("Titre :" + courant.getChild("titre").getText());
			System.out.println("Genre :" + courant.getChild("genre").getText());
			System.out
					.println("Numero :" + courant.getAttributeValue("numero"));

		}//*/

		return res;
	}
}
