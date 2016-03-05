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

	/**
	 * Method running the thread. If a messages is received from server, it
	 * checks if the message contains the word SECRET, which means it is an
	 * encrypted message. If it was encrypted, it decrypts the part of the
	 * message that's encrypted and then displays the decrypted message as well
	 * as the sender.
	 */
	@Override
	public void run() {
		String str;
		int pos;
		while (true) {
			try {
				str = conn.getText();
				if (str.contains("SECRET")) {
					pos = str.indexOf("SECRET");
					str = str.substring(0, pos - 1)
							+ secH.decryptString(str.substring(pos + 6,
									str.length()));
				}
				GUI.displayInput(str);
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(0);
			}
		}
	}

	/**
	 * Send the string with word SECRET infront so receiver can differ messages
	 * from the server and messages for a secure client. If the text is wwhhoo,
	 * message is sent unencrypted as it is a message to the server.
	 */
	public void send(String text) {
		if (text.equals("wwhhoo")) {
			conn.send(text);
		} else {
			conn.send(" SECRET" + secH.encryptString(text));
		}
	}
}
