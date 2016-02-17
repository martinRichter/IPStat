import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Connection implements Runnable {
	Thread t = new Thread(this);
	private boolean alive = true;
	private Socket socket;
	private PrintWriter out;
	private BufferedReader in;

	// private ObjectInputStream in;
	// private ObjectOutputStream out;

	/**
	 * Takes in an InetAddres and a port, tries to create socket, inputstream
	 * and outputstream
	 */
	public Connection(InetAddress host, int port) {

		try {
			socket = new Socket(host, port);
			in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			out = new PrintWriter(new OutputStreamWriter(
					socket.getOutputStream(), "ISO-8859-1"), true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		t.start();
	}

	// out = new ObjectOutputStream(socket.getOutputStream());
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// try {
	// System.out.println("Trying to create input");
	// in = new ObjectInputStream(socket.getInputStream());
	// System.out.println("Input created.");
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// /**Takes in an object and sends to socket.*/
	// public void send(Object obj){
	// try {
	// out.writeObject(obj);
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }
	//
	// /**Reads object from socket.*/
	// public Object getObject(){
	// try {
	// return in.readObject();
	// } catch (ClassNotFoundException | IOException e) {
	// e.printStackTrace();
	// return null;
	// }
	// }

	@Override
	public void run() {
		while (alive) {
		}
	}

	public void kill() {
		alive = false;
	}

	public void send(String text) {
		out.println(text);
	}

	public String getText() throws IOException {
		return in.readLine();
	}

}
