import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.crypto.SecretKey;


public class ChatRunner {

	public static void main(String[] args) {
		KeyGUI keyGUI = new KeyGUI();
		SecurityHandler secH = new SecurityHandler (keyGUI.getKey());
	
		InetAddress host = null;
		try {
//			host = InetAddress.getByName("atlas.dsv.su.se");
			host = InetAddress.getByName("localhost");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		int port = 2000;
//		int port = 9494;
		
		Handler handler = new Handler(secH, host, port);
	}

}
