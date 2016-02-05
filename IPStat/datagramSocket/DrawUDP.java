import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class DrawUDP {
	public static void main(String[] args) {
		// if (args.length < 3) {
		// System.out.println("Not enough arguments, please try again.");
		// System.exit(0);
		// }
		args = new String[3];
		args[0] = "2000"; // testing purpose
		args[1] = "localhost"; // testing purpose
		args[2] = "2001"; // testing purpose
		System.out.println(args[2]);
		int myPort = Integer.parseInt(args[0]);
		int hostPort = Integer.parseInt(args[2]);
		try {
			InetAddress host = InetAddress.getByName(args[1]);
			try (DatagramSocket socket = new DatagramSocket(myPort)) {
				socket.setSoTimeout(10000);
				ConnectionHandler connection = new ConnectionHandler(socket,
						host, hostPort);
				connection.run();
			} catch (SocketException e) {
				System.out.println("Couldn't create socket, please try again.");
				System.exit(0);
			}

		} catch (UnknownHostException e) {
			System.out.println("Can't resolve host, please try again.");
			System.exit(0);
		}

	}
}
