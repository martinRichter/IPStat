import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.crypto.SecretKey;

public class Handler implements Runnable {
	Thread t = new Thread(this);
	private SecretKey secKey;
	private Connection conn;
	private ClientGUI GUI;
	private KeyGUI keyGUI;
	private Socket socket;
	private SecurityHandler secH;

	public Handler() {
		createSocket();
		createConnection();
		keyGUI = new KeyGUI();
		secH = new SecurityHandler(keyGUI.getKey());
		createGUI(secH);
		t.start();
	}

	@Override
	public void run() {
		while (true) {
//			try {
//				GUI.displayInput(secH.decrypt(conn.getText()));
//			} catch (IOException e) {
//				System.out.println("Connection to server lost.");
//			}
		}
	}

	private void createSocket() {
		int port = 9494;
		InetAddress host;
		try {
			host = InetAddress.getByName("atlas.dsv.su.se");
			socket = new Socket(host, port);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void createGUI(SecurityHandler secH) {
		try {
			GUI = new ClientGUI(new PrintWriter(new OutputStreamWriter(
					socket.getOutputStream()), true), secH);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void createConnection() {
		try {
			conn = new Connection(new BufferedReader(new InputStreamReader(
					socket.getInputStream())));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
