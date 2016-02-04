import java.awt.Point;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ConnectionHandler implements Runnable{
	private Draw draw;
	private DatagramSocket socket;
	private InetAddress host;
	private int hostPort;

	public ConnectionHandler(DatagramSocket socket, InetAddress host,
			int hostPort) {
		draw = new Draw(this);
		this.socket = socket;
		this.host = host;
		this.hostPort = hostPort;
	}

	@Override
	public void run() {
		// TODO
		//create sockets?
		//loop receivePoint()
	}
	
	private void receivePoint(){
		// TODO Look for point in UDP socket, then call draw.addPoint(p);
	}
	

	public void sendPoint(Point p) {
		// TODO send point to UDP socket
	}

}
