package um2.websemantique.website.base;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public abstract class ApiCaller {

	public static String cUrl(String url_) {
		URL url = null;
		InputStream is = null;
		try {
			url = new URL (url_);
			is = url.openConnection ().getInputStream ();

		} catch ( IOException e ) {
			e.printStackTrace ();
		}

		BufferedReader reader = new BufferedReader (new InputStreamReader (is));

		String result = "";
		String line = null;
		try {
			while ((line = reader.readLine ()) != null) {
				result += line + "\n";
			}
			reader.close ();
		} catch ( IOException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace ();
		}

		return result;
	}

}
