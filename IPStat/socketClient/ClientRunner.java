import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.Socket;

public class ClientRunner implements Runnable {
	Thread t = new Thread(this);
	private InetAddress host;
	private int port;
	private Socket socket;
	private PrintWriter out;
	private BufferedReader in;
	private ClientGUI GUI;
	private ServerConnect connect;

	public ClientRunner(InetAddress host, int port) {
		this.host = host;
		this.port = port;
		Initiate();
		t.start();
	}

	/** Starts the socket connection, if fails, stops the program */
	private void Initiate() {
		try {
			socket = new Socket(host, port);
		} catch (IOException e) {
			closeOnError("Couldn't create socket.");
		}
		startIO();
	}

	@Override
	public void run() {
		GUI = new ClientGUI(out);
		GUI.setWindowTitle(host.getHostName(), Integer.toString(port));
		connect = new ServerConnect(in);
		while (true) {
			try {
				GUI.displayInput(connect.getText());
			} catch (IOException e) {
				closeOnError("Connection to server lost.");
			}
		}

	}

	/** Method for displaying error message and then close program */
	private void closeOnError(String s) {
		System.out.println(s + "\n"
				+ "Closing connection and software, please restart.");
		try {
			if (socket != null)
				socket.close();
		} catch (IOException e1) {
			System.out.println("Couldn't close socket.");
		}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.exit(0);
	}

	/**
	 * Method for creating the BufferedReader and the PrintWriter.
	 */
	private void startIO() {
		try {
			in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
		} catch (IOException e) {
			closeOnError("Couldn't create InputReader, IOException.");
		}

		try {
			out = new PrintWriter(new OutputStreamWriter(
					socket.getOutputStream(), "ISO-8859-1"), true);
		} catch (UnsupportedEncodingException e) {
			closeOnError("Encoding for PrintWriter not supported.");
		} catch (IOException e1) {
			closeOnError("Couldn't create OutPutStream, IOException.");
		}
	}
}
