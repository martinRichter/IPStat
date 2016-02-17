import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Connection implements Runnable {
	Thread t = new Thread(this);
	private boolean alive = true;
	private Socket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;

	/**
	 * Takes in an InetAddres and a port, tries to create socket, inputstream
	 * and outputstream
	 */
	public Connection(InetAddress host, int port) {
		try {
			socket = new Socket(host, port);
			in = new ObjectInputStream(socket.getInputStream());
			out = new ObjectOutputStream(socket.getOutputStream());
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

}
