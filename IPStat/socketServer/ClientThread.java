import java.io.*;
import java.net.*;

public class ClientThread implements Runnable {
	private Socket clientSocket;
	private BufferedReader in;
	private PrintWriter out;

	/**Creates the clientThread with a socket as input. Starts running the thread.*/
	public ClientThread(Socket socket) {
		clientSocket = socket;
		startIO();
		run();
	}

	@Override
	public void run() {
		while (true){
			
		}
	}

	/** Method for setting up PrintWriter and BufferedReader */
	private void startIO() {
		try {
			in = new BufferedReader(new InputStreamReader(
					clientSocket.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			out = new PrintWriter(clientSocket.getOutputStream(), true);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
