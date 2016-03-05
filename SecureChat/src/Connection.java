import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Connection implements Runnable {
	Thread t = new Thread(this);
	private boolean alive = true;
	private Socket socket;
	private PrintWriter out;
	private BufferedReader in;

	/**
	 * Takes in an InetAddres and a port, tries to create socket, inputstream
	 * and outputstream. Also starts running the thread.
	 */
	public Connection(InetAddress host, int port) {

		try {
			socket = new Socket(host, port);
			in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			out = new PrintWriter(new OutputStreamWriter(
					socket.getOutputStream()), true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		t.start();
	}

	@Override
	public void run() {
		while (alive) {
		}
	}

	public void kill() {
		alive = false;
	}

	/** Method for sending text to server via socket. */
	public void send(String text) {
		out.println(text);
	}

	/** Method for retrieving input from socket. */
	public String getText() throws IOException {
		return in.readLine();
	}

}
