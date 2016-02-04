import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

public class ServerManager implements Runnable {
	private ServerSocket server;
	private ServerGUI GUI;
	private ArrayList<ClientHandler> connections = new ArrayList<ClientHandler>();

	public ServerManager(ServerSocket server, ServerGUI GUI) {
		this.server = server;
		try {
			server.setSoTimeout(10);
		} catch (SocketException e) {
			System.out.println("Failure setting server accept timeout");
		}
		this.GUI = GUI;
		run();
	}

	public void run() {
		while (true) {
			listenForConnection();
			listenForMessage();
			GUI.updateWindowTitle("Clients connected: " + connections.size());

		}
	}

	private synchronized void listenForMessage() {
		for (int i = 0; i < connections.size(); i++) {
			ClientHandler client = connections.get(i);
			try {
				String str = client.getText();
				if (!str.isEmpty()) {
					displayText(client.getHost() + ": " + str);
				} else {
				}
			} catch (IOException e) {
				displayText(client.getHost() + " disconnected.");
				connections.remove(i);
			}
		}
	}

	private synchronized void listenForConnection() {
		Socket socket;
		ClientHandler client = null;
		try {
			socket = server.accept();
			try {
				client = new ClientHandler(socket);
			} catch (IOException e) {
				GUI.display("Unable for client " + client.getHost()
						+ " to create streams");
			}
		} catch (IOException e) {
			// no client connected this second
		}
		if (client != null) {
			displayText(client.getHost() + " connected");
			connections.add(client);
			client.run();
		}

	}

	/**
	 * Method to send text to all connected clients as well as displaying in GUI
	 */
	private void displayText(String s) {
		for (int i = 0; i < connections.size(); i++) {
			connections.get(i).sendText(s);
		}
		GUI.display(s);
	}

}
