import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.crypto.SecretKey;

public class SecureChat {

	/**
	 * Creates keyGUI and both handlers. Takes in host and port through args[],
	 * if no arguments are given the default value for port is 2000 and for host
	 * is localhost.
	 */
	public static void main(String[] args) {
		KeyGUI keyGUI = new KeyGUI();
		SecurityHandler secH = new SecurityHandler(keyGUI.getKey());

		InetAddress host = null;
		int port;

		if (args.length == 0) {
			try {
				host = InetAddress.getLocalHost();
			} catch (UnknownHostException e) {
				System.out.println("Unknown host, please try again.");
				System.exit(0);
			}
		} else {
			try {
				host = InetAddress.getByName(args[0]);
			} catch (UnknownHostException e) {
				System.out.println("Unknown host, please try again");
				System.exit(0);
			}
		}

		if (args.length < 2) {
			port = 2000;
		} else {
			port = Integer.parseInt(args[1]);
		}

		Handler handler = new Handler(secH, host, port);
	}

}
