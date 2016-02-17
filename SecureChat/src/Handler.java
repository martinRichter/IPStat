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
	private SecurityHandler secH;
	private Connection conn;


	public Handler(SecurityHandler secH, InetAddress host, int port) {
		this.secH = secH;
		conn = new Connection(host, port);
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


	public void send(String text) {
		// TODO Auto-generated method stub
		
	}
}
