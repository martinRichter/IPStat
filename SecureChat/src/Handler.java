import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.crypto.SealedObject;
import javax.crypto.SecretKey;

public class Handler implements Runnable {
	Thread t = new Thread(this);
	private SecurityHandler secH;
	private Connection conn;
	private ClientGUI GUI;

	public Handler(SecurityHandler secH, InetAddress host, int port) {
		this.secH = secH;
		GUI = new ClientGUI(this);
		conn = new Connection(host, port);
		t.start();
	}

	@Override
	public synchronized void run() {
		Object obj;
		SealedObject sObj;
		String str;
		while (true) {
			System.out.println("tries to getText");
			try {
				GUI.displayInput(conn.getText());
			} catch (IOException e) {
				e.printStackTrace();
			}
			// if ((obj = conn.getObject()) != null) {
			// sObj = (SealedObject) obj;
			// GUI.displayInput(secH.unSeal(sObj));
			// }
		}
	}

	public synchronized void send(String text) {
		// Object obj = secH.seal(text);
		conn.send(text);
	}
}
