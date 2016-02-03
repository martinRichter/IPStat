import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class ClientHandler implements Runnable {
	private int port;

	public ClientHandler() {
	}

	private void example() {
		ServerSocket server = null;

		try {
			server = new ServerSocket(port);
			while (true) {
				Socket connection = null;
				try {
					connection = server.accept();
					Writer out = new OutputStreamWriter(
							connection.getOutputStream());
				} catch (IOException e) {
				} finally {
					try {
						if (connection != null)
							connection.close();
					} catch (IOException e2) {
					}
				}
			}
		} catch (IOException e3) {
			e3.printStackTrace();
		} finally {
			try {
				if (server != null)
					server.close();
			} catch (IOException e4) {
			}
		}
	}

	// if message from client: call method to broadcast.

	public void run() {
		// String msg;
		// try {
		// while ((msg = reader.readLine()) != null) {
		// //... gör något med msg ...
		// }
		// writer.close();
		// reader.close();
		// socket.close();
		// } catch (IOException e) {
		// }
		// server.killThread(this);
	}
}
