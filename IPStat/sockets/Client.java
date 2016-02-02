import java.net.InetAddress;
import java.net.UnknownHostException;

public class Client {

	public static void main(String[] args) {
		//host = args[0]
		//port = args[1]
		InetAddress host = null;
		int port;
		ClientRunner client;

		if (args.length==0) {
			try {
				host = InetAddress.getLocalHost();
			} catch (UnknownHostException e) {
				System.out.println("Unknown host, please try again.");
				System.exit(0);
			}
		} else {
			try {
				host = InetAddress.getByName(args[0]);
			} catch (UnknownHostException e) {
				System.out.println("Unknown host, please try again");
				System.exit(0);
			}
		}
		
		if ( args.length <2){
			port = 2000;
		} else {
			port = Integer.parseInt(args[1]);
		}
		client = new ClientRunner (host, port);
	}

}