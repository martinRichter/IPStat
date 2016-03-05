import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

public class Server implements Runnable {
	private ServerSocket serverSocket;
	private ArrayList<ClientHandler> connections;
	private ServerGUI GUI;

	public Server(ServerSocket serverSocket, ServerGUI GUI) {
		this.serverSocket = serverSocket;
		this.GUI = GUI;
	}

	/**
	 * Renoves client from ArrayList and alerts all connected clients that
	 * client was disconnected.
	 */
	public synchronized void removeClient(ClientHandler client) {
		if (connections.remove(client)) {
			String str = client.getHostName() + " was disconnected.";
			GUI.updateWindowTitle("Clients connected: " + connections.size());
			sendAll(str);
		}
	}

	/** Method for telling a connected client about all connected clients. */
	public synchronized void who(ClientHandler client) {
		GUI.display(client.getHostName() + "requested to know connections");
		String str = "";
		for (ClientHandler cli : connections) {
			str = str + cli.getHostName() + " ";
		}
		client.send("Connected clients are: " + str);
	}

	/**
	 * Method for broadcasting a message to all clients showing the origin.
	 */
	public synchronized void broadcast(String host, String inputLine) {
		sendAll(host + ": " + inputLine);
	}

	/** Method for sending a string to all connected clients. */
	private synchronized void sendAll(String s) {
		GUI.display(s);
		for (ClientHandler client : connections) {
			client.send(s);
		}
	}

	/**
	 * While thread is running it checks for new connections and adds them to
	 * the arrayList of ClientHandlers.
	 */
	@Override
	public void run() {
		Socket socket;
		ClientHandler client;
		connections = new ArrayList<ClientHandler>();
		while (true) {
			GUI.updateWindowTitle("Clients connected: " + connections.size());
			try {
				socket = serverSocket.accept();
				client = new ClientHandler(socket, this);
				connections.add(client);
			} catch (IOException e) {
				// Didn't receive a new connection
			}
		}
	}

}
