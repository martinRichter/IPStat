import java.io.BufferedReader;
import java.io.IOException;

public class ServerConnect implements Runnable {
	Thread t = new Thread(this);
	private boolean alive = true;
	private BufferedReader in;

	public ServerConnect(BufferedReader in) {
		this.in = in;

		t.start();
	}
	
	public String getText(){
		try {
			return in.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}

	@Override
	public void run() {
		while (alive) {
		}
	}

	public void kill() {
		alive = false;
	}

}
