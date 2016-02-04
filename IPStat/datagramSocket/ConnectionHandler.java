import java.awt.Point;
import java.net.DatagramPacket;
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
		 //DatagramPacket request = new DatagramPacket(new byte[1], 1, host , PORT);
		 //DatagramPacket response = new DatagramPacket(new byte[1024], 1024);
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
