public class Browser {

	public static void main(String[] args) {

		BrowserConnection browserConnect = new BrowserConnection();
		BrowserGUI browGUI = new BrowserGUI(browserConnect);
	}

}
