import java.io.*;
import java.net.Socket;
import java.net.SocketException;

public class ClientHandler implements Runnable {
	private String host;
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	private boolean alive;

	public ClientHandler(Socket socket) throws IOException {
		this.socket = socket;
		host = socket.getInetAddress().getHostName();
		setupIO();
		alive = true;
	}

	/** Method for retrieving in put from client */
	public String getText() throws IOException {
		try {
			if (in.ready()) { // checks whether buffer is empty
				return in.readLine();
			}
		} catch (IOException e) {
			kill();
			throw e;
		}
		return "";
	}

	public String getHost() {
		return host;
	}

	public void sendText(String s) {
		out.println(s);
	}

	@Override
	public void run() {
	}

	/** Stops the thread from running and tries to close streams and socket. */
	private void kill() {
		alive = false;
		out.close();
		try {
			in.close();
		} catch (IOException e) {
		}
		try {
			socket.close();
		} catch (IOException e) {
		}
	}

	/** Method for setting up PrintWriter and BufferedReader */
	private void setupIO() throws IOException {
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(socket.getOutputStream(), true);
	}
}
