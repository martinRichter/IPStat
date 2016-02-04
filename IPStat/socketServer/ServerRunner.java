import java.io.IOException;
import java.net.ServerSocket;

public class ServerRunner {

	public static void main(String[] args) {
		int port;
		if (args.length == 0) {
			port = 2000;
		} else {
			port = Integer.parseInt(args[1]);
		}

		try {
			ServerManager manager = new ServerManager(new ServerSocket(port),
					new ServerGUI());
		} catch (IOException e) {
			System.out.println("Can't create server, please restart.");
			System.exit(0);
		}
	}

}
