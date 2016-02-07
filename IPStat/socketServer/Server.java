import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server implements Runnable {
	private ServerSocket serverSocket;
	private ArrayList<ClientHandler> connections;
	private ServerGUI GUI;

	public Server(ServerSocket serverSocket, ServerGUI GUI) {
		this.serverSocket = serverSocket;
		this.GUI = GUI;
	}

	public synchronized void removeClient(ClientHandler client) {
		String str = client.getHostName() + " was disconnected.";
		connections.remove(client);
		GUI.updateWindowTitle("Clients connected: " + connections.size());
		sendAll(str);
	}

	public void who(ClientHandler client) {
		GUI.display(client.getHostName() + "requested to know connections");
		// TODO send all connected clients
		client.send("Connected clients are: ");
	}

	public synchronized void broadcast(String host, String inputLine) {
		sendAll(host + ": " + inputLine);
	}

	private void sendAll(String s) {
		for (ClientHandler client : connections) {
			client.send(s);
		}
	}

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
				client.run();
			} catch (IOException e) {
				System.out.println("Couldn't receive new connection.");
			}
		}
	}

}
