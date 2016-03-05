import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

class ClientHandler implements Runnable {

	Thread t = new Thread(this);
	private Server server;
	private Socket socket;
	private String host;
	private PrintWriter out;

	public ClientHandler(Socket s, Server server) {
		this.socket = s;
		this.server = server;
		host = socket.getInetAddress().getHostName();
		t.start();
	}

	/**
	 * First creates bufferedReader and printWriter. While running it checks for
	 * input and broadcasts it unless the input is wwhhoo, then calls
	 * who-method.
	 */
	public void run() {
		BufferedReader in = null;
		try {
			out = new PrintWriter(socket.getOutputStream(), true);
		} catch (IOException e1) {
			System.out.println("FAILED TO CREATE OUTPUTSTREAM.");
		}
		try {
			in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
		} catch (IOException e) {
			System.out.println("FAILED: GET INPUTSTREAM FROM CLIENT SOCKET");
			server.removeClient(this);
			return;
		}

		String inputLine;
		try {
			while ((inputLine = in.readLine()) != null) {
				if (inputLine.equals("wwhhoo"))
					server.who(this);
				else
					server.broadcast(host, inputLine);
			}
			in.close();
			socket.close();
			System.out.println("CLIENT DISCONNECTED NICELY");
		} catch (IOException e) {
			System.out.println("CLIENT DISCONNECTED BRUTALLY");
		}
		server.removeClient(this);
	}

	public String getHostName() {
		return host;
	}

	public void send(String s) {
		out.println(s);
	}
}
