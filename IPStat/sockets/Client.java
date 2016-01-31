import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client implements Runnable{
	private InetAddress host;
	private int port;
	private Socket socket;
	private PrintWriter out;
	private BufferedReader in;

	public Client() throws UnknownHostException {
		host = InetAddress.getLocalHost();
		port = 2000;
		Initiate();
	}

	public Client(InetAddress host) {
		this.host = host;
		port = 2000;
		Initiate();
	}

	public Client(InetAddress host, int port) {
		this.host = host;
		this.port = port;
		Initiate();
	}

	private void Initiate() {

		// createGUI
		// create 2 threads, add printWriter to GUI.
		// put the whole NoName in this method?
		// run and listen to threads
		try {
			socket = new Socket(host, port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		startIO();
		

		run();
	}
	
	@Override
	public void run() {
		clientGUIthread GUI = new clientGUIthread (out);
		ServerConnect connect = new ServerConnect (in);
		String str;
		while (true) {
			str = connect.getText();
			if (str.length()>0){
				GUI.displayInput(str);
			}
		}
	}

	private void startIO() {
		try {
			in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			out = new PrintWriter(new OutputStreamWriter(
					socket.getOutputStream(), "ISO-8859-1"), true);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
