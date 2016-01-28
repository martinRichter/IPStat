import java.net.InetAddress;
import java.net.UnknownHostException;

public class Client {
	private InetAddress host;
	private int port;

	public Client() throws UnknownHostException {
		host = InetAddress.getLocalHost();
		port = 2000;
	}

	public Client(InetAddress host) throws UnknownHostException {
		this.host = host;
		port = 2000;
	}

	public Client(InetAddress host, int port) throws UnknownHostException{
		this.host = host;
		this.port = port;
	}

}
