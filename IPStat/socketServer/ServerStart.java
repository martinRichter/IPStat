import java.io.IOException;
import java.net.ServerSocket;

public class ServerStart {

	public static void main(String[] args) {
		int port;
		if (args.length == 0) {
			port = 2000;
		} else {
			port = Integer.parseInt(args[1]);
		}
		
		try {
			Server server = new Server(new ServerSocket(port), new ServerGUI());
			server.run();
		} catch (IOException e) {
			System.out.println("Can't create server, please restart.");
			System.exit(0);
		}
	}

}
