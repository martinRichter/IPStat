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
		System.out.println("running conHandler");
		while (true){
			receivePoint();
		}
	}

	private void receivePoint() {
		byte[] receiveData = new byte[1024];
		DatagramPacket receivePacket = new DatagramPacket(receiveData,
				receiveData.length);
		try {
			socket.receive(receivePacket);
			String str = new String(receiveData);
			String[] xy = str.split(" ");
			String xstr = xy[0];
			String ystr = xy[1];
			System.out.println(ystr +"y ends here");
			int x = Integer.parseInt(xstr);
//			int y = Integer.parseInt(ystr);
		} catch (IOException e) {
			System.out.println("Couldn't receive packet");
		}
	}

	public void sendPoint(Point p) {
		byte[] data = new byte[1024];
		String message = Integer.toString(p.x) + " " + Integer.toString(p.y);
		data = message.getBytes();
		DatagramPacket sendData = new DatagramPacket(data, data.length, host, hostPort);
		try {
			socket.send(sendData);
		} catch (IOException e) {
			System.out.println("Couldn't send packet");
		}
	}

}
