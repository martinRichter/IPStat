import java.awt.Point;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ConnectionHandler implements Runnable {
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
		byte[] receiveData = new byte[32];
		DatagramPacket receivePacket = new DatagramPacket(receiveData,
				receiveData.length);
		String[] xy;
		while (true) {
			try {
				socket.receive(receivePacket);
				String str = new String(receiveData);
				xy = str.split(" ");
				// Trim y value as whitespaces are added after the y value.
				draw.addPoint(new Point(Integer.parseInt(xy[0]), Integer
						.parseInt(xy[1].trim())));
			} catch (IOException e) {
			}
		}
	}

	public void sendPoint(Point p) {
		byte[] data = new byte[32];
		String message = Integer.toString(p.x) + " " + Integer.toString(p.y);
		data = message.getBytes();
		DatagramPacket sendData = new DatagramPacket(data, data.length, host,
				hostPort);
		try {
			socket.send(sendData);
		} catch (IOException e) {
			System.out.println("Couldn't send packet");
		}
	}

}
