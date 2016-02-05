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
//		String[] xy = message.split(" ");
//		Point p = new Point(Integer.parseInt(xy[0]), Integer.parseInt(xy[1])); 
//		draw.addPoint(p);
	}
	

	public void sendPoint(Point p) {
		String message = Integer.toString(p.x) + " " + Integer.toString(p.y);
		// TODO send point to UDP socket
	}

}
