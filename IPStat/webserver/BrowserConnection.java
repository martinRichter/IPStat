import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class BrowserConnection {

	/**Creates a BrowserConnection.*/
	public BrowserConnection() {
	}

	/**
	 * Tries to connect to URL provided and returns string. Adds result to
	 * string and adds error messages if exceptions are thrown.
	 */
	public String connect(String urlString) {
		StringBuffer buffer = new StringBuffer();
		String line = "";
		String text = "";
		URL url;
		try {
			url = new URL(urlString);
			BufferedReader br = new BufferedReader(new InputStreamReader(
					url.openStream()));
			while ((line = br.readLine()) != null) {
				buffer.append(line + "\n");
			}
			text = buffer.toString();
		} catch (MalformedURLException e) {
			text += ("ERROR: MalformedURL");
			e.printStackTrace();
		} catch (IOException e) {
			text += ("ERROR: IOException");
			e.printStackTrace();
		}
		return text;
	}
}
