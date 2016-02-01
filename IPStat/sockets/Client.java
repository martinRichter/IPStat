import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client implements Runnable {
	private InetAddress host;
	private int port;
	private Socket socket;
	private PrintWriter out;
	private BufferedReader in;
	private ClientGUIthread GUI;
	private ServerConnect connect;

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
		try {
			socket = new Socket(host, port);
		} catch (IOException e) {
			closeOnError("Couldn't create socket.");
		}
		startIO();

		run();
	}

	@Override
	public void run() {
		GUI = new ClientGUIthread(out);
		connect = new ServerConnect(in);
		String str;
		while (true) {
			try {
				str = connect.getText();
				if (str.length() > 0) {
					GUI.displayInput(str);
				}
			} catch (IOException e) {
				closeOnError("Connection to server lost.");
			}
		}

	}

	/** Method for displaying error message and then close program */
	private void closeOnError(String s ) {
		System.out.println(s + "\n" + "Closing connection and software, please restart");
		try {
			if(socket!=null)socket.close();
		} catch (IOException e1) {
			System.out.println("Couldn't close socket");
		}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.exit(0);
	}

	private void startIO() {
		try {
			in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
		} catch (IOException e) {
			closeOnError("Couldn't create InputReader, IOException");
		}

		try {
			out = new PrintWriter(new OutputStreamWriter(
					socket.getOutputStream(), "ISO-8859-1"), true);
		} catch (UnsupportedEncodingException e) {
			closeOnError("Encoding for PrintWriter not supported");
		} catch (IOException e1) {
			closeOnError("Couldn't create OutPutStream, IOException");
		}
	}
}
