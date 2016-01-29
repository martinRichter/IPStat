import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;


public class Client {
	private InetAddress host;
	private int port;
	private Handler handler;

	public Client() throws UnknownHostException {
		host = InetAddress.getLocalHost();
		port = 2000;
		Initiate();
	}

	public Client(InetAddress host) {
		this.host = host;
		port = 2000;
		Initiate();
	}

	public Client(InetAddress host, int port) {
		this.host = host;
		this.port = port;
		Initiate();
	}
	
	private void Initiate(){
		handler = new Handler(host, port);
	}
	

}
