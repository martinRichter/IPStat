import java.net.*;


public class Handler {
	private clientGUI GUI;
	private Connection conn;
	
	public Handler(InetAddress host, int port){
		GUI = new clientGUI(this);
		conn = new Connection(this);
	}
}
