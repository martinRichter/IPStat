import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;

public class Noname {
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	private clientGUI GUI;

	public Noname(Socket socket) {
		this.socket = socket;
		startIO();
	}

	
	private void startIO() {
		try {
			in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			out = new PrintWriter(new OutputStreamWriter(
					socket.getOutputStream(), "ISO-8859-1"), true);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
